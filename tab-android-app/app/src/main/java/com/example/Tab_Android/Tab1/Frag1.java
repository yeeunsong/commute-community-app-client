package com.example.Tab_Android.Tab1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.Tab_Android.R;
import com.example.Tab_Android.RetrofitClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import user.UserData;


public class Frag1 extends Fragment {
    boolean isCommuted = false;
    boolean isInRange;
    int totalcommuted = 0;
    long clicktime;

    Button commute_button;
    TextView interval_time;
    TextView commute_time;
    TextView leave_time;
    TextView totalcommute;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    String id = UserData.getUserId();

    double lat1 =36.37418, long1 = 127.3659; //Location of the company
    double lat2=0, long2=0; //Location of the user
    double dist; //Distance of the company and the use

    private CommuteAPI commuteAPI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1_commute_map, container, false);

        supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        commuteAPI = RetrofitClient.getClient().create(CommuteAPI.class);

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            getCurrentLocation();
        }else{
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


        commute_button = (Button) view.findViewById(R.id.commute_button);
        interval_time = (TextView) view.findViewById(R.id.interval_time);
        commute_time = (TextView) view.findViewById(R.id.commute_time);
        leave_time = (TextView) view.findViewById(R.id.leave_time);
        totalcommute = (TextView) view.findViewById(R.id.totalcommute);

        commute_button.setOnClickListener( new View.OnClickListener() {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(isInRange){
                    if(!isCommuted){
                        // Get current time
                        clicktime = System.currentTimeMillis();
                        Date time = new Date(clicktime);
                        String commutetime = dateFormat.format(time);
                        // Get today's date
                        String date = LocalDate.now().toString();


                        commute_time.setText("Arrival time: " + commutetime );
                        leave_time.setText("Leave time: ");
                        interval_time.setText("Total work time " + Long.toString(0) + " hours");
                        isCommuted = true;
                        setButtonUI("Leave", R.color.red);

                        ArrivalData arrivalData = new ArrivalData(id, true, commutetime, date);
                        createArrival(view, arrivalData); // retrofit POST request
                    }
                    else{
                        long temp = clicktime;
                        clicktime = System.currentTimeMillis();
                        Date temp_time = new Date(temp);
                        Date time = new Date(clicktime);

                        // interval time calculation
                        long intervaltime = (time.getTime() - temp_time.getTime())/1000+1;
                        String string_s = ""+intervaltime;
                        String total_work = "Total work time " + Long.toString(intervaltime) + " hours";
                        SpannableStringBuilder sp = new SpannableStringBuilder(total_work);
                        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#006400")), 2, (2+string_s.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                        String leavetime = dateFormat.format(time);
                        leave_time.setText("Leave time: "+leavetime);
                        String date = LocalDate.now().toString();

                        interval_time.setText(sp);
                        isCommuted=false;
                        totalcommute.setText("Total work "+(++totalcommuted)+" days");
                        setButtonUI("Commute", R.color.green);

                        OffData offData = new OffData(id, true, leavetime, Long.toString(intervaltime), date);
                        createOff(view, offData); // retrofit POST request


                    }
                }
                else{
                    Toast.makeText(view.getContext(),"Too far from workplace",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Return view
        return view;
    }


    /////////////////////////////////////////
    // METHODS FOR COMMUTE BUTTON PRESSING //
    /////////////////////////////////////////
    private void setButtonUI(String text, int color) {
        commute_button.setText(text);
        commute_button.setBackgroundColor(ContextCompat.getColor( getActivity(), color));
    }


    private void createArrival(View view, ArrivalData arrivalData) {

        Call<ArrivalData> call = commuteAPI.createArrival(arrivalData);

        call.enqueue(new Callback<ArrivalData>() {
            @Override
            public void onResponse(Call<ArrivalData> call, Response<ArrivalData> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(view.getContext(), "Response Error: Code "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(view.getContext(),"Arrive to work: " + id,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrivalData> call, Throwable t) {
                Toast.makeText(view.getContext(), "Request Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void createOff(View view, OffData offData) {

        Call<OffData> call = commuteAPI.createOff(offData);

        call.enqueue(new Callback<OffData>() {
            @Override
            public void onResponse(Call<OffData> call, Response<OffData> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(view.getContext(), "Response Error: Code "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(view.getContext(),"Leave from work: " +id ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<OffData> call, Throwable t) {
                Toast.makeText(view.getContext(), "Request Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }



    /////////////////////////////////////
    // METHODS FOR LOCATION ESTIMATION //
    /////////////////////////////////////


    private void getCurrentLocation() {
        //Initialize task location
        @SuppressLint("MissingPermission") Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //When success
                if(location!=null){
                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            googleMap.clear();

                            //Get lat long numbers
                            lat2 = location.getLatitude();
                            long2 = location.getLongitude();

                            //Initialize lat lng
                            LatLng latLng_company = new LatLng(lat1, long1);
                            LatLng latLng_current = new LatLng(lat2, long2);


                            //Calculate distance
                            dist = distance(lat1, long1, lat2, long2);
                            if (dist<=100) isInRange = true;
                            System.out.format("Distance: %2f", dist);

                            //Create marker options
                            MarkerOptions user = new MarkerOptions().position(latLng_current).title("you are here")
                                    .icon(BitmapFromVector(getActivity().getApplicationContext(), R.drawable.ic_baseline_fiber_manual_record_24));
                            MarkerOptions company = new MarkerOptions().position(latLng_company).title("company")
                                    .icon(BitmapFromVector(getActivity().getApplicationContext(), R.drawable.ic_baseline_apartment_24));
                            //Zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng_current, 17));
                            //Add marker on map
                            googleMap.addMarker(user);
                            googleMap.addMarker(company);
                            googleMap.addCircle(new CircleOptions()
                                    .center(latLng_company)
                                    .radius(100)
                                    .strokeWidth(3f)
                                    .strokeColor(Color.argb(103, 128, 159, 1))
                                    .fillColor(Color.argb(103, 128, 159, 1))
                                    .visible(true));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //When permission granted
                //Call method
                getCurrentLocation();
            }
        }
    }


    private double distance(double lat1, double long1, double lat2, double long2) {

        //Calculate distance
        double longDiff = long1 - long2;
        //Calculate distance
        double distance = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(longDiff));
        distance = Math.acos(distance);
        //Convert distance radian to degree
        distance = rad2deg(distance);
        //Distance in meters
        distance = distance * 111139;
        //Return distance value
        return distance;

    }

    //Convert radian to degree
    private double rad2deg(double distance) {
        return (distance * 180.0/Math.PI);
    }

    //Convert degree to radian
    private double deg2rad(double lat1) {
        return (lat1*Math.PI/180.0);
    }

    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}


