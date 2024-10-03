package com.miqueias.r.api_rest_spring_boot.mapper.v1;

import com.miqueias.r.api_rest_spring_boot.model.User;
import com.miqueias.r.api_rest_spring_boot.vo.v1.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "userID")
    @Mapping(source = "name", target = "userName")
    @Mapping(source = "nickname", target = "userNickname")
    @Mapping(source = "email", target = "userEmail")
    @Mapping(source = "cpf", target = "userCPF")
    @Mapping(source = "street", target = "userStreet")
    @Mapping(source = "state", target = "userState")
    @Mapping(source = "zipCode", target = "userZipCode")
    @Mapping(source = "complement", target = "userComplement")
    UserVO toUserVO(User user);

    @Mapping(source = "userID", target = "id")
    @Mapping(source = "userName", target = "name")
    @Mapping(source = "userNickname", target = "nickname")
    @Mapping(source = "userEmail", target = "email")
    @Mapping(source = "userCPF", target = "cpf")
    @Mapping(source = "userStreet", target = "street")
    @Mapping(source = "userState", target = "state")
    @Mapping(source = "userZipCode", target = "zipCode")
    @Mapping(source = "userComplement", target = "complement")
    User toUser(UserVO userVO);

    List<UserVO> toUsersVO(List<User> users);
}
