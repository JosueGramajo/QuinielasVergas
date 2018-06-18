package com.gramajo.josue.quinielasvergas;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.OnEventListener;

import dmax.dialog.SpotsDialog;

/**
 * Created by josuegramajo on 6/15/18.
 */

public class LoginActivity extends AppCompatActivity {

    EditText userEditText, passEditText;
    Button loginButton;
    TextView registerTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.btn_login);
        userEditText = findViewById(R.id.tv_user);
        passEditText = findViewById(R.id.tv_password);
        registerTextView = findViewById(R.id.tv_register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SpotsDialog dialog = (SpotsDialog) new SpotsDialog.Builder().setContext(LoginActivity.this).build();

                if(userEditText.getText().equals("") || passEditText.getText().equals("")){
                    Toast.makeText(LoginActivity.this, "Ingrese todos los valores", Toast.LENGTH_LONG).show();
                }else{
                    dialog.show();
                    FirebaseUtils firebase = new FirebaseUtils();
                    firebase.setOnEventListener(new OnEventListener() {
                        @Override
                        public void onFirestoreLoginSuccess() {
                            dialog.dismiss();
                            Intent returnIntent = new Intent();
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        }
                    });
                    firebase.login(LoginActivity.this, userEditText.getText().toString(), passEditText.getText().toString());
                }
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void register(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText userText = new EditText(this);
        final EditText passwordText = new EditText(this);
        alert.setMessage("Ingrese un nombre y una contraseña");
        alert.setTitle("Registro");

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 9, 20, 9);

        userText.setLayoutParams(lp);
        passwordText.setLayoutParams(lp);
        passwordText.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);

        userText.setHint("Usuario");
        passwordText.setHint("Contraseña");

        final LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.addView(userText);
        linear.addView(passwordText);

        alert.setView(linear);

        alert.setPositiveButton("Registrarse", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String user = userText.getText().toString();
                String pass = passwordText.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Ingresa todos los valores maje >:v", Toast.LENGTH_LONG).show();
                }else{
                    FirebaseUtils.INSTANCE.register(LoginActivity.this, user, pass);
                }
            }
        });

        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();
    }
}
