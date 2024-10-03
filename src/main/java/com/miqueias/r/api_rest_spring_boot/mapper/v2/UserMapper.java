package com.miqueias.r.api_rest_spring_boot.mapper.v2;

import com.miqueias.r.api_rest_spring_boot.model.User;
import com.miqueias.r.api_rest_spring_boot.vo.v2.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "identificador")
    @Mapping(source = "name", target = "nome")
    @Mapping(source = "nickname", target = "apelido")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "street", target = "rua")
    @Mapping(source = "state", target = "estado")
    @Mapping(source = "neighborhood", target = "bairro")
    @Mapping(source = "zipCode", target = "cep")
    @Mapping(source = "complement", target = "complemento")
    UserVO toUserVO(User user);

    @Mapping(source = "identificador", target = "id")
    @Mapping(source = "nome", target = "name")
    @Mapping(source = "apelido", target = "nickname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "rua", target = "street")
    @Mapping(source = "estado", target = "state")
    @Mapping(source = "bairro", target = "neighborhood")
    @Mapping(source = "cep", target = "zipCode")
    @Mapping(source = "complemento", target = "complement")
    User toUser(UserVO userVO);

    List<UserVO> toUsersVO(List<User> users);
}
