package com.roi.dao;

import com.roi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by alexander-k on 16.05.17.
 */

public interface RoomDao extends JpaRepository<Room, Integer> {
}
