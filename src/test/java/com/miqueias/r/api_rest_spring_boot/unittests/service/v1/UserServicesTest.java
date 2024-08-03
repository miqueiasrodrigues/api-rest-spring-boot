package com.miqueias.r.api_rest_spring_boot.unittests.service.v1;

import com.miqueias.r.api_rest_spring_boot.model.User;
import com.miqueias.r.api_rest_spring_boot.repository.UserRepository;
import com.miqueias.r.api_rest_spring_boot.service.v1.UserServices;
import com.miqueias.r.api_rest_spring_boot.unittests.mocks.v1.MockUser;
import com.miqueias.r.api_rest_spring_boot.vo.v1.UserVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    MockUser input;

    @InjectMocks
    private UserServices service;

    @Mock
    UserRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockUser();
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        User entity = input.mockEntity();
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void create() {
        User entity = input.mockEntity();
        entity.setId(1L);

        UserVO vo = input.mockVO();
        vo.setUserID(1L);

        when(repository.findByEmail(entity.getEmail())).thenReturn(Optional.empty());
        when(repository.findByCpf(entity.getCpf())).thenReturn(Optional.empty());
        when(repository.save(any(User.class))).thenReturn(entity);

        var result = service.create(vo);

        verify(repository, times(1)).findByEmail(entity.getEmail()); // Ensure this is the correct email
        verify(repository, times(1)).findByCpf(entity.getCpf());
        verify(repository, times(1)).save(any(User.class)); // Use any(User.class) to match any User object

        assertNotNull(result);
    }


    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}