module programacion3.parcial2.universidad {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.mail.api;
    requires java.logging;
    requires java.sql;
    requires org.mapstruct;


    opens programacion3.parcial2.universidad to javafx.fxml;
    exports programacion3.parcial2.universidad;
    opens programacion3.parcial2.universidad.viewController to javafx.fxml;
    exports programacion3.parcial2.universidad.viewController;
    opens programacion3.parcial2.universidad.model to javafx.fxml;
    exports programacion3.parcial2.universidad.model;
    opens programacion3.parcial2.universidad.controller to javafx.fxml;
    exports programacion3.parcial2.universidad.controller;
    opens programacion3.parcial2.universidad.mapping.dto to javafx.fxml;
    exports programacion3.parcial2.universidad.mapping.dto;
    opens programacion3.parcial2.universidad.mapping.mappers to javafx.fxml;
    exports programacion3.parcial2.universidad.mapping.mappers;
    opens programacion3.parcial2.universidad.mailer to javafx.fxml;
    exports programacion3.parcial2.universidad.mailer;
    opens programacion3.parcial2.universidad.enumm to javafx.fxml;
    exports programacion3.parcial2.universidad.enumm;

}