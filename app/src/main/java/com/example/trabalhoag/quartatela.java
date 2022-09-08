package com.example.trabalhoag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class quartatela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quartatela);
        getSupportActionBar().hide();

    }

    public void onClick (View v){
        Intent bacana = new Intent(quartatela.this, terceiratela.class);
        startActivity(bacana);
    }

        TextView log;
        TextView lat;
        TextView bairro;
        TextView est;
        TextView pais;

        private Location lugar;
        private LocationManager gps;

        private Address ender;

        public void busca (View v){
            lat = findViewById(R.id.lat);
            log = findViewById(R.id.lon);
            bairro = findViewById(R.id.bairro);
            est = findViewById(R.id.est);
            pais = findViewById(R.id.pais);

            double lati = 0.0;
            double logi = 0.0;

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            } else{
                gps = (LocationManager)
                        getSystemService(Context.LOCATION_SERVICE);
                lugar = gps.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            if(lugar != null){

                lati = lugar.getLatitude();
                logi = lugar.getLongitude();
            }

            log.setText("Longitude: " + logi);
            lat.setText("Latitude: " + lati);

            try {
                ender = buscarEndereco(lati, logi);

                bairro.setText("Bairro: " +ender.getSubLocality());
                est.setText("Estado: " +ender.getAdminArea());
                pais.setText("País: " +ender.getCountryName());

            } catch (IOException e) {

                Log.i("GPS", e.getMessage());
            }
        }

        public Address buscarEndereco(double lat, double log)
                throws IOException {

            Geocoder geocoder;
            Address address = null;
            List<Address> addresses;

            geocoder = new Geocoder(getApplicationContext());

            addresses = geocoder.getFromLocation(lat, log,1);
            if (addresses.size() > 0){
                address = addresses.get(0);

            }
            return address;

        }

    }
