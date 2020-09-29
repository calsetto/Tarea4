package com.example.tarea4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Edit_info.InfoListener, GestureDetector.OnGestureListener {

    private Button boton1, boton2, boton3;
    private CheckBox musica, carro, calle, persona;
    private TextView var1, var2, var3;
    private ImageView car, street, person, music;
    private static final  String TAG = "Swipe Position";
    float x1, x2, y1,y2;
    int MIN_DISTANCE = 150;
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1 = (Button) findViewById(R.id.boton1);
        boton2 = (Button) findViewById(R.id.boton2);
        musica = (CheckBox) findViewById(R.id.cb_musica);
        calle = (CheckBox) findViewById(R.id.cb_calle);
        carro = (CheckBox) findViewById(R.id.cb_carro);
        persona = (CheckBox) findViewById(R.id.cb_persona);
        var1 = (TextView) findViewById(R.id.textView);
        var2 = (TextView) findViewById(R.id.textView2);
        var3 = (TextView) findViewById(R.id.textView3);
        car = (ImageView) findViewById(R.id.imageView2);
        street = (ImageView) findViewById(R.id.imageView3);
        person = (ImageView) findViewById(R.id.imageView4);
        music = (ImageView) findViewById(R.id.imageView5);

        this.gestureDetector = new GestureDetector(MainActivity.this, this);

        //SetOnclicListener

        boton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                street.setVisibility(View.GONE);
                person.setVisibility(View.GONE);
                car.setVisibility(View.GONE);
                music.setVisibility(View.GONE);

                if(musica.isChecked()){
                    music.setVisibility(View.VISIBLE);
                    musica.setChecked(false);
                }
                if(carro.isChecked()){
                    car.setVisibility(View.VISIBLE);
                    carro.setChecked(false);
                }
                if(persona.isChecked()){
                    person.setVisibility(View.VISIBLE);
                    persona.setChecked(false);
                }
                if(calle.isChecked()){
                    street.setVisibility(View.VISIBLE);
                    calle.setChecked(false);
                }
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfo();
            }
        });
    }

    public void openInfo(){
        Context context;
        LayoutInflater imagen = LayoutInflater.from(MainActivity.this);
        final View view = imagen.inflate(R.layout.imagen, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Informacion");
        builder.setView(view);
        builder.setMessage("Mi nombre es " + var1.getText().toString() + ", esta materia es " +
                var2.getText().toString() + " y estudio en " + var3.getText().toString())
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OpenEdit();
                    }
                }).show();

    }

    public void OpenEdit(){
        Edit_info edit_info = new Edit_info();
        edit_info.show(getSupportFragmentManager(),"KLK");
    }

    @Override
    public void applytext(String value1, String value2, String value3) {
        var1.setText(value1);
        var2.setText(value2);
        var3.setText(value3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;

            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float valueX = x2 - x1;

                if(Math.abs(valueX) > MIN_DISTANCE){
                    //left to right
                    if(x2 < x1){
                        finish();
                    }
                }

        }

        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
