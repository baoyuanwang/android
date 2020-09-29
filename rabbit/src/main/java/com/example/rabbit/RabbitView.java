package com.example.rabbit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class RabbitView extends View {

    float ratbbitX=0.0f;
    float ratbbitY=0.0f;

    public RabbitView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.rabbit);
        canvas.drawBitmap(bitmap,ratbbitX,ratbbitY,paint);
        if(bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
