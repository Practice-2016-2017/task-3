package com.roi.service;

import com.roi.dao.RoomDao;
import com.roi.model.Hotel;
import com.roi.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexander-k on 16.05.17.
 */

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;

    @Override
    @Transactional
    public List<Room> getRoomByHotel(Hotel hotel){
        List<Room> roomList = roomDao.findAll();
        Iterator iter = roomList.iterator();
        while(iter.hasNext()) {
            Room room = (Room)iter.next();
            if(!room.getHotel().getHotelId().equals(hotel.getHotelId()))
                iter.remove();
        }
        return roomList;
    }

    @Override
    public List<Room> getRoomsInHotel(Hotel hotel) {
        List<Room> rooms = roomDao.findAll();
        Iterator iter = rooms.iterator();
        while (iter.hasNext()) {
            Room room = (Room)iter.next();
            if(!room.getHotel().getHotelId().equals(hotel.getHotelId()))
                iter.remove();
        }
        return rooms;
    }

    @Override
    @Transactional
    public void addRoomToHotel(Hotel hotel){
        Integer max = 0;
        List<Room> roomList = roomDao.findAll();
        Iterator iter = roomList.iterator();
        Room firstRoom = (Room)iter.next();
        while(iter.hasNext()) {
            Room room = (Room)iter.next();
            if(room.getHotel().getHotelId().equals(hotel.getHotelId()) && max < room.getRoomNum())
                max = room.getRoomNum();
        }

        max+=1;

        Room roomToAdd = new Room();
        roomToAdd.setHotel(hotel);
        roomToAdd.setRoomNum(max);
        roomDao.saveAndFlush(roomToAdd);
    }

    @Override
    public Room getRoomById(int id) {
        return roomDao.findOne(id);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDao.findAll();
    }


}
