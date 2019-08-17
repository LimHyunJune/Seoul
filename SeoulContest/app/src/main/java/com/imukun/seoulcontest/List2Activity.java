package com.imukun.seoulcontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class List2Activity extends AppCompatActivity {

    private long count;



    FirebaseAuth mAuth ;
    FirebaseUser fUser;
    DatabaseReference mDatabase;
    RecyclerViewAdapter2 adapter;

    @BindView(R.id.listrv2)
    RecyclerView rv2;


    @BindView(R.id.number)
    TextView number;

    private void setData() {





        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(fUser.getUid()).child("routezongro").addListenerForSingleValueEvent(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = dataSnapshot.getChildrenCount();
                grader();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String key = ds.getValue(String.class);
                    mDatabase.child("zongro").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            MapData data2 =dataSnapshot.getValue(MapData.class);
                            adapter.addItem(data2);









                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }





                                                                                                     }


        );







    }

    public void grader(){

        number.setText(Long.toString(count));







    }

    private long pressedTime;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        ButterKnife.bind(this);




        mAuth = FirebaseAuth.getInstance();
        fUser =mAuth.getCurrentUser();

        RecyclerView.LayoutManager lm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rv2.setLayoutManager(lm);
        adapter = new RecyclerViewAdapter2(List2Activity.this );
        rv2.setAdapter(adapter);



        setData();








    }
}
