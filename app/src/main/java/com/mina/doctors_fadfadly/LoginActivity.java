package com.mina.doctors_fadfadly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mina.doctors_fadfadly.Base.BaseActivity;
import com.mina.doctors_fadfadly.Database.daos.UsersDao;
import com.mina.doctors_fadfadly.Model.DataUtil;
import com.mina.doctors_fadfadly.Model.User;

public class LoginActivity extends BaseActivity  implements
         View.OnClickListener,OnCompleteListener<AuthResult>,OnFailureListener {

    protected EditText email;
    protected EditText password;
    protected Button login;
    protected TextView sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        initView();
        FirebaseAuth auth =FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            DataUtil.user =
                    FirebaseAuth.getInstance().getCurrentUser();
            showProgressDialog(R.string.loading);
            getUserFromDatabase();
            //startActivity(new Intent(LoginActivity.this,MainActivity.class));
            //finish();
        }
    }

    private void getUserFromDatabase(){
        UsersDao.getUser(DataUtil.user.getUid(), new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                hideProgressDialog();

                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot userDocument : task.getResult()){
                        if (userDocument!=null){
                            User currentUser = userDocument.toObject(User.class);
                            DataUtil.dbUser=currentUser;
                            startActivity(new Intent(LoginActivity.this , MainActivity.class));
                            finish();
                        }
                        break;
                    }
                }
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                hideProgressDialog();
                showMessage(e.getLocalizedMessage(),"ok");
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login) {
            String EmailText=email.getText().toString();
            String passwordText=password.getText().toString();
            if(EmailText.trim().isEmpty()){
                email.setError("required");
                return;
            }
            if(passwordText.trim().isEmpty()){
                password.setError("required");
                return;
            }
            //authontification

            FirebaseAuth auth=FirebaseAuth.getInstance();
            showProgressDialog(R.string.loading);
            auth.signInWithEmailAndPassword(EmailText,passwordText)
                    .addOnCompleteListener(this)
                    .addOnFailureListener(this);


        }
        else if (view.getId()==R.id.sign_up){
            startActivity(new Intent(this,RegisterActivity.class));
        }
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        hideProgressDialog();
        if(task.isSuccessful()){
            DataUtil.user =
                    FirebaseAuth.getInstance().getCurrentUser();
            //startActivity(new Intent(LoginActivity.this,MainActivity.class));
            //finish();
            getUserFromDatabase();
        }
    }


    @Override
    public void onFailure(@NonNull Exception e) {
        hideProgressDialog();
        showMessage("Invalid email or password","ok");

    }
    private void initView() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        sign_up=(TextView) findViewById(R.id.sign_up);
        login.setOnClickListener(LoginActivity.this);
        sign_up.setOnClickListener(this);
    }


}
