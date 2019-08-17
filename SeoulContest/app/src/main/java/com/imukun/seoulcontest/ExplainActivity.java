package com.imukun.seoulcontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExplainActivity extends AppCompatActivity {

    FirebaseAuth mAuth ;
    FirebaseUser fUser;

    @BindView(R.id.itemFL)
    FrameLayout FL;

    @OnClick({R.id.itemFL})
    public void onclick(View v)
    {
        if(v.equals(FL))
        {
            if (fUser!= null) {
                Intent intent = new Intent(ExplainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(ExplainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }


    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);
        ButterKnife.bind(this);

        mAuth= FirebaseAuth.getInstance();
        fUser=mAuth.getCurrentUser();
    }
}
