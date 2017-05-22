package com.roi.service;

import com.roi.dao.RoomDao;
import com.roi.model.Hotel;
import com.roi.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;


/**
 * Implementation of {@link RoomService}
 */
@Service
public class RoomServiceImpl implements RoomService {
    private static final Logger log = LoggerFactory.getLogger(RoomService.class);

    private final RoomDao roomDao;

    @Autowired
    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    @Transactional
    public List<Room> getRoomByHotel(Hotel hotel) {
        log.info("Getting all rooms in hotel " + hotel.getInfo());
        List<Room> roomList = roomDao.findAll();
        Iterator iter = roomList.iterator();
        while (iter.hasNext()) {
            Room room = (Room) iter.next();
            if (!room.getHotel().getHotelId().equals(hotel.getHotelId()))
                iter.remove();
        }
        return roomList;
    }

    @Override
    @Transactional
    public void addRoomToHotel(Hotel hotel) {
        log.info("Adding room to hotel " + hotel.getInfo());
        Integer max = 0;
        List<Room> roomList = roomDao.findAll();
        for (Room room : roomList) {
            if (room.getHotel().getHotelId().equals(hotel.getHotelId()) && max < room.getRoomNum())
                max = room.getRoomNum();
        }

        max += 1;

        Room roomToAdd = new Room();
        roomToAdd.setHotel(hotel);
        roomToAdd.setRoomNum(max);
        roomDao.saveAndFlush(roomToAdd);
    }

    @Override
    public Room getRoomById(int id) {
        log.info("Getting room by id " + id);
        return roomDao.findOne(id);
    }

    @Override
    public List<Room> getAllRooms() {
        log.info("Getting all rooms");
        return roomDao.findAll();
    }


}
