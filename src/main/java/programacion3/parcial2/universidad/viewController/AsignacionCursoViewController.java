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
import programacion3.parcial2.universidad.controller.CursoController;
import programacion3.parcial2.universidad.mapping.dto.*;
import programacion3.parcial2.universidad.model.Universidad;

public class AsignacionCursoViewController {

    CursoController cursoControllerService;
    Universidad universidad;
    MateriaDto materiaDto;
    ProfesorDto profesorDto;
    EstudianteDto estudianteDto;
    ObservableList<EstudianteDto> listaEstudiantes = FXCollections.observableArrayList();
    ObservableList<CursoDto> listaCursoDto = FXCollections.observableArrayList();
    ObservableList<MateriaDto> listaMaterias = FXCollections.observableArrayList();
    ObservableList<ProfesorDto> listProfesores = FXCollections.observableArrayList();
    CursoDto cursoSeleccionado;

    @FXML
    private ComboBox<EstudianteDto> cmbEstudiante;

    @FXML
    private ComboBox<MateriaDto> cmbMateria;

    @FXML
    private TableColumn<CursoDto, String> colCodigoEstudiante;

    @FXML
    private TableColumn<CursoDto, String> colCodigoMateria;

    @FXML
    private TableColumn<CursoDto, String> colMateria;

    @FXML
    private TableColumn<CursoDto, String> colNombreEstudiante;

    @FXML
    private TableColumn<CursoDto, String> colProfesor;

    @FXML
    private TableView<CursoDto> tableCurso;

    @FXML
    private TextField txfCodigoEstudiante;

    @FXML
    private TextField txfCodigoMateria;

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
    void initialize() {
        cursoControllerService = new CursoController();
        universidad = new Universidad();
        initView();
    }

    private void initView() {
        initDataBinding();
        mostrarMateria();
        mostrarEstudiante();
        getListaMaterias();
        getListaEstudiantes();
        tableCurso.getItems().clear();
        tableCurso.setItems(listaCursoDto);
        listenerSelection();
    }

    private void initDataBinding() {
        colCodigoMateria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMateriaDto().codigo()));
        colMateria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMateriaDto().nombre()));
        colProfesor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProfesorDto().nombres()));
        colCodigoEstudiante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstudianteDto().codigo()));
        colNombreEstudiante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstudianteDto().nombres()));

    }

    private void listenerSelection() {
        tableCurso.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            cursoSeleccionado = newSelection;
            mostrarInformacionCurso(cursoSeleccionado);
        });
    }

    public void mostrarMateria(){
        listaMaterias.add(materiaDto);
        cmbMateria.setItems(listaMaterias);
    }

    public void mostrarEstudiante(){
        listaEstudiantes.add(estudianteDto);
        cmbEstudiante.setItems(listaEstudiantes);
    }

    private void mostrarInformacionCurso(CursoDto cursoSeleccionado) {
        if(cursoSeleccionado != null){
            txfCodigoMateria.setText(cursoSeleccionado.getMateriaDto().codigo());
            cmbMateria.setValue(cursoSeleccionado.getMateriaDto());
            txfCodigoEstudiante.setText(cursoSeleccionado.getEstudianteDto().codigo());
            cmbEstudiante.setValue(cursoSeleccionado.getEstudianteDto());

        }
    }

    public ObservableList<MateriaDto> getListaMaterias() {
        listaMaterias.addAll(cursoControllerService.obtenerMaterias());
        return listaMaterias;
    }

    public ObservableList<EstudianteDto> getListaEstudiantes() {
        listaEstudiantes.addAll(cursoControllerService.obtenerEstudiantes());
        return listaEstudiantes;
    }


}
