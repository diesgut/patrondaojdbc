package com.diesgut.patrondaojdbc.dao;

import com.diesgut.patrondaojdbc.model.Actor;
import java.util.List;

public interface IPersonaDAO {

    void save(Actor actor);

    void delete(Long id);

    void update(Actor actor);

    List<Actor> all();

    Actor find(Long id);

}
