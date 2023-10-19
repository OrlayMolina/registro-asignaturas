package programacion3.parcial2.universidad.util;

import programacion3.parcial2.universidad.enumm.Programa;
import programacion3.parcial2.universidad.enumm.Sexo;
import programacion3.parcial2.universidad.enumm.TipoMateria;
import programacion3.parcial2.universidad.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Persistencia {

    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/programacion3/parcial2/universidad/archivos/UniversidadLog.txt";
    public static final String RUTA_ARCHIVO_ESTUDIANTES = "src/main/resources/programacion3/parcial2/universidad/archivos/estudiantes.txt";
    public static final String RUTA_ARCHIVO_PROFESORES = "src/main/resources/programacion3/parcial2/universidad/archivos/profesores.txt";
    public static final String RUTA_ARCHIVO_MATERIAS = "src/main/resources/programacion3/parcial2/universidad/archivos/materias.txt";

    public static final String RUTA_ARCHIVO_ASIGNACIONES = "src/main/resources/programacion3/parcial2/universidad/archivos/asignaciones.txt";
    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    public static void guardarEstudiante(ArrayList<Estudiante> listaEstudiantes) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for (Estudiante estudiante : listaEstudiantes) {
            contenido += estudiante.getCodigo() + ";" + estudiante.getNombres() + ";" + estudiante.getApellidos() + ";" + estudiante.getSexo()
                    + ";" + estudiante.getEdad() + ";" + estudiante.getCorreo() + ";" + estudiante.getTelefono() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ESTUDIANTES, contenido, false);
    }

    public static void guardarProfesor(ArrayList<Profesor> listaProfesores) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for (Profesor profesor : listaProfesores) {
            contenido += profesor.getCodigo() + ";" + profesor.getNombres() + ";" + profesor.getApellidos() + ";" + profesor.getSexo()
                    + ";" + profesor.getEdad() + ";" + profesor.getCorreo() + ";" + profesor.getTelefono() + ";" + profesor.getPrograma() + ";" + profesor.getProfesion() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PROFESORES, contenido, false);
    }

    public static void guardarMateria(ArrayList<Materia> listaMaterias) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for (Materia materia : listaMaterias) {
            contenido += materia.getCodigo() + "@@" + materia.getNombre() + "@@" + materia.getIntensidad() + "@@" + materia.getTipoMateria() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_MATERIAS, contenido, false);
    }

    public static void guardarAsignacion(ArrayList<Asignacion> listaAsignaciones) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for (Asignacion asignacion : listaAsignaciones) {
            contenido += asignacion.getCodigo() + "@@" + asignacion.getMateriaAsociada() + "@@" + asignacion.getProfesorAsociado() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ASIGNACIONES, contenido, false);
    }

    public static void cargarDatosArchivos(Universidad universidad) throws FileNotFoundException, IOException {

        ArrayList<Estudiante> estudiantesCargados = cargarEstudiante();
        if (estudiantesCargados.size() > 0)
            universidad.getListaEstudiantes().addAll(estudiantesCargados);

    }

    public static ArrayList<Estudiante> cargarEstudiante() throws FileNotFoundException, IOException {
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ESTUDIANTES);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan@@arias@@125454@@Armenia@@uni1@@@12454@@125444
            Estudiante estudiante = new Estudiante();
            estudiante.setCodigo(linea.split(";")[0]);
            estudiante.setNombres(linea.split(";")[1]);
            estudiante.setApellidos(linea.split(";")[2]);
            estudiante.setSexo(Sexo.valueOf(linea.split(";")[3]));
            estudiante.setEdad(Integer.valueOf(linea.split(";")[4]));
            estudiante.setCorreo(linea.split(";")[5]);
            estudiante.setTelefono(linea.split(";")[6]);
            estudiantes.add(estudiante);
        }
        return estudiantes;
    }

    public static void cargarDatosProfesores(Universidad universidad) throws FileNotFoundException, IOException {

        ArrayList<Profesor> profesoresCargados = cargarProfesor();
        if (profesoresCargados.size() > 0)
            universidad.getListaProfesores().addAll(profesoresCargados);

    }

    public static ArrayList<Profesor> cargarProfesor() throws FileNotFoundException, IOException {
        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PROFESORES);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan@@arias@@125454@@Armenia@@uni1@@@12454@@125444
            Profesor profesor = new Profesor();
            profesor.setCodigo(linea.split(";")[0]);
            profesor.setNombres(linea.split(";")[1]);
            profesor.setApellidos(linea.split(";")[2]);
            profesor.setSexo(Sexo.valueOf(linea.split(";")[3]));
            profesor.setEdad(Integer.valueOf(linea.split(";")[4]));
            profesor.setCorreo(linea.split(";")[5]);
            profesor.setTelefono(linea.split(";")[6]);
            profesor.setPrograma(Programa.valueOf(linea.split(";")[7]));
            profesor.setProfesion(linea.split(";")[8]);
            profesores.add(profesor);
        }
        return profesores;
    }

    public static void cargarDatosMaterias(Universidad universidad) throws FileNotFoundException, IOException {

        ArrayList<Materia> materiasCargados = cargarMateria();
        if (materiasCargados.size() > 0)
            universidad.getListaMaterias().addAll(materiasCargados);

    }

    public static ArrayList<Materia> cargarMateria() throws FileNotFoundException, IOException {
        ArrayList<Materia> materias = new ArrayList<Materia>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_MATERIAS);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan@@arias@@125454@@Armenia@@uni1@@@12454@@125444
            Materia materia = new Materia();
            materia.setCodigo(linea.split("@@")[0]);
            materia.setNombre(linea.split("@@")[1]);
            materia.setIntensidad(linea.split("@@")[2]);
            materia.setTipoMateria(TipoMateria.valueOf(linea.split("@@")[3]));

            materias.add(materia);
        }
        return materias;
    }

    public static void cargarDatosAsignaciones(Universidad universidad) throws FileNotFoundException, IOException {

        ArrayList<Asignacion> asignacionesCargados = cargarAsignaciones(universidad);
        if (asignacionesCargados.size() > 0)
            universidad.getListaAsignaciones().addAll(asignacionesCargados);

    }

    public static ArrayList<Asignacion> cargarAsignaciones(Universidad universidad) throws FileNotFoundException, IOException {
        ArrayList<Asignacion> asignaciones = new ArrayList<Asignacion>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ASIGNACIONES);

        for (String linea : contenido) {
            String[] partes = linea.split("@@");
            if (partes.length >= 3) {
                String codigo = partes[0];
                String materiaCodigo = partes[1];
                String profesorCodigo = partes[2];

                // Busca la materia y el profesor correspondientes en la universidad
                Materia materiaAsociada = universidad.obtenerMateria(materiaCodigo);
                Profesor profesorAsociado = universidad.obtenerProfesor(profesorCodigo);

                if (materiaAsociada != null && profesorAsociado != null) {
                    Asignacion asignacion = new Asignacion();
                    asignacion.setCodigo(codigo);
                    asignacion.setMateriaAsociada(materiaAsociada);
                    asignacion.setProfesorAsociado(profesorAsociado);
                    asignaciones.add(asignacion);
                }
            }
        }

        return asignaciones;
    }
}
