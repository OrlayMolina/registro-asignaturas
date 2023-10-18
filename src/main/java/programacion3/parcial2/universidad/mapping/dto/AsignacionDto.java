package programacion3.parcial2.universidad.mapping.dto;

public record AsignacionDto(
        String codigo,
        MateriaDto materiaAsociada,
        ProfesorDto profesorAsociado) {

    public MateriaDto getMateriaDto() {
        return materiaAsociada;
    }

    public ProfesorDto getProfesorDto() {
        return profesorAsociado;
    }
}
