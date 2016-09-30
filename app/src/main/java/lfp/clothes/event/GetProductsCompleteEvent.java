package lfp.clothes.event;


import java.util.List;

import lfp.clothes.model.Product;

/**
 * Created by lfagundez on 28/9/16.
 * Obtiene la respuesta del request en caso que haya sido exitosa,
 * para luego devolverlo a la activity y poder ser manejado
 */
public class GetProductsCompleteEvent {

    private List<Product> response;
    private String error;
    public GetProductsCompleteEvent(List<Product> response) {
        this.response = response;
    }

    public List<Product> getResponse() {
        return response;
    }
}
