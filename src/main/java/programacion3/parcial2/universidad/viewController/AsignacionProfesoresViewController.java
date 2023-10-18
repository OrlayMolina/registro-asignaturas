package programacion3.parcial2.universidad.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import programacion3.parcial2.universidad.controller.AsignacionController;
import programacion3.parcial2.universidad.mapping.dto.AsignacionDto;
import programacion3.parcial2.universidad.mapping.dto.MateriaDto;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;
import programacion3.parcial2.universidad.model.Universidad;


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
        colProfesor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProfesorDto().nombres()));
    }

    private void listenerSelection() {
        tableAsignaciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            asignacionSeleccionada = newSelection;
            mostrarInformacionAsignacion(asignacionSeleccionada);
        });
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

}
