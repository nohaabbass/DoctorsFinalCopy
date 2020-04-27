package com.mina.doctors_fadfadly.Database;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class MyDataBase {

    public static final String USERS_REF = "users";
    public static final String DOCTORS_REF = "doctors";

    private static CollectionReference getCollection(String collectionName){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        return firebaseFirestore.collection(collectionName);

    }
    public static CollectionReference getDoctorsReference(){
        return getCollection(DOCTORS_REF);
    }

    public static CollectionReference getUsersReference(){
        return getCollection(USERS_REF);
    }

}
