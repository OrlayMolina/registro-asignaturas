package programacion3.parcial2.universidad.util;

import programacion3.parcial2.universidad.mapping.dto.AsignacionDto;
import programacion3.parcial2.universidad.mapping.dto.CursoDto;

import java.util.function.Predicate;

public class CursoUtil {

    public static Predicate<CursoDto> buscarPorCodigo(String codigo) {
        return cursoDto -> cursoDto.codigo().contains(codigo);
    }

    public static Predicate<CursoDto> buscarPorCodigoMateria(String codigoMateria) {
        return cursoDto -> cursoDto.getMateriaDto().codigo().contains(codigoMateria);
    }

    public static Predicate<CursoDto> buscarPorMateria(String materia) {
        return cursoDto -> cursoDto.getMateriaDto().nombre().contains(materia);
    }

    public static Predicate<CursoDto> buscarPorCodigoEstudiante(String codigoEstudiante) {
        return cursoDto -> cursoDto.getEstudianteDto().codigo().contains(codigoEstudiante);
    }

    public static Predicate<CursoDto> buscarPorEstudiante(String estudiante) {
        return cursoDto -> cursoDto.getEstudianteDto().toString().contains(estudiante);
    }

    public static Predicate<CursoDto> buscarPorTodo(String codigo, String codigoMateria, String materia, String codigoEstudiante, String estudiante) {
        Predicate<CursoDto> predicado = cursoDto -> true;

        if (codigo != null && !codigo.isEmpty()) {
            predicado = predicado.and(buscarPorCodigo(codigo));
        }
        if (codigoMateria != null && !codigoMateria.isEmpty()) {
            predicado = predicado.and(buscarPorCodigoMateria(codigoMateria));
        }
        if (materia != null && !materia.isEmpty() && !materia.equals("null")) {
            predicado = predicado.and(buscarPorMateria(materia));
        }
        if (codigoEstudiante != null && !codigoEstudiante.isEmpty()) {
            predicado = predicado.and(buscarPorCodigoEstudiante(codigoEstudiante));
        }
        if (estudiante != null && !estudiante.isEmpty() && !estudiante.equals("null")) {
            predicado = predicado.and(buscarPorEstudiante(estudiante));
        }

        return predicado;
    }
}
