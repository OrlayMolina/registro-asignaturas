package programacion3.parcial2.universidad.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AsignacionProfesoresViewController {

    @FXML
    private ComboBox<?> cmbMateria;

    @FXML
    private ComboBox<?> cmbProfesor;

    @FXML
    private TableColumn<?, ?> colCodigo;

    @FXML
    private TableColumn<?, ?> colCodigoMateria;

    @FXML
    private TableColumn<?, ?> colCodigoProfesor;

    @FXML
    private TableColumn<?, ?> colMateria;

    @FXML
    private TableColumn<?, ?> colProfesor;

    @FXML
    private TableView<?> tableAsignaciones;

    @FXML
    private TextField txfCodigo;

    @FXML
    private TextField txfCodigoMateria;

    @FXML
    private TextField txfCodigoProfesor;

    @FXML
    void actualizarAsignacion(ActionEvent event) {

    }

    @FXML
    void agregarAsignacion(ActionEvent event) {

    }

    @FXML
    void buscarAsignacion(ActionEvent event) {

    }

    @FXML
    void cancelarFiltro(ActionEvent event) {

    }

    @FXML
    void eliminarAsignacion(ActionEvent event) {

    }

}
