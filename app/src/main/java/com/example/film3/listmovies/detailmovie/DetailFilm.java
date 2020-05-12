package com.example.film3.listmovies.detailmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.film3.R;
import com.example.film3.databinding.ActivityDetailFilmBinding;
import com.example.film3.listmovies.pojo.ResultsItem;

import java.util.Objects;

public class DetailFilm extends AppCompatActivity {
    public static final String SELECTED_MOVIE = "selected_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailFilmViewModel viewModel = ViewModelProviders.of(this).get(DetailFilmViewModel.class);
        ActivityDetailFilmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_film);
        ResultsItem movieModel = getIntent().getParcelableExtra(SELECTED_MOVIE);
        viewModel.setResultsItem(movieModel);
        binding.setViewmodel(viewModel);

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+movieModel.getPosterPath()).into(binding.imgView);
        setTitle(viewModel.getResultsItem().getTitle());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}