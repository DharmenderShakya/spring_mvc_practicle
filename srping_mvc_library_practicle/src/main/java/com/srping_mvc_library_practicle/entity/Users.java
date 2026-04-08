package com.srping_mvc_library_practicle.entity;

import com.srping_mvc_library_practicle.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    
    private String password;
    
    private Integer failedAttempts;
    
    private boolean accountNonLocked;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
	
		
}
