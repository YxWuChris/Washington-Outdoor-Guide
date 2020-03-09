package com.example.googlemap;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.List;

public class MapActivityWater extends FragmentActivity implements OnMapReadyCallback,OnMarkerClickListener {

    private GoogleMap mMap;
    private List<MarkerInfo> markerInfo;
    private TextView tvSpotInfo;
    private Button btnAdd;
    private Button btnDone;
    private Button toPlan;
    private DBHelper myDb;
    public static Marker myMarker;
    private ImageView img;
    private DataBaseAcess dataBaseAcess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_water);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tvSpotInfo = (TextView) findViewById(R.id.spotInfo);
        btnAdd = (Button) findViewById(R.id.Add);
        btnDone = (Button) findViewById(R.id.Done);
        toPlan = (Button)findViewById(R.id.seePlan);
        btnAdd.setVisibility(View.INVISIBLE);
        btnDone.setVisibility(View.INVISIBLE);
        myDb = new DBHelper(this);

        img= (ImageView) findViewById(R.id.safety);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;





        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(47.65, -122.3));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(11);

        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        DataBaseAcess dataBaseAcess = DataBaseAcess.getInstance(getApplicationContext());
        dataBaseAcess.open();

        markerInfo = dataBaseAcess.setMarkers("Water");
        for(int i = 0; i<markerInfo.size();i++){
            Marker marker;
            LatLng newLocation = new LatLng(markerInfo.get(i).getlat(),markerInfo.get(i).getlongt());
            if(markerInfo.get(i).getType().equals("skuba_diving")){
                marker = googleMap.addMarker(new MarkerOptions().position(newLocation)
                        .title(markerInfo.get(i).getName()).snippet("Skuba Diving")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_water_dive)));

            }else if(markerInfo.get(i).getType().equals("Kiteboading")){
                marker = googleMap.addMarker(new MarkerOptions().position(newLocation)
                        .title(markerInfo.get(i).getName()).snippet("Kiteboading")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_water_kiteboarding)));

            }else if(markerInfo.get(i).getType().equals("Kayak")){
                marker = googleMap.addMarker(new MarkerOptions().position(newLocation)
                        .title(markerInfo.get(i).getName()).snippet("Kayak")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_water_kayak)));

            }else if(markerInfo.get(i).getType().equals("surf")){
                marker = googleMap.addMarker(new MarkerOptions().position(newLocation)
                        .title(markerInfo.get(i).getName()).snippet("Surf")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.water_icon_surf)));

            }else if(markerInfo.get(i).getType().equals("swim")){
                marker = googleMap.addMarker(new MarkerOptions().position(newLocation)
                        .title(markerInfo.get(i).getName()).snippet("Swim")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_water_swim)));

            }
            else if(markerInfo.get(i).getType().equals("sail")){
                marker = googleMap.addMarker(new MarkerOptions().position(newLocation)
                        .title(markerInfo.get(i).getName()).snippet("Sail")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.water_icon_sail)));

            }
            else if(markerInfo.get(i).getType().equals("water ski")){
                marker = googleMap.addMarker(new MarkerOptions().position(newLocation)
                        .title(markerInfo.get(i).getName()).snippet("Water Ski")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_water_waterski)));

            }
        }

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        dataBaseAcess = DataBaseAcess.getInstance(getApplicationContext());
        dataBaseAcess.open();
        byte[] imageByte = dataBaseAcess.searchImageFromDB("Water",marker.getTitle());
        showImageFromByte(imageByte);
        tvSpotInfo.setText(marker.getSnippet()+" Spot:  "+ marker.getTitle());
        btnAdd.setVisibility(View.VISIBLE);
        btnDone.setVisibility(View.VISIBLE);
        myMarker = marker;
        toPlan.setVisibility(View.GONE);


        return true;
    }



    public void toPlanActivity(View v){
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);

    }

    public void getInfo(View v){


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

            dataBaseAcess = DataBaseAcess.getInstance(getApplicationContext());
            dataBaseAcess.open();
            String website = dataBaseAcess.searchWebsiteFromDB("Water",myMarker.getTitle());
            intent.setData(Uri.parse(website));



        startActivity(intent);
    }
    public void addPlan(View v){
        boolean isInserted = myDb.insertData(myMarker.getTitle(),"Water");
        Toast toast = Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showImageFromByte(byte[] photo){

        ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
        Bitmap bitmap= BitmapFactory.decodeStream(imageStream);
        img.setImageBitmap(bitmap);

    }




}