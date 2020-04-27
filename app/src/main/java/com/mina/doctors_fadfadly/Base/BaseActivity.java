package com.mina.doctors_fadfadly.Base;

import android.app.ProgressDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {


    public AlertDialog showMessage(String message, String posActionName){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.show();
    }
    public AlertDialog showMessage(int message, int posActionName){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.show();
    }

    public AlertDialog showMessage(String message, String posActionName,
                                   DialogInterface.OnClickListener onClickListener,
                                   boolean isCancelable){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionName,onClickListener );
        builder.setCancelable(isCancelable);
        return builder.show();
    }
    public AlertDialog showMessage(String message, String posActionName,
                                   DialogInterface.OnClickListener onPosClick,
                                   String negativeText,
                                   DialogInterface.OnClickListener onNegativeClick,
                                   boolean isCancelable){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionName,onPosClick );
        builder.setNegativeButton(negativeText,onNegativeClick );
        builder.setCancelable(isCancelable);
        return builder.show();
    }
public AlertDialog showMessage(int message, int posActionName,
                               DialogInterface.OnClickListener onClickListener,
                               boolean isCancelable
){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionName,onClickListener);
        builder.setCancelable(isCancelable);
        return builder.show();
    }
    public AlertDialog showMessage(int message, int posActionName,
                                   DialogInterface.OnClickListener onPosClick,
                                   int negativeText,
                                   DialogInterface.OnClickListener onNegativeClick,
                                   boolean isCancelable
    ){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionName,onPosClick );
        builder.setNegativeButton(negativeText,onNegativeClick );
        builder.setCancelable(isCancelable);
        return builder.show();
    }

    ProgressDialog progressDialog;
    public void showProgressDialog(int messageId){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(messageId));
        progressDialog.show();
    }
    public void hideProgressDialog(){
        if(progressDialog!=null&&progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
