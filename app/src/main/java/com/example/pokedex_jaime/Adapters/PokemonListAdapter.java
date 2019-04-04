package com.example.pokedex_jaime.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokedex_jaime.Common.Common;
import com.example.pokedex_jaime.Interface.InterfaceClickListener;
import com.example.pokedex_jaime.Model.Pokemon;
import com.example.pokedex_jaime.R;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    Context context;
    List<Pokemon>pokemonList;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,viewGroup
                ,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        //Load image
        Glide.with(context).load(pokemonList.get(position).getImg())
                .into(myViewHolder.pokemon_image);
        //Get name
        myViewHolder.pokemon_name.setText(pokemonList.get(position).getName());

        // Event
        myViewHolder.setInterfaceClickListener(new InterfaceClickListener() {
            @Override
            public void onClick(View view, int position) {
                //On click happening here

                // Using a local broadcast to open the fragment

                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(Common.KEY_ENABLE_HOME)
                                .putExtra( "position",position));


            }
        });


    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView pokemon_image;
        TextView pokemon_name;

        public void setInterfaceClickListener(InterfaceClickListener interfaceClickListener) {
            this.interfaceClickListener = interfaceClickListener;
        }

        InterfaceClickListener interfaceClickListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemon_image =  itemView.findViewById(R.id.pokemon_image);
            pokemon_name = itemView.findViewById(R.id.txt_pokemon_name);

            itemView.setOnClickListener(this );

        }

        @Override
        public void onClick(View view) {
            interfaceClickListener.onClick(view, getAdapterPosition());
        }
    }
}
