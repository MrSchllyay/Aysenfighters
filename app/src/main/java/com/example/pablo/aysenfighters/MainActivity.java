package com.example.pablo.aysenfighters;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText NombreTorneo,Propietario;
    Button id_guardar,id_buscar,bracket;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NombreTorneo = (EditText) findViewById(R.id.nombretorneo);
        Propietario = (EditText) findViewById(R.id.propietario);

        id_guardar = (Button) findViewById(R.id.id_guardar);
        id_buscar = (Button) findViewById(R.id.id_buscar);
        bracket = (Button) findViewById(R.id.bracket);

        id_guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                guardar(NombreTorneo.getText().toString(),Propietario.getText().toString());
            }
        });

        id_buscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, TorneosGenerados.class));
            }
        });

        bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Brackets.class));
            }
        });
    }


    private void guardar(String NombreTorneo, String Propietario){
        BDatos helper = new BDatos(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("TORNEO",NombreTorneo);
            c.put("USUARIO",Propietario);
            db.insert("TORNEOS",null,c);
            db.close();
            Toast.makeText(this,"Registro torneo completado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this,"Error:" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
