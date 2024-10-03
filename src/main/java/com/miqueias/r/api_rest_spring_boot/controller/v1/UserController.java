package com.miqueias.r.api_rest_spring_boot.controller.v1;

import com.miqueias.r.api_rest_spring_boot.config.endpoint.v1.UserEndpointConfig;
import com.miqueias.r.api_rest_spring_boot.service.v1.UserServices;
import com.miqueias.r.api_rest_spring_boot.utils.Response;
import com.miqueias.r.api_rest_spring_boot.vo.v1.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.miqueias.r.api_rest_spring_boot.utils.MediaType;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@Controller("userControllerV1")
@Tag(name = "User", description = "Endpoints for managing user")
public class UserController {

    @Autowired
    private UserServices service;

    @Autowired
    private UserEndpointConfig endpointConfig;

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML
            })

    @Operation(
            summary = "Finds all users",
            description = "Finds all users",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200"),

                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400", content = @Content),

                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401", content = @Content),

                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403", content = @Content),

                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500", content = @Content),
            })

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

    @GetMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})

    @Operation(
            summary = "Finds a user",
            description = "Finds a user",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200"),

                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400", content = @Content),

                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401", content = @Content),

                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403", content = @Content),

                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404", content = @Content),

                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500", content = @Content),
            })

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

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})

    @Operation(
            summary = "Adds a new user",
            description = "Adds a new user",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Create",
                            responseCode = "202"),

                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400", content = @Content),

                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401", content = @Content),

                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403", content = @Content),

                    @ApiResponse(
                            description = "Conflict",
                            responseCode = "409", content = @Content),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500", content = @Content),
            })

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

    @PutMapping(
            consumes = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})

    @Operation(
            summary = "Update a user",
            description = "Updates a user",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200"
                    ),

                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400", content = @Content),

                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401", content = @Content),

                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403", content = @Content),

                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404", content = @Content),


                    @ApiResponse(
                            description = "Conflict",
                            responseCode = "409", content = @Content),

                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500", content = @Content),
            })


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

    @DeleteMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})

    @Operation(
            summary = "Deteles a user",
            description = "Deteles a user",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "No content",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400", content = @Content),

                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401", content = @Content),

                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403", content = @Content),

                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404", content = @Content),


                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500", content = @Content),
            })

    public ResponseEntity<Response<Void>> delete(@PathVariable(value = "id") Long id) {
        endpointConfig.checkEndpointAccess();
        service.delete(id);
        Response<Void> response = new Response<>(
                "success",
                "User deleted successfully",
                null,
                HttpStatus.NO_CONTENT.value(),
                endpointConfig.getDetailMessage(),
                new Date()
        );
        return ResponseEntity.ok(response);
    }
}
