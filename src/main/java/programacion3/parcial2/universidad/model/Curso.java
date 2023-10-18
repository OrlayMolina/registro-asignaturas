package programacion3.parcial2.universidad.model;

public class Curso {

    private Materia materiaAsociada;
    private Profesor profesorAsociado;
    private Estudiante estudianteAsociado;

    public Curso(){

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

    public Estudiante getEstudianteAsociado() {
        return estudianteAsociado;
    }

    public void setEstudianteAsociado(Estudiante estudianteAsociado) {
        this.estudianteAsociado = estudianteAsociado;
    }
}
