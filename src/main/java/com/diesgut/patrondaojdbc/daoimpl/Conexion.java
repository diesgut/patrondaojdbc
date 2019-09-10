package com.diesgut.patrondaojdbc.daoimpl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {

    protected static Connection con;

    public static Connection conectar() {
        Logger logger = LoggerFactory.getLogger(Conexion.class);
        try {
            if (con != null && !con.isClosed()) {
                return con;
            }
            InputStream inputStream = Conexion.class.getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            con = null;
            logger.error("error al conectar", e);
        } finally {
            return con;
        }
    }

    public static void desconectar() {
        Logger logger = LoggerFactory.getLogger(Conexion.class);
        try {
            if (con == null || con.isClosed()) {
                return;
            }
            con.close();
        } catch (Exception e) {
            logger.error("error al desconectar", e);
        }
    }

}
