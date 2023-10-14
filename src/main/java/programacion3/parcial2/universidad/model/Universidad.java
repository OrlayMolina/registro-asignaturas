package programacion3.parcial2.universidad.model;

import java.util.ResourceBundle;

public class Universidad {


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
