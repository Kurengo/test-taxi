package com.example.test_task.net;


import com.example.test_task.models.Cities;

public interface NetManager {
    void loadCities(NetManagerImpl.NetManagerResponse<Cities> netManagerResponse);
}