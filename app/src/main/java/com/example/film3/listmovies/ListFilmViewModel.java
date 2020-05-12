package com.example.film3.listmovies;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.film3.MovieCatalogue;
import com.example.film3.listmovies.pojo.ResponseMovies;

public class ListFilmViewModel extends ViewModel {

    private MutableLiveData<ResponseMovies> reposneMovies = new MutableLiveData<>();

    public MutableLiveData getMovies(){
        if (reposneMovies==null){
            doRequestListMovies();
        }
        return reposneMovies;
    }

    public void doRequestListMovies(){
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/movie")
                .addQueryParameter("api_key", MovieCatalogue.MOVIE_DB_API_KEY)
                .addQueryParameter("language","en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseMovies.class, new ParsedRequestListener<ResponseMovies>(){
                    @Override
                    public void onResponse(ResponseMovies responseMovies) {
                        reposneMovies.postValue(responseMovies);
                    }

                    @Override
                    public void onError(ANError anError) {
                        reposneMovies.setValue(new ResponseMovies(anError));
                    }
                });


    }
}
