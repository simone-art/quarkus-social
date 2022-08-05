package io.github.simone.art.quarkussocial.rest;

import io.github.simone.art.quarkussocial.rest.dto.CreateUserRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

}
