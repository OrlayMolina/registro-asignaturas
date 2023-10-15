package programacion3.parcial2.universidad.util;

import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;

import java.util.function.Predicate;

public class EstudianteUtil {

    public static Predicate<EstudianteDto> buscarPorCodigo(String codigo){
        return estudianteDto -> estudianteDto.codigo().contains(codigo);
    }

    public static Predicate<EstudianteDto> buscarPorNombres(String nombres){
        return estudianteDto -> estudianteDto.nombres().contains(nombres);
    }

    public static Predicate<EstudianteDto> buscarPorApellidos(String apellidos){
        return estudianteDto -> estudianteDto.apellidos().contains(apellidos);
    }

    public static Predicate<EstudianteDto> buscarPorSexo(String sexo){
        return estudianteDto -> estudianteDto.sexo().contains(sexo);
    }

    public static Predicate<EstudianteDto> buscarPorEdad(String edad){
        return estudianteDto -> estudianteDto.edad().contains(edad);
    }

    public static Predicate<EstudianteDto> buscarPorCorreo(String correo){
        return estudianteDto -> estudianteDto.correo().contains(correo);
    }

    public static Predicate<EstudianteDto> buscarPorTelefono(String telefono){
        return estudianteDto -> estudianteDto.telefono().contains(telefono);
    }

    public static Predicate<EstudianteDto> buscarPorTodo(String codigo, String nombres, String apellidos, String sexo,
                                                         String edad, String correo, String telefono) {

        Predicate<EstudianteDto> predicado = estudianteDto -> true;

        if( codigo != null && !codigo.isEmpty() ){
            predicado = predicado.and(buscarPorCodigo(codigo));
        }
        if( nombres != null && !nombres.isEmpty() ){
            predicado = predicado.and(buscarPorNombres(nombres));
        }
        if( apellidos != null && !apellidos.isEmpty() ){
            predicado = predicado.and(buscarPorApellidos(apellidos));
        }
        if( sexo != null && !sexo.isEmpty() ){
            predicado = predicado.and(buscarPorSexo(sexo));
        }
        if( edad != null && !edad.isEmpty() ){
            predicado = predicado.and(buscarPorEdad(edad));
        }
        if( correo != null && !correo.isEmpty() ){
            predicado = predicado.and(buscarPorCorreo(correo));
        }
        if( telefono != null && !telefono.isEmpty() ){
            predicado = predicado.and(buscarPorTelefono(telefono));
        }

        return predicado;
    }
}
