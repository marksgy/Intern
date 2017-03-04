package com.example.marks.intern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    CustomView customView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView= (CustomView) findViewById(R.id.CustomView);
        editText= (EditText) findViewById(R.id.editText);
    }
    public void onClick(View view){
        int polygon= Integer.parseInt(editText.getText().toString());
        customView.setPolygon(polygon);
        customView.invalidate();
    }
}
