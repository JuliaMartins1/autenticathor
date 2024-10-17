package com.example.autenticador

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            App()
        }


    }
}


@SuppressLint("UnrememberedMutableState", "SuspiciousIndentation")
@Composable
fun App() {
    val auth = Firebase.auth

    Log.i(TAG, "onCreate usuário atual: ${auth.currentUser}")
}
    val email by remember {
        mutableStateOf("")
    }
    var senha by remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        //titulo
        Row(
            Modifier.fillMaxWidth(),
            Arrangement.Center
        ) {
            Text(
                text = "App Database",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        //nome com set value
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email:") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        //telefone com set value
        TextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha:") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                auth.createUserWithEmailAndPassword(
                    email,
                    senha
                ).addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Log.i(TAG, "onCreate: Sucesso")
                    }else{
                        Log.i(TAG, "onCreate: Falha -> ${task.exception}")
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cadastrar")
        }
