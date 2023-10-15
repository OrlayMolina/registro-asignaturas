package programacion3.parcial2.universidad.model;

import programacion3.parcial2.universidad.exception.EstudianteException;
import programacion3.parcial2.universidad.exception.ProfesorException;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Universidad {

    private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
    private ArrayList<Profesor> listaProfesores = new ArrayList<>();

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public ArrayList<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void agregarEstudiante(Estudiante nuevoEstudiante) throws EstudianteException {
        getListaEstudiantes().add(nuevoEstudiante);
    }

    public Boolean eliminarEstudiante(String codigo) throws EstudianteException {
        Estudiante estudiante = null;
        boolean flagExiste = false;
        estudiante = obtenerEstudiante(codigo);
        if(estudiante == null)
            throw new EstudianteException("El estudiante a eliminar no existe");
        else{
            getListaEstudiantes().remove(estudiante);
            flagExiste = true;
        }
        return flagExiste;
    }

    public boolean actualizarEstudiante(String codigo, Estudiante estudiante) throws EstudianteException {
        Estudiante estudianteActual = obtenerEstudiante(codigo);
        if(estudianteActual == null)
                throw new EstudianteException("El estudiante a actualizar no existe");
        else{
            estudianteActual.setCodigo(estudiante.getCodigo());
            estudianteActual.setNombres(estudiante.getNombres());
            estudianteActual.setApellidos(estudiante.getApellidos());
            estudianteActual.setSexo(estudiante.getSexo());
            estudianteActual.setEdad(estudiante.getEdad());
            estudianteActual.setCorreo(estudiante.getCorreo());
            estudianteActual.setTelefono(estudiante.getTelefono());
            return true;
        }
    }

    public void agregarProfesor(Profesor nuevoProfesor) throws ProfesorException {
        getListaProfesores().add(nuevoProfesor);
    }

    public Boolean eliminarProfesor(String codigo) throws ProfesorException {
        Profesor profesor = null;
        boolean flagExiste = false;
        profesor = obtenerProfesor(codigo);
        if(profesor == null)
            throw new ProfesorException("El profesor a eliminar no existe");
        else{
            getListaProfesores().remove(profesor);
            flagExiste = true;
        }
        return flagExiste;
    }

    public boolean actualizarProfesor(String codigo, Profesor profesor) throws ProfesorException {
        Profesor profesorActual = obtenerProfesor(codigo);
        if(profesorActual == null)
            throw new ProfesorException("El profesor a actualizar no existe");
        else{
            profesorActual.setCodigo(profesor.getCodigo());
            profesorActual.setNombres(profesor.getNombres());
            profesorActual.setApellidos(profesor.getApellidos());
            profesorActual.setSexo(profesor.getSexo());
            profesorActual.setEdad(profesor.getEdad());
            profesorActual.setCorreo(profesor.getCorreo());
            profesorActual.setTelefono(profesor.getTelefono());
            profesorActual.setPrograma(profesor.getPrograma());
            profesorActual.setTelefono(profesor.getProfesion());
            return true;
        }
    }

    public Estudiante obtenerEstudiante(String codigo) {
        Estudiante estudianteEncontrado = null;
        for (Estudiante estudiante : getListaEstudiantes()) {
            if(estudiante.getCodigo().equalsIgnoreCase(codigo)){
                estudianteEncontrado = estudiante;
                break;
            }
        }
        return estudianteEncontrado;
    }

    public Profesor obtenerProfesor(String codigo) {
        Profesor profesorEncontrado = null;
        for (Profesor profesor : getListaProfesores()) {
            if(profesor.getCodigo().equalsIgnoreCase(codigo)){
                profesorEncontrado = profesor;
                break;
            }
        }
        return profesorEncontrado;
    }

    public boolean verificarEstudianteExistente(String codigo) throws EstudianteException {
        if(estudianteExiste(codigo)){
            throw new EstudianteException("El estudiante con código: "+codigo+" ya existe");
        }else{
            return false;
        }
    }

    public boolean verificarProfesorExistente(String codigo) throws ProfesorException {
        if(profesorExiste(codigo)){
            throw new ProfesorException("El profesor con código: "+codigo+" ya existe");
        }else{
            return false;
        }
    }

    public boolean estudianteExiste(String codigo) {
        boolean estudianteEncontrado = false;
        for (Estudiante estudiante : getListaEstudiantes()) {
            if(estudiante.getCodigo().equalsIgnoreCase(codigo)){
                estudianteEncontrado = true;
                break;
            }
        }
        return estudianteEncontrado;
    }

    public boolean profesorExiste(String codigo) {
        boolean profesorEncontrado = false;
        for (Profesor profesor : getListaProfesores()) {
            if(profesor.getCodigo().equalsIgnoreCase(codigo)){
                profesorEncontrado = true;
                break;
            }
        }
        return profesorEncontrado;
    }

    public boolean comprobarAcceso(String usuario, String password){
        boolean continuar = false;
        if(usuario.equals(usuarioProperties()) && password.equals(passwordProperties())){
            continuar = true;
        }
        return continuar;
    }
    public String usuarioProperties(){
        ResourceBundle resourceBundle;

        resourceBundle = ResourceBundle.getBundle("programacion3/parcial2/universidad/Credenciales");
        String usuario = resourceBundle.getString("usuario");
        return usuario;

    }
    public String passwordProperties(){
        ResourceBundle resourceBundle;

        resourceBundle = ResourceBundle.getBundle("programacion3/parcial2/universidad/Credenciales");
        String password = resourceBundle.getString("contraseña");
        return password;

    }
    public String nombreProperties(){
        ResourceBundle resourceBundle;

        resourceBundle = ResourceBundle.getBundle("programacion3/parcial2/universidad/Credenciales");
        String nombre = resourceBundle.getString("nombre");
        return nombre;

    }

    public String correoProperties(){
        ResourceBundle resourceBundle;

        resourceBundle = ResourceBundle.getBundle("programacion3/parcial2/universidad/Credenciales");
        String correo = resourceBundle.getString("correo");
        return correo;

    }
}
