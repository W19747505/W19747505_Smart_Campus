/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.model.Discovery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class DiscoveryResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Discovery getDiscovery(){
        Discovery response = new Discovery();
        response.setApiName("Smart Campus Sensor & Room Management API");
        response.setVersion("v1");
        response.setAdminContact("admin@westminster.ac.uk");
        response.getResources().put("rooms", "/api/v1/rooms");
        response.getResources().put("sensors", "/api/v1/sensors");
        return response;
    }
}
