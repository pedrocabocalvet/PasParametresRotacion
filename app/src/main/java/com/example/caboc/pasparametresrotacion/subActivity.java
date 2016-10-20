package com.example.caboc.pasparametresrotacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class subActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnContinuar;
    TextView txtPresentacion;
    EditText editTextEdad;
    String presentacionNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        btnContinuar = (Button) findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(this);
        txtPresentacion = (TextView) findViewById(R.id.txtPresentacion);
        editTextEdad = (EditText) findViewById(R.id.editTextEdat);

        if(savedInstanceState==null){
            Bundle b = getIntent().getExtras();
            presentacionNombre=  b.getString("nombre");
            txtPresentacion.setText("Hola "+ presentacionNombre + " rellena los siguientes datos");
        }else{
            presentacionNombre= savedInstanceState.getString("nombre");
            txtPresentacion.setText("Hola "+ presentacionNombre + " rellena los siguientes datos");
            editTextEdad.setText(savedInstanceState.getString("edad"));
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnContinuar:
                Intent i = new Intent();
                Bundle b = new Bundle();
                b.putString("edad",editTextEdad.getText().toString());
                i.putExtras(b);
                setResult(RESULT_OK,i);
                finish();

                break;
        }

    }

    public void onSaveInstanceState(Bundle bundleRestore) {
        bundleRestore.putString("nombre",presentacionNombre);
        bundleRestore.putString("edad",editTextEdad.getText().toString());
    }
}
