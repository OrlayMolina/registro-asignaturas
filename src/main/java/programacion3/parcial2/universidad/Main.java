package programacion3.parcial2.universidad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import programacion3.parcial2.universidad.model.Universidad;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    Universidad universidad = new Universidad();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        String rutaRelativa = "/programacion3/parcial2/universidad/logo-parcial2.png";
        Image iconImage = new Image(Objects.requireNonNull(getClass().getResource(rutaRelativa)).toExternalForm());
        stage.getIcons().add(iconImage);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void cargarVentanaTabulador() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tabulador-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            String rutaRelativa = "/programacion3/parcial2/universidad/logo-parcial2.png";
            Image iconImage = new Image(Objects.requireNonNull(getClass().getResource(rutaRelativa)).toExternalForm());
            newStage.getIcons().add(iconImage);
            newStage.setTitle("Universidad del Quindío | usuario: " + universidad.usuarioProperties());
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}