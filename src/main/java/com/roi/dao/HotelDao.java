package com.roi.dao;

import com.roi.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HotelDao extends JpaRepository<Hotel,Integer> {
    Hotel findByInfo(String info);
}
