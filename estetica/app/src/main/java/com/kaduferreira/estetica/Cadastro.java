package com.kaduferreira.estetica;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {

    Button fimCadastro, cancelar;
    EditText nome, editEmail, editSenha;
    CheckBox termos;

    ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(Cadastro.this);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        termos = (CheckBox) findViewById(R.id.btnConfirmarTermos);
        nome = (EditText) findViewById(R.id.editNome);
        fimCadastro = (Button) findViewById(R.id.btnFimCadastro);
        cancelar = (Button) findViewById(R.id.btnCancelarCadastro);

        nome.requestFocus();
        fimCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Cadastro.this, "Please complet this camp requered", Toast.LENGTH_LONG).show();

                    return;
                }
                if (TextUtils.isEmpty(senha)){
                    Toast.makeText(Cadastro.this, "Please complet this camp requered", Toast.LENGTH_LONG).show();
                    return;
                }

                progressDialog.setMessage("Registrando Usuario...");
                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Cadastro.this, "Registrado com Sucesso", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(Cadastro.this, "Ocorreu algum erro, tente novamente", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(Cadastro.this, MainActivity.class);
                startActivity(login);
                Cadastro.this.finish();
            }
        });

        termos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Cadastro.this);
                alerta.setTitle("Contrato e Termos de Serviço");
                alerta.setMessage("Nesta pagina irão conter todas as regras e informações que serão necessarias para que o usuario " +
                        "possa utilizar sem problemas o aplicativo");
                alerta.setPositiveButton("Aceitar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        termos.setChecked(true);
                    }
                });
                alerta.setNegativeButton("Recusar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        termos.setChecked(false);
                    }
                });
                if (termos.isChecked()){
                    AlertDialog criar = alerta.create();
                    criar.show();
                }
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
        AlertDialog novo = alerta.create();
        novo.show();
    }
}
