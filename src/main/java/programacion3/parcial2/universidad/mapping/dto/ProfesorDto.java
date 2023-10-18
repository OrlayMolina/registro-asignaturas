package programacion3.parcial2.universidad.mapping.dto;

public record ProfesorDto(
        String codigo,
        String nombres,
        String apellidos,
        String sexo,
        String edad,
        String correo,
        String telefono,
        String programa,
        String profesion) {

    @Override
    public String toString() {
        return nombres + " " + apellidos;
    }
}
