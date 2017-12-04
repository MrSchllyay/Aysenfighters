package com.example.pablo.aysenfighters;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {
    EditText nombretorneo,propietario;
    Button modificar;
    Button btnborrar;
    int id;
    String torneo;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b=getIntent().getExtras();
        if (b!=null){
            id = b.getInt("ID");
            torneo=b.getString("TORNEO");
            usuario=b.getString("USUARIO");
        }

        nombretorneo = (EditText) findViewById(R.id.nombretorneo);
        propietario = (EditText) findViewById(R.id.propietario);

        nombretorneo.setText(torneo);
        propietario.setText(usuario);

        modificar = (Button) findViewById(R.id.id_modificar);
        btnborrar = (Button) findViewById(R.id.id_borrar);

        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(id);
                onBackPressed();
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar(id, nombretorneo.getText().toString(), propietario.getText().toString());
                onBackPressed();
            }
        });
    }

    private void Modificar(int id, String NombreTorneo, String Propietario){
        BDatos helper = new BDatos(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql ="UPDATE TORNEOS set TORNEO='" + NombreTorneo + "',USUARIO='" + Propietario + "' WHERE ID="+id;
        db.execSQL(sql);
        db.close();
    }

    private void Eliminar(int id){
        BDatos helper = new BDatos(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql ="DELETE from TORNEOS where ID="+id;
        db.execSQL(sql);
        db.close();
    }
}
