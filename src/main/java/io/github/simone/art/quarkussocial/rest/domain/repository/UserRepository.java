package io.github.simone.art.quarkussocial.rest.domain.repository;

import io.github.simone.art.quarkussocial.rest.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
//@ApplicationScoped cria uma instancia do repositório para poder usar como injeção de dependência
public class UserRepository implements PanacheRepository<User> {
}
