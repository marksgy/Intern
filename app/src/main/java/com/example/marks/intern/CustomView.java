package com.example.marks.intern;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by marks on 2017/3/4.
 */

public class CustomView extends View {

    Paint paint;
    Path path = new Path();
    private int polygon,a,r,g,b;
    private float x_start,x_end,y_start,y_end;
    private float radius = 90-360/polygon;
    private float length=getWidth()/polygon;
    private float move_right= (float) (Math.cos(radius*Math.PI/180)*length);
    private float move_above= (float) (Math.sin(radius*Math.PI/180)*length);

    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0);
        int count=typedArray.getIndexCount();
        for (int i=0;i<count;i++){
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.CustomView_customA:
                    a=typedArray.getInteger(attr,255);
                    break;
                case R.styleable.CustomView_customB:
                    b=typedArray.getInteger(attr,255);
                    break;
                case R.styleable.CustomView_customG:
                    g=typedArray.getInteger(attr,255);
                    break;
                case R.styleable.CustomView_customR:
                    r=typedArray.getInteger(attr,255);
                    break;
                case R.styleable.CustomView_polygon:
                    polygon=typedArray.getInteger(attr,4);
                    break;
            }
        }
        typedArray.recycle();
    }



    public void setPolygon(int polygon){
        this.polygon=polygon;

    }

    public void setA(int a) {
        this.a = a;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setG(int g) {
        this.g = g;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        paint.setARGB(a,r,g,b);
        paint.setStyle(Paint.Style.FILL);
        x_start=0;
        y_start=getHeight()/2;
        path.moveTo(x_start, y_start);

        for (int i=1;i<=1;i++){
            x_end=x_start+move_right;
            y_end=y_start+move_above;
            path.lineTo(x_end,y_end);
            x_start=x_end;
            y_start=y_end;
        }
        path.close();
        canvas.drawPath(path,paint);

    }
}
