package com.example.rgbpicker;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar;

public class ColorPicker2 extends AppCompatActivity {

    ColorPickerView colorPickerView;
    TextView rgbValue,hexValue;
    ImageButton rgbButton,hexButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker2);

        colorPickerView = findViewById(R.id.colorPickerView);
        rgbValue = findViewById(R.id.rgbValue);
        hexValue = findViewById(R.id.hexValue);

        rgbButton = findViewById(R.id.rgbCopy);
        hexButton = findViewById(R.id.hexCopy);

//        AlphaSlideBar alphaSlideBar = findViewById(R.id.alphaSlideBar);
//        colorPickerView.attachAlphaSlider(alphaSlideBar);

        BrightnessSlideBar brightnessSlideBar = findViewById(R.id.brightnessSlide);
        colorPickerView.attachBrightnessSlider(brightnessSlideBar);



//        colorPickerView.setColorListener(new ColorListener() {
//            @Override
//            public void onColorSelected(int color, boolean fromUser) {
//                LinearLayout linearLayout = findViewById(R.id.linearLayout);
//                linearLayout.setBackgroundColor(color);
//            }
//        });
        colorPickerView.setColorListener(new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                hexValue.setBackgroundColor(envelope.getColor());
                //hexValue.setText("#"+ envelope.getHexCode());

                int r = Color.red(envelope.getColor());
                int g = Color.green(envelope.getColor());
                int b = Color.blue(envelope.getColor());

                if( r <= 170 && g <= 170 && b <= 170 ) {
                    hexValue.setTextColor(Color.parseColor("#ffffff"));
                    rgbValue.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    hexValue.setTextColor(Color.parseColor("#000000"));
                    rgbValue.setTextColor(Color.parseColor("#000000"));
                }

                String hex = String.format("#%02x%02x%02x", r, g, b);
                hexValue.setText(hex);
                rgbValue.setBackgroundColor(envelope.getColor());
                rgbValue.setText(r+", "+g+", "+b);

            }
        });
        rgbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", rgbValue.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(ColorPicker2.this, "Copied.",Toast.LENGTH_SHORT).show();

            }
        });

        hexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", hexValue.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(ColorPicker2.this, "Copied.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SelectMenu.class);
        startActivity(intent);
        finish();
    }
}
