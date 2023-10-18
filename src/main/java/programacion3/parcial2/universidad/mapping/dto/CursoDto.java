package programacion3.parcial2.universidad.mapping.dto;

public record CursoDto(
        MateriaDto materiaAsociada,
        ProfesorDto profesorAsociado,
        EstudianteDto estudianteAsociado) {

    public MateriaDto getMateriaDto() {
        return materiaAsociada;
    }

    public ProfesorDto getProfesorDto() {
        return profesorAsociado;
    }

    public EstudianteDto getEstudianteDto() {
        return estudianteAsociado;
    }
}
