package com.roi.model;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Set;

/**
 * JavaBean object that represents a User
 */


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel attachedHotel;


    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Hotel getAttachedHotel() {
        return attachedHotel;
    }

    public void setAttachedHotel(Hotel attachedHotel) {
        this.attachedHotel = attachedHotel;
    }

    public Role getBiggestRole() {
        int id = 1;
        Role res = null;
        Iterator rolesIter = roles.iterator();
        while(rolesIter.hasNext()) {
            Role role = (Role)rolesIter.next();
            if(role.getId() >= id) {
                res = role;
                id = role.getId();
            }
        }
        return res;
    }
}