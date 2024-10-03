package com.miqueias.r.api_rest_spring_boot.service.v1;

import com.miqueias.r.api_rest_spring_boot.controller.v1.UserController;
import com.miqueias.r.api_rest_spring_boot.exception.CpfAlreadyInUseException;
import com.miqueias.r.api_rest_spring_boot.exception.CpfUpdateNotAllowedException;
import com.miqueias.r.api_rest_spring_boot.exception.EmailAlreadyInUseException;
import com.miqueias.r.api_rest_spring_boot.exception.ResourceNotFoundException;
import com.miqueias.r.api_rest_spring_boot.mapper.v1.UserMapper;
import com.miqueias.r.api_rest_spring_boot.model.User;
import com.miqueias.r.api_rest_spring_boot.repository.UserRepository;
import com.miqueias.r.api_rest_spring_boot.repository.UserSecurityRepository;
import com.miqueias.r.api_rest_spring_boot.vo.v1.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service("UserServicesV1")
public class UserSecurityServices implements UserDetailsService {
    private final Logger logger = Logger.getLogger(UserSecurityServices.class.getName());

    @Autowired
    private UserSecurityRepository repository;

    public UserSecurityServices(UserSecurityRepository repository) {
        this.repository = repository;
    }

    private final UserMapper userMapper = UserMapper.INSTANCE;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userSecurity = repository.findByUsername(username);
        if(userSecurity != null){
            return userSecurity;
        }else{
            throw new UsernameNotFoundException("Username "+ username + " Not found!");
        }
    }
}
