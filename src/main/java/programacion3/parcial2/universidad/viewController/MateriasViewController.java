package programacion3.parcial2.universidad.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import programacion3.parcial2.universidad.controller.MateriaController;
import programacion3.parcial2.universidad.controller.ProfesorController;
import programacion3.parcial2.universidad.enumm.Sexo;
import programacion3.parcial2.universidad.enumm.TipoMateria;
import programacion3.parcial2.universidad.mapping.dto.MateriaDto;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;
import programacion3.parcial2.universidad.model.Universidad;
import programacion3.parcial2.universidad.util.MateriaUtil;
import programacion3.parcial2.universidad.util.ProfesorUtil;

import java.util.Optional;
import java.util.function.Predicate;

public class MateriasViewController {

    MateriaController materiaControllerService;
    Universidad universidad;
    ObservableList<MateriaDto> listaMateriasDto = FXCollections.observableArrayList();
    ObservableList<String> listaTipoMateria = FXCollections.observableArrayList();
    MateriaDto materiaSeleccionado;

    @FXML
    private ComboBox<String> cmbTipoMateria;

    @FXML
    private TableColumn<MateriaDto, String> colCodigo;

    @FXML
    private TableColumn<MateriaDto, String> colIntensidad;

    @FXML
    private TableColumn<MateriaDto, String> colNombre;

    @FXML
    private TableColumn<MateriaDto, String> colTipoMateria;

    @FXML
    private TableView<MateriaDto> tableMaterias;

    @FXML
    private TextField txfCodigo;

    @FXML
    private TextField txfIntensidad;

    @FXML
    private TextField txfNombre;

    @FXML
    void actualizarMateria(ActionEvent event) {
        actualizarMateria();
    }

    @FXML
    void agregarMateria(ActionEvent event) {
        crearMateria();
    }

    @FXML
    void buscarMateria(ActionEvent event) {
        String codigo = txfCodigo.getText();
        String nombre = txfNombre.getText();
        String intensidad = txfIntensidad.getText();
        String tipoMateria = cmbTipoMateria.getValue();

        buscarMaterias(codigo, nombre, intensidad, tipoMateria);
    }

    @FXML
    void cancelarFiltro(ActionEvent event) {
        cancelarBusqueda();
    }

    @FXML
    void eliminarMateria(ActionEvent event) {
        eliminarMateria();
    }

    @FXML
    void initialize() {
        materiaControllerService = new MateriaController();
        universidad = new Universidad();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerMaterias();
        mostrarTipoMaterias();
        tableMaterias.getItems().clear();
        tableMaterias.setItems(listaMateriasDto);
        listenerSelection();
    }

    private void initDataBinding() {
        colCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().codigo()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        colIntensidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().intensidad()));
        colTipoMateria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().tipoMateria()));
    }

    private void listenerSelection() {
        tableMaterias.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            materiaSeleccionado = newSelection;
            mostrarInformacionMateria(materiaSeleccionado);
        });
    }

    public void mostrarTipoMaterias(){
        listaTipoMateria.add(String.valueOf(TipoMateria.Práctica));
        listaTipoMateria.add(String.valueOf(TipoMateria.Teórica));
        listaTipoMateria.add(String.valueOf(TipoMateria.Teórica_Práctica));
        cmbTipoMateria.setItems(listaTipoMateria);
    }

    private void crearMateria() {

        MateriaDto materiaDto = construirMateriaDto();

        if(datosValidos(materiaDto)){
            if(materiaControllerService.agregarMateria(materiaDto)){
                listaMateriasDto.add(materiaDto);
                mostrarMensaje("Notificación materia", "Materia creado", "El materia se ha creado con éxito", Alert.AlertType.INFORMATION);
                registrarAcciones("Materia creado",1, "Creación de un materia, acción realizada por " + universidad.nombreProperties());
                limpiarCamposMateria();

            }else{
                mostrarMensaje("Notificación materia", "Materia no creado", "El materia no se ha creado", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación materia", "Materia no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    private void eliminarMateria() {
        boolean materiaEliminado = false;
        if(materiaSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de eliminar la materia?")){
                materiaEliminado = materiaControllerService.eliminarMateria(materiaSeleccionado.codigo());
                if(materiaEliminado){
                    listaMateriasDto.remove(materiaSeleccionado);
                    materiaSeleccionado = null;
                    tableMaterias.getSelectionModel().clearSelection();
                    limpiarCamposMateria();
                    registrarAcciones("Materia eliminado",1, "Materia eliminado, acción realizada por " + universidad.nombreProperties());
                    mostrarMensaje("Notificación materia", "Materia eliminado", "El materia se ha eliminado con éxito.", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación materia", "Materia no eliminado", "El materia no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación materia", "Materia no seleccionado", "Seleccionado un materia de la lista", Alert.AlertType.WARNING);
        }
    }

    private void actualizarMateria() {
        boolean materiaActualizado = false;

        String codigo = materiaSeleccionado.codigo();
        MateriaDto materiaDto = construirMateriaDto();

        if(materiaSeleccionado != null){

            if(datosValidos(materiaSeleccionado)){
                materiaActualizado = materiaControllerService.actualizarMateria(codigo, materiaDto);
                if(materiaActualizado){
                    listaMateriasDto.remove(materiaSeleccionado);
                    listaMateriasDto.add(materiaDto);
                    tableMaterias.refresh();
                    mostrarMensaje("Notificación materia", "Materia actualizado", "El materia se ha actualizado con éxito.", Alert.AlertType.INFORMATION);
                    limpiarCamposMateria();
                    registrarAcciones("Materia actualizado",1, "Materia actualizado, acción realizada por " + universidad.nombreProperties());
                }else{
                    mostrarMensaje("Notificación materia", "Materia no actualizado", "El materia no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    registrarAcciones("Materia no actualizado",1, "Actualizar materia");
                }
            }else{
                mostrarMensaje("Notificación materia", "Materia no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }
    }

    private void buscarMaterias(String codigo, String nombre, String intensidad, String tipoMateria) {

        Predicate<MateriaDto> predicado = MateriaUtil.buscarPorTodo(codigo, nombre, intensidad, tipoMateria);
        ObservableList<MateriaDto> materiasFiltrados = listaMateriasDto.filtered(predicado);
        tableMaterias.setItems(materiasFiltrados);
    }

    private void cancelarBusqueda(){
        limpiarCamposMateria();
        tableMaterias.getSelectionModel().clearSelection();
        tableMaterias.setItems(listaMateriasDto);
        listenerSelection();
    }

    private void obtenerMaterias() {
        listaMateriasDto.addAll(materiaControllerService.obtenerMaterias());
    }

    private void mostrarInformacionMateria(MateriaDto materiaSeleccionado) {
        if(materiaSeleccionado != null){
            txfCodigo.setText(materiaSeleccionado.codigo());
            txfNombre.setText(materiaSeleccionado.nombre());
            txfIntensidad.setText(materiaSeleccionado.intensidad());
            cmbTipoMateria.setValue(materiaSeleccionado.tipoMateria());

        }
    }

    private MateriaDto construirMateriaDto() {
        return new MateriaDto(
                txfCodigo.getText(),
                txfNombre.getText(),
                txfIntensidad.getText(),
                cmbTipoMateria.getValue()

        );
    }

    private void limpiarCamposMateria() {
        txfCodigo.setText("");
        txfNombre.setText("");
        txfIntensidad.setText("");
        cmbTipoMateria.setValue(null);

    }

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        materiaControllerService.registrarAcciones(mensaje, nivel, accion);
    }

    private boolean datosValidos(MateriaDto materiaDto) {
        String mensaje = "";
        if(materiaDto.codigo() == null || materiaDto.codigo().equals(""))
            mensaje += "El código de la materia es invalido \n" ;
        if(materiaDto.nombre() == null || materiaDto.nombre() .equals(""))
            mensaje += "El nombre de la materia es invalido \n" ;
        if(materiaDto.intensidad() == null || materiaDto.intensidad() .equals(""))
            mensaje += "la intensidad de la materia es invalido \n" ;
        if(materiaDto.tipoMateria() == null || materiaDto.tipoMateria() .equals(""))
            mensaje += "El tipo de materia es invalido \n" ;

        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación Materia", "Materia no creado", mensaje, Alert.AlertType.ERROR);
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
