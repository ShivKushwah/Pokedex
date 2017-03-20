package com.example.shiv.mdbpokedexproject2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Shiv on 2/11/17.
 */

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.CustomViewHolder> {


    private final SortedList<Pokedex.Pokemon> mSortedList = new SortedList<>(Pokedex.Pokemon.class, new SortedList.Callback<Pokedex.Pokemon>() {
        @Override
        public int compare(Pokedex.Pokemon a, Pokedex.Pokemon b) {
            return mComparator.compare(a, b);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(Pokedex.Pokemon oldItem, Pokedex.Pokemon newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(Pokedex.Pokemon item1, Pokedex.Pokemon item2) {
            return item1 == item2;
        }
    });

    private final Comparator<Pokedex.Pokemon> mComparator;
    Context context;
    public ArrayList<Pokedex.Pokemon> pokemons;


    public PokedexAdapter(Context context, ArrayList<Pokedex.Pokemon> pokemons, Comparator<Pokedex.Pokemon> comparator) {
        this.context = context;
        this.pokemons = pokemons;
        mComparator = comparator;
    }



    @Override
    public PokedexAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new CustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final PokedexAdapter.CustomViewHolder holder, int position) {

        //Just like Intents Worksheet 2
        final Pokedex.Pokemon poke = pokemons.get(position);
        holder.textView.setText(poke.name);



        class DownloadFilesTask extends AsyncTask<String, Void, Bitmap> {
            protected Bitmap doInBackground(String... strings) {

                try {

                    if (strings[0].equals("Flabébé")) {
                        return Glide.with(context).load("http://img.pokemondb.net/artwork/" + "flabebe" + ".jpg").asBitmap().into(100, 100).get(); // .into(holder.imageView);
                    } else if (strings[0].equals("Farfetch'd")) {

                        return Glide.with(context).load("http://img.pokemondb.net/artwork/" + "farfetchd" + ".jpg").asBitmap().into(100, 100).get();//

                    } else {
                        return Glide.with(context).load("http://img.pokemondb.net/artwork/" + strings[0].toLowerCase() + ".jpg").asBitmap().into(100, 100).get();//
                    }
                } catch (Exception e) {
                    return null;
                }


                /*

                try {return Glide.
                        with(context).
                        load(strings[0]).
                        asBitmap().
                        into(100, 100). // Width and height
                        get();}
                catch (Exception e) {return null;}
                */
            }

            protected void onProgressUpdate(Void... progress) {}

            protected void onPostExecute(Bitmap result) {

                holder.imageView.setImageBitmap(result);
                /*

                if (poke.name.equals("Flabébé")) {
                    Glide.with(context).load("http://img.pokemondb.net/artwork/" + "flabebe" + ".jpg").into(holder.imageView);
                }
                else if (poke.name.equals("Farfetch'd")) {

                    Glide.with(context).load("http://img.pokemondb.net/artwork/" + "farfetchd" + ".jpg").into(holder.imageView);
                }
                else {
                    Glide.with(context).load("http://img.pokemondb.net/artwork/" + poke.name.toLowerCase() + ".jpg").into(holder.imageView);
                }
                */



            }

        }
        (new DownloadFilesTask()).execute(poke.name);



        /*
        //Get images

        if (poke.name.equals("Flabébé")) {
            Glide.with(context).load("http://img.pokemondb.net/artwork/" + "flabebe" + ".jpg").into(holder.imageView);
        }
        else if (poke.name.equals("Farfetch'd")) {

            Glide.with(context).load("http://img.pokemondb.net/artwork/" + "farfetchd" + ".jpg").into(holder.imageView);
        }
        else {
            Glide.with(context).load("http://img.pokemondb.net/artwork/" + poke.name.toLowerCase() + ".jpg").into(holder.imageView);
        }
        */


        //Send to PokemonActivity if Clicked
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PokemonActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", poke.name);
                intent.putExtra("number", poke.number);
                intent.putExtra("hp", poke.hp);
                intent.putExtra("attack", poke.attack);
                intent.putExtra("defense", poke.defense);
                intent.putExtra("species", poke.species);

                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void add(List<Pokedex.Pokemon> models) {
        mSortedList.addAll(models);
    }

    //Class for a Pokemon row
    public class CustomViewHolder extends RecyclerView.ViewHolder {


        TextView textView;
        ImageView imageView;

        public CustomViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            imageView = (ImageView) view.findViewById(R.id.imageView);


        }
    }



}
