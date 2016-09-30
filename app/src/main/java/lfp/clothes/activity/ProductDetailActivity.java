package lfp.clothes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import java.util.HashMap;
import java.util.List;

import lfp.clothes.R;
import lfp.clothes.model.Product;
/**
 * Created by lfagundez on 28/9/16.
 * Pantalla donde se muestra el detalle del producto
 */
public class ProductDetailActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener{

    private SliderLayout slider;
    private Product product;
    private TextView name;
    private TextView description;
    private TextView code;
    private TextView size;
    private TextView price;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_datail);
        slider = (SliderLayout) findViewById(R.id.slider);
        Intent intent = getIntent();
        product = intent.getParcelableExtra("product");
        List<Object> imagesGallery = product.getProductGalleryImages();
        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.description);
        price = (TextView) findViewById(R.id.price);
        code = (TextView) findViewById(R.id.code);
        size = (TextView) findViewById(R.id.size);

        loadImages(imagesGallery);
        loadDetails();

        slider.stopAutoCycle();
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        else
            return false;

    }

    private void loadDetails() {

        name.setText(product.getName());
        description.setText(product.getDescription());
        price.setText(product.getPrice()+" "+product.getCurrency());
        code.setText(getString(R.string.code)+" "+product.getCode());
        String sizes = new String();
        for (int i=0;i<product.getSizes().size();i++) {
                sizes += product.getSizes().get(i).getName()+", ";
        }
        size.setText(getString(R.string.sizes)+" "+sizes.substring(0,sizes.length()-2));
    }
    /* Se construye un slider para las imágenes que contiene el producto*/
    private void loadImages(List<Object> imagesGallery) {
        for (int i = 0; i < imagesGallery.size(); i++) {

            HashMap<String, String> image = (HashMap<String, String>) imagesGallery.get(i);


            for (String name : image.keySet()) {
                if(name.equals("image")) {
                    TextSliderView textSliderView = new TextSliderView(this);

                    textSliderView
                            .image(image.get(name))
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(this);

                    //add your extra information
                    textSliderView.getPicasso();
                    textSliderView.bundle(new Bundle());
                    textSliderView.getBundle()
                            .putString("extra", name);

                    slider.addSlider(textSliderView);
                }
            }

        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
