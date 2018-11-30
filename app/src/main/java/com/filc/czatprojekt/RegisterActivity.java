package com.filc.czatprojekt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    FirebaseAuth firebaseAuth;
    EditText emailText;
    EditText passwordText;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.email_et2);
        passwordText = findViewById(R.id.password_et2);
        registerButton = findViewById(R.id.complete_reg_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                if(!isValid(email, password)) {
                    Toast.makeText(RegisterActivity.this,
                            "Email i Hasło nie mogą być puste",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "registerUser: success");
                                    Intent chatIntent = new Intent(RegisterActivity.this, ChatActivity.class);
                                    startActivity(chatIntent);

                                } else {
                                    Log.d(TAG, "registerUser: failure");
                                    Toast.makeText(RegisterActivity.this,
                                            "Rejestracja zakończona niepowodzeniem",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private boolean isValid(String email, String password) {
        boolean valid = true;
        if (email.isEmpty() || password.isEmpty()) {
            valid = false;
        }
        return valid;
    }
}
