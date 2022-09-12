package io.github.simone.art.quarkussocial.rest;


import io.github.simone.art.quarkussocial.rest.dto.CreateUserRequest;
import io.github.simone.art.quarkussocial.rest.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.transaction.Transactional;
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
    @Transactional
    //@Transactional é uma anotation que se requer pra fazer a manipulação do banco de dados
    public Response createUser(CreateUserRequest userRequest){
        //Persistindo os dados do usuário no banco de dados
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        //Persiste é o método que salva os dados na entidade
        user.persist();
        return Response.ok(user).build();

    }

    @GET
    public Response listAllUsers(){
        //query.list lista as consultas das entidades
        PanacheQuery<User> query = User.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    // PathParam: passando o parâmetro na URL
    // O @Transactional é necessário colocar para alterar a base de dados
    public Response deleteUser(@PathParam("id") Long id){
        User user = User.findById(id);
        if (user != null){
            user.delete();
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData){
        return Response.ok().build();
    }
}
