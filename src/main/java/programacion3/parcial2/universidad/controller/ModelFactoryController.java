package programacion3.parcial2.universidad.controller;

import programacion3.parcial2.universidad.exception.EstudianteException;
import programacion3.parcial2.universidad.exception.MateriaException;
import programacion3.parcial2.universidad.exception.ProfesorException;
import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;
import programacion3.parcial2.universidad.mapping.dto.MateriaDto;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;
import programacion3.parcial2.universidad.mapping.mappers.UniversidadMapper;
import programacion3.parcial2.universidad.model.Estudiante;
import programacion3.parcial2.universidad.model.Materia;
import programacion3.parcial2.universidad.model.Profesor;
import programacion3.parcial2.universidad.model.Universidad;
import programacion3.parcial2.universidad.util.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController {
    private Universidad universidad;
    private UniversidadMapper mapper = UniversidadMapper.INSTANCE;

    private Persistencia persistencia = new Persistencia();

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController(){
        cargarDatosDesdeArchivos();
    }

    private void cargarDatosDesdeArchivos() {
        universidad = new Universidad();
        try {
            Persistencia.cargarDatosArchivos(universidad);
            Persistencia.cargarDatosProfesores(universidad);
            Persistencia.cargarDatosMaterias(universidad);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Universidad getUniversidad() {
        return universidad;
    }
    public boolean comprobarAcceso(String usuario, String password){
        return universidad.comprobarAcceso(usuario, password);
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return getUniversidad().getListaEstudiantes();
    }

    public ArrayList<Profesor> getListaProfesores() {
        return getUniversidad().getListaProfesores();
    }

    public ArrayList<Materia> getListaMaterias() {
        return getUniversidad().getListaMaterias();
    }

    public boolean agregarEstudiante(EstudianteDto estudianteDto) {
        try{
            if(!universidad.verificarEstudianteExistente(estudianteDto.codigo())) {
                Estudiante estudiante = mapper.estudianteDtoToEstudiante(estudianteDto);
                getUniversidad().agregarEstudiante(estudiante);
                Persistencia.guardarEstudiante(getListaEstudiantes());
            }
            return true;
        }catch (EstudianteException e){
            e.getMessage();
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean eliminarEstudiante(String codigo) {
        boolean flagExiste = false;
        try {
            flagExiste = getUniversidad().eliminarEstudiante(codigo);
            Persistencia.guardarEstudiante(getListaEstudiantes());
        } catch (EstudianteException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flagExiste;
    }

    public boolean actualizarEstudiante(String codigoActual, EstudianteDto estudianteDto) {
        try {
            Estudiante estudiante = mapper.estudianteDtoToEstudiante(estudianteDto);
            getUniversidad().actualizarEstudiante(codigoActual, estudiante);
            Persistencia.guardarEstudiante(getListaEstudiantes());
            return true;
        } catch (EstudianteException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean agregarProfesor(ProfesorDto profesorDto) {
        try{
            if(!universidad.verificarProfesorExistente(profesorDto.codigo())) {
                Profesor profesor = mapper.profesorDtoToProfesor(profesorDto);
                getUniversidad().agregarProfesor(profesor);
                Persistencia.guardarProfesor(getListaProfesores());
            }
            return true;
        }catch (ProfesorException e){
            e.getMessage();
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean eliminarProfesor(String codigo) {
        boolean flagExiste = false;
        try {
            flagExiste = getUniversidad().eliminarProfesor(codigo);
            Persistencia.guardarProfesor(getListaProfesores());
        } catch (ProfesorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return flagExiste;
    }

    public boolean actualizarProfesor(String codigoActual, ProfesorDto profesorDto) {
        try {
            Profesor profesor = mapper.profesorDtoToProfesor(profesorDto);
            getUniversidad().actualizarProfesor(codigoActual, profesor);
            Persistencia.guardarProfesor(getListaProfesores());
            return true;
        } catch (ProfesorException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean agregarMateria(MateriaDto materiaDto) {
        try{
            if(!universidad.verificarMateriaExistente(materiaDto.codigo())) {
                Materia materia = mapper.materiaDtoToMateria(materiaDto);
                getUniversidad().agregarMateria(materia);
                Persistencia.guardarMateria(getListaMaterias());
            }
            return true;
        }catch (MateriaException e){
            e.getMessage();
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean eliminarMateria(String codigo) {
        boolean flagExiste = false;
        try {
            flagExiste = getUniversidad().eliminarMateria(codigo);
            Persistencia.guardarMateria(getListaMaterias());
        } catch (MateriaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return flagExiste;
    }

    public boolean actualizarMateria(String codigoActual, MateriaDto materiaDto) {
        try {
            Materia materia = mapper.materiaDtoToMateria(materiaDto);
            getUniversidad().actualizarMateria(codigoActual, materia);
            Persistencia.guardarMateria(getListaMaterias());
            return true;
        } catch (MateriaException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<EstudianteDto> obtenerEstudiantes() {
        return  mapper.getEstudianteDto(universidad.getListaEstudiantes());
    }
    public List<ProfesorDto> obtenerProfesores() {
        return  mapper.getProfesorDto(universidad.getListaProfesores());
    }

    public List<MateriaDto> obtenerMaterias() {
        return  mapper.getMateriaDto(universidad.getListaMaterias());
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

}
