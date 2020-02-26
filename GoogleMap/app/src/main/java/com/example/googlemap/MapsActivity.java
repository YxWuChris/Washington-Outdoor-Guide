package com.example.googlemap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.net.URI;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker myMarker_1, myMarker_2,myMarker_3,myMarker_4,myMarker_5;
    private TextView tvSpotInfo;
    private Button btnAdd;
    private Button btnDone;
    private DBHelper myDb;
    public static Marker myMarker;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tvSpotInfo = (TextView) findViewById(R.id.spotInfo);
        btnAdd = (Button) findViewById(R.id.Add);
        btnDone = (Button) findViewById(R.id.Done);
        btnAdd.setVisibility(View.INVISIBLE);
        btnDone.setVisibility(View.INVISIBLE);
        myDb = new DBHelper(this);
        img= (ImageView) findViewById(R.id.safety);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney, Australia, and move the camera.
        //LatLng seattle = new LatLng(47.608, -122.335);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng crystal_mountain = new LatLng(46.924846, -121.502764);
        LatLng stevens_pass = new LatLng(47.745112, -121.088860);
        LatLng mount_baker = new LatLng(48.775809, -121.814972);
        LatLng snoqualmie_pass = new LatLng(47.421947,  -121.420619);
        LatLng white_pass = new LatLng(46.639199, -121.390015);

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(47.608, -122.335));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(8);

        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        myMarker_1 = googleMap.addMarker(new MarkerOptions()
                .position(crystal_mountain)
                .title("crystal mountain")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMarker_2 = googleMap.addMarker(new MarkerOptions()
                .position(stevens_pass)
                .title("stevens pass")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMarker_3 = googleMap.addMarker(new MarkerOptions()
                .position(mount_baker)
                .title("mount baker")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMarker_4 = googleMap.addMarker(new MarkerOptions()
                .position(snoqualmie_pass)
                .title("snoqualmie pass")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMarker_5 = googleMap.addMarker(new MarkerOptions()
                .position(white_pass)
                .title("white pass")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        if(marker.equals(myMarker_1)) img.setImageResource(R.drawable.crystal_mountain);
        else if(marker.equals(myMarker_2)) img.setImageResource(R.drawable.stevens_pass);
        else if(marker.equals(myMarker_3)) img.setImageResource(R.drawable.mount_baker);
        else if(marker.equals(myMarker_4)) img.setImageResource(R.drawable.snoqualmie_pass);
        else if(marker.equals(myMarker_5)) img.setImageResource(R.drawable.white_pass);
        tvSpotInfo.setText(marker.getTitle());
        btnAdd.setVisibility(View.VISIBLE);
        btnDone.setVisibility(View.VISIBLE);
        myMarker = marker;

        return true;
    }

    public void addPlan(View v){
        boolean isInserted = myDb.insertData(myMarker.getTitle());
       if(isInserted == true) System.out.println("DB works!!!!!!!");

    }

    public void toPlanActivity(View v){
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);

    }

    public void getInfo(View v){


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        if (myMarker.getTitle().equals("crystal mountain")) intent.setData(Uri.parse("https://www.crystalmountainresort.com"));
        else if (myMarker.getTitle().equals("stevens pass")) intent.setData(Uri.parse("https://www.stevenspass.com"));
        else if (myMarker.getTitle().equals("mount baker")) intent.setData(Uri.parse("https://www.mtbaker.us"));
        else if (myMarker.getTitle().equals("snoqualmie pass")) intent.setData(Uri.parse("https://summitatsnoqualmie.com"));
        else if (myMarker.getTitle().equals("white pass")) intent.setData(Uri.parse("https://skiwhitepass.com/"));
        startActivity(intent);
    }




}