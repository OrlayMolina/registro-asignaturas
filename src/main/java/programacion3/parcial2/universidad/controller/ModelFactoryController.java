package programacion3.parcial2.universidad.controller;

import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;
import programacion3.parcial2.universidad.mapping.mappers.UniversidadMapper;
import programacion3.parcial2.universidad.model.Estudiante;
import programacion3.parcial2.universidad.model.Universidad;
import programacion3.parcial2.universidad.util.Persistencia;

import java.util.List;

public class ModelFactoryController {
    Universidad universidad = new Universidad();
    UniversidadMapper mapper = UniversidadMapper.INSTANCE;

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public Universidad getUniversidad() {
        return universidad;
    }
    public boolean comprobarAcceso(String usuario, String password){
        return universidad.comprobarAcceso(usuario, password);
    }

    public boolean agregarEstudiante(EstudianteDto estudianteDto) {
        try{
            if(!universidad.verificarEstudianteExistente(estudianteDto.codigo())) {
                Estudiante estudiante = mapper.estudianteDtoToEstudiante(estudianteDto);
                getUniversidad().agregarEstudiante(estudiante);
            }
            return true;
        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }

    public List<EstudianteDto> obtenerEstudiantes() {
        return  mapper.getEstudianteDto(universidad.getListaEstudiantes());
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }
}
