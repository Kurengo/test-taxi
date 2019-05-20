package com.example.test_task.net;

import com.example.test_task.models.Cities;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManagerImpl implements NetManager {

    private static final String URL = "https://beta.taxistock.ru/taxik/api/";
    private NetApi netApi;

    public NetManagerImpl() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        netApi = retrofit.create(NetApi.class);
    }

    @Override
    public void loadCities(NetManagerResponse<Cities> netManagerResponse) {
        Call<Cities> call = netApi.loadCities();
        call.enqueue(new Request<>(netManagerResponse));
    }

    private class Request<T> implements Callback<T> {

        private NetManagerResponse<T> netManagerResponse;

        private Request(NetManagerResponse<T> netManagerResponse) {
            this.netManagerResponse = netManagerResponse;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (netManagerResponse != null) {
                netManagerResponse.onSuccess(response.body());
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            if (netManagerResponse != null) {
                netManagerResponse.onError(new NetException(t.getMessage()));
            }
        }
    }

    public interface NetManagerResponse<T> {

        void onSuccess(T data);

        void onError(NetException netException);
    }
}