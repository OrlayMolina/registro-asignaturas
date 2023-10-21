package programacion3.parcial2.universidad.mapping.dto;

public record CursoDto(

        String codigo,
        String codigoMateria,
        String nombreMateria,

        String nombreProfesor,
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
