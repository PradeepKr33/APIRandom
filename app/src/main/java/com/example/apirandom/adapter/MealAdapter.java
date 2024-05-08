package com.example.apirandom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apirandom.R;
import com.example.apirandom.response.MealsItem;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MealsItem> mealsItemList;
    private MealClickListner listener;

    public MealAdapter(Context context, ArrayList<MealsItem> mealsItemList, MealClickListner listener) {
        this.context = context;
        this.mealsItemList = mealsItemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.ViewHolder holder, int position) {
         MealsItem mealsItem = mealsItemList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(mealsItem.getStrMealThumb())
//                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.strMealThumb);

        holder.strMeal.setText(mealsItem.getStrMeal());
        holder.idMeal.setText(String.format("Meal Id: %s", mealsItem.getIdMeal()));
        holder.strCategory.setText(String.format("Category: %s", mealsItem.getStrCategory()));
        holder.strArea.setText(String.format("Area: %s", mealsItem.getStrArea()));

//        holder.strCategory.setText(mealsItem.getStrCategory());


        holder.RecipeBtn.setOnClickListener(v -> listener.OnRecipe(mealsItem));
        holder.instructionsBtn.setOnClickListener(v -> listener.OnIngredients(mealsItem));
        holder.ingredientsBtn.setOnClickListener(v -> listener.OnInstructions(mealsItem));

    }

    @Override
    public int getItemCount() {
        {
            if (mealsItemList != null) {
                return mealsItemList.size();
            } else {
                return 0;
            }
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView strMeal, strCategory,strArea,idMeal;
        ImageView strMealThumb;
        Button RecipeBtn,instructionsBtn,ingredientsBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            strMeal = itemView.findViewById(R.id.strMeal);
            strCategory = itemView.findViewById(R.id.strCategory);
            strArea = itemView.findViewById(R.id.strArea);
            idMeal = itemView.findViewById(R.id.idMeal);
            strMealThumb = itemView.findViewById(R.id.strMealThumb);
            RecipeBtn = itemView.findViewById(R.id.RecipeBtn);
            instructionsBtn = itemView.findViewById(R.id.instructionsBtn);
            ingredientsBtn = itemView.findViewById(R.id.ingredientsBtn);
        }
    }

    public interface MealClickListner {
        void OnRecipe(MealsItem mealsItem);
        void OnIngredients(MealsItem mealsItem);
        void OnInstructions(MealsItem mealsItem);
    }
}
