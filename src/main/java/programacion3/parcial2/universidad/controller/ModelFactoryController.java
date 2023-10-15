package programacion3.parcial2.universidad.controller;

import programacion3.parcial2.universidad.exception.EstudianteException;
import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;
import programacion3.parcial2.universidad.mapping.mappers.UniversidadMapper;
import programacion3.parcial2.universidad.model.Estudiante;
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
        } catch (Exception e) {
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

    public List<EstudianteDto> obtenerEstudiantes() {
        return  mapper.getEstudianteDto(universidad.getListaEstudiantes());
    }

    public List<ProfesorDto> obtenerProfesores() {
        return  mapper.getProfesorDto(universidad.getListaProfesores());
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

}
