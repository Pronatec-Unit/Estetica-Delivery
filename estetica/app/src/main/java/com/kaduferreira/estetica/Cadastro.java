package com.kaduferreira.estetica;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cadastro extends AppCompatActivity {

    Button fimCadastro, cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        fimCadastro = (Button) findViewById(R.id.btnFimCadastro);
        cancelar = (Button) findViewById(R.id.btnCancelarCadastro);

        fimCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(Cadastro.this, MainActivity.class);
                startActivity(login);
                Cadastro.this.finish();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
    public void onBackPressed(){
        final AlertDialog.Builder alerta = new AlertDialog.Builder(Cadastro.this);
        alerta.setTitle("Quer Desistir Mesmo?");
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent login = new Intent(Cadastro.this, MainActivity.class);
                startActivity(login);
                Cadastro.this.finish();
            }
        });
        alerta.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
    }
}
