package com.example.test_task.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.test_task.R;
import com.example.test_task.models.Cities;
import com.example.test_task.models.City;
import com.example.test_task.repository.RepositoryCallback;
import com.example.test_task.repository.RepositoryException;
import com.example.test_task.ui.activities.CitiesAdapter;
import com.example.test_task.ui.activities.MapActivity;
import com.example.test_task.utils.ToastUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CitiesFragment extends BaseFragment {
    public static final String TAG = CitiesFragment.class.getSimpleName();

    private List<City> citiesList;

    private CitiesAdapter citiesAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeToRefresh;

    public static CitiesFragment getInstance() {
        return new CitiesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities, container, false);
        initViews(view);
        initAdapter();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasInBackground()) {
            progressBar.setVisibility(View.VISIBLE);
            loadCities();
        }
    }

    private void initViews(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        swipeToRefresh = view.findViewById(R.id.swipeToRefresh);
        swipeToRefresh.setOnRefreshListener(new SwipeToRefreshListener());

        recyclerView = view.findViewById(R.id.cities_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        citiesAdapter = new CitiesAdapter(getActivity());
        citiesAdapter.setItemClickListener(new CityItemClickListener());
        recyclerView.setAdapter(citiesAdapter);
    }

    private void loadCities() {
        getRepositoryManager().getCities(new CitiesCallback());
    }

    public void sortByCity() {
        Collections.sort(citiesList, new Comparator<City>() {
            @Override
            public int compare(City city, City anotherCity) {
                return city.getCityName().compareToIgnoreCase(anotherCity.getCityName());
            }
        });
        citiesAdapter.notifyDataSetChanged();
    }

    public void sortById() {
        Collections.sort(citiesList, new Comparator<City>() {
            @Override
            public int compare(City city, City anotherCity) {
                return city.getCityId().compareTo(anotherCity.getCityId());
            }
        });
        citiesAdapter.notifyDataSetChanged();
    }

    private class CitiesCallback implements RepositoryCallback<Cities> {

        @Override
        public void onSuccess(Cities cities) {
            if (cities != null && !cities.getCitiesList().isEmpty()) {
                citiesList = cities.getCitiesList();
                citiesAdapter.setCityList(citiesList);
                citiesAdapter.notifyDataSetChanged();
            }
            progressBar.setVisibility(View.INVISIBLE);
            swipeToRefresh.setRefreshing(false);
        }

        @Override
        public void onError(RepositoryException repositoryException) {
            if (TextUtils.isEmpty(repositoryException.getMessage())) {
                ToastUtils.showShort(repositoryException.getMessage());
            } else {
                getString(R.string.error_server_response);
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    class SwipeToRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            loadCities();
        }
    }

    class CityItemClickListener implements CitiesAdapter.ItemClickListener {

        @Override
        public void onItemClick(City city) {
            if (city != null && getActivity() != null) {
                MapActivity.start(getActivity(), city);
            } else {
                ToastUtils.showShort(getString(R.string.error_location));
            }
        }
    }
}