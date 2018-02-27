package mx.edu.ittepic.tam_sqlite_prueba_vicentemz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button orden,cliente,aparato,sal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orden=(Button)findViewById(R.id.ordenes);
        cliente=(Button)findViewById(R.id.clientes);
        aparato=(Button)findViewById(R.id.aparatos);
        sal=(Button)findViewById(R.id.salir);

        sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ListSong = new Intent(getApplicationContext(), Clientes.class);
                startActivity(ListSong);

            }
        });


    }
}