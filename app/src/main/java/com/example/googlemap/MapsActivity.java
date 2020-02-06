package com.example.googlemap;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker myMarker,myMaker_1,myMaker_2,myMaker_3,myMaker_4,myMaker_5,myMaker_6;
    private TextView tvSpotInfo;
    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tvSpotInfo = (TextView) findViewById(R.id.spotInfo);
        btnAdd = (Button) findViewById(R.id.Add);
        btnAdd.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney, Australia, and move the camera.
        LatLng seattle = new LatLng(47.608, -122.335);

        LatLng chihuly_glass_and_garden = new LatLng(47.622057, -122.350011);
        LatLng pike_place_market = new LatLng(47.609306, -122.341806);
        LatLng museum_of_fight = new LatLng(47.518189, -122.296373);
        LatLng space_needle = new LatLng(47.620745, -122.349202);
        LatLng sky_view_observatory = new LatLng(47.605353, -122.330442);
        LatLng green_lake_park = new LatLng(47.680544, -122.328450);
        LatLng japanese_garden = new LatLng(47.640720, -122.297677);


        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(47.608, -122.335));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(10);

        mMap.setOnMarkerClickListener(this);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        myMarker = googleMap.addMarker(new MarkerOptions()
                .position(seattle)
                .title("My Spot")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        //set markers for attraciton spots
//        myMaker_1 = googleMap.addMarker(new MarkerOptions()
//                .position(chihuly_glass_and_garden)
//                .title("Chihuly Glass and Garden"));

//        myMaker_2 = googleMap.addMarker(new MarkerOptions()
//                .position(pike_place_market)
//                .title("Pike Place Market"));
        myMaker_2 = googleMap.addMarker(new MarkerOptions()
                .position(japanese_garden)
                .title("Japanese Garden"));

        myMaker_3 = googleMap.addMarker(new MarkerOptions()
                .position(museum_of_fight)
                .title("Museum of Flight"));

        myMaker_4 = googleMap.addMarker(new MarkerOptions()
                .position(space_needle)
                .title("Space Needle"));

        myMaker_5 = googleMap.addMarker(new MarkerOptions()
                .position(green_lake_park)
                .title("Green Lake Park"));

        myMaker_6 = googleMap.addMarker(new MarkerOptions()
                .position(sky_view_observatory)
                .title("Sky View Observatory"));



    }

    @Override
    public boolean onMarkerClick(final Marker marker) {



        if (marker.equals(myMarker))
        {
            //System.out.println("Hello, it works!!!!!!!!!!!!!!!!!!!!!!!");
            tvSpotInfo.setText(myMarker.getTitle());
            btnAdd.setVisibility(View.VISIBLE);

        }

        return true;
    }

    public void toPlanActivity(View v){
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);


    }




}