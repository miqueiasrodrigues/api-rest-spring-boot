package com.miqueias.r.api_rest_spring_boot.vo.v2;

import java.io.Serializable;
import java.util.Objects;




public class UserVO implements Serializable {

    private static final long SerialVersionUID = 1L;

    private Long identificador;
    private String nome;
    private String apelido;
    private String email;
    private String cpf;
    private String rua;
    private String bairro;
    private String estado;
    private String cep;
    private String complemento;

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVO userVO = (UserVO) o;
        return Objects.equals(identificador, userVO.identificador) && Objects.equals(nome, userVO.nome) && Objects.equals(apelido, userVO.apelido) && Objects.equals(email, userVO.email) && Objects.equals(cpf, userVO.cpf) && Objects.equals(rua, userVO.rua) && Objects.equals(bairro, userVO.bairro) && Objects.equals(estado, userVO.estado) && Objects.equals(cep, userVO.cep) && Objects.equals(complemento, userVO.complemento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador, nome, apelido, email, cpf, rua, bairro, estado, cep, complemento);
    }
}