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


    private int polygon=4;
    private int a,r,g,b;
    private float x_start,x_end,y;
    private float radius = 360/polygon;



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
                    a=typedArray.getInteger(attr,120);
                    break;
                case R.styleable.CustomView_customB:
                    b=typedArray.getInteger(attr,120);
                    break;
                case R.styleable.CustomView_customG:
                    g=typedArray.getInteger(attr,120);
                    break;
                case R.styleable.CustomView_customR:
                    r=typedArray.getInteger(attr,120);
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
        this.radius=360/polygon;
        requestLayout();
        invalidate();

    }

    public void setA(int a) {

        this.a = a;
        requestLayout();
        invalidate();
    }

    public void setR(int r) {

        this.r = r;
        requestLayout();
        invalidate();
    }

    public void setB(int b) {

        this.b = b;
        requestLayout();
        invalidate();
    }

    public void setG(int g) {

        this.g = g;
        requestLayout();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float length= (float) (getWidth()/polygon);
        if (!isInEditMode()){
            canvas.translate(getWidth()/2,getHeight()/2);
            Paint paint=new Paint();
            Path path = new Path();
            paint.setARGB(a,r,g,b);
            paint.setStyle(Paint.Style.FILL);
            x_start=-length/2;
            x_end=length/2;
            y= (float) ((length/2)/Math.tan(radius/2*Math.PI/180));

            for (int i=1;i<=polygon;i++){

                path.lineTo(x_start, y);
                path.lineTo(x_end,y);
                path.close();
                canvas.drawPath(path,paint);
                canvas.rotate(radius);


            }


        }


    }
}
