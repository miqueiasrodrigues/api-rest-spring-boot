package com.miqueias.r.api_rest_spring_boot.service.v2;

import com.miqueias.r.api_rest_spring_boot.exception.CpfAlreadyInUseException;
import com.miqueias.r.api_rest_spring_boot.exception.CpfUpdateNotAllowedException;
import com.miqueias.r.api_rest_spring_boot.exception.EmailAlreadyInUseException;
import com.miqueias.r.api_rest_spring_boot.exception.ResourceNotFoundException;
import com.miqueias.r.api_rest_spring_boot.mapper.v2.UserMapper;
import com.miqueias.r.api_rest_spring_boot.model.User;
import com.miqueias.r.api_rest_spring_boot.repository.UserRepository;
import com.miqueias.r.api_rest_spring_boot.vo.v2.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service("UserServicesV2")
public class UserServices {
    private final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository repository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public List<UserVO> findAll() {
        logger.info("Buscando lista de usuários");
        List<User> users = repository.findAll();

        return userMapper.toUsersVO(users);
    }

    public UserVO findById(Long id) {
        logger.info("Buscando uma usuário pelo ID");

        User user = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado!")
        );

        return userMapper.toUserVO(user);
    }

    public UserVO create(UserVO userVO) {
        logger.info("Criando usuário");

        Optional<User> existingUserByEmail = repository.findByEmail(userMapper.toUser(userVO).getEmail());

        if (existingUserByEmail.isPresent()) {
            throw new EmailAlreadyInUseException("O Email já está em uso!");
        }

        Optional<User> existingUserByCpf = repository.findByCpf(userMapper.toUser(userVO).getCpf());
        if (existingUserByCpf.isPresent()) {
            throw new CpfAlreadyInUseException("O CPF já está em uso!");
        }

        User createdUser = repository.save(userMapper.toUser(userVO));

        return userMapper.toUserVO(createdUser);
    }

    public UserVO update(UserVO userVO) {
        logger.info("Atualização de usuário");

        User entity = repository.findById(userMapper.toUser(userVO).getId()).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado!")
        );

        if (!entity.getCpf().equals(userMapper.toUser(userVO).getCpf())) {
            throw new CpfUpdateNotAllowedException("Não é possível alterar o CPF!");
        }

        Optional<User> existingUserByEmail = repository.findByEmail(userMapper.toUser(userVO).getEmail());

        if (existingUserByEmail.isPresent() && !existingUserByEmail.get().getId().equals(userMapper.toUser(userVO).getId())) {
            throw new EmailAlreadyInUseException("O Email já está em uso!");
        }

        entity.setName(userMapper.toUser(userVO).getName());
        entity.setNickname(userMapper.toUser(userVO).getNickname());
        entity.setEmail(userMapper.toUser(userVO).getEmail());
        entity.setCpf(userMapper.toUser(userVO).getCpf());
        entity.setStreet(userMapper.toUser(userVO).getStreet());
        entity.setState(userMapper.toUser(userVO).getState());
        entity.setNeighborhood(userMapper.toUser(userVO).getNeighborhood());
        entity.setZipCode(userMapper.toUser(userVO).getZipCode());
        entity.setComplement(userMapper.toUser(userVO).getComplement());

        User updatedUser =  repository.save(entity);

        return userMapper.toUserVO(updatedUser);
    }

    public void delete(Long id) {
        User entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado!")
        );

        repository.delete(entity);
    }
}