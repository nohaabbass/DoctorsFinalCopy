package com.mina.doctors_fadfadly.Database.daos;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.mina.doctors_fadfadly.Database.MyDataBase;
import com.mina.doctors_fadfadly.Model.User;

/**
 * Created by Mohamed Nabil Mohamed on 10/5/2019.
 * m.nabil.fci2015@gmail.com
 */
public class UsersDao {

    public static void addUser(User user, OnCompleteListener onSuccessListener,
                               OnFailureListener onFailureListener){
      MyDataBase.getDoctorsReference()
              .document(user.getId())
              .set(user)
              .addOnCompleteListener(onSuccessListener)
              .addOnFailureListener(onFailureListener);
    }
    public static void getUser(String userId,
                               OnCompleteListener onSuccessListener,
                               OnFailureListener onFailureListener){
        MyDataBase.getDoctorsReference()
                .whereEqualTo("id",userId)
                .get()
                .addOnCompleteListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);    }
}
