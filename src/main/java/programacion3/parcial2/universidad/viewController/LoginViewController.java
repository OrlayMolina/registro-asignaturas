package programacion3.parcial2.universidad.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programacion3.parcial2.universidad.Main;
import programacion3.parcial2.universidad.controller.ModelFactoryController;
import programacion3.parcial2.universidad.mailer.MailSend;
import programacion3.parcial2.universidad.model.Universidad;

import java.io.UnsupportedEncodingException;

public class LoginViewController {

    ModelFactoryController modelFactoryController;
    Universidad universidad;
    Main main;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private PasswordField pwfContrasenia;

    @FXML
    private TextField txfNombreUsuario;

    @FXML
    void recuperarAcceso(ActionEvent event) throws UnsupportedEncodingException {
        enviarCorreo();
    }

    @FXML
    void iniciarSesion(ActionEvent event) {
        iniciarSesion();
    }

    @FXML
    void initialize() {
        modelFactoryController = new ModelFactoryController();
        universidad = new Universidad();
        main = new Main();
    }

    private void iniciarSesion(){
        String usuario = txfNombreUsuario.getText();
        String contrasenia = pwfContrasenia.getText();
        if(modelFactoryController.comprobarAcceso(usuario, contrasenia)){
            cerrarVentana(btnIniciarSesion);
            main.cargarVentanaTabulador();
        }else{
            mostrarMensaje("Notificación de Acceso", "Inicio de Sesión", "El inicio de sesion es incorrecto", Alert.AlertType.ERROR);
        }
    }

    void enviarCorreo() throws UnsupportedEncodingException {
        String correo = universidad.correoProperties();
        String body;
        MailSend mailSend;
        String template = "<p style=\"text-align:center\"><span style=\"font-size:22px\"><span style=\"font-family:Verdana,Geneva,sans-serif\">Hola <span style=\"color:#16a085\">[Primer_Nombre]</span>, recibimos tu solicitud para recuperar el acceso a tu cuenta</span></span></p>\n" +
                "\n" +
                "<p style=\"text-align:center\"><img src=\"https://i.ibb.co/gMPkhFz/logo-parcial2.png\" style=\"height:331px; width:820px\" /></p>\n" +
                "\n" +
                "<p style=\"text-align:center\"><span style=\"font-size:22px\"><span style=\"font-family:Verdana,Geneva,sans-serif\">Usuario: <span style=\"color:#16a085\">[userName]</span></span></span></p>\n" +
                "\n" +
                "<p style=\"text-align:center\"><span style=\"font-size:22px\"><span style=\"font-family:Verdana,Geneva,sans-serif\">Contrase&ntilde;a: <span style=\"color:#16a085\">[password]</span></span></span></p>\n" +
                "\n" +
                "<p style=\"text-align:center\"><span style=\"font-size:22px\"><span style=\"font-family:Verdana,Geneva,sans-serif\">Recuerda cambiar tu clave periodicamente</span></span></p>\n" +
                "\n" +
                "<p style=\"text-align:center\"><span style=\"font-size:22px\"><span style=\"font-family:Verdana,Geneva,sans-serif\">Feliz dia  </span></span></p>\n" +
                "\n" +
                "<p style=\"text-align:center\">&nbsp;</p>";

        mailSend = new MailSend();
        body = template.replace("[Primer_Nombre]", universidad.nombreProperties()).replace("[userName]", universidad.usuarioProperties()).replace("[password]", universidad.passwordProperties());
        mailSend.createEmail(correo, universidad.nombreProperties() + ", tenemos novedades de tu cuenta: ", new String(body.getBytes("ISO-8859-1"), "UTF-8"));
        mailSend.sendEmail();
        mostrarMensaje("Mensaje: ", "envio de correo","Correo enviado exitosamente",Alert.AlertType.INFORMATION);
    }

    public void cerrarVentana(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

}
