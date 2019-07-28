/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.ea.rest;

import com.isec.fishtravel.dto.DTOFlight;
import com.isec.fishtravel.facade.client.FTFlightFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author LM
 */
@Stateless
@Path("FlightRS")
public class FlightRS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FlightRS
     */
    
    @EJB
    private FTFlightFacade ejbFlightFacade;
    
    public FlightRS() {
    }
    
    @GET
    @Path("/flight/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public DTOFlight getFlightById(@PathParam("id") int id) {
        return ejbFlightFacade.getFlightById(id);
    }

    @GET
    @Path("/flights")
    @Produces(MediaType.APPLICATION_XML)
    public Response getFlights(@QueryParam("dest") String dest) {
        
        //TODO return proper representation object
        GenericEntity<List<DTOFlight>> list = null;
        
        if (dest != null){
            list = new GenericEntity<List<DTOFlight>>(ejbFlightFacade.getFlightsByDest(dest)) {};
        } else {
            list = new GenericEntity<List<DTOFlight>>(ejbFlightFacade.getAllFlights()) {};
        }

        return Response.ok(list).build();
        
        /* ON CLIENT DO:
        Response r = c.getFlights(Response.class, null);
        List<DTOFlight> flights = r.readEntity(new GenericType<List<DTOFlight>>(){});  
        c.close();
        */
    }

    /**
     * Retrieves representation of an instance of com.isec.fishtravel.ea.rest.FlightRS
     * @return an instance of com.isec.fishtravel.dto.DTOFlight
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public DTOFlight getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of FlightRS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(com.isec.fishtravel.dto.DTOFlight content) {
    }
    
    private FTFlightFacade lookupFlightFacade() {
        try {
            InitialContext c = new InitialContext();
            return (FTFlightFacade) c.lookup("java:global/FishTravel-EA-ear/FishTravel-EA-web-1.0-SNAPSHOT/FTFlightFacade");
        } catch (NamingException ne) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public FTFlightFacade getEjbFlightFacade() {
        return ejbFlightFacade;
    }

    public void setEjbFlightFacade(FTFlightFacade ejbFlightFacade) {
        this.ejbFlightFacade = ejbFlightFacade;
    }
    
}
