package programacion3.parcial2.universidad.controller;

import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;
import programacion3.parcial2.universidad.mapping.dto.MateriaDto;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;

import java.util.List;

public class CursoController {

    ModelFactoryController modelFactoryController;

    public CursoController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<MateriaDto> obtenerMaterias(){
        return modelFactoryController.obtenerMaterias();
    }

    public List<ProfesorDto> obtenerProfesores(){
        return modelFactoryController.obtenerProfesores();
    }

    public List<EstudianteDto> obtenerEstudiantes(){
        return modelFactoryController.obtenerEstudiantes();
    }
}
