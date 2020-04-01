package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MealDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        TextView mealTitle = findViewById(R.id.titleDetail);
        TextView mealDescription = findViewById(R.id.descriptionDetail);
        TextView mealInfo = findViewById(R.id.meal_info);
        ImageView mealImage = findViewById(R.id.imageViewMealDetail);

        mealTitle.setText(getIntent().getStringExtra("title"));
        mealDescription.setText(getIntent().getStringExtra("description"));
        mealInfo.setText(getIntent().getStringExtra("link"));

        Glide.with(this).load(getIntent().getIntExtra("image_resource", 0)).into(mealImage);

    }
}
