package com.kovalenko.diploma.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "AVATAR_ID")
    private Image avatar;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "REGISTRATION_DATE")
    private LocalDateTime registrationDate;

    @Column(name = "LAST_VISIT_DATE")
    private LocalDateTime lastVisitDate;

    public User(String fullName, UserType userType, Contact contact, Image avatar, String password, String login,
                LocalDateTime registrationDate, LocalDateTime lastVisitDate) {
        this.fullName = fullName;
        this.userType = userType;
        this.contact = contact;
        this.avatar = avatar;
        this.password = password;
        this.login = login;
        this.registrationDate = registrationDate;
        this.lastVisitDate = lastVisitDate;
    }

    public User() {
    }

    public User(String fullName) {
        this.fullName = fullName;
    }

    @Embedded
    private Contact contact;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDateTime lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;

        return Objects.equals(id, user.id) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(avatar, user.avatar) &&
                userType == user.userType &&
                Objects.equals(password, user.password) &&
                Objects.equals(login, user.login) &&
                Objects.equals(registrationDate, user.registrationDate) &&
                Objects.equals(lastVisitDate, user.lastVisitDate) &&
                Objects.equals(contact, user.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, birthDate, avatar, userType, password, login, registrationDate,
                lastVisitDate, contact);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", avatar=" + avatar +
                ", userType=" + userType +
                ", contact=" + contact +
                '}';
    }
}
