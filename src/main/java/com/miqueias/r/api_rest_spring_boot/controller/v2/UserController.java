package com.miqueias.r.api_rest_spring_boot.controller.v2;


import com.miqueias.r.api_rest_spring_boot.config.endpoint.v2.UserEndpointConfig;
import com.miqueias.r.api_rest_spring_boot.service.v2.UserServices;
import com.miqueias.r.api_rest_spring_boot.utils.Response;
import com.miqueias.r.api_rest_spring_boot.vo.v2.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v2/user")
@Controller("userControllerV2")
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
                "successo",
                "Usuários recuperados com sucesso",
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
                "successo",
                "Usuário recuperado com sucesso",
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
                "successo",
                "Usuário criado com sucesso",
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
                "successo",
                "Usuário atualizado com sucesso",
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
                "successo",
                "Usuário deletado com sucesso",
                null,
                HttpStatus.NO_CONTENT.value(),
                endpointConfig.getDetailMessage(),
                new Date()
        );
        return ResponseEntity.ok(response);
    }
}
