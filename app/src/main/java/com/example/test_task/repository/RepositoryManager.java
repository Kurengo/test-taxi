package com.example.test_task.repository;

import android.text.TextUtils;
import android.util.Log;

import com.example.test_task.App;
import com.example.test_task.R;
import com.example.test_task.models.Cities;
import com.example.test_task.net.NetException;
import com.example.test_task.net.NetManager;
import com.example.test_task.net.NetManagerImpl;

public class RepositoryManager {

    private NetManager netManager;

    public RepositoryManager(NetManager netManager) {
        this.netManager = netManager;
    }

    public void getCities(final RepositoryCallback<Cities> callback) {
        loadCitiesFromNet(new RepositoryCallback<Cities>() {

            @Override
            public void onSuccess(Cities cities) {
                if (callback != null) {
                    callback.onSuccess(cities);
                }
            }

            @Override
            public void onError(RepositoryException repositoryException) {
                if (callback != null) {
                    Log.d("logd", "onError: "+repositoryException.getMessage());
                    callback.onError(repositoryException);
                }
            }
        });
    }

    private void loadCitiesFromNet(final RepositoryCallback<Cities> callback) {
        netManager.loadCities(new NetManagerImpl.NetManagerResponse<Cities>() {
            @Override
            public void onSuccess(Cities cities) {
                if (callback != null) {
                    callback.onSuccess(cities);
                }
            }

            @Override
            public void onError(NetException netException) {
                if (callback != null) {
                    String errorString = App.getInstance().getString(R.string.error_server_response);
                    if (!TextUtils.isEmpty(netException.getMessage())) {
                        errorString = netException.getMessage();
                    }
                    callback.onError(new RepositoryException(errorString));
                }
            }
        });
    }
}