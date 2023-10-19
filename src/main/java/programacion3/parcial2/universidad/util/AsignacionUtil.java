package programacion3.parcial2.universidad.util;

import programacion3.parcial2.universidad.mapping.dto.AsignacionDto;
import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;

import java.util.function.Predicate;

public class AsignacionUtil {

    public static Predicate<AsignacionDto> buscarPorCodigo(String codigo){
        return asignacionDto -> asignacionDto.codigo().contains(codigo);
    }

    public static Predicate<AsignacionDto> buscarPorCodigoMateria(String codigoMateria){
        return asignacionDto -> asignacionDto.getMateriaDto().codigo().contains(codigoMateria);
    }

    public static Predicate<AsignacionDto> buscarPorMateria(String materia){
        return asignacionDto -> asignacionDto.getMateriaDto().nombre().contains(materia);
    }

    public static Predicate<AsignacionDto> buscarPorCodigoProfesor(String codigoProfesor){
        return asignacionDto -> asignacionDto.getProfesorDto().codigo().contains(codigoProfesor);
    }

    public static Predicate<AsignacionDto> buscarPorProfesor(String profesor){
        return asignacionDto -> asignacionDto.getProfesorDto().toString().contains(profesor);
    }

    public static Predicate<AsignacionDto> buscarPorTodo(String codigo, String codigoMateria, String materia, String codigoProfesor,
                                                         String profesor) {

        Predicate<AsignacionDto> predicado = asignacionDto -> true;

        if( codigo != null && !codigo.isEmpty() ){
            predicado = predicado.and(buscarPorCodigo(codigo));
        }
        if( codigoMateria != null && !codigoMateria.isEmpty() ){
            predicado = predicado.and(buscarPorCodigoMateria(codigoMateria));
        }
        if( materia != null && !materia.isEmpty() ){
            predicado = predicado.and(buscarPorMateria(materia));
        }
        if( codigoProfesor != null && !codigoProfesor.isEmpty() ){
            predicado = predicado.and(buscarPorCodigoProfesor(codigoProfesor));
        }
        if( profesor != null && !profesor.isEmpty() ){
            predicado = predicado.and(buscarPorProfesor(profesor));
        }

        return predicado;
    }
}
