package com.crud.sqlite;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crud.sqlite.utils.Items;

import java.text.NumberFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    private ArrayList<Items> listItem;
    private Context context;

    public MainActivityAdapter(ArrayList<Items> items, Context ctx) {

        // initial data dan variable yang akan digunakan
        listItem    = items;
        context     = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // initial viewHolder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);

        // set ukuran view, margin, padding dan parameter lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        // menampilkan data pada view
        final String name   = listItem.get(position).getItems_name();
        final String price  = numberFormat(listItem.get(position).getItems_price());

        holder.itemsName.setText(name);
        holder.itemsPrice.setText(price);
        // detail
        holder.ltListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(DetailActivity.getActIntent((Activity) context)
                        .putExtra("id", listItem.get(position).getItems_id())
                );
            }
        });
        holder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(DetailActivity.getActIntent((Activity) context)
                        .putExtra("id", listItem.get(position).getItems_id())

                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // initial view pada item_main.xml
        // CardView cvListing;
        de.hdodenhof.circleimageview.CircleImageView circleImageView;
        LinearLayout ltListing;
        TextView itemsName;
        TextView itemsPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //cvListing   = (CardView) itemView.findViewById(R.id.cv_listing);
            circleImageView = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.img_profile);
            ltListing   = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            itemsName   = (TextView) itemView.findViewById(R.id.items_name);
            itemsPrice  = (TextView) itemView.findViewById(R.id.items_price);
        }
    }

    public String numberFormat(String s) {
        int number      = Integer.parseInt(s);
        String result   = "Rp. " + NumberFormat.getInstance().format(number);

        return result;
    }


}
