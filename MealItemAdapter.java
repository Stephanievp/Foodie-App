package com.example.foodieapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MealItemAdapter extends RecyclerView.Adapter<MealItemAdapter.MealItemHolder> {

    private int selectedItem = -1;
    private ArrayList<MealItem> mealData;
    private Context context;

    public MealItemAdapter(Context context, ArrayList<MealItem> mealData) {
        this.mealData = mealData;
        this.context = context;
    }

    @NonNull
    @Override
    public MealItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealItemHolder(LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealItemHolder holder, int position) {
        MealItem currentMeal = mealData.get(position);
        holder.bindItem(currentMeal);
    }

    @Override
    public int getItemCount() {
        return mealData.size();
    }


    //inner class
    protected class MealItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView textTitle, textDescription;
        private ImageView imageViewMeal;

        public MealItemHolder(@NonNull View itemView) {
            super(itemView); //constructor takes in view

            textTitle       = itemView.findViewById(R.id.title);
            textDescription = itemView.findViewById(R.id.description);
            imageViewMeal   = itemView.findViewById(R.id.imageViewMeal);
            TextView textInfo = itemView.findViewById(R.id.meal_info);

            //set onClickListener
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //open new activity when view is clicked
            MealItem currentMeal = mealData.get(getAdapterPosition());

            Intent detailIntent = new Intent(context, MealDetailActivity.class);
            detailIntent.putExtra("title", currentMeal.getTitle());
            detailIntent.putExtra("description", currentMeal.getDescription());
            detailIntent.putExtra("image_resource", currentMeal.getImageId());
            detailIntent.putExtra("link", currentMeal.getMeal_info());
            context.startActivity(detailIntent);
        }

        @Override
        public boolean onLongClick(View v) {
            selectedItem = getLayoutPosition();
            AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(context);

            myAlertBuilder.setTitle(context.getString(R.string.alert_title));
            myAlertBuilder.setMessage(context.getString(R.string.alert_message));

            myAlertBuilder.setPositiveButton(context.getString(R.string.yes_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mealData.remove(selectedItem);
                    notifyItemRemoved(selectedItem);
                }
            });
            myAlertBuilder.setNegativeButton(context.getString(R.string.no_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //do nothing
                }
            });

            myAlertBuilder.show();
            return true;
        }

        public void bindItem(MealItem currentMeal) {

            textTitle.setText(currentMeal.getTitle());
            textDescription.setText(currentMeal.getDescription());
            Glide.with(context).load(currentMeal.getImageId()).into(imageViewMeal);
        }

    }//end of inner class


}
