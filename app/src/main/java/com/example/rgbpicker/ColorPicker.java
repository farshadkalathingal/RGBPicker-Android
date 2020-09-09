package com.example.rgbpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ColorPicker extends AppCompatActivity {

    ImageView imageView;
    TextView hexValue, rValue, gValue, bValue, rgbValue;
    ProgressBar rBar, gBar, bBar;
    ImageButton rgbButton,hexButton;
    SeekBar seekBar;
    //View cview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        imageView = findViewById(R.id.imageView);
        hexValue = findViewById(R.id.hexValue);
        rValue = findViewById(R.id.rValue);
        gValue = findViewById(R.id.gValue);
        bValue = findViewById(R.id.bValue);
     //   cview = findViewById(R.id.colorView);
        rgbValue = findViewById(R.id.rgbValue);
        rgbButton = findViewById(R.id.rgbCopy);
        hexButton = findViewById(R.id.hexCopy);
        seekBar = findViewById(R.id.colorSeek);
        

        rBar = findViewById(R.id.redBar);
        rBar.setMax(256);
        rBar.setProgress(0);
        gBar = findViewById(R.id.greenBar);
        gBar.setMax(256);
        gBar.setProgress(0);
        bBar = findViewById(R.id.blueBar);
        bBar.setMax(256);
        bBar.setProgress(0);


        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    Bitmap bitmap = imageView.getDrawingCache();

                    int pixel = bitmap.getPixel((int)motionEvent.getX(), (int)motionEvent.getY());

                    //Getting RGB Values

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    //getting Hex Value

                    //String hex = "#"+ Integer.toHexString(pixel);

                    String hex = String.format("#%02x%02x%02x", r, g, b);

                    //set Background color

//                    cview.setBackgroundColor(Color.rgb(r,g,b));
                    rgbValue.setBackgroundColor(Color.rgb(r,g,b));
                    rgbValue.setText(r+", "+g+", "+b);
                    rgbButton.setVisibility(View.VISIBLE);
                    hexValue.setBackgroundColor(Color.rgb(r,g,b));
                    hexValue.setText(hex);
                    hexButton.setVisibility(View.VISIBLE);

                    rValue.setText("Red : "+r);
                    gValue.setText("Green : "+g);
                    bValue.setText("Blue : "+b);


                    rBar.setProgress(r);
                    gBar.setProgress(g);
                    bBar.setProgress(b);
                }
                return true;
            }
        });

        rgbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", rgbValue.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(ColorPicker.this, "Copied.",Toast.LENGTH_SHORT).show();

            }
        });

        hexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", hexValue.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(ColorPicker.this, "Copied.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to Exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(1);
                finish();
                ColorPicker.super.onBackPressed();

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
