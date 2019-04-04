package com.example.pokedex_jaime.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pokedex_jaime.Common.Common;
import com.example.pokedex_jaime.Interface.InterfaceClickListener;
import com.example.pokedex_jaime.Model.Evolution;
import com.example.pokedex_jaime.R;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;

import java.util.ArrayList;
import java.util.List;

public class PokemonEvolutionAdapter extends RecyclerView.Adapter<PokemonEvolutionAdapter.MyviewHolder> {

// This class also manages the weakness attribute of pokemon


    Context context;
    List<Evolution> evolutionList;

    public PokemonEvolutionAdapter(Context context, List<Evolution> evolutionList) {
        this.context = context;
        if(evolutionList != null){

            this.evolutionList = evolutionList;
        }else{

            this.evolutionList = new ArrayList<>(); // Fix crash for pre evolutions
        }

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,
                viewGroup,false);

        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonEvolutionAdapter.MyviewHolder myviewHolder, int position) {
        // Manager of the chip info

        myviewHolder.chip.setChipText(evolutionList.get(position).getName());
        myviewHolder.chip.changeBackgroundColor(
                Common.getColorByType(Common.findPokemonByNum
                        (evolutionList.get(position).getNum()
                        ).getType().get(0)));



    }

    @Override
    public int getItemCount() {
        return evolutionList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        Chip chip;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip_item);
            chip.setOnChipClickListener(new OnChipClickListener() {
                @Override
                public void onChipClick(View v) {

                    LocalBroadcastManager.getInstance(context).sendBroadcast
                            (new Intent(Common.KEY_NUM_EVOLUTION).
                                    putExtra("num",evolutionList.get(getAdapterPosition())
                                    .getNum()));
                }
            });
        }
    }
}