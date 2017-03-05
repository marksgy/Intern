package com.example.marks.intern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    CustomView customView;
    EditText editText;
    SeekBar seekBar1,seekBar2,seekBar3,seekBar4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView= (CustomView) findViewById(R.id.CustomView);
        editText= (EditText) findViewById(R.id.editText);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2= (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar4 = (SeekBar) findViewById(R.id.seekBar4);

        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
        seekBar4.setOnSeekBarChangeListener(this);
    }
    public void onClick(View view){
        int polygon= Integer.parseInt(editText.getText().toString());
        customView.setPolygon(polygon);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekBar1:
                customView.setA(progress);
                break;
            case R.id.seekBar2:
                customView.setR(progress);
                break;
            case R.id.seekBar3:
                customView.setG(progress);
                break;
            case R.id.seekBar4:
                customView.setB(progress);
                break;

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
