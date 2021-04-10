package com.kovalenko.diploma.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@ApiModel(description = "User entity")
public class UserDto {
    @ApiModelProperty("User`s id")
    private Long id;

    @ApiModelProperty("User`s avatar id")
    private Long avatarId;

    @ApiModelProperty("User`s fullName")
    @NotNull
    @NotEmpty
    private String fullName;

    @ApiModelProperty("User`s profession")
    private String profession;

    @ApiModelProperty("User`s email")
    private String email;


    @ApiModelProperty("User`s cost per hour")
    private Double rate;

    public UserDto() {

    }

    public UserDto(Long id, Long avatarId, @NotNull @NotEmpty String fullName, String profession, String email,
                   Double rate) {
        this.id = id;
        this.avatarId = avatarId;
        this.fullName = fullName;
        this.profession = profession;
        this.email = email;
        this.rate = rate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;

        return Objects.equals(id, userDto.id) &&
                Objects.equals(fullName, userDto.fullName) &&
                Objects.equals(rate, userDto.rate) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(avatarId, userDto.avatarId) &&
                Objects.equals(profession, userDto.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, rate, email, avatarId, profession);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", rate=" + rate +
                ", email=" + email +
                ", avatarId=" + avatarId +
                ", profession='" + profession + '\'' +
                '}';
    }
}