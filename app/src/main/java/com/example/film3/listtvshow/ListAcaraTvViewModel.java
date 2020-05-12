package com.example.film3.listtvshow;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.film3.MovieCatalogue;
import com.example.film3.listtvshow.pojo.ResponseTv;

public class ListAcaraTvViewModel extends ViewModel {
    private MutableLiveData<ResponseTv> responseTvShow = new MutableLiveData<>();

    MutableLiveData getTvShowList() {
        if (responseTvShow == null) {
            doRequestListTvShow();
        }
        return  responseTvShow;
    }

    void doRequestListTvShow() {
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/tv")
                .addQueryParameter("api_key", MovieCatalogue.MOVIE_DB_API_KEY)
                .addQueryParameter("language", "en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseTv.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        responseTvShow.postValue((ResponseTv) response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        responseTvShow.setValue(new ResponseTv(anError));

                    }
                });
    }
}
