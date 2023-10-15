package programacion3.parcial2.universidad.util;

import programacion3.parcial2.universidad.mapping.dto.ProfesorDto;

import java.util.function.Predicate;

public class ProfesorUtil {

    public static Predicate<ProfesorDto> buscarPorCodigo(String codigo){
        return profesorDto -> profesorDto.codigo().contains(codigo);
    }

    public static Predicate<ProfesorDto> buscarPorNombres(String nombres){
        return profesorDto -> profesorDto.nombres().contains(nombres);
    }

    public static Predicate<ProfesorDto> buscarPorApellidos(String apellidos){
        return profesorDto -> profesorDto.apellidos().contains(apellidos);
    }

    public static Predicate<ProfesorDto> buscarPorSexo(String sexo){
        return profesorDto -> profesorDto.sexo().contains(sexo);
    }

    public static Predicate<ProfesorDto> buscarPorEdad(String edad){
        return profesorDto -> profesorDto.edad().contains(edad);
    }

    public static Predicate<ProfesorDto> buscarPorCorreo(String correo){
        return profesorDto -> profesorDto.correo().contains(correo);
    }

    public static Predicate<ProfesorDto> buscarPorTelefono(String telefono){
        return profesorDto -> profesorDto.telefono().contains(telefono);
    }

    public static Predicate<ProfesorDto> buscarPorPrograma(String programa){
        return profesorDto -> profesorDto.programa().contains(programa);
    }

    public static Predicate<ProfesorDto> buscarPorProfesion(String profesion){
        return profesorDto -> profesorDto.profesion().contains(profesion);
    }

    public static Predicate<ProfesorDto> buscarPorTodo(String codigo, String nombres, String apellidos, String sexo,
                                                         String edad, String correo, String telefono, String programa,
                                                       String profesion) {

        Predicate<ProfesorDto> predicado = profesorDto -> true;

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
        if( programa != null && !programa.isEmpty() ){
            predicado = predicado.and(buscarPorPrograma(programa));
        }
        if( profesion != null && !profesion.isEmpty() ){
            predicado = predicado.and(buscarPorProfesion(profesion));
        }

        return predicado;
    }
}
