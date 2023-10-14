module programacion3.parcial2.universidad {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.mail.api;
    requires java.logging;
    requires java.sql;


    opens programacion3.parcial2.universidad to javafx.fxml;
    exports programacion3.parcial2.universidad;
    opens programacion3.parcial2.universidad.viewController to javafx.fxml;
    exports programacion3.parcial2.universidad.viewController;

}