package com.example.pokedex_jaime;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize media player for background music
        initMediaplayer();


    }


    void initMediaplayer(){

        MediaPlayer theme = MediaPlayer.create(MainActivity.this,
                R.raw.pokeapi_theme);
        theme.setLooping(true);
        theme.start();
    }

    public void changeActivityOnTouch(View view){
        Intent toPokedex = new Intent(this, Pokedex.class);
        startActivity(toPokedex);

    }
}
