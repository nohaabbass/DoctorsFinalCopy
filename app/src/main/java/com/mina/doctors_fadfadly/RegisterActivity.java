package com.mina.doctors_fadfadly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mina.doctors_fadfadly.Base.BaseActivity;
import com.mina.doctors_fadfadly.Database.daos.UsersDao;
import com.mina.doctors_fadfadly.Model.DataUtil;
import com.mina.doctors_fadfadly.Model.User;


public class RegisterActivity extends BaseActivity implements View.OnClickListener,
        OnFailureListener, OnCompleteListener<AuthResult> {


    protected ImageView log;
    protected ImageView icon;
    protected TextView title;
    protected TextView username;
    protected EditText usernameText;
    protected TextView age;
    protected EditText ageText;
    protected TextView email;
    protected EditText emailText;
    protected TextView password;
    protected EditText passwordText;
    protected EditText nationalIdRegisterEdittext;
    protected TextView nationalIdRegister;
    protected EditText addressRegisterEdittext;
    protected TextView addressRegister;
    protected EditText bachelorEdit;
    protected TextView bachelor;
    protected Button buttonRegister;
    protected CardView card;
    protected RadioGroup group;
    protected RadioButton genderButton;
    private String gender ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_register);
        initView();
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                genderButton = group.findViewById(i);
                switch (i){
                    case R.id.male :
                        gender = genderButton.getText().toString();
                        break;
                    case R.id.female :
                        gender = genderButton.getText().toString();
                        break;
                }
            }
        });
    }

    User user;
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        hideProgressDialog();
        if(task.isSuccessful()){
            DataUtil.user =
                    FirebaseAuth.getInstance().getCurrentUser();
            user.setId(DataUtil.user.getUid());
            UsersDao.addUser(user, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            DataUtil.dbUser=user;
                            hideProgressDialog();
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                            finish();

                        }
                    },
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            hideProgressDialog();
                            showMessage(e.getLocalizedMessage(),"ok");

                        }
                    });
        }
    }


    @Override
    public void onFailure(@NonNull Exception e) {
        hideProgressDialog();
        showMessage(e.getLocalizedMessage(),"ok");

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_Register) {
                String UsernameText = usernameText.getText().toString();
                String AgeText = ageText.getText().toString();
                String EmailText = emailText.getText().toString();
                String PasswordText = passwordText.getText().toString();
                String NationalID = nationalIdRegisterEdittext.getText().toString();
                String Bachelor = bachelorEdit.getText().toString();
                String Address = addressRegisterEdittext.getText().toString();




                if (UsernameText.trim().isEmpty()) {
                     usernameText.setError("required");
                     return;
                }

                if (AgeText.trim().isEmpty()) {
                    ageText.setError("required");
                    return;
                }
                if (EmailText.trim().isEmpty()) {
                    emailText.setError("required");
                    return;
                }
                if (PasswordText.trim().isEmpty()) {
                    passwordText.setError("required");
                    return;
                }

                if (NationalID.trim().isEmpty()) {
                    nationalIdRegisterEdittext.setError("required");
                    return;
                }

                if (Bachelor.trim().isEmpty()) {
                    bachelorEdit.setError("required");
                    return;
                }
                if (Address.trim().isEmpty()) {
                    addressRegisterEdittext.setError("required");
                    return;
                }

            if (gender.trim().isEmpty()) {
                Toast.makeText(this, "please enter gender", Toast.LENGTH_SHORT).show();
                return;
            }


                //authontification
                user = new User();
                user.setName(UsernameText);
                user.setAge(AgeText);
                user.setEmail(EmailText);
                user.setPassword(PasswordText);
                user.setGender(gender);
                user.setNationalId(NationalID);
                user.setBachelor(Bachelor);
                user.setAddress(Address);

                FirebaseAuth auth = FirebaseAuth.getInstance();
                showProgressDialog(R.string.loading);
                auth.createUserWithEmailAndPassword(EmailText, PasswordText)
                        .addOnCompleteListener(this)
                        .addOnFailureListener(this);

            }
        }


    private void initView() {
        log = (ImageView) findViewById(R.id.log);
        icon = (ImageView) findViewById(R.id.icon);
        title = (TextView) findViewById(R.id.title);
        username = (TextView) findViewById(R.id.username);
        usernameText = (EditText) findViewById(R.id.username_text);
        age = (TextView) findViewById(R.id.age);
        ageText = (EditText) findViewById(R.id.age_text);
        email = (TextView) findViewById(R.id.email);
        emailText = (EditText) findViewById(R.id.email_text);
        password = (TextView) findViewById(R.id.password);
        passwordText = (EditText) findViewById(R.id.password_text);
        nationalIdRegisterEdittext = (EditText) findViewById(R.id.nationalId_register_edittext);
        nationalIdRegister = (TextView) findViewById(R.id.nationalId_register);
        addressRegisterEdittext = (EditText) findViewById(R.id.address_register_edittext);
        addressRegister = (TextView) findViewById(R.id.address_register);
        bachelorEdit = (EditText) findViewById(R.id.bachelor_edit);
        bachelor = (TextView) findViewById(R.id.bachelor);
        group = (RadioGroup) findViewById(R.id.radio);
        buttonRegister = (Button) findViewById(R.id.button_Register);
        buttonRegister.setOnClickListener(RegisterActivity.this);
        card = (CardView) findViewById(R.id.card);
    }
}

