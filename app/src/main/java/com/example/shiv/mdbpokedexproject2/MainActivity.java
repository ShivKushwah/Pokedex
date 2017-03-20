package com.example.shiv.mdbpokedexproject2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    RecyclerView rview;
    PokedexAdapter pokedexAdapter;

    boolean isGridView = false;

    //variables for the filter method
    public static int min_attack = 0;
    public static int min_defense = 0;
    public static int min_health = 0;
    public static ArrayList<String> type = new ArrayList<>();
    public static boolean displayAllTypes = true;

    //list of filtered pokemons to display
    private List<Pokedex.Pokemon> mModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize
        rview = (RecyclerView) findViewById(R.id.recyclableView);
        rview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Arraylist of all the pokemon
        ArrayList <Pokedex.Pokemon> pokedex = new Pokedex().getPokemon();

        //Since the pokedex types aren't working, initialize random types for proof of concept
        for (int i = 0; i < 30; i++) {
            pokedex.get(i).type = "Dragon";
        }
        for (int i = 31; i < 60; i++) {
            pokedex.get(i).type = "Fairy";
        }
        for (int i = 61; i < 90; i++) {
            pokedex.get(i).type = "Normal";
        }
        for (int i = 91; i < pokedex.size(); i++) {
            pokedex.get(i).type = "Fire";
        }



        pokedexAdapter = new PokedexAdapter(getApplicationContext(), pokedex, new Comparator<Pokedex.Pokemon>() {
            @Override
            public int compare(Pokedex.Pokemon pokemon, Pokedex.Pokemon t1) {
                return pokemon.name.compareTo(t1.name);
            }
        });

        rview.setAdapter(pokedexAdapter);

        //update mModels with all the pokemon to display on create
        mModels = new ArrayList<>();
        for (Pokedex.Pokemon k: pokedex) {
            mModels.add(k);
        }

        pokedexAdapter.add(mModels);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);



        return true;

    }

    //Implement active search bar listening
    @Override
    public boolean onQueryTextChange(String query) {

        //Case 1: If the query is an integer
        try {
            int x = Integer.parseInt(query);
            final ArrayList<Pokedex.Pokemon> filteredModelList = filter2(mModels, x);
            //Set the pokemons array to this new filtered list
            pokedexAdapter.pokemons = filteredModelList;
            //refresh the recycler view
            rview.setAdapter(pokedexAdapter);
            this.rview.scrollToPosition(0);
            return true;

        } catch (Throwable e) {
            //Case 2: If the query is the name
            final ArrayList<Pokedex.Pokemon> filteredModelList = filter(mModels, query);
            //Set the pokemons array to this new filtered list
            pokedexAdapter.pokemons = filteredModelList;
            //refresh the recycler view
            rview.setAdapter(pokedexAdapter);
            this.rview.scrollToPosition(0);
            return true;
        }

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }



    //Filters with respect to pokemon name
    private static ArrayList<Pokedex.Pokemon> filter(List<Pokedex.Pokemon> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final ArrayList<Pokedex.Pokemon> filteredModelList = new ArrayList<>();
        for (Pokedex.Pokemon model : models) {
            final String text = model.name.toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    //Filters with respect to pokemon number
    private static ArrayList<Pokedex.Pokemon> filter2(List<Pokedex.Pokemon> models, int query) {
        String numberString = Integer.toString(query);
        final ArrayList<Pokedex.Pokemon> filteredModelList = new ArrayList<>();
        for (Pokedex.Pokemon model : models) {
            final String text = model.number;
            if (text.contains(numberString)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //if user chose grid view icon
            case R.id.grid_view:
                //switch view logic

                if (!isGridView) {
                    rview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

                    isGridView = true;
                    return true;
                }
                else {

                    rview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    isGridView = false;
                    return true;

                }

            case R.id.search:

                return true;

            case R.id.filter:

                Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
                startActivity(intent);

                return true;

            case R.id.randomPoke:
                ArrayList <Pokedex.Pokemon> pokedex = new Pokedex().getPokemon();
                Random integer = new Random();
                ArrayList <Pokedex.Pokemon> randPoke = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    randPoke.add(pokedex.get(integer.nextInt(pokedex.size())));
                }
                mModels = randPoke;
                pokedexAdapter.pokemons = randPoke;
                rview.setAdapter(pokedexAdapter);

                this.rview.scrollToPosition(0);
                Toast toast3 = Toast.makeText(getApplicationContext(), R.string.toastForRandomPokemon, Toast.LENGTH_SHORT);
                toast3.show();




            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    //filter the pokemon based on min_attack, min_defense, and min_health
    private static ArrayList<Pokedex.Pokemon> filterAttributes(List<Pokedex.Pokemon> models) {

        final ArrayList<Pokedex.Pokemon> filteredModelList = new ArrayList<>();
        for (Pokedex.Pokemon model : models) {

            if (Integer.parseInt(model.attack) >= MainActivity.min_attack && Integer.parseInt(model.defense) >= MainActivity.min_defense && Integer.parseInt(model.hp) >= MainActivity.min_health) {
                if (displayAllTypes) {
                    filteredModelList.add(model);
                }
                else {
                    if (inList(MainActivity.type,model.type)) {
                        filteredModelList.add(model);
                    }
                }
            }
        }
        return filteredModelList;
    }



    private static boolean inList(ArrayList<String> list, String str) {
        boolean in = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(str)) {
                in = true;
            }
        }
        return in;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Filter the pokemon based on the attributes set in FilterActivity
        final ArrayList<Pokedex.Pokemon> filteredModelList = filterAttributes(mModels);
        mModels = filteredModelList;
        pokedexAdapter.pokemons = filteredModelList;

        this.rview.scrollToPosition(0);
    }

    //Lost points on this last time.
    //Is there any reason to include onPause method if it just invokes super?
    @Override
    protected void onPause() {
        super.onPause();
    }
}
