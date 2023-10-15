package programacion3.parcial2.universidad.controller;

import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;

import java.util.List;

public class ProfesorController {

    ModelFactoryController modelFactoryController;

    public ProfesorController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }

    public List<ProfesorDto> obtenerProfesores() {
        return modelFactoryController.obtenerProfesores();
    }

}
