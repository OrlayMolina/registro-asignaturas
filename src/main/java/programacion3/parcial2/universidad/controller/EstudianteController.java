package programacion3.parcial2.universidad.controller;

import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;

import java.util.List;

public class EstudianteController {

    ModelFactoryController modelFactoryController;

    public EstudianteController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public boolean agregarEstudiante(EstudianteDto estudianteDto) {
        return modelFactoryController.agregarEstudiante(estudianteDto);
    }

    public List<EstudianteDto> obtenerEstudiantes() {
        return modelFactoryController.obtenerEstudiantes();
    }
}
