package io.github.simone.art.quarkussocial.rest.quarkussocial.domain.model;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="users")
//O PanacheEntityBase tem vários métodos embutidos pra manipular as entidades
public class User extends PanacheEntityBase {
    @Id
    // anotation @Generated delega ao banco de dados fazer o id automático do objeto
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // equals e user são importantes para comparação de objetos no Java
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && name.equals(user.name) && age.equals(user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}
