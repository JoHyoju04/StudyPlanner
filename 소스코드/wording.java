//메뉴에서 공부명언을 선택했을 경우
package com.example.sksms.project20172183;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class wording extends Activity {
    myPictureView myPicture;
    File[] imageFiles;
    String imageFname;
    int curNum;
    Button btnPrev,btnNext,btnReturn2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wording);
        setTitle("공부 명언");
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        btnPrev=(Button)findViewById(R.id.btnPrev);
        btnNext=(Button)findViewById(R.id.btnNext);
        myPicture=(myPictureView)findViewById(R.id.myRictreView1);
        btnReturn2=(Button)findViewById(R.id.btnReturn2);

        imageFiles=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        imageFname=imageFiles[0].toString();
        myPicture.imagePath=imageFname;
        
        //이전 버튼을 선택하였을 경우
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum<=0)
                    Toast.makeText(getApplicationContext(),"첫번째 명언입니다",Toast.LENGTH_SHORT).show();
                else{
                    curNum--;
                    imageFname=imageFiles[curNum].toString();
                    myPicture.imagePath=imageFname;
                    myPicture.invalidate();
                }
            }
        });
        
        //다음 버튼을 선택하였을 경우
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum>=imageFiles.length-1){
                    Toast.makeText(getApplicationContext(),"마지막 명언입니다",Toast.LENGTH_SHORT).show();
                }
                else{
                    curNum++;
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath = imageFname;
                    myPicture.invalidate();
                }
            }
        });

        //돌아가기 버튼을 선택하였을 경우
        btnReturn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
