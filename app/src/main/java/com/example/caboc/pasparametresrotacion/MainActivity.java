package com.example.caboc.pasparametresrotacion;


import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDades;
    EditText nombre;
    RadioButton rbSexeMasc;
    RadioButton rbSexeFem;
    TextView mensajeFinal;
    TextView txtNombre;
    TextView txtSexe;
    boolean ultimaPantalla;
    String textoMensajeFinal;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ultimaPantalla = false;
        textoMensajeFinal = "";
        setContentView(R.layout.activity_main);
        btnDades = (Button) findViewById(R.id.button);
        btnDades.setOnClickListener(this);
        nombre = (EditText) findViewById(R.id.editTextNom);
        rbSexeMasc = (RadioButton) findViewById(R.id.radioButtonSexe);
        rbSexeFem = (RadioButton) findViewById(R.id.radioButtonSexe2);
        mensajeFinal = (TextView) findViewById(R.id.txtMensajeFinal);
        mensajeFinal.setVisibility(View.INVISIBLE);
        txtNombre = (TextView) findViewById(R.id.txtNom);
        txtSexe = (TextView) findViewById(R.id.txtSexe);

        if(savedInstanceState != null){

            if (savedInstanceState.getBoolean("ultimaPantalla")){
                ultimaPantalla = savedInstanceState.getBoolean("ultimaPantalla");
                textoMensajeFinal = savedInstanceState.getString("mensajeFinal");
                mensajeFinal.setText(savedInstanceState.getString("mensajeFinal"));
                viewUltimaPantalla();
            }
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button:

                if(!isEmpty(nombre) && (rbSexeFem.isChecked() || rbSexeMasc.isChecked())) {
                    Intent i = new Intent(getApplicationContext(), subActivity.class);
                    Bundle b = new Bundle();
                    b.putString("nombre",nombre.getText().toString());
                    i.putExtras(b);
                    startActivityForResult(i, 0);
                }else{
                    Toast.makeText(MainActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 0:
                Bundle b = data.getExtras();
                String cadenaEdad = b.getString("edad");
                int edad = Integer.parseInt(cadenaEdad);


                if(edad > 18 && edad < 25)
                    textoMensajeFinal = "Ja eres major d'edat";
                else if (edad >= 25 && edad < 35)
                    textoMensajeFinal = "Estas en la flor de la vida";
                else if (edad >= 35 )
                    textoMensajeFinal = "ai, ai, ai";

                mensajeFinal.setText(textoMensajeFinal);

                viewUltimaPantalla();
                ultimaPantalla = true;

                break;
        }
    }

    public void viewUltimaPantalla(){
        mensajeFinal.setVisibility(View.VISIBLE);
        btnDades.setVisibility(View.INVISIBLE);
        nombre.setVisibility(View.INVISIBLE);
        rbSexeMasc.setVisibility(View.INVISIBLE);
        rbSexeFem.setVisibility(View.INVISIBLE);
        txtNombre.setVisibility(View.INVISIBLE);
        txtSexe.setVisibility(View.INVISIBLE);
    }

    public void onSaveInstanceState(Bundle bundleRestore){
        super.onSaveInstanceState(bundleRestore);
        bundleRestore.putBoolean("ultimaPantalla",ultimaPantalla);
        bundleRestore.putString("mensajeFinal",textoMensajeFinal);
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
