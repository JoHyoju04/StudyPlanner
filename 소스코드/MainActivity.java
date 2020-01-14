package com.example.sksms.project20172183;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {
    DatePicker dp;
    EditText edtDiary1,edtDiary2,edtDiary3,edtDiary4,dlgMonth,dlgWeek;
    Button btnWrite,btnCheck;
    String fileName1,fileName2,fileName3,fileName4;
    int cMonth,cYear,cDay;
    View dialogView;
    TextView tvWeek,tvMonth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("나만의 스터디 플래너");

        dp=(DatePicker)findViewById(R.id.datePicker1);
        edtDiary1=(EditText)findViewById(R.id.edtDiary1);
        edtDiary2=(EditText)findViewById(R.id.edtDiary2);
        edtDiary3=(EditText)findViewById(R.id.edtDiary3);
        edtDiary4=(EditText)findViewById(R.id.edtDiary4);
        btnWrite=(Button)findViewById(R.id.btnWrite);
        btnCheck=(Button)findViewById(R.id.btnCheck);
        Calendar cal=Calendar.getInstance();
        cYear=cal.get(Calendar.YEAR);
        cMonth=cal.get(Calendar.MONTH);
        cDay=cal.get(Calendar.DAY_OF_MONTH);
        tvMonth=(TextView)findViewById(R.id.tvMonth);
        tvWeek=(TextView)findViewById(R.id.tvWeek);


        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                fileName1=Integer.toString(year)+"_"+Integer.toString(monthOfYear+1)+"_"+Integer.toString(dayOfMonth)+"_1.txt";
                fileName2=Integer.toString(year)+"_"+Integer.toString(monthOfYear+1)+"_"+Integer.toString(dayOfMonth)+"_2.txt";
                fileName3=Integer.toString(year)+"_"+Integer.toString(monthOfYear+1)+"_"+Integer.toString(dayOfMonth)+"_3.txt";
                fileName4=Integer.toString(year)+"_"+Integer.toString(monthOfYear+1)+"_"+Integer.toString(dayOfMonth)+"_4.txt";

                String str1=readDiary(fileName1);
                String str2=readDiary(fileName2);
                String str3=readDiary(fileName3);
                String str4=readDiary(fileName4);

                edtDiary1.setText(str1);
                edtDiary2.setText(str2);
                edtDiary3.setText(str3);
                edtDiary4.setText(str4);
                btnWrite.setEnabled(true);
            }
        });
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileOutputStream outFs1=openFileOutput(fileName1, Context.MODE_PRIVATE);
                    FileOutputStream outFs2=openFileOutput(fileName2, Context.MODE_PRIVATE);
                    FileOutputStream outFs3=openFileOutput(fileName3, Context.MODE_PRIVATE);
                    FileOutputStream outFs4=openFileOutput(fileName4, Context.MODE_PRIVATE);
                    String str1=edtDiary1.getText().toString();
                    String str2=edtDiary2.getText().toString();
                    String str3=edtDiary3.getText().toString();
                    String str4=edtDiary4.getText().toString();
                    outFs1.write(str1.getBytes());
                    outFs1.close();

                    outFs2.write(str2.getBytes());
                    outFs2.close();

                    outFs3.write(str3.getBytes());
                    outFs3.close();

                    outFs4.write(str4.getBytes());
                    outFs4.close();
                    Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();

                }catch (IOException e){

                }
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,check_list.class);
                intent.putExtra("edtDiary1",edtDiary1.getText().toString());
                intent.putExtra("edtDiary2",edtDiary2.getText().toString());
                intent.putExtra("edtDiary3",edtDiary3.getText().toString());
                intent.putExtra("edtDiary4",edtDiary4.getText().toString());

                startActivity(intent);
            }
        });

}
    String readDiary(String fName){
        String diaryStr=null;
        FileInputStream inFs;
        try{
            inFs=openFileInput(fName);
            byte[]txt=new byte[100];
            inFs.read(txt);
            inFs.close();
            diaryStr=(new String(txt)).trim();
            btnWrite.setText("수정 하기");
        }catch (IOException e){
            edtDiary1.setHint("입력하세요");
            edtDiary2.setHint("입력하세요");
            edtDiary3.setHint("입력하세요");
            edtDiary4.setHint("입력하세요");
            btnWrite.setText("새로 저장");
        }
        return diaryStr;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater=getMenuInflater();
        mInflater.inflate(R.menu.menu1,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.itemMonth:
                dialogView=(View)View.inflate(MainActivity.this,R.layout.dialog_month,null);
                AlertDialog.Builder dlg=new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("이번 달 목표 입력");
                dlg.setIcon(R.drawable.calendar);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dlgMonth=(EditText)dialogView.findViewById(R.id.dlgMonth);
                                tvMonth.setText("< 이번달 목표 : "+dlgMonth.getText().toString()+" >");
                            }
                        });
                dlg.setNegativeButton("취소",null);
                dlg.show();
                return true;

            case R.id.itemWeek:
                dialogView=(View)View.inflate(MainActivity.this,R.layout.dialog_week,null);
                AlertDialog.Builder dlg2=new AlertDialog.Builder(MainActivity.this);
                dlg2.setTitle("이번 주 목표 입력");
                dlg2.setIcon(R.drawable.calendar);
                dlg2.setView(dialogView);
                dlg2.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dlgWeek=(EditText)dialogView.findViewById(R.id.dlgWeek);
                                tvWeek.setText("< 이번주 목표 : "+dlgWeek.getText().toString()+" >");
                            }
                        });
                dlg2.setNegativeButton("취소",null);
                dlg2.show();
                return true;

            case R.id.itemSaying:
                Intent intent2=new Intent(getApplicationContext(),wording.class);
                startActivity(intent2);
                return true;

        }
        return false;
    }
}
