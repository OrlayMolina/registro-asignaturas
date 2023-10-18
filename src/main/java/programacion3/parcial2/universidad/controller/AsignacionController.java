package programacion3.parcial2.universidad.controller;

import programacion3.parcial2.universidad.mapping.dto.MateriaDto;
import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;

import java.util.ArrayList;
import java.util.List;

public class AsignacionController {

    ModelFactoryController modelFactoryController;

    public AsignacionController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<MateriaDto> obtenerMaterias(){
        return modelFactoryController.obtenerMaterias();
    }

    public List<ProfesorDto> obtenerProfesores(){
        return modelFactoryController.obtenerProfesores();
    }
}
