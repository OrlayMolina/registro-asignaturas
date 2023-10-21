package programacion3.parcial2.universidad.model;

import programacion3.parcial2.universidad.exception.*;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Universidad {

    private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
    private ArrayList<Profesor> listaProfesores = new ArrayList<>();
    private ArrayList<Materia> listaMaterias = new ArrayList<>();

    private ArrayList<Asignacion> listaAsignaciones = new ArrayList<>();
    private ArrayList<Curso> listaCursos = new ArrayList<>();
    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public ArrayList<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public ArrayList<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public ArrayList<Asignacion> getListaAsignaciones() {
        return listaAsignaciones;
    }

    public ArrayList<Curso> getListaCursos() {
        return listaCursos;
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

    public void agregarMateria(Materia nuevaMateria) throws MateriaException {
        getListaMaterias().add(nuevaMateria);
    }

    public Boolean eliminarMateria(String codigo) throws MateriaException {
        Materia materia = null;
        boolean flagExiste = false;
        materia = obtenerMateria(codigo);
        if(materia == null)
            throw new MateriaException("La materia a eliminar no existe");
        else{
            getListaMaterias().remove(materia);
            flagExiste = true;
        }
        return flagExiste;
    }

    public boolean actualizarMateria(String codigo, Materia materia) throws MateriaException {
        Materia materiaActual = obtenerMateria(codigo);
        if(materiaActual == null)
            throw new MateriaException("La materia a actualizar no existe");
        else{
            materiaActual.setCodigo(materia.getCodigo());
            materiaActual.setNombre(materia.getNombre());
            materiaActual.setIntensidad(materia.getIntensidad());
            materiaActual.setTipoMateria(materia.getTipoMateria());
            return true;
        }
    }


    public void agregarAsignacion(Asignacion nuevaAsignacion) throws AsignacionException {
        getListaAsignaciones().add(nuevaAsignacion);
    }

    public Boolean eliminarAsignacion(String codigo) throws AsignacionException {
        Asignacion asignacion = null;
        boolean flagExiste = false;
        asignacion = obtenerAsignacion(codigo);
        if(asignacion == null)
            throw new AsignacionException("La asignacion a eliminar no existe");
        else{
            getListaAsignaciones().remove(asignacion);
            flagExiste = true;
        }
        return flagExiste;
    }

    public boolean actualizarAsignacion(String codigo, Asignacion asignacion) throws AsignacionException {
        Asignacion asignacionActual = obtenerAsignacion(codigo);
        if(asignacionActual == null)
            throw new AsignacionException("La asignación a actualizar no existe");
        else{
            asignacionActual.setCodigo(asignacion.getCodigo());
            asignacionActual.setCodigoMateria(asignacion.getCodigoMateria());
            asignacionActual.setNombreMateria(asignacion.getNombreMateria());
            asignacionActual.setCodigoProfesor(asignacion.getCodigoProfesor());
            asignacionActual.setProfesor(asignacion.getProfesor());
            return true;
        }
    }

    public void agregarCurso(Curso nuevoCurso) throws CursoException {
        getListaCursos().add(nuevoCurso);
    }

    public Boolean eliminarCurso(String codigo) throws CursoException {
        Curso curso = null;
        boolean flagExiste = false;
        curso = obtenerCurso(codigo);
        if(curso == null)
            throw new CursoException("El Curso a eliminar no existe");
        else{
            getListaCursos().remove(curso);
            flagExiste = true;
        }
        return flagExiste;
    }

    public boolean actualizarCurso(String codigo, Curso curso) throws CursoException {
        Curso cursoActual = obtenerCurso(codigo);
        if(cursoActual == null)
            throw new CursoException("El Curso a actualizar no existe");
        else{
            cursoActual.setCodigo(curso.getCodigo());
            cursoActual.setCodigoMateria(curso.getCodigoMateria());
            cursoActual.setNombreMateria(curso.getNombreMateria());
            cursoActual.setCodigoEstudiante(curso.getCodigoEstudiante());
            cursoActual.setEstudiante(curso.getEstudiante());
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

    public Materia obtenerMateria(String codigo) {
        Materia materiaEncontrado = null;
        for (Materia materia : getListaMaterias()) {
            if(materia.getCodigo().equalsIgnoreCase(codigo)){
                materiaEncontrado = materia;
                break;
            }
        }
        return materiaEncontrado;
    }

    public Asignacion obtenerAsignacion(String codigo) {
        Asignacion asignacionEncontrado = null;
        for (Asignacion asignacion : getListaAsignaciones()) {
            if(asignacion.getCodigo().equalsIgnoreCase(codigo)){
                asignacionEncontrado = asignacion;
                break;
            }
        }
        return asignacionEncontrado;
    }

    public Curso obtenerCurso(String codigo) {
        Curso cursoEncontrado = null;
        for (Curso curso : getListaCursos()) {
            if(curso.getCodigo().equalsIgnoreCase(codigo)){
                cursoEncontrado = curso;
                break;
            }
        }
        return cursoEncontrado;
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

    public boolean verificarMateriaExistente(String codigo) throws MateriaException {
        if(materiaExiste(codigo)){
            throw new MateriaException("La materia con código: "+codigo+" ya existe");
        }else{
            return false;
        }
    }

    public boolean verificarAsignacionExistente(String codigo) throws AsignacionException {
        if(asignacionExiste(codigo)){
            throw new AsignacionException("La asignación con código: "+codigo+" ya existe");
        }else{
            return false;
        }
    }

    public boolean verificarCursoExistente(String codigo) throws CursoException {
        if(cursoExiste(codigo)){
            throw new CursoException("El Curso con código: "+codigo+" ya existe");
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

    public boolean materiaExiste(String codigo) {
        boolean materiaEncontrado = false;
        for (Materia materia : getListaMaterias()) {
            if(materia.getCodigo().equalsIgnoreCase(codigo)){
                materiaEncontrado = true;
                break;
            }
        }
        return materiaEncontrado;
    }

    public boolean asignacionExiste(String codigo) {
        boolean asignacionEncontrado = false;
        for (Asignacion asignacion : getListaAsignaciones()) {
            if(asignacion.getCodigo().equalsIgnoreCase(codigo)){
                asignacionEncontrado = true;
                break;
            }
        }
        return asignacionEncontrado;
    }

    public boolean cursoExiste(String codigo) {
        boolean cursoEncontrado = false;
        for (Curso curso : getListaCursos()) {
            if(curso.getCodigo().equalsIgnoreCase(codigo)){
                cursoEncontrado = true;
                break;
            }
        }
        return cursoEncontrado;
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
