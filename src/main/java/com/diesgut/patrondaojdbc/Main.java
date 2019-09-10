package com.diesgut.patrondaojdbc;

import com.diesgut.patrondaojdbc.dao.IPersonaDAO;
import com.diesgut.patrondaojdbc.daoimpl.PersonaDAOImpl;
import com.diesgut.patrondaojdbc.model.Actor;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        IPersonaDAO personaDAO = new PersonaDAOImpl();

        List<Actor> actores = personaDAO.all();
        actores.forEach(System.out::println);
    }

}
