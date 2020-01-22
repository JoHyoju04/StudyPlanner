//메뉴에서 공부명언을 선택했을 경우
package com.example.sksms.project20172183;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

//커스텀위젯 설정
public class myPictureView extends View {
    String imagePath=null;
    public myPictureView(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
    }
    
    //캔버스에 SD카드의 이미지 파일을 보여주기 위한 메소드
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if(imagePath !=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            int picX=(this.getWidth()-bitmap.getWidth())/2;
            int picY=(this.getHeight()-bitmap.getHeight())/2;
            canvas.scale(2,2,this.getWidth()/2,this.getHeight()/2);
            canvas.drawBitmap(bitmap,picX,picY,null);
            bitmap.recycle();
        }
    }
}
