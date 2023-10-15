package programacion3.parcial2.universidad.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import programacion3.parcial2.universidad.controller.ProfesorController;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;
import programacion3.parcial2.universidad.model.Universidad;

public class ProfesoresViewController {

    ProfesorController profesorControllerService;
    Universidad universidad;
    ObservableList<ProfesorDto> listaProfesoresDto = FXCollections.observableArrayList();
    ObservableList<String> listaGenero = FXCollections.observableArrayList();
    ObservableList<String> listaPrograma = FXCollections.observableArrayList();
    ProfesorDto profesorSeleccionado;

    @FXML
    private ComboBox<String> cmbPrograma;

    @FXML
    private ComboBox<String> cmbSexo;

    @FXML
    private TextField txfApellidos;

    @FXML
    private TextField txfCodigo;

    @FXML
    private TextField txfCorreo;

    @FXML
    private TextField txfEdad;

    @FXML
    private TextField txfNombres;

    @FXML
    private TextField txfProfesion;

    @FXML
    private TextField txfTelefono;

    @FXML
    private TableColumn<ProfesorDto, String> colApellidos;

    @FXML
    private TableColumn<ProfesorDto, String> colCodigo;

    @FXML
    private TableColumn<ProfesorDto, String> colCorreo;

    @FXML
    private TableColumn<ProfesorDto, String> colEdad;

    @FXML
    private TableColumn<ProfesorDto, String> colNombres;

    @FXML
    private TableColumn<ProfesorDto, String> colProfesion;

    @FXML
    private TableColumn<ProfesorDto, String> colPrograma;

    @FXML
    private TableColumn<ProfesorDto, String> colSexo;

    @FXML
    private TableColumn<ProfesorDto, String> colTelefono;

    @FXML
    private TableView<ProfesorDto> tableProfesores;

    @FXML
    void actualizarProfesor(ActionEvent event) {

    }

    @FXML
    void agregarProfesor(ActionEvent event) {

    }

    @FXML
    void buscarProfesor(ActionEvent event) {

    }

    @FXML
    void cancelarFiltro(ActionEvent event) {

    }

    @FXML
    void eliminarProfesor(ActionEvent event) {

    }

    @FXML
    void generarPDF(ActionEvent event) {

    }

}
