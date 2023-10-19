package programacion3.parcial2.universidad.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import programacion3.parcial2.universidad.controller.AsignacionController;
import programacion3.parcial2.universidad.mapping.dto.AsignacionDto;
import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;
import programacion3.parcial2.universidad.mapping.dto.MateriaDto;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;
import programacion3.parcial2.universidad.model.Universidad;
import programacion3.parcial2.universidad.util.AsignacionUtil;
import programacion3.parcial2.universidad.util.EstudianteUtil;

import java.util.Optional;
import java.util.function.Predicate;


public class AsignacionProfesoresViewController {

    AsignacionController asignacionControllerService;
    Universidad universidad;
    MateriaDto materiaDto;
    ProfesorDto profesorDto;
    ObservableList<AsignacionDto> listaAsignacionesDto = FXCollections.observableArrayList();
    ObservableList<MateriaDto> listaMaterias = FXCollections.observableArrayList();
    ObservableList<ProfesorDto> listProfesores = FXCollections.observableArrayList();
    AsignacionDto asignacionSeleccionada;

    @FXML
    private ComboBox<MateriaDto> cmbMateria;

    @FXML
    private ComboBox<ProfesorDto> cmbProfesor;

    @FXML
    private TableColumn<AsignacionDto, String> colCodigo;

    @FXML
    private TableColumn<AsignacionDto, String> colCodigoMateria;

    @FXML
    private TableColumn<AsignacionDto, String> colCodigoProfesor;

    @FXML
    private TableColumn<AsignacionDto, String> colMateria;

    @FXML
    private TableColumn<AsignacionDto, String> colProfesor;

    @FXML
    private TableView<AsignacionDto> tableAsignaciones;

    @FXML
    private TextField txfCodigo;

    @FXML
    private TextField txfCodigoMateria;

    @FXML
    private TextField txfCodigoProfesor;

    @FXML
    void actualizarAsignacion(ActionEvent event) {
        actualizarAsignacion();
    }

    @FXML
    void agregarAsignacion(ActionEvent event) {
        crearAsignacion();
    }

    @FXML
    void buscarAsignacion(ActionEvent event) {
        String codigo = txfCodigo.getText();
        String codigoMateria = txfCodigoMateria.getText();
        String materia = String.valueOf(cmbMateria.getValue());
        String codigoProfesor = txfCodigoProfesor.getText();
        String profesor = String.valueOf(cmbProfesor.getValue());

        buscarAsignaciones(codigo, codigoMateria, materia, codigoProfesor, profesor);
    }

    @FXML
    void cancelarFiltro(ActionEvent event) {
        cancelarBusqueda();
    }

    @FXML
    void eliminarAsignacion(ActionEvent event) {
        eliminarAsignacion();
    }

    @FXML
    void llenarCodigoMateria(ActionEvent event) {
        txfCodigoMateria.setText(cmbMateria.getSelectionModel().getSelectedItem().codigo());
    }

    @FXML
    void llenarCodigoProfesor(ActionEvent event) {
        txfCodigoProfesor.setText(cmbProfesor.getSelectionModel().getSelectedItem().codigo());
    }


    @FXML
    void initialize() {
        asignacionControllerService = new AsignacionController();
        universidad = new Universidad();
        initView();
    }

    private void initView() {
        initDataBinding();
        mostrarMateria();
        mostrarProfesor();
        getListaMaterias();
        getListaProfesores();
        tableAsignaciones.getItems().clear();
        tableAsignaciones.setItems(listaAsignacionesDto);
        listenerSelection();
    }

    private void initDataBinding() {
        colCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().codigo()));
        colCodigoMateria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMateriaDto().codigo()));
        colMateria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMateriaDto().nombre()));
        colCodigoProfesor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProfesorDto().codigo()));
        colProfesor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProfesorDto().toString()));
    }

    private void listenerSelection() {
        tableAsignaciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            asignacionSeleccionada = newSelection;
            mostrarInformacionAsignacion(asignacionSeleccionada);
        });
    }

    private void crearAsignacion() {

        AsignacionDto asignacionDto = construirAsignacionDto();

        if(datosValidos(asignacionDto)){
            if(asignacionControllerService.agregarAsignacion(asignacionDto)){
                listaAsignacionesDto.add(asignacionDto);
                mostrarMensaje("Notificación asignación", "Asignación creado", "La asignación se ha creado con éxito", Alert.AlertType.INFORMATION);
                registrarAcciones("Asignación creado",1, "Creación de un asignación, acción realizada por " + universidad.nombreProperties());
                limpiarCamposAsignaciones();

            }else{
                mostrarMensaje("Notificación asignación", "Asignación no creado", "La asignación no se ha creado", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación asignación", "Asignación no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    private void eliminarAsignacion() {
        boolean asignacionEliminado = false;
        if(asignacionSeleccionada != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de eliminar al asignación?")){
                asignacionEliminado = asignacionControllerService.eliminarAsignacion(asignacionSeleccionada.codigo());
                if(asignacionEliminado){
                    listaAsignacionesDto.remove(asignacionSeleccionada);
                    asignacionSeleccionada = null;
                    tableAsignaciones.getSelectionModel().clearSelection();
                    limpiarCamposAsignaciones();
                    registrarAcciones("Asignación eliminado",1, "Asignación eliminado, acción realizada por " + universidad.nombreProperties());
                    mostrarMensaje("Notificación asignación", "Asignación eliminado", "La asignación se ha eliminado con éxito.", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación asignación", "Asignación no eliminado", "La asignación no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación asignación", "Asignación no seleccionado", "Seleccionado una asignación de la lista", Alert.AlertType.WARNING);
        }
    }

    private void actualizarAsignacion() {
        boolean asignacionActualizado = false;

        String codigo = asignacionSeleccionada.codigo();
        AsignacionDto asignacionDto = construirAsignacionDto();

        if(asignacionSeleccionada != null){

            if(datosValidos(asignacionSeleccionada)){
                asignacionActualizado = asignacionControllerService.actualizarAsignacion(codigo, asignacionDto);
                if(asignacionActualizado){
                    listaAsignacionesDto.remove(asignacionSeleccionada);
                    listaAsignacionesDto.add(asignacionDto);
                    tableAsignaciones.refresh();
                    mostrarMensaje("Notificación asignacion", "Asignacion actualizado", "El asignacion se ha actualizado con éxito.", Alert.AlertType.INFORMATION);
                    limpiarCamposAsignaciones();
                    registrarAcciones("Asignacion actualizado",1, "Asignacion actualizado, acción realizada por " + universidad.nombreProperties());
                }else{
                    mostrarMensaje("Notificación asignacion", "Asignacion no actualizado", "El asignacion no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    registrarAcciones("Asignacion no actualizado",1, "Actualizar asignacion");
                }
            }else{
                mostrarMensaje("Notificación asignacion", "Asignacion no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }
    }

    private void buscarAsignaciones(String codigo, String codigoMateria, String materia, String codigoProfesor, String profesor) {

        Predicate<AsignacionDto> predicado = AsignacionUtil.buscarPorTodo(codigo, codigoMateria, materia, codigoProfesor, profesor);
        ObservableList<AsignacionDto> asignacionesFiltradas = listaAsignacionesDto.filtered(predicado);
        tableAsignaciones.setItems(asignacionesFiltradas);
    }

    private void cancelarBusqueda(){
        limpiarCamposAsignaciones();
        tableAsignaciones.getSelectionModel().clearSelection();
        tableAsignaciones.setItems(listaAsignacionesDto);
        listenerSelection();
    }

    private AsignacionDto construirAsignacionDto() {
        String codigo = txfCodigo.getText();
        MateriaDto materiaSeleccionada = cmbMateria.getValue();
        ProfesorDto profesorSeleccionado = cmbProfesor.getValue();

        return new AsignacionDto(codigo, materiaSeleccionada, profesorSeleccionado);
    }


    private void limpiarCamposAsignaciones() {
        txfCodigo.setText("");
        txfCodigoMateria.setText("");
        cmbMateria.setValue(null);
        txfCodigoProfesor.setText("");
        cmbProfesor.setValue(null);

    }

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        asignacionControllerService.registrarAcciones(mensaje, nivel, accion);
    }

    public void mostrarMateria(){
        listaMaterias.add(materiaDto);
        cmbMateria.setItems(listaMaterias);
    }

    public void mostrarProfesor(){
        listProfesores.add(profesorDto);
        cmbProfesor.setItems(listProfesores);
    }

    private void mostrarInformacionAsignacion(AsignacionDto asignacionSeleccionada) {
        if(asignacionSeleccionada != null){
            txfCodigo.setText(asignacionSeleccionada.codigo());
            txfCodigoMateria.setText(asignacionSeleccionada.codigo());
            cmbMateria.setValue(asignacionSeleccionada.materiaAsociada());
            txfCodigoProfesor.setText(asignacionSeleccionada.codigo());
            cmbProfesor.setValue(asignacionSeleccionada.profesorAsociado());

        }
    }

    public ObservableList<MateriaDto> getListaMaterias() {
        listaMaterias.addAll(asignacionControllerService.obtenerMaterias());
        return listaMaterias;
    }

    public ObservableList<ProfesorDto> getListaProfesores() {
        listProfesores.addAll(asignacionControllerService.obtenerProfesores());
        return listProfesores;
    }

    private boolean datosValidos(AsignacionDto asignacionDto) {
        String mensaje = "";
        if(asignacionDto.codigo() == null || asignacionDto.codigo().equals(""))
            mensaje += "El código de la asignación es invalido \n" ;
        if(asignacionDto.getMateriaDto().codigo() == null || asignacionDto.getMateriaDto().codigo() .equals(""))
            mensaje += "El código de la materia es invalido \n" ;
        if(asignacionDto.getMateriaDto().nombre() == null || asignacionDto.getMateriaDto().nombre() .equals(""))
            mensaje += "El nombre de la materia es invalido \n" ;
        if(asignacionDto.getProfesorDto().codigo() == null || asignacionDto.getProfesorDto().codigo() .equals(""))
            mensaje += "El código del profesor es invalido \n" ;
        if(asignacionDto.getProfesorDto().toString() .equals(""))
            mensaje += "El nombre del profesor es invalidoo \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación asignación", "Asignación no creado", mensaje, Alert.AlertType.ERROR);
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
