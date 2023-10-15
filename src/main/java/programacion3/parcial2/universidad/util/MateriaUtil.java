package programacion3.parcial2.universidad.util;

import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;
import programacion3.parcial2.universidad.mapping.dto.MateriaDto;

import java.util.function.Predicate;

public class MateriaUtil {

    public static Predicate<MateriaDto> buscarPorCodigo(String codigo){
        return materiaDto -> materiaDto.codigo().contains(codigo);
    }

    public static Predicate<MateriaDto> buscarPorNombre(String nombre){
        return materiaDto -> materiaDto.nombre().contains(nombre);
    }

    public static Predicate<MateriaDto> buscarPorIntensidad(String intensidad){
        return materiaDto -> materiaDto.intensidad().contains(intensidad);
    }

    public static Predicate<MateriaDto> buscarPorTipoMateria(String tipoMateria){
        return materiaDto -> materiaDto.tipoMateria().contains(tipoMateria);
    }


    public static Predicate<MateriaDto> buscarPorTodo(String codigo, String nombre, String intensidad, String tipoMateria) {

        Predicate<MateriaDto> predicado = materiaDto -> true;

        if( codigo != null && !codigo.isEmpty() ){
            predicado = predicado.and(buscarPorCodigo(codigo));
        }
        if( nombre != null && !nombre.isEmpty() ){
            predicado = predicado.and(buscarPorNombre(nombre));
        }
        if( intensidad != null && !intensidad.isEmpty() ){
            predicado = predicado.and(buscarPorIntensidad(intensidad));
        }
        if( tipoMateria != null && !tipoMateria.isEmpty() ){
            predicado = predicado.and(buscarPorTipoMateria(tipoMateria));
        }


        return predicado;
    }
}
