package com.kaduferreira.estetica;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9000;
    Button btnEntrar, tela, signOutButton;
    SignInButton signInButton;
    EditText email;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.inpEmail);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signOutButton = (Button) findViewById(R.id.sign_out_button);


        tela = (Button) findViewById(R.id.disconnect_button);

        email.requestFocus();
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Base.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        tela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(login);
            }
        });
}

}
