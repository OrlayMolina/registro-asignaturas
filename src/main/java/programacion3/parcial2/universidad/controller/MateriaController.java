package programacion3.parcial2.universidad.controller;

import programacion3.parcial2.universidad.mapping.dto.MateriaDto;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;

import java.util.List;

public class MateriaController {

    ModelFactoryController modelFactoryController;

    public MateriaController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }

    public boolean agregarMateria(MateriaDto materiaDto) {
        return modelFactoryController.agregarMateria(materiaDto);
    }

    public boolean eliminarMateria(String codigo) {
        return modelFactoryController.eliminarMateria(codigo);
    }

    public boolean actualizarMateria(String codigo, MateriaDto materiaDto) {
        return modelFactoryController.actualizarMateria(codigo, materiaDto);
    }

    public List<MateriaDto> obtenerMaterias() {
        return modelFactoryController.obtenerMaterias();
    }
}
