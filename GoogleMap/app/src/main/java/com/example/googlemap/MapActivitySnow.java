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

public class MapActivitySnow extends FragmentActivity implements OnMapReadyCallback,OnMarkerClickListener {

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
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(7);

        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        DataBaseAcess dataBaseAcess = DataBaseAcess.getInstance(getApplicationContext());
        dataBaseAcess.open();

        markerInfo = dataBaseAcess.setMarkers("Snow");
        for(int i = 0; i<markerInfo.size();i++){
            Marker marker;
            LatLng newLocation = new LatLng(markerInfo.get(i).getlat(),markerInfo.get(i).getlongt());
            if(markerInfo.get(i).getType().equals("Skiing")){
                marker = googleMap.addMarker(new MarkerOptions().position(newLocation)
                        .title(markerInfo.get(i).getName()).snippet("Skiing")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.snow_icon_skii)));

            }else if(markerInfo.get(i).getType().equals("Snow Tubing")){
                marker = googleMap.addMarker(new MarkerOptions().position(newLocation)
                        .title(markerInfo.get(i).getName()).snippet("Snow Tubing")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_snow_tubing)));

            }
        }

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        dataBaseAcess = DataBaseAcess.getInstance(getApplicationContext());
        dataBaseAcess.open();
        byte[] imageByte = dataBaseAcess.searchImageFromDB("Snow",marker.getTitle());
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
        String website = dataBaseAcess.searchWebsiteFromDB("Snow",myMarker.getTitle());
        intent.setData(Uri.parse(website));



        startActivity(intent);
    }
    public void addPlan(View v){
        boolean isInserted = myDb.insertData(myMarker.getTitle(),"Snow");
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
