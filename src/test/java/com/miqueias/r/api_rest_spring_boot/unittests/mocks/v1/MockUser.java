package com.miqueias.r.api_rest_spring_boot.unittests.mocks.v1;

import com.miqueias.r.api_rest_spring_boot.model.User;
import com.miqueias.r.api_rest_spring_boot.vo.v1.UserVO;

import java.util.ArrayList;
import java.util.List;

public class MockUser {
    public User mockEntity() {
        return mockEntity(0);
    }

    public UserVO mockVO() {
        return mockVO(0);
    }

    public List<User> mockEntityList() {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 14; i++) {
            User user = mockEntity(i);
            user.setId(1L);
            users.add(user);
        }
        return users;
    }

    public List<UserVO> mockVOList() {
        List<UserVO> usersVO = new ArrayList<UserVO>();
        for (int i = 0; i < 14; i++) {
            UserVO userVO = mockVO(i);
            userVO.setUserID(1L);
            usersVO.add(userVO);
        }
        return usersVO;
    }

    public User mockEntity(Integer number) {
        User user = new User();
        user.setName("Name" + number);
        user.setNickname("Nickname" + number);
        user.setEmail("email" + number + "@example.com");
        user.setCpf("123456789" + number);
        user.setStreet("Street " + number);
        user.setState("State " + number);
        user.setNeighborhood("Neighborhood " + number);
        user.setZipCode("12345-678");
        user.setComplement("Complement " + number);
        return user;
    }

    public UserVO mockVO(Integer number) {
        UserVO userVO = new UserVO();
        userVO.setUserName("Name" + number);
        userVO.setUserNickname("Nickname" + number);
        userVO.setUserEmail("email" + number + "@example.com");
        userVO.setUserCPF("123456789" + number);
        userVO.setUserStreet("Street " + number);
        userVO.setUserState("State " + number);
        userVO.setUserZipCode("12345-678");
        userVO.setUserComplement("Complement " + number);
        return userVO;
    }

}
