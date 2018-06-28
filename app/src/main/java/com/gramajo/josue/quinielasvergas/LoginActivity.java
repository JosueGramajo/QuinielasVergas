package com.gramajo.josue.quinielasvergas;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.OnEventListener;
import com.gramajo.josue.quinielasvergas.Helpers.SharedPreferencesUtils;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

/**
 * Created by josuegramajo on 6/15/18.
 */

public class LoginActivity extends AppCompatActivity {

    EditText userEditText, passEditText;
    Button loginButton;
    TextView registerTextView;

    FirebaseUtils firebase;

    SpotsDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.btn_login);
        userEditText = findViewById(R.id.tv_user);
        passEditText = findViewById(R.id.tv_password);
        registerTextView = findViewById(R.id.tv_register);

        userEditText.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        passEditText.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        dialog = (SpotsDialog) new SpotsDialog.Builder().setContext(LoginActivity.this).build();

        firebase = new FirebaseUtils();
        firebase.setOnEventListener(new OnEventListener() {
            @Override
            public void onFirestoreLoginSuccess() {
                dialog.dismiss();

                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            }

            @Override
            public void onFirestoreLoginFailure() {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "Usuario o contraseña invalidos", Toast.LENGTH_LONG).show();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userEditText.getText().equals("") || passEditText.getText().equals("")){
                    Toast.makeText(LoginActivity.this, "Ingrese todos los valores", Toast.LENGTH_LONG).show();
                }else{
                    dialog.show();
                    firebase.login(LoginActivity.this, userEditText.getText().toString(), passEditText.getText().toString(), LoginActivity.this);
                }
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        ArrayList<String> values = SharedPreferencesUtils.INSTANCE.readUserAndPassword(this);
        if(!values.get(0).equals("") && !values.get(1).equals("")){
            dialog.show();
            firebase.login(LoginActivity.this, values.get(0), values.get(1), this);
        }
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

        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        userText.setLayoutParams(lp);
        passwordText.setLayoutParams(lp);
        passwordText.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);

        userText.setHint("Usuario");
        userText.setSingleLine(true);
        userText.setTextColor(getResources().getColor(R.color.white));

        passwordText.setHint("Contraseña");
        passwordText.setSingleLine(true);
        passwordText.setTextColor(getResources().getColor(R.color.white));

        final LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.addView(userText);
        linear.addView(passwordText);
        linear.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.blue_wallpaper));
        linear.setLayoutParams(linearParams
        );

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
