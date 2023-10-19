package programacion3.parcial2.universidad.mapping.dto;

public record AsignacionDto(
        String codigo,
        String codigoMateria,
        String nombreMateria,
        String codigoProfesor,
        String profesor) {

    public MateriaDto getMateriaDto(){
        MateriaDto materiaDto = new MateriaDto(codigoMateria, nombreMateria,"","");
        return materiaDto;
    }

    public ProfesorDto getProfesorDto(){
        ProfesorDto profesorDto = new ProfesorDto(codigoProfesor, profesor,"","","","","","","");
        return profesorDto;
    }

    @Override
    public String toString() {
        return profesor;
    }
}
