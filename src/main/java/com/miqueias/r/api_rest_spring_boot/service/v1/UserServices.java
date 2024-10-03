package com.miqueias.r.api_rest_spring_boot.service.v1;

import com.miqueias.r.api_rest_spring_boot.controller.v1.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.miqueias.r.api_rest_spring_boot.exception.CpfAlreadyInUseException;
import com.miqueias.r.api_rest_spring_boot.exception.CpfUpdateNotAllowedException;
import com.miqueias.r.api_rest_spring_boot.exception.EmailAlreadyInUseException;
import com.miqueias.r.api_rest_spring_boot.exception.ResourceNotFoundException;
import com.miqueias.r.api_rest_spring_boot.mapper.v1.UserMapper;
import com.miqueias.r.api_rest_spring_boot.model.User;
import com.miqueias.r.api_rest_spring_boot.repository.UserRepository;
import com.miqueias.r.api_rest_spring_boot.vo.v1.UserVO;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service("UserServicesV1")
public class UserServices {
    private final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    private UserRepository repository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public List<UserVO> findAll() {
        logger.info("Fetching list of users");
        List<User> users = repository.findAll();
        List<UserVO> usersVO = userMapper.toUsersVO(users);
        for (UserVO userVO : usersVO) {
            userVO.add(linkTo(methodOn(UserController.class).findById(userVO.getUserID())).withSelfRel());
        }
        return  usersVO;
    }

    public UserVO findById(Long id) {
        logger.info("Fetching a user by ID");

        User user = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found!")
        );

        UserVO userVO = userMapper.toUserVO(user);
        userVO.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
        return userVO;
    }

    public UserVO create(UserVO userVO) {
        logger.info("Creating user");

        Optional<User> existingUserByEmail = repository.findByEmail(userMapper.toUser(userVO).getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new EmailAlreadyInUseException("Email is already in use!");
        }

        Optional<User> existingUserByCpf = repository.findByCpf(userMapper.toUser(userVO).getCpf());
        if (existingUserByCpf.isPresent()) {
            throw new CpfAlreadyInUseException("CPF is already in use!");
        }

        User createdUser = repository.save(userMapper.toUser(userVO));

        UserVO createdUserVO = userMapper.toUserVO(createdUser);
        userVO.add(linkTo(methodOn(UserController.class).findById(createdUser.getId())).withSelfRel());
        return createdUserVO;
    }

    public UserVO update(UserVO userVO) {
        logger.info("Updating user");

        User entity = repository.findById(userMapper.toUser(userVO).getId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found!")
        );

        if (!entity.getCpf().equals(userMapper.toUser(userVO).getCpf())) {
            throw new CpfUpdateNotAllowedException("CPF cannot be changed!");
        }

        Optional<User> existingUserByEmail = repository.findByEmail(userMapper.toUser(userVO).getEmail());

        if (existingUserByEmail.isPresent() && !existingUserByEmail.get().getId().equals(userMapper.toUser(userVO).getId())) {
            throw new EmailAlreadyInUseException("Email is already in use!");
        }

        entity.setName(userMapper.toUser(userVO).getName());
        entity.setNickname(userMapper.toUser(userVO).getNickname());
        entity.setEmail(userMapper.toUser(userVO).getEmail());
        entity.setCpf(userMapper.toUser(userVO).getCpf());
        entity.setStreet(userMapper.toUser(userVO).getStreet());
        entity.setState(userMapper.toUser(userVO).getState());
        entity.setZipCode(userMapper.toUser(userVO).getZipCode());
        entity.setComplement(userMapper.toUser(userVO).getComplement());

        User updatedUser = repository.save(entity);

        UserVO updatedUserVO = userMapper.toUserVO(updatedUser);
        userVO.add(linkTo(methodOn(UserController.class).findById(updatedUser.getId())).withSelfRel());
        return updatedUserVO;
    }

    public void delete(Long id) {
        User entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found!")
        );

        repository.delete(entity);
    }
}
