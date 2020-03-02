package com.example.mretro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mretro.Heros;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Hero> heroList;
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Hero> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        heroList = new ArrayList<>();
        data = new ArrayList<>();
        //adapter = new RecyclerAdapter(heroList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(adapter);
        getHeros();
    }

    private void getHeros() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<List<Hero>> call = api.getHeros();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                    List<Hero> heroList = response.body();
                    for (int i=0;i<heroList.size();i++){

                        data.add(new  Hero(heroList.get(i).getName(),heroList.get(i).getRealname(),heroList.get(i).getTeam(),heroList.get(i).getFirstappearance(),heroList.get(i).getCreatedby(),heroList.get(i).getPublisher(),heroList.get(i).getImageurl(),heroList.get(i).getBio()));

                    }
                adapter = new RecyclerAdapter(data,getApplicationContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
