package android.example.canvasviewapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabColor, fabWidth;
    private MyCanvasView myCanvasView;

    private int mSelectedColor=R.color.purple_500;
    private int mSelectedWidth=12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyCanvasView myCanvasView;
//        myCanvasView = new MyCanvasView(this);
//        myCanvasView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        myCanvasView = findViewById(R.id.canvas_view);

        fabColor = findViewById(R.id.fb_colorpicker);
        fabWidth = findViewById(R.id.fb_widthstroke);

        fabColor.setOnClickListener((view)->{
            ColorPicker colorPicker = new ColorPicker(this);
            colorPicker.setOnFastChooseColorListener(new ColorPicker.OnFastChooseColorListener() {
                @Override
                public void setOnFastChooseColorListener(int position, int color) {
                    mSelectedColor = color;
                    myCanvasView.setPathColor(color);
                }

                @Override
                public void onCancel() {
                    colorPicker.dismissDialog();

                }
            }).disableDefaultButtons(true)
                    .setColumns(5)
                    .setRoundColorButton(true)
                    .show();
        });

        fabWidth.setOnClickListener((view)-> {
            MaterialNumberPicker mNumberPicker = new MaterialNumberPicker.Builder(this)
                    .minValue(1)
                    .maxValue(20)
                    .defaultValue(mSelectedWidth)
                    .backgroundColor(Color.WHITE)
                    .separatorColor(Color.TRANSPARENT)
                    .textSize(12)
                    .enableFocusability(false)
                    .wrapSelectorWheel(true)
                    .build();

            new AlertDialog.Builder(this)
                    .setTitle("Pick Stroke Width")
                    .setView(mNumberPicker).setNegativeButton(getString(android.R.string.cancel), null)
                    .setPositiveButton(getString(android.R.string.ok), (dialogInterface, which) -> {
                        mSelectedWidth = mNumberPicker.getValue();
                        myCanvasView.setWidthStroke(mNumberPicker.getValue());
                    })
                    .show();
        });

    }

}