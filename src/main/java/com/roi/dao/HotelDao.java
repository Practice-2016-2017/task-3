package com.roi.dao;

import com.roi.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HotelDao extends JpaRepository<Hotel,Integer> {
}
