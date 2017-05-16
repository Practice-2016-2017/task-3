package com.roi.model;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roomId;

    @Column(name = "roomNum")
    private Integer roomNum;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;


    public Room(){}

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }
}
