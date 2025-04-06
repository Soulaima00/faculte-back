package com.example.demo.faculte.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {}

    public User(String nom, String prenom, String email, String password, String telephone, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.role = role;
    }


    public Long getId() { 
    	return id; 
    }
    
    public void setId(Long id) {
    	this.id = id; 
    }
    

    public String getNom() {
    	return nom; 
    }
    
    public void setNom(String nom) {
    	this.nom = nom;
    }
    

    public String getPrenom() { 
    	return prenom; 
    }
    
    public void setPrenom(String prenom) {
    	this.prenom = prenom; 
    }

    public String getEmail() { 
    	return email; 
    }
    
    public void setEmail(String email) {
    	this.email = email; 
    }
    

    public String getPassword() { 
    	return password; 
    }
    
    public void setPassword(String password) { 
    	this.password = password; 
    }

    public String getTelephone() { 
    	return telephone; 
    }
    
    public void setTelephone(String telephone) {
    	this.telephone = telephone;
    }
    

    public Role getRole() { 
    	return role; 
    }
    
    public void setRole(Role role) {
    	this.role = role; 
    }
    
}