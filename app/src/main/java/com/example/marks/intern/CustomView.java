package com.example.marks.intern;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by marks on 2017/3/4.
 */

public class CustomView extends View {


    private int polygon = 4;
    private int a, r, g, b;
    private float x_start, x_end, y_start, y_end;
    float x_down=0;
    float y_down=0;
    float oldDist,newDist;
    private float arc = 360 / polygon;
    private float radius ;

    private Bitmap bitmap;
    Matrix matrix = new Matrix();
    Matrix matrix1= new Matrix();
    Matrix savedMatrix=new Matrix();
    PointF start = new PointF();
    PointF mid = new PointF();
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    int mode=NONE;

    boolean matrixCheck=false;


    Paint paint = new Paint();



    public CustomView(Context context) {

        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomView_customA:
                    a = typedArray.getInteger(attr, 120);
                    break;
                case R.styleable.CustomView_customB:
                    b = typedArray.getInteger(attr, 120);
                    break;
                case R.styleable.CustomView_customG:
                    g = typedArray.getInteger(attr, 120);
                    break;
                case R.styleable.CustomView_customR:
                    r = typedArray.getInteger(attr, 120);
                    break;
                case R.styleable.CustomView_polygon:
                    polygon = typedArray.getInteger(attr, 4);
                    break;
            }
        }
        typedArray.recycle();
    }


    public void setPolygon(int polygon) {
        this.polygon = polygon;
        this.arc = 360 / polygon;
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

    public void setShade(Bitmap bitmap) {


        this.bitmap = bitmap;
        requestLayout();
        invalidate();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mode = DRAG;
                x_down=event.getX();
                y_down=event.getY();
                savedMatrix.set(matrix);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mode = ZOOM;
                oldDist = spacing(event);

                savedMatrix.set(matrix);
                midPoint(mid, event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == ZOOM) {
                    matrix1.set(savedMatrix);
                    float scale = newDist / oldDist;
                    matrix1.postScale(scale, scale, mid.x, mid.y);// 縮放

                    if (matrixCheck == false) {
                        matrix.set(matrix1);
                        invalidate();
                    }
                } else if (mode == DRAG) {
                    matrix1.set(savedMatrix);
                    matrix1.postTranslate(event.getX() - x_down, event.getY()
                            - y_down);// 平移

                    if (matrixCheck == false) {
                        matrix.set(matrix1);
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
        }
        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (!isInEditMode()) {
            canvas.translate(getWidth() / 2, getHeight() / 2);

            if (getHeight()>=getWidth()){
                radius = (float) (getWidth()/2) ;
            }else {
                radius = (float) (getHeight()/2 ) ;
            }

            Path path = new Path();
            paint.setARGB(a, r, g, b);
            paint.setStyle(Paint.Style.FILL);


            x_start = radius;
            y_start = 0;
            path.moveTo(x_start, y_start);
            for (int i = 1; i <= polygon; i++) {
                x_end = (float) (radius * Math.cos(i * arc * Math.PI / 180));
                y_end = (float) (radius * Math.sin(i * arc * Math.PI / 180));
                path.lineTo(x_end, y_end);
            }
            path.close();
            canvas.clipPath(path);
            canvas.drawPath(path, paint);
            if (bitmap!=null){
                int width=bitmap.getWidth();
                int height=bitmap.getHeight();

                float scale;
                if (width>=height){
                    scale=radius*2/height;
                }else {
                    scale=radius*2/width;
                }
                matrix.setScale(scale,scale);
                Bitmap dstbmp = Bitmap.createBitmap(bitmap,0,0 , width, height,
                        matrix, true);
                canvas.drawBitmap(dstbmp,-radius,-radius,paint);


            }


        }


    }
    // 触碰两点间距离
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    // 取手势中心点
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
}
