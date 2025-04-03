package com.example.solitodo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vieModel = CountViewModel()
        enableEdgeToEdge()
        setContent {
            Scaffold(
                topBar = {

                    TopAppBar(
                        title = { Text("Solitodo") }
                    )
                },
                content = {
                    val count by vieModel.count
                    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth().fillMaxHeight()){
                        Button(onClick = { vieModel.increment() }) {
                            Text("Increment")
                        }
                            Text("Count: $count")
                        Button(onClick = { vieModel.decrement() }) {
                            Text("Decrement")
                        }
                    }
                }
            )
        }
    }
}
