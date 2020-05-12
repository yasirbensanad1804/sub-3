package com.example.film3.listmovies.detailmovie;

import androidx.lifecycle.ViewModel;

import com.example.film3.listmovies.pojo.ResultsItem;

public class DetailFilmViewModel extends ViewModel {
    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem (ResultsItem resultsItem) {
        this.resultsItem = resultsItem;
    }
}
