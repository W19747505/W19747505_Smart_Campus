/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.dao.GenericDAO;
import com.smartcampus.dao.Database;
import com.smartcampus.exception.RoomNotEmptyException;
import com.smartcampus.model.Room;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {
    
    private GenericDAO<Room> roomDAO = new GenericDAO<>(Database.ROOMS);
    
    @GET
    public List<Room> getRooms() {
        return roomDAO.getAll();
    }
    
    @POST
    public Room createRoom(Room room) {
        roomDAO.add(room);
        return room;
    }
    
    @GET
    @Path("/{id}")
    public Room getRoom(@PathParam("id") String id) {
        return roomDAO.getById(id);
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteRoom(@PathParam("id") String id) {
        Room r = roomDAO.getById(id);
        if (r.getSensorIds() != null && !r.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room contains sensors");
        }
        roomDAO.delete(id);
    }
    
}
