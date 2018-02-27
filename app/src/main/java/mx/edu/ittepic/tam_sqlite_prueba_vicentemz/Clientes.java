package mx.edu.ittepic.tam_sqlite_prueba_vicentemz;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Clientes extends AppCompatActivity {
    Button act,bor,ise,bus,sal;
    EditText nom,dom,col;
    BaseDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        act=(Button)findViewById(R.id.actualizar);
        bor=(Button)findViewById(R.id.eliminar);
        ise=(Button)findViewById(R.id.insertar);
        bus=(Button)findViewById(R.id.consultar);
        sal=(Button)findViewById(R.id.salir);

        nom=(EditText)findViewById(R.id.nombre);
        dom=(EditText)findViewById(R.id.domi);
        col=(EditText)findViewById(R.id.colo);

        db = new BaseDatos(this,"partido",null,1);

        sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nom.getText().toString().length() != 0 && dom.getText().toString().length() != 0) {

                    try {
                        //Toast.makeText(MainActivity.this,"si llega!",Toast.LENGTH_LONG).show();
                        SQLiteDatabase base = db.getWritableDatabase();
                        String sentenciaSQL = "INSERT INTO CLIENTE VALUES("+null+",'NOMBRE','DOMICILIO','COLONIA')";

                        sentenciaSQL = sentenciaSQL.replace("NOMBRE", "" + nom.getText().toString());
                        sentenciaSQL = sentenciaSQL.replace("DOMICILIO", dom.getText().toString());
                        sentenciaSQL = sentenciaSQL.replace("COLONIA", col.getText().toString());

                        base.execSQL(sentenciaSQL);//aqui se hace la insecion

                        Toast.makeText(Clientes.this, "SE INSERTO CON EXITO", Toast.LENGTH_LONG).show();

                        //equipo1.setText("");anotacionEquipo1.setText("");equipo2.setText("");anotacionEquipo2.setText("");fecha.setText("");
                        base.close();

                    } catch (SQLiteException e) {

                        Toast.makeText(Clientes.this, "Error:" + e, Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(Clientes.this, "Campos vacios!", Toast.LENGTH_LONG).show();
                }
            }
        });

        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nom.getText().toString().length() != 0 && dom.getText().toString().length() != 0) {
                    try {
                        //Toast.makeText(MainActivity.this,"si llega!",Toast.LENGTH_LONG).show();
                        SQLiteDatabase base = db.getWritableDatabase();
                        String sentenciaSQL = "UPDATE CLIENTE SET NOMBRE='" +nom.getText().toString()+
                                "' , DOMICILIO='" +dom.getText().toString()+
                                "' , COLONIA='" +col.getText().toString()+
                                "' WHERE NOMBRE ='" + nom.getText().toString()+"'";
                        base.execSQL(sentenciaSQL);//aqui se hace la insecion
                        Toast.makeText(Clientes.this, "SE ACTUALIZO CON EXITO", Toast.LENGTH_LONG).show();
                        //equipo1.setText("");anotacionEquipo1.setText("");equipo2.setText("");anotacionEquipo2.setText("");fecha.setText("");
                        base.close();
                    }catch (Exception e){
                        Toast.makeText(Clientes.this, "Error:"+e, Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(Clientes.this, "Campos vacios!", Toast.LENGTH_LONG).show();
                }

            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nom.getText().toString().length() != 0){
                    try{

                        SQLiteDatabase database = db.getReadableDatabase();
                        String SQL = "SELECT * FROM CLIENTE WHERE NOMBRE='"+nom.getText().toString()+"'";
                        Cursor resultadoConsulta = database.rawQuery(SQL,null);

                        if(resultadoConsulta.moveToFirst()){
                            Toast.makeText(Clientes.this, "SI HAY REGISTROS", Toast.LENGTH_SHORT).show();
                            dom.setText(""+resultadoConsulta.getString(2)+"");
                            col.setText(""+resultadoConsulta.getString(3)+"");

                        }
                        else{
                            Toast.makeText(Clientes.this, "No hay coincidencia", Toast.LENGTH_SHORT).show();
                        }
                        database.close();
                    }catch(SQLiteException e){
                        Toast.makeText(Clientes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Clientes.this,"NOMBRE VACIO!",Toast.LENGTH_LONG).show();
                }
            }
        });

        bor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nom.getText().toString().length() != 0){
                    eliminarDatos();
                }
                else{
                    Toast.makeText(Clientes.this,"NOMBRE VACIO!",Toast.LENGTH_LONG).show();
                }



            }
        });

    }
    private void eliminarDatos(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCION").setMessage("Esta seguro que desea borrar?").setPositiveButton("BORRAR",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminarDatos2();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

    private  void eliminarDatos2(){
        try{
            SQLiteDatabase base = db.getWritableDatabase();
            String sentenciaSQL= "DELETE FROM CLIENTE WHERE "
                    +"NOMBRE='"+nom.getText().toString()+"'";
            base.execSQL(sentenciaSQL);
            Toast.makeText(this,"Se borro con exito!",Toast.LENGTH_LONG)
                    .show();
            base.close();

        }catch (SQLiteException e ){
            Toast.makeText(Clientes.this,"Error:"+e,Toast.LENGTH_LONG).show();
        }
    }
}
