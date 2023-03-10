package org.ssb.trainrootmanagement.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;

@Entity
@Table(name = "user_details",uniqueConstraints = {@UniqueConstraint(columnNames = {"userName", "phoneNumber"})})
@Data
public class LoginDetails implements UserDetails  {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    private String userName;

    private Long phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    private boolean locked;
    private boolean credentialsExpired;
    private Boolean isActive;

    @Override
    public List< GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER_ROLE"));
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    
    }



}