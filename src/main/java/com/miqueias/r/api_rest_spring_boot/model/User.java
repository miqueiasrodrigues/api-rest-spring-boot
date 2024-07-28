package com.miqueias.r.api_rest_spring_boot.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @Column(name = "state", nullable = false, length = 75)
    private String state;

    @Column(name = "neighborhood", length = 150)
    private String neighborhood;

    @Column(name = "zip_code", nullable = false, length = 8)
    private String zipCode;

    @Column(name = "complement", length = 150)
    private String complement;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNeighborhood() {
        return neighborhood;
    }
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(nickname, user.nickname) && Objects.equals(email, user.email) && Objects.equals(cpf, user.cpf) && Objects.equals(street, user.street) && Objects.equals(state, user.state) && Objects.equals(neighborhood, user.neighborhood) && Objects.equals(zipCode, user.zipCode) && Objects.equals(complement, user.complement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nickname, email, cpf, street, state, neighborhood, zipCode, complement);
    }
}
