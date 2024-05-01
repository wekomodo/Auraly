package com.wekomodo.auraly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wekomodo.auraly.screens.HomeScreen
import com.wekomodo.auraly.ui.theme.AuralyTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuralyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // val key = BuildConfig.apiKey
                  /*  val generativeModel = GenerativeModel(
                        // Use a model that's applicable for your use case (see "Implement basic use cases" below)
                        modelName = "gemini-pro",
                        // Access your API key as a Build Configuration variable (see "Set up your API key" above)
                        apiKey = BuildConfig.apiKey
                    )
                    val prompt = "Write a story about a magic backpack."
                    LaunchedEffect(Unit) {
                        val response = generativeModel.generateContent(prompt)
                        print(response.text)
                    }*/
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AuralyTheme {
        Greeting("Android")
    }
}