/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.dao.Database;
import com.smartcampus.dao.GenericDAO;
import com.smartcampus.exception.LinkedResourceNotFoundException;
import com.smartcampus.model.Room;
import com.smartcampus.model.Sensor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(Database.SENSORS);
    private GenericDAO<Room> roomDAO = new GenericDAO<>(Database.ROOMS);

    @GET
    public List<Sensor> getAllSensors(@QueryParam("type") String type) {
        List<Sensor> allSensors = sensorDAO.getAll();

        if (type == null || type.trim().isEmpty()) {
            return allSensors;
        }

        List<Sensor> filteredSensors = new ArrayList<>();

        for (Sensor sensor : allSensors) {
            if (sensor.getType() != null && sensor.getType().equalsIgnoreCase(type)) {
                filteredSensors.add(sensor);
            }
        }

        return filteredSensors;
    }

    @POST
    public Response createSensor(Sensor sensor) {
        Room room = roomDAO.getById(sensor.getRoomId());

        if (room == null) {
            throw new LinkedResourceNotFoundException("Room does not exist.");
        }

        Sensor existingSensor = sensorDAO.getById(sensor.getId());
        if (existingSensor != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Sensor already exists.")
                    .build();
        }

        sensorDAO.add(sensor);

        room.getSensorIds().add(sensor.getId());

        Database.READINGS.put(sensor.getId(), new ArrayList<>());

        return Response.status(Response.Status.CREATED)
                .entity(sensor)
                .build();
    }

    @GET
    @Path("/{sensorId}")
    public Response getSensorById(@PathParam("sensorId") String sensorId) {
        Sensor sensor = sensorDAO.getById(sensorId);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor with ID " + sensorId + " not found.")
                    .build();
        }

        return Response.ok(sensor).build();
    }

    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }
}
