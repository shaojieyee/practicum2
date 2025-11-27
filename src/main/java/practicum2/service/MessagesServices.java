/**********************************************************************************
*
* From: https://www.baeldung.com/jax-rs-spec-and-implementations
* Edited By: Angeline Tan
* Edited On: 20 November 2021
* Description: REST resource
*
**********************************************************************************/

package practicum2.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import practicum2.model.Message;

@Path("/demo")
public class MessagesServices {

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotification(@PathParam("id") int id) {
        return Response.ok()
                .entity(new Message(id, "test message"))
                .build();
    }

    @POST
    @Path("/post/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNotification(Message notification) {
        return Response.status(201).entity(notification).build();
    }
}
