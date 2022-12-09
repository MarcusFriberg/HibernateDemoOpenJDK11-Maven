package com.friberg.hibernatedemoopenjdk11maven;

import com.friberg.hibernatedemoopenjdk11maven.view.Menu;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;

public class HelloApplication {
    public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    public static void main(String[] args) throws SQLException {
        CarController carController = new CarController();
        Menu menu = new Menu(carController);
        menu.showMainMenu();
    }
}