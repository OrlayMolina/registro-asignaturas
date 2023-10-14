package programacion3.parcial2.universidad.controller;

import programacion3.parcial2.universidad.model.Universidad;

public class ModelFactoryController {
    Universidad universidad = new Universidad();

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public boolean comprobarAcceso(String usuario, String password){
        return universidad.comprobarAcceso(usuario, password);
    }
}
