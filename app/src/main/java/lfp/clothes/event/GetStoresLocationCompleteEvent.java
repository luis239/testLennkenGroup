package lfp.clothes.event;


import java.util.List;

import lfp.clothes.model.Store;

/**
 * Created by lfagundez on 29/9/16.
 */
public class GetStoresLocationCompleteEvent {

    private List<Store> response;
    private String error;
    public GetStoresLocationCompleteEvent(List<Store> response) {
        this.response = response;
    }

    public GetStoresLocationCompleteEvent(String error) {
        this.error = error;
    }

    public List<Store> getResponse() {
        return response;
    }
}
