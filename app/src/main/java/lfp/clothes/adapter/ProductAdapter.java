package lfp.clothes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;
import lfp.clothes.R;
import lfp.clothes.model.Product;

/**
 * Created by lfagundez on 28/9/2016.
 * Adaptador para el manejo del recycleview y las cardview
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private List<Product> items;

    private OnItemClickListener listener;
    private Context context;
    public interface OnItemClickListener {
        void onClick(View v, int index,List<Product> collection);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        public TextView price;
        public ImageView image;
        public TextView name;

        public RelativeLayout layout;
        public ProductViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.article_pic);
            price = (TextView) v.findViewById(R.id.price);
            name = (TextView) v.findViewById(R.id.name);
            layout = (RelativeLayout) v.findViewById(R.id.card_view);
            layout.setOnClickListener(this);
            image.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition(),items);
        }
    }

    public ProductAdapter(List<Product> items) {

        this.items = items;
    }

        public ProductAdapter(List<Product> items, OnItemClickListener listener, Context context) {

            this.items = items;
            this.listener = listener;
            this.context = context;
        }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card, viewGroup, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        holder.name.setText(items.get(position).getName());
        holder.price.setText(items.get(position).getPrice()+" "+items.get(position).getCurrency());
        Picasso.with(context).load(items.get(position).getProductImage().getDefault())
                .placeholder(R.drawable.no_image_available)
                .error(R.drawable.no_image_available)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}







