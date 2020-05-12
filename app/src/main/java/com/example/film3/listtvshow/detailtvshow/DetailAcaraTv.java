package com.example.film3.listtvshow.detailtvshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.film3.R;
import com.example.film3.databinding.ActivityAcaratvBinding;
import com.example.film3.listtvshow.pojo.ResultsItem;

import java.util.Objects;

public class DetailAcaraTv extends AppCompatActivity {
    public static final String SELECTED_TV_SHOWS = "selected_tv_show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailAcaraTvViewModel viewModel = ViewModelProviders.of(this).get(DetailAcaraTvViewModel.class);
        ActivityAcaratvBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_acaratv);
        ResultsItem pilemModel = getIntent().getParcelableExtra(SELECTED_TV_SHOWS);
        viewModel.setResultsItem(pilemModel);
        binding.setViewmodel(viewModel);

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+pilemModel.getPosterPath()).into(binding.imgView);
        setTitle(viewModel.getResultsItem().getName());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() { finish();
    return true;
    }
}
