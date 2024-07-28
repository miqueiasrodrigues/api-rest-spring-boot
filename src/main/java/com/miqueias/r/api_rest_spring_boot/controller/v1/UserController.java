package com.miqueias.r.api_rest_spring_boot.controller.v1;

import com.projetoSpring.springAPI.config.endpoint.v1.UserEndpointConfig;
import com.projetoSpring.springAPI.data.vo.v1.UserVO;
import com.projetoSpring.springAPI.service.v1.UserServices;
import com.projetoSpring.springAPI.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@Controller("userControllerV1")
public class UserController {

    @Autowired
    private UserServices service;

    @Autowired
    private UserEndpointConfig endpointConfig;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<UserVO>>> findAll() {
        endpointConfig.checkEndpointAccess();
        List<UserVO> users = service.findAll();
        Response<List<UserVO>> response = new Response<>(
                "success",
                "Users retrieved successfully",
                users,
                HttpStatus.OK.value(),
                endpointConfig.getDetailMessage(),
                new Date()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserVO>> findById(@PathVariable(value = "id") Long id) {
        endpointConfig.checkEndpointAccess();
        UserVO user = service.findById(id);
        Response<UserVO> response = new Response<>(
                "success",
                "User retrieved successfully",
                user,
                HttpStatus.OK.value(),
                endpointConfig.getDetailMessage(),
                new Date()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserVO>> create(@RequestBody UserVO userVO) {
        endpointConfig.checkEndpointAccess();
        UserVO createdUser = service.create(userVO);
        Response<UserVO> response = new Response<>(
                "success",
                "User created successfully",
                createdUser,
                HttpStatus.CREATED.value(),
                endpointConfig.getDetailMessage(),
                new Date()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserVO>> update(@RequestBody UserVO userVO) {
        endpointConfig.checkEndpointAccess();
        UserVO updatedUser = service.update(userVO);
        Response<UserVO> response = new Response<>(
                "success",
                "User updated successfully",
                updatedUser,
                HttpStatus.OK.value(),
                endpointConfig.getDetailMessage(),
                new Date()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable(value = "id") Long id) {
        endpointConfig.checkEndpointAccess();
        service.delete(id);
        Response<Void> response = new Response<>(
                "success",
                "User deleted successfully",
                null,
                HttpStatus.OK.value(),
                endpointConfig.getDetailMessage(),
                new Date()
        );
        return ResponseEntity.ok(response);
    }
}
