package programacion3.parcial2.universidad.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import programacion3.parcial2.universidad.controller.EstudianteController;
import programacion3.parcial2.universidad.controller.ProfesorController;
import programacion3.parcial2.universidad.enumm.Programa;
import programacion3.parcial2.universidad.enumm.Sexo;
import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;
import programacion3.parcial2.universidad.model.Universidad;

import java.util.Optional;

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

    @FXML
    void initialize() {
        profesorControllerService = new ProfesorController();
        universidad = new Universidad();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerEstudiantes();
        mostrarSexo();
        mostrarPrograma();
        tableProfesores.getItems().clear();
        tableProfesores.setItems(listaProfesoresDto);
        listenerSelection();
    }

    private void initDataBinding() {
        colCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().codigo()));
        colNombres.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombres()));
        colApellidos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellidos()));
        colSexo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().sexo()));
        colEdad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().edad()));
        colCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().correo()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().telefono()));
        colPrograma.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().programa()));
        colProfesion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().profesion()));
    }

    private void listenerSelection() {
        tableProfesores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            profesorSeleccionado = newSelection;
            mostrarInformacionProfesor(profesorSeleccionado);
        });
    }

    public void mostrarSexo(){
        listaGenero.add(String.valueOf(Sexo.Femenino));
        listaGenero.add(String.valueOf(Sexo.Masculino));
        cmbSexo.setItems(listaGenero);
    }

    public void mostrarPrograma(){
        listaPrograma.add(String.valueOf(Programa.Artes_Visuales));
        listaPrograma.add(String.valueOf(Programa.Ingeniería_Civil));
        cmbSexo.setItems(listaPrograma);
    }


    private void obtenerEstudiantes() {
        listaProfesoresDto.addAll(profesorControllerService.obtenerProfesores());
    }

    private void mostrarInformacionProfesor(ProfesorDto profesorSeleccionado) {
        if(profesorSeleccionado != null){
            txfCodigo.setText(profesorSeleccionado.codigo());
            txfNombres.setText(profesorSeleccionado.nombres());
            txfApellidos.setText(profesorSeleccionado.apellidos());
            cmbSexo.setValue(profesorSeleccionado.sexo());
            txfEdad.setText(profesorSeleccionado.edad());
            txfCorreo.setText(profesorSeleccionado.correo());
            txfTelefono.setText(profesorSeleccionado.telefono());
            cmbPrograma.setValue(profesorSeleccionado.correo());
            txfProfesion.setText(profesorSeleccionado.telefono());

        }
    }

    private ProfesorDto construirProfesorDto() {
        return new ProfesorDto(
                txfCodigo.getText(),
                txfNombres.getText(),
                txfApellidos.getText(),
                cmbSexo.getValue(),
                txfEdad.getText(),
                txfCorreo.getText(),
                txfTelefono.getText(),
                cmbPrograma.getValue(),
                txfProfesion.getText()

        );
    }

    private void limpiarCamposEstudiante() {
        txfCodigo.setText("");
        txfNombres.setText("");
        txfApellidos.setText("");
        cmbSexo.setValue(null);
        txfEdad.setText("");
        txfCorreo.setText("");
        txfTelefono.setText("");
        cmbPrograma.setValue(null);
        txfProfesion.setText("");

    }

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        profesorControllerService.registrarAcciones(mensaje, nivel, accion);
    }

    private boolean datosValidos(ProfesorDto profesorDto) {
        String mensaje = "";
        if(profesorDto.codigo() == null || profesorDto.codigo().equals(""))
            mensaje += "El código del profesor es invalido \n" ;
        if(profesorDto.nombres() == null || profesorDto.nombres() .equals(""))
            mensaje += "Los nombres del profesor es invalido \n" ;
        if(profesorDto.apellidos() == null || profesorDto.apellidos() .equals(""))
            mensaje += "los apellidos del profesor es invalido \n" ;
        if(profesorDto.sexo() == null || profesorDto.sexo() .equals(""))
            mensaje += "El Sexo del profesor es invalido \n" ;
        if(profesorDto.edad() == null || profesorDto.edad() .equals(""))
            mensaje += "El edad del profesor es invalido \n" ;
        if(profesorDto.correo() == null || profesorDto.correo() .equals(""))
            mensaje += "El correo del profesor es invalido \n" ;
        if(profesorDto.telefono() == null || profesorDto.telefono() .equals(""))
            mensaje += "El teléfono del profesor es invalido \n" ;
        if(profesorDto.programa() == null || profesorDto.programa() .equals(""))
            mensaje += "El programa del profesor es invalido \n" ;
        if(profesorDto.profesion() == null || profesorDto.profesion() .equals(""))
            mensaje += "El profesion del profesor es invalido \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación profesor", "Profesor no creado", mensaje, Alert.AlertType.ERROR);
            return false;
        }
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

}
