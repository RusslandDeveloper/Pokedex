package com.example.pokedex_jaime.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokedex_jaime.Common.Common;
import com.example.pokedex_jaime.Interface.InterfaceClickListener;
import com.example.pokedex_jaime.R;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;

import java.util.List;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.MyviewHolder> {

// This class also manages the weakness attribute of pokemon


    Context context;
    List<String>typeList;


    public PokemonTypeAdapter(Context context, List<String> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,
                viewGroup,false);


        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int position) {

        // Manager of the chip info

        myviewHolder.chip.setChipText(typeList.get(position));
        myviewHolder.chip.changeBackgroundColor(Common.getColorByType(typeList.get(position)));
        myviewHolder.setInterfaceClickListener(new InterfaceClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Fix crash?
            }
        });

    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }





    public class MyviewHolder extends RecyclerView.ViewHolder {

        Chip chip;

        InterfaceClickListener interfaceClickListener;

        public void setInterfaceClickListener(InterfaceClickListener interfaceClickListener) {
            this.interfaceClickListener = interfaceClickListener;
        }

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip_item);
            chip.setOnChipClickListener(new OnChipClickListener() {
                @Override
                public void onChipClick(View v) {

                    interfaceClickListener.onClick(v,getAdapterPosition());
                }
            });
        }
    }
}
