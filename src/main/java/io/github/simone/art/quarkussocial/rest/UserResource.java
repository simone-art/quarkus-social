package io.github.simone.art.quarkussocial.rest;


import io.github.simone.art.quarkussocial.rest.domain.repository.UserRepository;
import io.github.simone.art.quarkussocial.rest.dto.CreateUserRequest;
import io.github.simone.art.quarkussocial.rest.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/users")
// Anotação consumes permite definir o tipo de body a ser recebido
@Consumes(MediaType.APPLICATION_JSON)
// Anotação produces indica o tipo de body a obter na resposta
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserRepository userRepository;
    private Validator validator;

    @Inject
    //@Inject anotação pra fazer a injeção de dependência do repository
    public UserResource(UserRepository userRepository, Validator validator){
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @POST
    @Transactional
    //@Transactional é uma anotation que se requer pra fazer a manipulação do banco de dados
    public Response createUser(CreateUserRequest userRequest){
        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        if(!violations.isEmpty()){
            return Response.status(400).build();
        }
        //Persistindo os dados do usuário no banco de dados
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        //Persiste é o método que salva os dados na entidade
        userRepository.persist(user);
        return Response.ok(user).build();

    }

    @GET
    public Response listAllUsers(){
        //query.list lista as consultas das entidades
        PanacheQuery<User> query = userRepository.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    // PathParam: passando o parâmetro na URL
    // O @Transactional é necessário colocar para alterar a base de dados
    public Response deleteUser(@PathParam("id") Long id){
        User user = userRepository.findById(id);
        if (user != null){
            userRepository.delete(user);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData){
        User user = userRepository.findById(id);
        if(user != null){
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
