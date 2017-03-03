package au.com.wsit.project12.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import au.com.wsit.project12.R;
import au.com.wsit.project12.adapters.RoverAdapter;
import au.com.wsit.project12.api.RoverApi;
import au.com.wsit.project12.model.Rover;
import au.com.wsit.project12.utils.Constants;
import au.com.wsit.project12.utils.Generator;

/**
 * Created by guyb on 28/02/17.
 */

public class OpportunityFragment extends Fragment
{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RoverAdapter roverAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_opportunity, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.roverRecyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        roverAdapter = new RoverAdapter(getContext());
        recyclerView.setAdapter(roverAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                getImages();
            }
        });

        getImages();


        return rootView;
    }

    // Call to the API
    private void getImages()
    {
        RoverApi roverApi = new RoverApi();
        swipeRefreshLayout.setRefreshing(true);
        roverApi.getImages(Constants.OPPORTUNITY, new RoverApi.RoverCallback()
        {

            @Override
            public void onResult(ArrayList<Rover> roverData)
            {
                swipeRefreshLayout.setRefreshing(false);
                roverAdapter.swap(roverData);
            }

            @Override
            public void onFail(String errorMessage)
            {
                swipeRefreshLayout.setRefreshing(false);
                Snackbar.make(swipeRefreshLayout, errorMessage, Snackbar.LENGTH_LONG).show();

            }
        });
    }
}
