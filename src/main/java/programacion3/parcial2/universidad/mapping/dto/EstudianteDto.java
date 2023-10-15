package programacion3.parcial2.universidad.mapping.dto;

import programacion3.parcial2.universidad.enumm.Sexo;

public record EstudianteDto(
        String codigo,
        String nombres,
        String apellidos,
        String sexo,
        String edad,
        String correo,
        String telefono) {
}
