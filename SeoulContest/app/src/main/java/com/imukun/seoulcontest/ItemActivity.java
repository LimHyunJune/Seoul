package com.imukun.seoulcontest;

import androidx.annotation.NonNull;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.util.FusedLocationSource;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemActivity extends BaseActivity {

    private long origin;

    DatabaseReference mDatabase;
    MapData data;

    FirebaseAuth mAuth ;
    FirebaseUser fUser;

    LatLng myLatLng;

    FusedLocationSource mLocationSource;
    NaverMap mNaverMap;



    double longitude;
    double latitude;










    public String key;

    boolean count = true;





    @BindView(R.id.itemFL)
    FrameLayout itemfl;

    @OnClick({R.id.itemFL})
    public void onclick(View v) {

        if (v.equals(itemfl)) {

            if(count ){

                if(myLatLng.distanceTo(data.getLatLng())<=50)
                { pushdata();}
              else{
                    Toast.makeText(ItemActivity.this, "해당 장소 근처가 아닙니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }else{

                Toast.makeText(ItemActivity.this, "이미 방문한 지점입니다", Toast.LENGTH_SHORT).show();
                finish();

            }





        }






        }





    private void pushbomul() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(fUser.getUid()).child("bomul").push().setValue(key).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                finish();
                                                                                                                          }
                                                                                                                      }
        );
    }


    private void pushdata() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(fUser.getUid()).child("routezongro").push().setValue(key).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ItemActivity.this, "방문 완료", Toast.LENGTH_SHORT).show();

                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("users").child(fUser.getUid()).child("routezongro").addListenerForSingleValueEvent(new ValueEventListener() {

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        origin = dataSnapshot.getChildrenCount();
                        if(origin==4 || origin ==7 || origin == 9|| origin ==13)
                        {

                            Intent intent = new Intent(ItemActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        }
        );




    }



    @BindView(R.id.itemTV)
    TextView itemtv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);

        mAuth=FirebaseAuth.getInstance();
        fUser=mAuth.getCurrentUser();






        key = getIntent().getStringExtra("KEY");
        longitude = getIntent().getDoubleExtra("myLng",0);
        latitude = getIntent().getDoubleExtra("myLat",0);

    myLatLng = new LatLng(latitude,longitude);

        data =new MapData();
        setData();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(fUser.getUid()).child("routezongro").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //If email exists then toast shows else store the data on new key
                    if (!data.getValue(String.class).equals(key)) {



                    } else {
                        count =false;


                    }
                }
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
            }
        });


    }





    private void setData() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("zongro").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = dataSnapshot.getValue(MapData.class);
                setInfo();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

    private void setInfo() {


        itemtv.setText(data.getComplex());
    }
}

