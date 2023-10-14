package programacion3.parcial2.universidad.model;

import programacion3.parcial2.universidad.enumm.Programa;

public class Profesor extends Persona{

    private Programa programa;

    private String profesion;

    public Profesor(){

    }

    public Profesor(Programa programa, String profesion) {
        this.programa = programa;
        this.profesion = profesion;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public String toString() {
        return "programa=" + programa +
                ", profesion='" + profesion;
    }
}
