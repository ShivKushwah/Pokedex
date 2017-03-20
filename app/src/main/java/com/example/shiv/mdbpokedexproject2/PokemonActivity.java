package com.example.shiv.mdbpokedexproject2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        String number = intent.getStringExtra("number");
        String attack = intent.getStringExtra("attack");
        String defense = intent.getStringExtra("defense");
        String hp = intent.getStringExtra("hp");
        String species = intent.getStringExtra("species");


        ImageView pokeImage = (ImageView) findViewById(R.id.imageView2);
        TextView pokeName = (TextView) findViewById(R.id.textView2);
        TextView pokeNumber = (TextView) findViewById(R.id.textView3);
        TextView pokeAttack = (TextView) findViewById(R.id.textView4);
        TextView pokeDefense = (TextView) findViewById(R.id.textView5);
        TextView pokeHP = (TextView) findViewById(R.id.textView6);
        TextView pokeSpecies = (TextView) findViewById(R.id.textView7);
        Button searchButton = (Button) findViewById(R.id.button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "http://www.google.com/#q=" + name;
                Uri uri = Uri.parse(str);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        if (name.equals("Flabébé")) {
            Glide.with(getApplicationContext()).load("http://img.pokemondb.net/artwork/" + "flabebe" + ".jpg").into(pokeImage);
        }
        else if (name.equals("Farfetch'd")) {

            Glide.with(getApplicationContext()).load("http://img.pokemondb.net/artwork/" + "farfetchd" + ".jpg").into(pokeImage);
        }
        else {
            Glide.with(getApplicationContext()).load("http://img.pokemondb.net/artwork/" + name.toLowerCase() + ".jpg").into(pokeImage);
        }


        pokeName.setText("" + name);
        pokeNumber.setText("#" + number);
        pokeAttack.setText("Attack: " + attack);
        pokeDefense.setText("Defense: " + defense);
        pokeHP.setText("HP: " + hp);
        pokeSpecies.setText("Species: " + species);


    }
}
