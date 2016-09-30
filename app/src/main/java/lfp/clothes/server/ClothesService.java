package lfp.clothes.server;

import lfp.clothes.model.Products;
import lfp.clothes.model.Stores;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lfagundez on 28/9/16.
 */
public interface ClothesService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://demos.dflabs.io/store/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("products")
    Call<Products> listProducts();

    @GET("stores")
    Call<Stores> getStoresLocation();



}
