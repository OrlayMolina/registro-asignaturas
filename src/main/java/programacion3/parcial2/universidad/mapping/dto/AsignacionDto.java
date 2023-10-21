package programacion3.parcial2.universidad.mapping.dto;

public record AsignacionDto(
        String codigo,
        String codigoMateria,
        String nombreMateria,
        String codigoProfesor,
        String profesor) {

    public MateriaDto getMateriaDto(){
        return new MateriaDto(codigoMateria, nombreMateria,"","");
    }

    public ProfesorDto getProfesorDto(){
        return new ProfesorDto(codigoProfesor, profesor,"","","","","","","");
    }

    @Override
    public String toString() {
        return profesor;
    }

}
