package lfp.clothes.controller;


import lfp.clothes.event.BusManager;
import lfp.clothes.event.GetProductsCompleteEvent;
import lfp.clothes.event.GetProductsFailureEvent;
import lfp.clothes.model.Products;
import lfp.clothes.server.ClothesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lfagundez on 28/9/16.
 */
public class ProductController{

    public void listProducts(){

        ClothesService clothesService = ClothesService.retrofit.create(ClothesService.class);
        final Call<Products> call = clothesService.listProducts();
        call.enqueue(new Callback<Products>() {

            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {

                BusManager.post(new GetProductsCompleteEvent(response.body().getResults()));


            }
            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                BusManager.post(new GetProductsFailureEvent(t.getMessage()));
            }
        });
    }

}
