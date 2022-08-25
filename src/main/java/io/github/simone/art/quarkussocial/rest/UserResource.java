package io.github.simone.art.quarkussocial.rest;


import io.github.simone.art.quarkussocial.rest.dto.CreateUserRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
// Anotação consumes permite definir o tipo de body a ser recebido
@Consumes(MediaType.APPLICATION_JSON)
// Anotação produces indica o tipo de body a obter na resposta
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    public Response createUser(CreateUserRequest createUserRequest){
        return Response.ok(createUserRequest).build();

    }

    @GET
    public Response listAllUsers(){
        return Response.ok().build();
    }
}
