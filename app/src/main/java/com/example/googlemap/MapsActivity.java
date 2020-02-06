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
    private Marker myMarker;
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