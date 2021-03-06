package com.example.admin.bknote;

import android.app.ProgressDialog;

import android.content.Intent;

import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.text.TextUtils;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.TextView;

import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button idLogin;

    private EditText idUser, idPw;

    private TextView idReg;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);

        if (firebaseAuth.getCurrentUser() != null)

        {

            finish();

            startActivity(new Intent(getApplicationContext(), TrangChu.class));

        }

        idUser = (EditText)findViewById(R.id.idUser);

        idPw = (EditText)findViewById(R.id.idPw);

        idLogin = (Button)findViewById(R.id.idLogin);

        idReg = (TextView)findViewById(R.id.idReg);

        idLogin.setOnClickListener(this);

        idReg.setOnClickListener(this);

    }

    private void userLogin()

    {

        String email = idUser.getText().toString().trim();

        String password = idPw.getText().toString().trim();

        if (TextUtils.isEmpty(email))

        {

            Toast.makeText(this, "Vui lòng nhập địa chỉ Email", Toast.LENGTH_SHORT).show();

            return;

        }

        if (TextUtils.isEmpty(password))

        {

            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();

            return;

        }

        progressDialog.setMessage("Đang đăng nhập...");

        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();

                        if(task.isSuccessful())

                        {

                            finish();

                            startActivity(new Intent(getApplicationContext(), TrangChu.class));

                        }

                    }

                });

    }



    @Override

    public void onClick(View v) {

        if(v== idLogin)

        {

            userLogin();

        }

        if(v== idReg)

        {

            startActivity(new Intent(this, Register.class));

        }

    }

}
