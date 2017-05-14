package com.roi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer hotelId;

    @Column(name = "info")
    private String info;

    @OneToMany(mappedBy = "attachedHotel")
    private Set<User> users;

    public Hotel(){};

    public Hotel(String info){
        this.info = info;
    }
    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", info='" + info + '\'' +
                '}';
    }
}
