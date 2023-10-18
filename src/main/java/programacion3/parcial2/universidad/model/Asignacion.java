package programacion3.parcial2.universidad.model;

import programacion3.parcial2.universidad.model.Materia;
import programacion3.parcial2.universidad.model.Profesor;

public class Asignacion {

    private String codigo;
    private Materia materiaAsociada;
    private Profesor profesorAsociado;

    public Asignacion(){

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Materia getMateriaAsociada() {
        return materiaAsociada;
    }

    public void setMateriaAsociada(Materia materiaAsociada) {
        this.materiaAsociada = materiaAsociada;
    }

    public Profesor getProfesorAsociado() {
        return profesorAsociado;
    }

    public void setProfesorAsociado(Profesor profesorAsociado) {
        this.profesorAsociado = profesorAsociado;
    }
}
