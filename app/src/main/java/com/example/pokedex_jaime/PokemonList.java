package com.example.pokedex_jaime;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokedex_jaime.Adapters.PokemonListAdapter;
import com.example.pokedex_jaime.Common.Common;
import com.example.pokedex_jaime.Common.ItemOffsetDecoration;
import com.example.pokedex_jaime.Model.Pokedex;
import com.example.pokedex_jaime.RetrofitConfig.PokemonInterface;
import com.example.pokedex_jaime.RetrofitConfig.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonList extends Fragment {

    // Loading data from the Pokemon API


    PokemonInterface pokemonInterface;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView pokemon_list_RecyclerView;


  static PokemonList instance;

  public static PokemonList getInstance(){

        if(instance == null){
            instance = new PokemonList();
        }

        return instance;

  }

    public PokemonList() {
        // Required empty public constructor
        Retrofit retrofit = RetrofitClient.getInstance();
        pokemonInterface = retrofit.create(PokemonInterface.class);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        // Calling recycler view
        pokemon_list_RecyclerView = view.findViewById(R.id.pokemon_list_RecyclerView);
        pokemon_list_RecyclerView.setHasFixedSize(true);
        pokemon_list_RecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        ItemOffsetDecoration itemOffsetDecoration = new
                ItemOffsetDecoration(getActivity(),R.dimen.spacing);
        pokemon_list_RecyclerView.addItemDecoration(itemOffsetDecoration);

        fetchdata();

        return view;
    }


    // Fetching data with RX java
    private void fetchdata() {
      compositeDisposable.add(pokemonInterface.getListPokemon()
      .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Consumer<Pokedex>() {
                  @Override
                  public void accept(Pokedex pokedex) throws Exception {
                      Common.commonPokemonList = pokedex.getPokemon();
                      PokemonListAdapter adapter = new PokemonListAdapter
                              (getActivity(), Common.commonPokemonList);
                      pokemon_list_RecyclerView.setAdapter(adapter);
                  }
              }));


    }

}
