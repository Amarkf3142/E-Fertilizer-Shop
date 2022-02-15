package com.Shop.shopping;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Shop.shopping.Model.Products;
import com.Shop.shopping.Model.model;
import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import java.util.*;

public class MyAdapter extends FirebaseRecyclerAdapter <model, MyAdapter.myViewholder> {

    public MyAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull model model) {
        holder.name.setText(model.getPname());
        holder.description.setText(model.getDescription());
        holder.price.setText(model.getPrice());
        holder.quantity.setText(model.getQuantity());
        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview = dialogPlus.getHolderView();
                EditText name=myview.findViewById(R.id.product_name);
                EditText description=myview.findViewById(R.id.product_description);
                EditText price=myview.findViewById(R.id.product_price);
                EditText quantity=myview.findViewById(R.id.product_quantity);
                Button submit=myview.findViewById(R.id.add_new_product);

                name.setText(model.getPname());
                description.setText(model.getDescription());
                price.setText(model.getPrice());
                quantity.setText(model.getQuantity());

                dialogPlus.show();

               submit.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Map<String, Object> map=new HashMap<>();
                       map.put("name", name.getText().toString());
                       map.put("description", description.getText().toString());
                       map.put("price", price.getText().toString());
                       map.put("quantity", quantity.getText().toString());

                       FirebaseDatabase.getInstance().getReference().child("Products")
                               .child(getRef(position).getKey()).updateChildren(map)
                               .addOnSuccessListener(new OnSuccessListener<Void>() {
                                   @Override
                                   public void onSuccess(Void unused) {
                                       dialogPlus.dismiss();
                                   }
                               })
                               .addOnFailureListener(new OnFailureListener() {
                                   @Override
                                   public void onFailure(@NonNull Exception e) {
                                       dialogPlus.dismiss();
                                   }
                               });
                   }
               });

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete..");


                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Products")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });


    }



    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{

        ImageView img, delete, edit;

        TextView name, description, price, quantity;

        public myViewholder(@NonNull View itemView) {
            super(itemView);

            img= (ImageView) itemView.findViewById(R.id.product_image_details);
            name=(TextView) itemView.findViewById(R.id.product_name_details);
            description=(TextView) itemView.findViewById(R.id.product_description_details);
            price=(TextView) itemView.findViewById(R.id.product_price_details);
            quantity=(TextView) itemView.findViewById(R.id.product_quantity_details);


            edit=(ImageView) itemView.findViewById(R.id.editicon);
            delete=(ImageView) itemView.findViewById(R.id.deleteicon);
        }
    }
}