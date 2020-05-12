package com.example.film3.listtvshow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.film3.R;
import com.example.film3.listtvshow.detailtvshow.DetailAcaraTv;
import com.example.film3.listtvshow.pojo.ResponseTv;
import com.example.film3.listtvshow.pojo.ResultsItem;


public class ListAcaraTvFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlertDialog alertDialog;
    private ProgressBar progressBar;

    private Observer <ResponseTv> getTvShow = new Observer<ResponseTv>() {
        @Override
        public void onChanged(ResponseTv responseTv) {
            if (responseTv!=null){
                if (responseTv.getAnError()==null){
                    ListAcaraTvAdapter mAdapter = new ListAcaraTvAdapter(responseTv.getResults());
                    mAdapter.SetOnItemClickListener(new ListAcaraTvAdapter.OnItemClickListener() {
                        @Override
                        public void OnItemClick(View view, ResultsItem model) {
                            Intent gotoDetail = new Intent(view.getContext(), DetailAcaraTv.class);
                            gotoDetail.putExtra(DetailAcaraTv.SELECTED_TV_SHOWS,model);
                            startActivity(gotoDetail);
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                }else{
                    alertDialog.setMessage(responseTv.getAnError().getMessage());
                    alertDialog.show();
                }
            }
            progressBar.setVisibility(View.GONE);
        }

    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_acaratv_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
  //unchecked
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyler_view);
        progressBar = view.findViewById(R.id.progressBar);

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ListAcaraTvViewModel mViewModel = ViewModelProviders.of(this).get(ListAcaraTvViewModel.class);
        mViewModel.doRequestListTvShow();
        mViewModel.getTvShowList().observe(this, getTvShow);
    }
}
