package lfp.clothes.event;

/**
 * Created by lfagundez on 28/9/16.
 * Capturar el error en caso de que falle el request
 * para obtener los productos, luego sera mostrado en un diálogo
 */
public class GetProductsFailureEvent {

    private String error;

    public GetProductsFailureEvent(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
