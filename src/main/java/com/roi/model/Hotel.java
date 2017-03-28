package com.roi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @Column(name = "hotelId")
    private Integer hotelId;

    @Column(name = "info")
    private String info;

    @Column(name = "user_userId")
    private Integer user_userId;

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

    public Integer getUser_userId() {
        return user_userId;
    }

    public void setUser_userId(Integer user_userId) {
        this.user_userId = user_userId;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", info='" + info + '\'' +
                ", user_userId=" + user_userId +
                '}';
    }
}
