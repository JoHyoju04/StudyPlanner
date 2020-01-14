package com.example.sksms.project20172183;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.Toast;

public class check_list extends MainActivity {
    CheckBox box1,box2,box3,box4;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_list);
        setTitle("CkeckList");
        Button btnReturn=(Button)findViewById(R.id.btnReturn);
        RatingBar star=(RatingBar)findViewById(R.id.star);
        box1=(CheckBox)findViewById(R.id.box1);
        box2=(CheckBox)findViewById(R.id.box2);
        box3=(CheckBox)findViewById(R.id.box3);
        box4=(CheckBox)findViewById(R.id.box4);

        Intent intent=getIntent();
        String check1=intent.getExtras().getString("edtDiary1");
        String check2=intent.getExtras().getString("edtDiary2");
        String check3=intent.getExtras().getString("edtDiary3");
        String check4=intent.getExtras().getString("edtDiary4");

        box1.setText(check1);
        box2.setText(check2);
        box3.setText(check3);
        box4.setText(check4);

        star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast tMsg=Toast.makeText(getApplicationContext(),"♥오늘 하루 고생했다♥",Toast.LENGTH_SHORT);
                Display display=((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

                tMsg.setGravity(Gravity.CENTER,0,300);
                tMsg.show();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}

