package com.cinematools.aboutfilm.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cinematools.aboutfilm.R;
import com.cinematools.aboutfilm.model.data.Film;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Film> films;
    List<String> genresList;
    List<Integer> headerList;

    private int selectedPos = RecyclerView.NO_POSITION;

    private static final int TYPE_HEADER_GENRES = 0;
    private static int TYPE_HEADER_FILMS = 0;
    private static final int TYPE_ITEM_GENRES = 1;
    private static final int TYPE_ITEM_FILMS = 2;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClickGenres(View itemView, int position);

        void onItemClickFilm(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public RecycleViewAdapter() {
        films = new ArrayList<>();
        genresList = new ArrayList<>();
        headerList = new ArrayList<>();
    }

    public void setFilmsList(List<Film> filmsList) {
        films.clear();
        films.addAll(filmsList);
    }

    public void setGenresList(HashSet<String> genresList) {
        this.genresList.addAll(genresList);
        TYPE_HEADER_FILMS = genresList.size() + 1;
        headerList.add(TYPE_HEADER_FILMS);
        headerList.add(TYPE_HEADER_GENRES);
    }

    public int getGenresCount() {
        return genresList.size() + headerList.size();
    }

    public List<String> getGenresList() {
        return genresList;
    }

    public ArrayList<Film> getFilmsList() {
        return films;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == TYPE_HEADER_GENRES || i == TYPE_HEADER_FILMS) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_header, viewGroup, false);
            return new ViewHolderHeader(v);
        } else if (i == TYPE_ITEM_FILMS) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_film, viewGroup, false);
            return new ViewHolderFilms(v);
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_genres, viewGroup, false);
            return new ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof ViewHolder) {
            holder.itemView.setSelected(selectedPos == i);
            genresList = new ArrayList<>(genresList);
            if (genresList.size() != 0 && i <= genresList.size()) {
                ((ViewHolder) holder).genresTextView.setText(genresList.get(i - 1));
            }

        } else if (holder instanceof ViewHolderHeader) {
            if (i == TYPE_HEADER_GENRES) {
                ((ViewHolderHeader) holder).headerTextView.setText(R.string.header_genres);
            } else if (i == TYPE_HEADER_FILMS) {
                ((ViewHolderHeader) holder).headerTextView.setText(R.string.header_films);
            }
        } else if (holder instanceof ViewHolderFilms) {
            if (films.get(i - headerList.size() - genresList.size()).getImage_url() != null) {
                Picasso.get().load(films.get(i - headerList.size() - genresList.size()).getImage_url()).into(((ViewHolderFilms) holder).previewImageView);
            } else {
                ((ViewHolderFilms) holder).previewImageView.setVisibility(View.INVISIBLE);
            }
            ((ViewHolderFilms) holder).titleTextView.setText(films.get(i - headerList.size() - genresList.size()).getLocalizedName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_HEADER_GENRES) {
            return TYPE_HEADER_GENRES;
        } else if (position == TYPE_HEADER_FILMS) {
            return TYPE_HEADER_FILMS;
        } else if (position >= genresList.size() + headerList.size()) {
            return TYPE_ITEM_FILMS;
        }
        return TYPE_ITEM_GENRES;
    }

    @Override
    public int getItemCount() {
        return genresList.size() + films.size() + headerList.size();
    }

    private static class ViewHolderHeader extends RecyclerView.ViewHolder {

        TextView headerTextView;

        public ViewHolderHeader(View itemView) {
            super(itemView);
            headerTextView = itemView.findViewById(R.id.header);
        }
    }

    private class ViewHolderFilms extends RecyclerView.ViewHolder {

        ImageView previewImageView;
        TextView titleTextView;

        public ViewHolderFilms(View itemView) {
            super(itemView);
            previewImageView = itemView.findViewById(R.id.preview_film);
            titleTextView = itemView.findViewById(R.id.title_film);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClickFilm(itemView, position - headerList.size() - genresList.size());
                    }
                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView genresTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            genresTextView = itemView.findViewById(R.id.genres);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        notifyItemChanged(selectedPos);
                        selectedPos = getLayoutPosition();
                        notifyItemChanged(selectedPos);
                        listener.onItemClickGenres(itemView, position - 1);
                    }
                }
            });
        }
    }
}