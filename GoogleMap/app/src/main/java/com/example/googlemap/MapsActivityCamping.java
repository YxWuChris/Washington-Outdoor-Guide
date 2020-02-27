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
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityCamping extends FragmentActivity implements OnMapReadyCallback,OnMarkerClickListener {

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
        setContentView(R.layout.activity_maps_camping);
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

        LatLng royal_basin_campsite = new LatLng(47.833244, -123.211992);
        LatLng lake_pleasant_rv_park = new LatLng(47.791633, -122.214642);
        LatLng park_lake_day_camp = new LatLng(47.512806, -122.343582);
        LatLng harry_osbourne_cowboy_campsite = new LatLng(48.548041, -121.979707);
        LatLng cle_elum_river_group_site = new LatLng(47.456457, -121.126053);

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(47.608, -122.335));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(6);

        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        myMarker_1 = googleMap.addMarker(new MarkerOptions()
                .position(royal_basin_campsite)
                .title("royal basin campsite")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMarker_2 = googleMap.addMarker(new MarkerOptions()
                .position(lake_pleasant_rv_park)
                .title("lake pleasant rv park")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMarker_3 = googleMap.addMarker(new MarkerOptions()
                .position(park_lake_day_camp)
                .title("park lake day camp")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMarker_4 = googleMap.addMarker(new MarkerOptions()
                .position(harry_osbourne_cowboy_campsite)
                .title("harry osbourne cowboy campsite")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        myMarker_5 = googleMap.addMarker(new MarkerOptions()
                .position(cle_elum_river_group_site)
                .title("cle elum river group site")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        if(marker.equals(myMarker_1)) img.setImageResource(R.drawable.royal_basin_campsite);
        else if(marker.equals(myMarker_2)) img.setImageResource(R.drawable.lake_pleasant_rv_park);
        else if(marker.equals(myMarker_3)) img.setImageResource(R.drawable.park_lake_day_camp);
        else if(marker.equals(myMarker_4)) img.setImageResource(R.drawable.harry_osbourne_cowboy_campsite);
        else if(marker.equals(myMarker_5)) img.setImageResource(R.drawable.cle_elum_river_group_site);
        tvSpotInfo.setText(marker.getTitle());
        btnAdd.setVisibility(View.VISIBLE);
        btnDone.setVisibility(View.VISIBLE);
        myMarker = marker;

        return true;
    }

    public void addPlan(View v){
        boolean isInserted = myDb.insertData(myMarker.getTitle(),"Camping");
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
        if (myMarker.getTitle().equals("royal basin campsite")) intent.setData(Uri.parse("https://www.nps.gov/olym/planyourvisit/royal-basin.htm"));
        else if (myMarker.getTitle().equals("lake pleasant rv park")) intent.setData(Uri.parse("https://www.goodsam.com/campgrounds-rv-parks/washington/bothell/lake-pleasant-rv-park-890001015/"));
        else if (myMarker.getTitle().equals("park lake day camp")) intent.setData(Uri.parse("https://www.manta.com/d/mm6zncx/park-lake-day-camp"));
        else if (myMarker.getTitle().equals("harry osbourne cowboy campsite")) intent.setData(Uri.parse("https://thedyrt.com/camping/washington/harry-o-s-cowboy-camp"));
        else if (myMarker.getTitle().equals("cle elum river group site")) intent.setData(Uri.parse("https://www.recreation.gov/camping/campgrounds/233682"));
        startActivity(intent);
    }



    }