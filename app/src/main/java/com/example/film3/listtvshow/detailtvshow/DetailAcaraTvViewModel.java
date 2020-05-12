package com.example.film3.listtvshow.detailtvshow;

import androidx.lifecycle.ViewModel;

import com.example.film3.listtvshow.pojo.ResultsItem;

public class DetailAcaraTvViewModel extends ViewModel {
    private ResultsItem resultsItem;
    public ResultsItem getResultsItem(){
        return resultsItem;
    }
    public void setResultsItem(ResultsItem resultsItem){
        this.resultsItem = resultsItem;
    }
}
