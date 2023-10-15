package programacion3.parcial2.universidad.model;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Universidad {

    private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void agregarEstudiante(Estudiante nuevoEstudiante) throws Exception{
        getListaEstudiantes().add(nuevoEstudiante);
    }

    public boolean verificarEstudianteExistente(String codigo) throws Exception {
        if(estudianteExiste(codigo)){
            throw new Exception("El estudiante con código: "+codigo+" ya existe");
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
