package com.miqueias.r.api_rest_spring_boot.vo.v1;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

//@JsonPropertyOrder({"userID", "userName", "userNickname", "userEmail", "userCPF", "userStreet",
//"userState", "userZipCode", "userComplement"})
public class UserVO extends RepresentationModel<UserVO> implements Serializable {

    private static final long SerialVersionUID = 1L;
//    @JsonProperty("userID")
//    @JsonIgnore
    private Long userID;
    private String userName;
    private String userNickname;
    private String userEmail;
    private String userCPF;
    private String userStreet;
    private String userState;
    private String userZipCode;
    private String userComplement;

    public UserVO() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCPF() {
        return userCPF;
    }

    public void setUserCPF(String userCPF) {
        this.userCPF = userCPF;
    }

    public String getUserStreet() {
        return userStreet;
    }

    public void setUserStreet(String userStreet) {
        this.userStreet = userStreet;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserZipCode() {
        return userZipCode;
    }

    public void setUserZipCode(String userZipCode) {
        this.userZipCode = userZipCode;
    }

    public String getUserComplement() {
        return userComplement;
    }

    public void setUserComplement(String userComplement) {
        this.userComplement = userComplement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVO userVO = (UserVO) o;
        return Objects.equals(userID, userVO.userID) && Objects.equals(userName, userVO.userName) && Objects.equals(userNickname, userVO.userNickname) && Objects.equals(userEmail, userVO.userEmail) && Objects.equals(userCPF, userVO.userCPF) && Objects.equals(userStreet, userVO.userStreet) && Objects.equals(userState, userVO.userState) && Objects.equals(userZipCode, userVO.userZipCode) && Objects.equals(userComplement, userVO.userComplement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userName, userNickname, userEmail, userCPF, userStreet, userState, userZipCode, userComplement);
    }
}