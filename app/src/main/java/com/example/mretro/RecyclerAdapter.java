package com.example.mretro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Hero> heroes;
    private LayoutInflater inflater;
    private Context ctx;

    public RecyclerAdapter(ArrayList<Hero> heroes, Context ctx) {
        this.heroes = heroes;
        this.inflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Hero hero = heroes.get(position);
        holder.txtName.setText(hero.getName());
        holder.txtRealName.setText(hero.getRealname());
        holder.txtTeam.setText(hero.getTeam());
        holder.firstApp.setText(hero.getFirstappearance());
        holder.createdBy.setText(hero.getCreatedby());
        holder.txtPublisher.setText(hero.getPublisher());
        holder.txtImge.setText(hero.getImageurl());
        holder.txtBio.setText(hero.getBio());
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName,txtRealName,txtTeam,firstApp,createdBy,txtPublisher,txtImge,txtBio;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtRealName = itemView.findViewById(R.id.txtRealName);
            txtTeam = itemView.findViewById(R.id.txtTeam);
            firstApp = itemView.findViewById(R.id.firstAppearance);
            createdBy = itemView.findViewById(R.id.CreatedBy);
            txtPublisher = itemView.findViewById(R.id.publisher);
            txtImge = itemView.findViewById(R.id.imageUrl);
            txtBio = itemView.findViewById(R.id.bio);
        }
    }
}
