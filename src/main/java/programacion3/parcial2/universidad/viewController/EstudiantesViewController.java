package programacion3.parcial2.universidad.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import programacion3.parcial2.universidad.controller.EstudianteController;
import programacion3.parcial2.universidad.enumm.Sexo;
import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;
import programacion3.parcial2.universidad.model.Universidad;

import java.util.Optional;

public class EstudiantesViewController {

    EstudianteController estudianteControllerService;

    Universidad universidad;
    ObservableList<EstudianteDto> listaEstudiantes = FXCollections.observableArrayList();
    ObservableList<String> listaGenero = FXCollections.observableArrayList();
    EstudianteDto estudianteSeleccionado;

    @FXML
    private ComboBox<String> cmbSexo;

    @FXML
    private TableColumn<EstudianteDto, String> colApellidos;

    @FXML
    private TableColumn<EstudianteDto, String> colCodigo;

    @FXML
    private TableColumn<EstudianteDto, String> colCorreo;

    @FXML
    private TableColumn<EstudianteDto, String> colEdad;

    @FXML
    private TableColumn<EstudianteDto, String> colNombres;

    @FXML
    private TableColumn<EstudianteDto, String> colSexo;

    @FXML
    private TableColumn<EstudianteDto, String> colTelefono;

    @FXML
    private TableView<EstudianteDto> tableEstudiantes;

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
    private TextField txfTelefono;

    @FXML
    void actualizarEstudiante(ActionEvent event) {

    }

    @FXML
    void agregarEstudiante(ActionEvent event) {
        crearEstudiante();
    }

    @FXML
    void buscarEstudiante(ActionEvent event) {

    }

    @FXML
    void cancelarFiltro(ActionEvent event) {

    }

    @FXML
    void eliminarEstudiante(ActionEvent event) {
        eliminarEstudiante();
    }

    @FXML
    void generarPDF(ActionEvent event) {

    }

    @FXML
    void initialize() {
        estudianteControllerService = new EstudianteController();
        universidad = new Universidad();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerEstudiantes();
        mostrarSexo();
        tableEstudiantes.getItems().clear();
        tableEstudiantes.setItems(listaEstudiantes);
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
    }

    private void listenerSelection() {
        tableEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            estudianteSeleccionado = newSelection;
            mostrarInformacionEstudiante(estudianteSeleccionado);
        });
    }

    public void mostrarSexo(){
        listaGenero.add(String.valueOf(Sexo.Femenino));
        listaGenero.add(String.valueOf(Sexo.Masculino));
        cmbSexo.setItems(listaGenero);
    }

    private void crearEstudiante() {

        EstudianteDto estudianteDto = construirEstudianteDto();

        if(datosValidos(estudianteDto)){
            if(estudianteControllerService.agregarEstudiante(estudianteDto)){
                listaEstudiantes.add(estudianteDto);
                mostrarMensaje("Notificación estudiante", "Estudiante creado", "El estudiante se ha creado con éxito", Alert.AlertType.INFORMATION);
                registrarAcciones("Estudiante creado",1, "Creación de un estudiante, acción realizada por " + universidad.nombreProperties());
                limpiarCamposEstudiante();

            }else{
                mostrarMensaje("Notificación estudiante", "Estudiante no creado", "El estudiante no se ha creado", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación estudiante", "Estudiante no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    private void eliminarEstudiante() {
        boolean estudianteEliminado = false;
        if(estudianteSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de elmininar al estudiante?")){
                estudianteEliminado = estudianteControllerService.eliminarEstudiante(estudianteSeleccionado.codigo());
                if(estudianteEliminado){
                    listaEstudiantes.remove(estudianteSeleccionado);
                    estudianteSeleccionado = null;
                    tableEstudiantes.getSelectionModel().clearSelection();
                    limpiarCamposEstudiante();
                    registrarAcciones("Estudiante eliminado",1, "Eliminar estudiante");
                    mostrarMensaje("Notificación estudiante", "Estudiante eliminado", "El estudiante se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación estudiante", "Estudiante no eliminado", "El estudiante no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación producto", "Producto no seleccionado", "Seleccionado un empleado de la lista", Alert.AlertType.WARNING);
        }
    }

    private void obtenerEstudiantes() {
        listaEstudiantes.addAll(estudianteControllerService.obtenerEstudiantes());
    }

    private void mostrarInformacionEstudiante(EstudianteDto estudianteSeleccionado) {
        if(estudianteSeleccionado != null){
            txfCodigo.setText(estudianteSeleccionado.codigo());
            txfNombres.setText(estudianteSeleccionado.nombres());
            txfApellidos.setText(estudianteSeleccionado.apellidos());
            cmbSexo.setValue(estudianteSeleccionado.sexo());
            txfEdad.setText(estudianteSeleccionado.edad());
            txfCorreo.setText(estudianteSeleccionado.correo());
            txfTelefono.setText(estudianteSeleccionado.telefono());

        }
    }

    private EstudianteDto construirEstudianteDto() {
        return new EstudianteDto(
                txfCodigo.getText(),
                txfNombres.getText(),
                txfApellidos.getText(),
                cmbSexo.getValue(),
                txfEdad.getText(),
                txfCorreo.getText(),
                txfTelefono.getText()

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

    }

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        estudianteControllerService.registrarAcciones(mensaje, nivel, accion);
    }

    private boolean datosValidos(EstudianteDto estudianteDto) {
        String mensaje = "";
        if(estudianteDto.codigo() == null || estudianteDto.codigo().equals(""))
            mensaje += "El código del estudiante es invalido \n" ;
        if(estudianteDto.nombres() == null || estudianteDto.nombres() .equals(""))
            mensaje += "Los nombres del estudiante es invalido \n" ;
        if(estudianteDto.apellidos() == null || estudianteDto.apellidos() .equals(""))
            mensaje += "los apellidos del estudiante es invalido \n" ;
        if(estudianteDto.sexo() == null || estudianteDto.sexo() .equals(""))
            mensaje += "El Sexo del estudiante es invalido \n" ;
        if(estudianteDto.edad() == null || estudianteDto.edad() .equals(""))
            mensaje += "El edad del estudiante es invalido \n" ;
        if(estudianteDto.correo() == null || estudianteDto.correo() .equals(""))
            mensaje += "El correo del estudiante es invalido \n" ;
        if(estudianteDto.telefono() == null || estudianteDto.telefono() .equals(""))
            mensaje += "El teléfono del estudiante es invalido \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación estudiante", "Estudiante no creado", mensaje, Alert.AlertType.ERROR);
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
