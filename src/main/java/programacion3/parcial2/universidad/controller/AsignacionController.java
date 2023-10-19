package programacion3.parcial2.universidad.controller;

import programacion3.parcial2.universidad.mapping.dto.AsignacionDto;
import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;
import programacion3.parcial2.universidad.mapping.dto.MateriaDto;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;

import java.util.ArrayList;
import java.util.List;

public class AsignacionController {

    ModelFactoryController modelFactoryController;

    public AsignacionController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }

    public boolean agregarAsignacion(AsignacionDto asignacionDto) {
        return modelFactoryController.agregarAsignacion(asignacionDto);
    }

    public boolean eliminarAsignacion(String codigo) {
        return modelFactoryController.eliminarAsignacion(codigo);
    }

    public boolean actualizarAsignacion(String codigo, AsignacionDto asignacionDto) {
        return modelFactoryController.actualizarAsignacion(codigo, asignacionDto);
    }

    public List<MateriaDto> obtenerMaterias(){
        return modelFactoryController.obtenerMaterias();
    }

    public List<ProfesorDto> obtenerProfesores(){
        return modelFactoryController.obtenerProfesores();
    }

    public List<AsignacionDto> obtenerAsignaciones() {
        return modelFactoryController.obtenerAsignaciones();
    }
}
