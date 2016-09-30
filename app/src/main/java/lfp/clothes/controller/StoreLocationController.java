package lfp.clothes.controller;

import lfp.clothes.event.BusManager;
import lfp.clothes.event.GetLocationFailureEvent;
import lfp.clothes.event.GetStoresLocationCompleteEvent;
import lfp.clothes.model.Stores;
import lfp.clothes.server.ClothesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lfagundez on 28/9/16.
 */
public class StoreLocationController {

    public void getStoresLocation(){

        ClothesService clothesService = ClothesService.retrofit.create(ClothesService.class);
        final Call<Stores> call = clothesService.getStoresLocation();
        call.enqueue(new Callback<Stores>() {

            @Override
            public void onResponse(Call<Stores> call, Response<Stores> response) {

                BusManager.post(new GetStoresLocationCompleteEvent(response.body().getResults()));


            }
            @Override
            public void onFailure(Call<Stores> call, Throwable t) {
                BusManager.post(new GetLocationFailureEvent(t.getMessage()));
            }
        });
    }

}
