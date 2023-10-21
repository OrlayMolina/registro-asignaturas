package programacion3.parcial2.universidad.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import programacion3.parcial2.universidad.controller.CursoController;
import programacion3.parcial2.universidad.mapping.dto.*;
import programacion3.parcial2.universidad.model.Universidad;
import programacion3.parcial2.universidad.util.AsignacionUtil;
import programacion3.parcial2.universidad.util.CursoUtil;

import java.util.Optional;
import java.util.function.Predicate;

public class CursoViewController {

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
    private TableColumn<CursoDto, String> colCodigo;

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
    private TextField txfCodigo;

    @FXML
    private TextField txfCodigoEstudiante;

    @FXML
    private TextField txfCodigoMateria;

    @FXML
    void actualizarAsignacion(ActionEvent event) {
        actualizarCurso();
    }

    @FXML
    void agregarAsignacion(ActionEvent event) {
        crearCurso();
    }

    @FXML
    void buscarAsignacion(ActionEvent event) {
        String codigo = txfCodigo.getText();
        String codigoMateria = txfCodigoMateria.getText();
        String materia = String.valueOf(cmbMateria.getValue());
        String codigoEstudiante = txfCodigoEstudiante.getText();
        String estudiante = String.valueOf(cmbEstudiante.getValue());

        buscarCursos(codigo, codigoMateria, materia, codigoEstudiante, estudiante);
    }

    @FXML
    void cancelarFiltro(ActionEvent event) {
        cancelarBusqueda();
    }

    @FXML
    void eliminarAsignacion(ActionEvent event) {
        eliminarCurso();
    }

    @FXML
    void llenarCodigoEstudiante(ActionEvent event) {
        llenarCodigoEstudiante();
    }

    @FXML
    void llenarCodigoMateria(ActionEvent event) {
        llenarCodigoMateria();
    }


    @FXML
    void initialize() {
        cursoControllerService = new CursoController();
        universidad = new Universidad();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerCursos();
        mostrarMateria();
        mostrarEstudiante();
        getListaMaterias();
        getListaEstudiantes();
        tableCurso.getItems().clear();
        tableCurso.setItems(listaCursoDto);
        listenerSelection();
    }

    private void initDataBinding() {
        colCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().codigo()));
        colCodigoMateria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMateriaDto().codigo()));
        colMateria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMateriaDto().nombre()));
        colProfesor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProfesorDto().toString()));
        colCodigoEstudiante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstudianteDto().codigo()));
        colNombreEstudiante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstudianteDto().toString()));

    }

    private void listenerSelection() {
        tableCurso.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            cursoSeleccionado = newSelection;
            mostrarInformacionCurso(cursoSeleccionado);
        });
    }

    private void crearCurso() {

        CursoDto cursoDto = construirCursoDto();

        if(datosValidos(cursoDto)){
            if(cursoControllerService.agregarCurso(cursoDto)){
                listaCursoDto.add(cursoDto);
                mostrarMensaje("Notificación curso", "Curso creado", "El curso se ha creado con éxito", Alert.AlertType.INFORMATION);
                registrarAcciones("Curso creado",1, "Creación de un curso, acción realizada por " + universidad.nombreProperties());
                limpiarCamposAsignaciones();

            }else{
                mostrarMensaje("Notificación curso", "Curso no creado", "El curso no se ha creado", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación curso", "Curso no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    private void eliminarCurso() {
        boolean asignacionEliminado = false;
        if(cursoSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de eliminar el curso?")){
                asignacionEliminado = cursoControllerService.eliminarCurso(cursoSeleccionado.codigo());
                if(asignacionEliminado){
                    listaCursoDto.remove(cursoSeleccionado);
                    cursoSeleccionado = null;
                    tableCurso.getSelectionModel().clearSelection();
                    limpiarCamposAsignaciones();
                    registrarAcciones("Curso eliminado",1, "Curso eliminado, acción realizada por " + universidad.nombreProperties());
                    mostrarMensaje("Notificación curso", "Curso eliminado", "El curso se ha eliminado con éxito.", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación curso", "Curso no eliminado", "El curso no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación curso", "Curso no seleccionado", "Seleccionado un curso de la lista", Alert.AlertType.WARNING);
        }
    }

    private void actualizarCurso() {
        boolean asignacionActualizado = false;

        String codigo = cursoSeleccionado.codigo();
        CursoDto cursoDto = construirCursoDto();

        if(cursoSeleccionado != null){

            if(datosValidos(cursoSeleccionado)){
                asignacionActualizado = cursoControllerService.actualizarCurso(codigo, cursoDto);
                if(asignacionActualizado){
                    listaCursoDto.remove(cursoSeleccionado);
                    listaCursoDto.add(cursoDto);
                    tableCurso.refresh();
                    mostrarMensaje("Notificación curso", "Curso actualizado", "El curso se ha actualizado con éxito.", Alert.AlertType.INFORMATION);
                    limpiarCamposAsignaciones();
                    registrarAcciones("Curso actualizado",1, "Curso actualizado, acción realizada por " + universidad.nombreProperties());
                }else{
                    mostrarMensaje("Notificación curso", "Curso no actualizado", "El curso no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    registrarAcciones("Curso no actualizado",1, "Actualizar curso");
                }
            }else{
                mostrarMensaje("Notificación curso", "Curso no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }
    }

    private void buscarCursos(String codigo, String codigoMateria, String materia, String codigoEstudiante, String estudiante) {

        Predicate<CursoDto> predicado = CursoUtil.buscarPorTodo(codigo, codigoMateria, materia, codigoEstudiante, estudiante);
        ObservableList<CursoDto> cursosFiltradas = listaCursoDto.filtered(predicado);
        tableCurso.setItems(cursosFiltradas);

    }

    private void cancelarBusqueda(){
        limpiarCamposAsignaciones();
        tableCurso.getSelectionModel().clearSelection();
        tableCurso.setItems(listaCursoDto);
        listenerSelection();
    }

    private void llenarCodigoMateria(){
        MateriaDto materiaSeleccionado = cmbMateria.getSelectionModel().getSelectedItem();
        if (materiaSeleccionado != null) {
            txfCodigoMateria.setText(materiaSeleccionado.codigo());
        }
    }

    private void llenarCodigoEstudiante(){
        EstudianteDto estudianteSeleccionado = cmbEstudiante.getSelectionModel().getSelectedItem();
        if (estudianteSeleccionado != null) {
            txfCodigoEstudiante.setText(estudianteSeleccionado.codigo());
        }
    }

    private void obtenerCursos() {
        listaCursoDto.addAll(cursoControllerService.obtenerCursos());
    }

    private CursoDto construirCursoDto() {
        String codigo = txfCodigo.getText();
        String codigoMateria = txfCodigoMateria.getText();
        String materia = String.valueOf(cmbMateria.getValue());
        String codigoEstudiante = txfCodigoEstudiante.getText();
        String estudiante = String.valueOf(cmbEstudiante.getValue());

        return new CursoDto(codigo, codigoMateria, materia, codigoEstudiante, estudiante);
    }


    private void limpiarCamposAsignaciones() {
        txfCodigoMateria.setText("");
        cmbMateria.setValue(null);
        txfCodigoEstudiante.setText("");
        cmbEstudiante.setValue(null);

    }

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        cursoControllerService.registrarAcciones(mensaje, nivel, accion);
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

    private boolean datosValidos(CursoDto cursoDto) {
        String mensaje = "";
        if(cursoDto.getMateriaDto().codigo() == null || cursoDto.getEstudianteDto().codigo().equals(""))
            mensaje += "El código de la materia es invalido \n" ;
        if(cursoDto.getMateriaDto().nombre() == null || cursoDto.getMateriaDto().nombre() .equals(""))
            mensaje += "El nombre de la materia es invalido \n" ;
        if(cursoDto.getEstudianteDto().codigo() == null || cursoDto.getEstudianteDto().codigo() .equals(""))
            mensaje += "El código del estudiante es invalido \n" ;
        cursoDto.getEstudianteDto();
        if(cursoDto.getEstudianteDto().toString() .equals(""))
            mensaje += "El nombre del estudiante es invalidoo \n" ;
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
