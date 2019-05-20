package com.example.test_task.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.test_task.R;
import com.example.test_task.models.City;
import com.example.test_task.utils.ToastUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends BaseActivity {
    private static final String CITY_EXTRA = "city_extra";
    private static final int MAP_ZOOM = 12;

    private MapView mapView;
    private City city;

    public static void start(Context context, City city) {
        Intent intent = new Intent(context, MapActivity.class);
        if (city != null) {
            intent.putExtra(CITY_EXTRA, city);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        if (getIntent() != null) {
            city = getIntent().getParcelableExtra(CITY_EXTRA);
        }
        initMap(savedInstanceState);
    }

    private void initMap(Bundle savedInstanceState) {
        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new MapCallback());
        MapsInitializer.initialize(getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private class MapCallback implements OnMapReadyCallback {

        @Override
        public void onMapReady(GoogleMap map) {
            if (city != null) {
                Double latitude = city.getCityLatitude();
                Double longitude = city.getCityLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                String title = getString(R.string.marker_title, city.getCityName());
                map.addMarker(buildMarkerOptions(title, latLng));

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(latLng)
                        .zoom(MAP_ZOOM)
                        .build();

                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            } else {
                ToastUtils.showShort(getString(R.string.error_load_city));
            }
        }

        private MarkerOptions buildMarkerOptions(String markerTitle, LatLng latLng) {
            String title = getString(R.string.marker_title);
            if (!TextUtils.isEmpty(markerTitle)) {
                title = markerTitle;
            }
            return new MarkerOptions()
                    .position(latLng)
                    .title(title);
        }
    }
}