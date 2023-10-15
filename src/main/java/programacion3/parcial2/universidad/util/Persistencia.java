package programacion3.parcial2.universidad.util;

import programacion3.parcial2.universidad.enumm.Sexo;
import programacion3.parcial2.universidad.model.Estudiante;
import programacion3.parcial2.universidad.model.Universidad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Persistencia {

    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/programacion3/parcial2/universidad/archivos/UniversidadLog.txt";
    public static final String RUTA_ARCHIVO_ESTUDIANTES = "src/main/resources/programacion3/parcial2/universidad/archivos/estudiantes.txt";

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
}
