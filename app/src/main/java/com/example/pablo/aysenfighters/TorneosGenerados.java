package com.example.pablo.aysenfighters;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TorneosGenerados extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneos_generados);
        listView = (ListView) findViewById(R.id.ListView);

        CargarListado();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(TorneosGenerados.this,listado.get(position),Toast.LENGTH_SHORT ).show();
                int clave=Integer.parseInt(listado.get(position).split(" ")[0]);
                String torneo = listado.get(position).split(" ")[1];
                String usuario = listado.get(position).split(" ")[2];
                Intent intent = new Intent(TorneosGenerados.this, Modificar.class);
                intent.putExtra("ID",clave);
                intent.putExtra("TORNEO",torneo);
                intent.putExtra("USUARIO",usuario);
                startActivity(intent);
            }
        });


        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId()==android.R.id.home){
        finish();
    }
    return super.onOptionsItemSelected(item);
    }

    private void CargarListado() {
        listado = ListaTorneos();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListaTorneos(){
        ArrayList<String> datos = new ArrayList<String>();
        BDatos helper = new BDatos(this,"Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT ID,TORNEO,USUARIO from TORNEOS";
        Cursor c = db.rawQuery(sql,null);
        if (c.moveToFirst()){
            do {
                String linea = c.getInt(0) + " " + c.getString(1) + " " + c.getString(2);
                datos.add(linea);
            }while (c.moveToNext());
            }
            db.close();
        return datos;
        }
    }

