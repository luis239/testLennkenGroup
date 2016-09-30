package lfp.clothes.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;


import lfp.clothes.R;
import lfp.clothes.adapter.ProductAdapter;
import lfp.clothes.controller.ProductController;
import lfp.clothes.event.GetProductsCompleteEvent;
import lfp.clothes.event.GetProductsFailureEvent;
import lfp.clothes.model.Product;

/**
 * Created by lfagundez on 28/9/16.
 * Pantalla donde se muetra la lista de productos una vez hecho el request al servicio
 */
public class MainActivity extends BaseActivity implements ProductAdapter.OnItemClickListener,SearchView.OnQueryTextListener{

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    ProductController productController;
    private List <Product> products;
    private TextView noResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        noResults = (TextView)findViewById(R.id.no_result);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getProducts();
    }

    @Override
    public void onClick(View v, int index,List<Product> collection) {

        Product product= collection.get(index);
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        productController = new ProductController();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubar, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.map_action:
                Intent intent = new Intent(this, StoreLocationActivity.class);
                startActivity(intent);

                break;
            case android.R.id.home:
                onBackPressed();
            default:
                break;
        }

        return true;
    }

    public void getProducts() {
        progressDialog = ProgressDialog.show(MainActivity.this, "", getString(R.string.loading));
        productController.listProducts();
    }

    @Subscribe
    public void onGetProductsComplete(GetProductsCompleteEvent event) {
        products = event.getResponse();
        adapter = new ProductAdapter(products,MainActivity.this,getApplicationContext());
        recycler.setAdapter(adapter);
        onFinishTask();
    }

    @Subscribe
    public void onGetProductsFailure(GetProductsFailureEvent event) {
        String error = event.getError();
        onFinishTask();
        AlertDialogToExit(error);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        query = query.toLowerCase();

        final List<Product> filteredList = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {

            final String text = products.get(i).getName().toLowerCase();
            if (text.contains(query)) {

                filteredList.add(products.get(i));
            }
        }
        adapter = new ProductAdapter(filteredList,MainActivity.this,this);
        recycler.setAdapter(adapter);
        if(!filteredList.isEmpty())
            noResults.setVisibility(View.GONE);
        else
            noResults.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        return true;
    }
}
