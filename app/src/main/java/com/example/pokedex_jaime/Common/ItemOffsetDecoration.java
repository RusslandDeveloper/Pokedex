package com.example.pokedex_jaime.Common;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.reactivex.annotations.NonNull;

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int itemOffset;

    public ItemOffsetDecoration(int itemOffset){
            this.itemOffset = itemOffset;

    }

    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int dimens){

        this(context.getResources().getDimensionPixelSize(dimens));

    }


    @Override
    public void getItemOffsets(@android.support.annotation.NonNull Rect outRect, @android.support.annotation.NonNull View view, @android.support.annotation.NonNull RecyclerView parent, @android.support.annotation.NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(itemOffset,itemOffset,itemOffset,itemOffset);
    }
}
