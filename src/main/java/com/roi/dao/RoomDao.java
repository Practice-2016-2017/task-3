package com.roi.dao;

import com.roi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomDao extends JpaRepository<Room, Integer> {
}
