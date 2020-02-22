package com.example.googlemap;

import android.content.Intent;
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
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker myMaker_1,myMaker_2,myMaker_3,myMaker_4,myMaker_5;;
    private TextView tvSpotInfo;
    private Button btnAdd;
    private Button btnDone;
    DBHelper myDb;
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
        img= (ImageView) findViewById(R.id.image);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney, Australia, and move the camera.
        //LatLng seattle = new LatLng(47.608, -122.335);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng chihuly_glass_and_garden = new LatLng(47.622057, -122.350011);
        LatLng pike_place_market = new LatLng(47.609306, -122.341806);
        LatLng museum_of_fight = new LatLng(47.518189, -122.296373);
        LatLng space_needle = new LatLng(47.620745, -122.349202);
        LatLng sky_view_observatory = new LatLng(47.605353, -122.330442);

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(47.608, -122.335));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(11);

        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        myMaker_1 = googleMap.addMarker(new MarkerOptions()
                .position(chihuly_glass_and_garden)
                .title("Chihuly Glass and Garden")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMaker_2 = googleMap.addMarker(new MarkerOptions()
                .position(pike_place_market)
                .title("Pike Place Market")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMaker_3 = googleMap.addMarker(new MarkerOptions()
                .position(museum_of_fight)
                .title("Museum of Filght")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMaker_4 = googleMap.addMarker(new MarkerOptions()
                .position(space_needle)
                .title("Space Needle")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMaker_5 = googleMap.addMarker(new MarkerOptions()
                .position(sky_view_observatory)
                .title("Sky View Observatory")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        img.setImageResource(R.drawable.seattle);
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




}