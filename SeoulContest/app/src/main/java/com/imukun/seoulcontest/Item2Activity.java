package com.imukun.seoulcontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Item2Activity extends AppCompatActivity {

    private long origin;

    DatabaseReference mDatabase;
    MapData data;

    FirebaseAuth mAuth ;
    FirebaseUser fUser;




    public String key;
    boolean count = true;

    LatLng myLatLng;
    public double latitude;
    public double longitude;




    @BindView(R.id.itemFL)
    FrameLayout itemfl;

    @OnClick({R.id.itemFL})
    public void onclick(View v) {

        if (v.equals(itemfl)) {



            if(count){

                if(myLatLng.distanceTo(data.getLatLng())<=50)
                {pushbomul();}
               else{
                    Toast.makeText(Item2Activity.this, "해당 장소 근처가 아닙니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }else{

                Toast.makeText(Item2Activity.this, "이미 획득한 보물입니다.", Toast.LENGTH_SHORT).show();
                finish();

            }






        }






    }





    private void pushbomul() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(fUser.getUid()).child("bomul").push().setValue(key).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Item2Activity.this, "보물창고에 보관하였습니다", Toast.LENGTH_SHORT).show();

                finish();
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





        data =new MapData();
        setData();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(fUser.getUid()).child("bomul").addListenerForSingleValueEvent(new ValueEventListener() {
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


