package programacion3.parcial2.universidad.model;

import programacion3.parcial2.universidad.enumm.TipoMateria;

public class Materia {

    private String codigo;
    private String nombre;
    private String intensidad;
    private TipoMateria tipoMateria;

    public Materia(){

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }

    public TipoMateria getTipoMateria() {
        return tipoMateria;
    }

    public void setTipoMateria(TipoMateria tipoMateria) {
        this.tipoMateria = tipoMateria;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", intensidad='" + intensidad + '\'' +
                ", tipoMateria=" + tipoMateria +
                '}';
    }
}
