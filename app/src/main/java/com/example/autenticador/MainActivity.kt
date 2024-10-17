package com.example.autenticador

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = Firebase.auth

        Log.i(TAG, "onCreate usuário atual: ${auth.currentUser}")

        auth.createUserWithEmailAndPassword(
            "arnaldobernardini@gmail.com",
            "1234567"
        ).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                Log.i(TAG, "onCreate: Sucesso")
            }else{
                Log.i(TAG, "onCreate: Falha -> ${task.exception}")
            }
        }
    }
}