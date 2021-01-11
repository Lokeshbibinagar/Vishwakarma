package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    String longitude,latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

         longitude = getIntent().getStringExtra("mapLong");
         latitude = getIntent().getStringExtra("mapLat");
        Toast.makeText(this, longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        double lon = Double.parseDouble(longitude);
        double lat = Double.parseDouble(latitude);


        LatLng location = new LatLng(lon,lat);
        map.addMarker(new MarkerOptions().position(location).title("Its here"));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10), 5000,null);
    }
}