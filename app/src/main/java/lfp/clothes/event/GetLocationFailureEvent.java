package lfp.clothes.event;

/**
 * Created by lfagundez on 29/9/16.
 * Capturar el error en caso de que falle el request
 * para obtener las tiendas, luego sera mostrado en un diálogo
 */
public class GetLocationFailureEvent {

    private String error;

    public GetLocationFailureEvent(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
