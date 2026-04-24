/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.dao;

import com.smartcampus.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    public static final List<Room> ROOMS = new ArrayList<>();
    public static final List<Sensor> SENSORS = new ArrayList<>();
    public static final Map<String, List<SensorReading>> READINGS = new HashMap<>();

    static {
        Room room1 = new Room("LIB-301", "Library Quiet Study", 40);
        ROOMS.add(room1);

        Sensor sensor1 = new Sensor("TEMP-001", "Temperature", "MAINTENANCE", 22.5, "LIB-301");
        SENSORS.add(sensor1);

        room1.getSensorIds().add(sensor1.getId());
        READINGS.put(sensor1.getId(), new ArrayList<SensorReading>());
    }

}
