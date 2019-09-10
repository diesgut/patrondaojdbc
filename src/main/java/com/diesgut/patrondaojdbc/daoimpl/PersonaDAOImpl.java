package com.diesgut.patrondaojdbc.daoimpl;

import com.diesgut.patrondaojdbc.dao.IPersonaDAO;
import com.diesgut.patrondaojdbc.model.Actor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonaDAOImpl implements IPersonaDAO {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Connection con;

    public PersonaDAOImpl() {
        con = Conexion.conectar();
    }

    @Override
    public void save(Actor actor) {
        try {
            String sql = "INSERT INTO ACTOR (first_name,last_name,last_update) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, actor.getFirstName());
            ps.setString(2, actor.getLastName());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            logger.error("Error al guardar el actor", e);
        } finally {
            Conexion.desconectar();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM ACTOR WHERE ID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            logger.error("Error al eliminar el actor", e);
        } finally {
            Conexion.desconectar();
        }
    }

    @Override
    public void update(Actor actor) {
        try {
            String sql = "UPDATE ACTOR SET FIRST_NAME=?, LAST_NAME=?, LAST_UPDATE WHERE ID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, actor.getFirstName());
            ps.setString(2, actor.getLastName());
            ps.setDate(3, this.convertDateToLocalSqlSDate(actor.getLastUpdate()));
            ps.setLong(4, actor.getActodId());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            logger.error("Error al guardar el actor", e);
        } finally {
            Conexion.desconectar();
        }
    }

    @Override
    public List<Actor> all() {
        List<Actor> actores = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ACTOR";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rsActores = ps.executeQuery();
            while (rsActores.next()) {
                Actor actor = new Actor();
                actor.setActodId(rsActores.getLong("actor_id"));
                actor.setFirstName(rsActores.getString("first_name"));
                actor.setLastName(rsActores.getString("last_name"));
                actor.setLastUpdate(rsActores.getDate("last_update"));
                actores.add(actor);
            }
        } catch (Exception e) {
            logger.error("Error al listar actores", e);
        } finally {
            Conexion.desconectar();
            return actores;
        }
    }

    @Override
    public Actor find(Long id) {
        Actor actor = null;
        try {
            String sql = "SELECT * FROM ACTOR WHERE ACTOR_ID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rsActores = ps.executeQuery();
            while (rsActores.next()) {
                actor = new Actor();
                actor.setActodId(rsActores.getLong("actor_id"));
                actor.setFirstName(rsActores.getString("first_name"));
                actor.setLastName(rsActores.getString("last_name"));
                actor.setLastUpdate(rsActores.getDate("last_update"));
            }
        } catch (Exception e) {
            logger.error("Error al listar actores", e);
        } finally {
            Conexion.desconectar();
            return actor;
        }
    }

    java.sql.Date convertDateToLocalSqlSDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return java.sql.Date.valueOf(localDate);
    }

}
