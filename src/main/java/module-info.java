module com.tec.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires spring.security.crypto;
    requires java.desktop;
    requires javafx.graphics;

    opens com.tec.app to javafx.fxml;
    opens com.tec.app.controller to javafx.fxml;
    opens com.tec.app.model to javafx.base;

    exports com.tec.app;
    exports com.tec.app.controller;
}