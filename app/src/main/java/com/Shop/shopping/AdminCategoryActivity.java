package com.Shop.shopping;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class AdminCategoryActivity extends AppCompatActivity {
    private ImageView fertilizer1, fertilizer2, growthharmons ;
    private ImageView pesticide, herbicide, fungicide ;
    private ImageView seeds ;
    private Button LogoutBtn, CheckOrdersBtn, CheckStockbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        LogoutBtn = (Button) findViewById(R.id.admin_logout_btn);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        CheckStockbtn = (Button) findViewById(R.id.admin_view_stock);
        CheckStockbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, ViewStockActivity.class);
                startActivity(intent);
            }
       });

        CheckOrdersBtn = (Button) findViewById(R.id.check_orders_btn);
        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewOrdersActivity.class);
                startActivity(intent);
            }
        });


        fertilizer1 = (ImageView) findViewById(R.id.fertilizer1);
        fertilizer2 = (ImageView) findViewById(R.id.fertilizer2);
        growthharmons = (ImageView) findViewById(R.id.growthharmons);

        pesticide = (ImageView) findViewById(R.id.pesticide);
        herbicide = (ImageView) findViewById(R.id.herbicide);
        fungicide = (ImageView) findViewById(R.id.fungicide);

        seeds = (ImageView) findViewById(R.id.seeds);


        fertilizer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, com.Shop.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "fertilizer1");
                startActivity(intent);
            }
        });
        fertilizer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, com.Shop.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "fertilizer2");
                startActivity(intent);
            }
        });


        growthharmons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, com.Shop.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "growthharmons");
                startActivity(intent);
            }
        });


        pesticide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, com.Shop.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Pesticide");
                startActivity(intent);
            }
        });


        herbicide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, com.Shop.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Herbicide");
                startActivity(intent);
            }
        });


        fungicide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, com.Shop.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Fungicide");
                startActivity(intent);
            }
        });


        seeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, com.Shop.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Seeds");
                startActivity(intent);
            }
        });

    }
}
