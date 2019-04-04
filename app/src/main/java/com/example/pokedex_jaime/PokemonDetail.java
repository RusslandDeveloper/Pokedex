package com.example.pokedex_jaime;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokedex_jaime.Adapters.PokemonEvolutionAdapter;
import com.example.pokedex_jaime.Adapters.PokemonTypeAdapter;
import com.example.pokedex_jaime.Common.Common;
import com.example.pokedex_jaime.Model.Pokemon;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDetail extends Fragment {


    ImageView pokemon_image;
    TextView pokemon_name, pokemon_height,pokemon_weight;

    Pokemon pokemon;

    RecyclerView recycler_type, recycler_weakness,
            recycler_PEvolution,recycler_NEvolution;

    static PokemonDetail instance;

    public static PokemonDetail getInstance() {
        if(instance == null){

            instance = new PokemonDetail();
        }
        return instance;
    }

    public PokemonDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemview = inflater.inflate(R.layout.fragment_pokemon_detail,container,false);

        // Get position from argument
        if(getArguments().get("num") == null){

            pokemon = Common.commonPokemonList.get(getArguments().getInt("position"));

        }else {

            pokemon = Common.findPokemonByNum(getArguments().getString("num"));
        }

         pokemon_image = itemview.findViewById(R.id.pokemon_image_detail);
         pokemon_name = itemview.findViewById(R.id.txtV_name_detail);
         pokemon_height = itemview.findViewById(R.id.txtV_height_detail);
         pokemon_weight = itemview.findViewById(R.id.txtV_weight_detail);

         recycler_type = itemview.findViewById(R.id.recycler_type);
         recycler_type.setHasFixedSize(true);
         recycler_type.setLayoutManager(new LinearLayoutManager(getActivity(),
                 LinearLayoutManager.HORIZONTAL,false));

        recycler_weakness = itemview.findViewById(R.id.recycler_weakness);
        recycler_weakness.setHasFixedSize(true);
        recycler_weakness.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,false));

        recycler_PEvolution = itemview.findViewById(R.id.recycler_PEvolution);
        recycler_PEvolution.setHasFixedSize(true);
        recycler_PEvolution.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,false));

        recycler_NEvolution = itemview.findViewById(R.id.recycler_NEvolution);
        recycler_NEvolution.setHasFixedSize(true);
        recycler_NEvolution.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,false));



        setDetailsPokemon(pokemon);


        return itemview;
    }

    private void setDetailsPokemon(Pokemon pokemon) {

        // Load image
        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemon_image);

        pokemon_name.setText(pokemon.getName());

        pokemon_weight.setText(String.format("Weight: %s", pokemon.getWeight()));
        pokemon_height.setText(String.format("Height: %s",pokemon.getHeight() ));

        // Set type
        PokemonTypeAdapter typeAdapter = new PokemonTypeAdapter(getActivity(),pokemon.getType());
        recycler_type.setAdapter(typeAdapter);

        // Set weakness
        PokemonTypeAdapter weaknessAdapter = new PokemonTypeAdapter(getActivity(),pokemon.getWeaknesses());
        recycler_weakness.setAdapter(weaknessAdapter);

        // Set Evolutions
        PokemonEvolutionAdapter prevEvolutionAdapter = new PokemonEvolutionAdapter(getActivity(),pokemon.getPrev_evolution());
        recycler_PEvolution.setAdapter(prevEvolutionAdapter);

        PokemonEvolutionAdapter nextEvolutionAdapter = new PokemonEvolutionAdapter(getActivity(),pokemon.getNext_evolution());
        recycler_NEvolution.setAdapter(nextEvolutionAdapter);

    }

}
