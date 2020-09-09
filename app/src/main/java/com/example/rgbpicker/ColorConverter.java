package com.example.rgbpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static java.lang.Integer.parseInt;

public class ColorConverter extends AppCompatActivity {

    View colorView;
    EditText hexCode, rgbCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_converter);

        colorView = findViewById(R.id.colorView);
        hexCode = findViewById(R.id.hexCode);
        rgbCode = findViewById(R.id.rgbCode);
        hexCode.setInputType(InputType.TYPE_CLASS_TEXT);
//        colorView.setBackgroundColor(Color.parseColor("#fffff"));

//        InputFilter inputFilter = new InputFilter() {
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                for (int i = start; i < end; i++ ) {
//                    if(!Character.isLetterOrDigit(source.charAt(i)) && !Character.isSpaceChar(source.charAt(i))
//                    && source.charAt(i)!='-' && source.charAt(i)!='.' && source.charAt(i)!='!') {
//                        return "";
//
//                    }
//                }
//                return null;
//            }
//
//        };
//        hexCode.setFilters(new InputFilter[] {inputFilter});
//        hexCode.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        hexCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String hex = "#000000";
                String val = s.toString();
                int init = 1;
                int len = val.length();

                hex = hex.substring(0, init) + s + hex.substring(init+len);

                int r = Color.red(Color.parseColor(hex));
                int g = Color.green(Color.parseColor(hex));
                int b = Color.blue(Color.parseColor(hex));
                colorView.setBackgroundColor(Color.parseColor(hex));

                rgbCode.setText(r+", "+g+", "+b);
               // rgbCode.setText(hex);

            }

            @Override
            public void afterTextChanged(Editable s) {

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
