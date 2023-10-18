package programacion3.parcial2.universidad.mapping.dto;

public record MateriaDto(
        String codigo,
        String nombre,
        String intensidad,
        String tipoMateria) {

    public MateriaDto(String nombre) {
        this("", nombre, "", "");
    }

    @Override
    public String toString() {
        return nombre;
    }
}
