package com.wekomodo.auraly.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.ai.client.generativeai.GenerativeModel
import com.wekomodo.auraly.BuildConfig
import com.wekomodo.auraly.R
import com.wekomodo.auraly.ui.components.DropDownMenu
import com.wekomodo.auraly.ui.components.MyTextField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val defaultKeyBoardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    )
    var sample_lyrics by remember { mutableStateOf("") }
    val musicGenres = arrayOf(
        "Blues",
        "Classical",
        "Country",
        "Electronic",
        "International",
        "Jazz",
        "Pop/Rock",
        "R&B",
        "Rap"
    )
    val moodList = arrayOf(
        "sad",
        "happy",
        "energetic",
        "fear",
        "anger"
    )
    val timeList = arrayOf(
        "1 minutes",
        "2 minutes",
        "3 minutes",
    )
    var result by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var mood by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome to", fontSize = 32.sp)
        Text(text = stringResource(id = R.string.app_name), fontSize = 32.sp)
        DropDownMenu(musicGenres) { genre = it }
        DropDownMenu(moodList) { mood = it }
        DropDownMenu(timeList) {
            time = it
        }
        MyTextField(
            label = R.string.sampleLyrics,
            supportText = R.string.sampleLyricsSupportText,
            keyboardOptions = defaultKeyBoardOptions,
            value = sample_lyrics,
            onValueChange = { sample_lyrics = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(100.dp)
        )
        Button(onClick = {
            loading = true
            val generativeModel = GenerativeModel(
                // Use a model that's applicable for your use case (see "Implement basic use cases" below)
                modelName = "gemini-pro",
                // Access your API key as a Build Configuration variable (see "Set up your API key" above)
                apiKey = BuildConfig.apiKey
            )
            val prompt =
                "Write a song with these parameters: mood($mood),time($time),genre($genre),sample lyrics($sample_lyrics)"
            CoroutineScope(Dispatchers.IO).launch {
                val response = generativeModel.generateContent(prompt)
                response.text?.let {
                    result = it
                }
                Log.d("result", result)
                loading = false
            }
        }) {
            Text(text = stringResource(id = R.string.generate))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Output")
                    val scroll = rememberScrollState(0)
                    Text(
                        text = result, modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scroll)
                    )
                }

            }
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

/*
fun generateSong(genre: String, mood: String, time: String, sampleLyrics: String) : String{
    var result = ""
    val generativeModel = GenerativeModel(
        // Use a model that's applicable for your use case (see "Implement basic use cases" below)
        modelName = "gemini-pro",
        // Access your API key as a Build Configuration variable (see "Set up your API key" above)
        apiKey = BuildConfig.apiKey
    )
    val prompt = "Write a song with these parameters: mood($mood),time($time),genre($genre),sample lyrics($sampleLyrics)"
    CoroutineScope(Dispatchers.IO).launch {
        result = generativeModel.generateContent(prompt).text.toString()
        Log.d("result",result)
    }
    return result
}
*/


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}