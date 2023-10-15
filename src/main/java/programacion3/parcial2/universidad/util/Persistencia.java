package programacion3.parcial2.universidad.util;

public class Persistencia {

    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/programacion3/parcial2/universidad/archivos/UniversidadLog.txt";

    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }
}
