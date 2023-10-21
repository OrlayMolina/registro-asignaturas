package programacion3.parcial2.universidad.mapping.dto;

public record CursoDto(
        String codigoMateria,
        String nombreMateria,
        String codigoEstudiante,
        String estudiante) {

    public MateriaDto getMateriaDto(){
        return new MateriaDto(codigoMateria, nombreMateria,"","");
    }

    public EstudianteDto getEstudianteDto(){
        return new EstudianteDto(codigoEstudiante, estudiante,"","", "", "", "");
    }

    public ProfesorDto getProfesorDto(){
        return new ProfesorDto("", "","","","","","","","");
    }

    @Override
    public String toString() {
        return getEstudianteDto().toString();
    }
}
