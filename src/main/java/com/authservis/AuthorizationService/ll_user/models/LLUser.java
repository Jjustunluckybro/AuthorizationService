package com.authservis.AuthorizationService.ll_user.models;

import com.authservis.AuthorizationService.ll_user.LLUserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "ll_users")
public class LLUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LLUserRole role = LLUserRole.USER;

    @Column(nullable = true)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email address")
    private String email;

    @Column(nullable = true)
    @Pattern(regexp = "^\\+7\\d{10}$", message = "Invalid phone number")
    private String phone;

    /**
     * All parameters
     */
    public LLUser(
            Integer id,
            String name,
            String password,
            String email,
            String phone,
            LLUserRole role
    ) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    /**
     * Without id
     */
    public LLUser(
            String name,
            String password,
            String email,
            String phone,
            LLUserRole role
    ) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    /**
     * Without id and phone
     */
    public LLUser(LLUserRole role, String email, String password, String name) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    /**
     * Without id and email
     */
    public LLUser(String name, String password, String phone, LLUserRole role) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    /**
     * Without id and role
     */
    public LLUser(String name, String password, String email, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    /**
     * empty constructor
     */
    public LLUser() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LLUserRole getRole() {
        return role;
    }

    public void setRole(LLUserRole role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
