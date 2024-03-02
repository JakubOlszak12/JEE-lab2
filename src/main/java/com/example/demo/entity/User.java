package com.example.demo.entity;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    @NotBlank(message = "Imię jest wymagane")
    @Size(min = 2, max = 16, message = "Imię musi zawierać od 2 do 16 znaków")
    private String name;
    @NotBlank(message = "Nazwisko jest wymagane")
    @Size(min = 2, max = 16, message = "Nazwisko musi zawierać od 2 do 16 znaków")
    private String surname;

    @NotBlank(message = "Login jest wymagany")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login może zawierać tylko litery i cyfry")
    @Size(min = 5, max = 20, message = "Login musi zawierać od 5 do 20 znaków")
    private String login;
    @NotBlank(message = "Hasło jest wymagane")
    private String password;
    public User() {
    }
    public User(String name, String surname, String login,
                String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }
    //metody get i set

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}