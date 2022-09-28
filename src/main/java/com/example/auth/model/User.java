package com.example.auth.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String username;
   @Column(nullable = false)
    private String userPass;
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private Set<UserRole> userRoles=new HashSet<>();
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Instant created;
    @Column(nullable = false)
    private boolean enabled;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getUserPass() {
//        return userPass;
//    }
//
//    public void setUserPass(String userPass) {
//        this.userPass = userPass;
//    }
//
//    public Set<UserRole> getUserRoles() {
//        return userRoles;
//    }
//
//    public void setUserRoles(Set<UserRole> userRoles) {
//        this.userRoles = userRoles;
//
//        for(UserRole userRole:userRoles){
//            userRole.setUser(this);
//        }
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Instant getCreated() {
//        return created;
//    }
//
//    public void setCreated(Instant created) {
//        this.created = created;
//    }
//
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }

