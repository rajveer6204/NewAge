package com.example.nearnestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.nearnestapp.navigation.NearNestNavGraph
import com.example.nearnestapp.ui.theme.NearNestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearNestTheme {
                NearNestNavGraph()
            }
        }
    }
}
