package com.example.film3.listmovies;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.film3.R;
import com.example.film3.listmovies.detailmovie.DetailFilm;
import com.example.film3.listmovies.pojo.ResponseMovies;
import com.example.film3.listmovies.pojo.ResultsItem;

public class ListFilmFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private AlertDialog alertDialog;

    private Observer<ResponseMovies> getMovies = new Observer<ResponseMovies>() {
        @Override
        public void onChanged(ResponseMovies responseMovies) {
            if (responseMovies!=null){
                if (responseMovies.getAnError()==null){
                    ListFilmAdapter mAdapter = new ListFilmAdapter(responseMovies.getResults());
                    mAdapter.SetOnItemClickListener(new ListFilmAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ResultsItem model) {
                            Intent gotoDetailMovie = new Intent(view.getContext(), DetailFilm.class);
                            gotoDetailMovie.putExtra(DetailFilm.SELECTED_MOVIE,model);
                            startActivity(gotoDetailMovie);
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                }else{
                    alertDialog.setMessage(responseMovies.getAnError().getMessage());
                    alertDialog.show();
                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_film_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyler_view);
        progressBar = view.findViewById(R.id.progressBar);

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).create();

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ListFilmViewModel mViewModel = ViewModelProviders.of(this).get(ListFilmViewModel.class);
        mViewModel.doRequestListMovies();
        mViewModel.getMovies().observe(this,getMovies);
    }
}
