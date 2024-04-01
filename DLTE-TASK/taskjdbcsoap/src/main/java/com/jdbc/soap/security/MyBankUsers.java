package com.jdbc.soap.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyBankUsers implements UserDetails{

    private String name;
    private String username;
    private String password;
    private String email;
    private Long contact;
    private Long aadhaar;

    public MyBankUsers(String name, String username, String password, String email, Long contact, Long aadhaar) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
        this.aadhaar = aadhaar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public Long getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(Long aadhaar) {
        this.aadhaar = aadhaar;
    }
}
