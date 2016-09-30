package lfp.clothes.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;

import java.util.List;

import lfp.clothes.R;
import lfp.clothes.controller.StoreLocationController;
import lfp.clothes.event.GetLocationFailureEvent;
import lfp.clothes.event.GetStoresLocationCompleteEvent;
import lfp.clothes.model.Store;

/**
 * Created by lfagundez on 29/9/16.
 * Clase que muestra el Mapa y un pin en la ubicacion que se encuentre la tienda
 */

public class StoreLocationActivity extends BaseActivity implements OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private StoreLocationController storeController;
    private List<Store> stores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_location);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        storeController = new StoreLocationController();
        getStoresCoordinates();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                break;
        }

        return true;
    }

    public void getStoresCoordinates() {

        progressDialog = ProgressDialog.show(StoreLocationActivity.this, "", getString(R.string.loading));
        storeController.getStoresLocation();

    }
    //se coloca los pines en el mapa, segun sus coordenadas
    private void setMark(LatLng coordinates, String title, String snippet) {

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(coordinates)
                .zoom(10)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        Marker info = mMap.addMarker(new MarkerOptions()
                .position(coordinates)
                .title(title)
                .snippet(snippet));

    }

    @Subscribe
    public void onGetProductsComplete(GetStoresLocationCompleteEvent event) {
        stores = event.getResponse();
        LatLng coord;

        for (int i = 0;i<stores.size();i++){
            if (i==0)
                mMap.clear();
            Store store = stores.get(i);
            coord = new LatLng(Float.parseFloat(store.getLatitude()),
                    Float.parseFloat(store.getLongitude()));
            String title = store.getName();
            String snippet = store.getAddress() +"\n"+"Tlf: "+ store.getPhone();
            setMark(coord,title,snippet);

        }

        onFinishTask();
    }

    /*+En caso de querer informacion sobre la sucursal este método
        se encargará manejar la acción y de mostrarlo en un diálogo
    */
    @Override
    public void onInfoWindowClick(Marker marker) {

        AlertDialog(marker.getSnippet());
    }
    @Subscribe
    public void onGetStoreLocationFailure(GetLocationFailureEvent event) {

        String error = event.getError();
        onFinishTask();
        AlertDialog(error);
    }

}
