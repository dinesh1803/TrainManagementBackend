package org.ssb.trainrootmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_details")
@Data
public class LoginDetails {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    private String userName;

    private Long phoneNumber;

    private String email;

    @Column(nullable = false)
    private String password;
    

}