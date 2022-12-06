module com.friberg.hibernatedemoopenjdk11maven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;

    opens com.friberg.hibernatedemoopenjdk11maven to javafx.fxml;
    exports com.friberg.hibernatedemoopenjdk11maven;
}