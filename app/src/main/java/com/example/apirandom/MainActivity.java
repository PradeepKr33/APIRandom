package com.example.apirandom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.apirandom.adapter.MealAdapter;
import com.example.apirandom.response.MealResponse;
import com.example.apirandom.response.MealsItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MealAdapter.MealClickListner {
    private MealAdapter adapter;
    private final ArrayList<MealsItem> mealsItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =new MealAdapter(this,mealsItemList,this);
        recyclerView.setAdapter(adapter);

        fetchMealData();
    }

    private void fetchMealData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        api.getMealsData().enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    mealsItemList.clear();
                    mealsItemList.addAll(response.body().getMeals());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Internet issue ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void OnRecipe(MealsItem mealsItem) {
        Toast.makeText(this, "Watch here recipe", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnIngredients(MealsItem mealsItem) {
        Toast.makeText(this, "ingredients", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void OnInstructions(MealsItem mealsItem) {
        Toast.makeText(this, "instructions", Toast.LENGTH_SHORT).show();

    }
}