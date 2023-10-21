package programacion3.parcial2.universidad.controller;

import programacion3.parcial2.universidad.mapping.dto.*;

import java.util.List;

public class CursoController {

    ModelFactoryController modelFactoryController;

    public CursoController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }

    public boolean agregarCurso(CursoDto cursoDto) {
        return modelFactoryController.agregarCurso(cursoDto);
    }

    public boolean eliminarCurso(String codigo) {
        return modelFactoryController.eliminarCurso(codigo);
    }

    public boolean actualizarCurso(String codigo, CursoDto cursoDto) {
        return modelFactoryController.actualizarCurso(codigo, cursoDto);
    }

    public List<MateriaDto> obtenerMaterias(){
        return modelFactoryController.obtenerMaterias();
    }

    public List<CursoDto> obtenerCursos(){
        return modelFactoryController.obtenerCursos();
    }

    public List<EstudianteDto> obtenerEstudiantes(){
        return modelFactoryController.obtenerEstudiantes();
    }
}
