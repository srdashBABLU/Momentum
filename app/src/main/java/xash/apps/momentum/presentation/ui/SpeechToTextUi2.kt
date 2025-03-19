package xash.apps.momentum.presentation.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun SpeechToTextFeature2() {
    val context = LocalContext.current
    val speechRecognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }
    var spokenText by remember { mutableStateOf(TextFieldValue("Press the button and start speaking...")) }
    var isListening by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf(Locale.getDefault().language) }

    var mediaRecorder: MediaRecorder? by remember { mutableStateOf(null) }
    var audioFilePath by remember { mutableStateOf("") }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.values.all { it }
        if (!granted) {
            Toast.makeText(context, "Permissions denied!", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )
    }

    val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, selectedLanguage)
        putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
    }

    speechRecognizer.setRecognitionListener(object : RecognitionListener {
        override fun onResults(results: Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            spokenText = TextFieldValue(matches?.get(0) ?: "Could not recognize speech.")
            isListening = false
        }

        override fun onPartialResults(partialResults: Bundle?) {
            val matches = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (!matches.isNullOrEmpty()) {
                spokenText = TextFieldValue(matches[0])
            }
        }

        override fun onReadyForSpeech(params: Bundle?) {}
        override fun onBeginningOfSpeech() { spokenText = TextFieldValue("Listening...") }
        override fun onRmsChanged(rmsdB: Float) {}
        override fun onBufferReceived(buffer: ByteArray?) {}
        override fun onEndOfSpeech() { isListening = false }
        override fun onError(error: Int) { spokenText = TextFieldValue("Error occurred! Try again."); isListening = false }
        override fun onEvent(eventType: Int, params: Bundle?) {}
    })

    fun startRecording() {
        val audioFile = File(context.getExternalFilesDir(Environment.DIRECTORY_MUSIC), "recorded_audio.3gp")
        audioFilePath = audioFile.absolutePath
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(audioFilePath)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            try {
                prepare()
                start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
        Toast.makeText(context, "Audio saved at $audioFilePath", Toast.LENGTH_LONG).show()
    }

    fun saveTextToFile(text: String) {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "transcription.txt")
        try {
            FileOutputStream(file).use {
                it.write(text.toByteArray())
            }
            Toast.makeText(context, "Transcription saved: ${file.absolutePath}", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Language Selection Dropdown
        DropdownMenuDemo { selectedLanguage = it }

        Spacer(modifier = Modifier.height(20.dp))

        // Display Real-Time Text
        BasicTextField(
            value = spokenText,
            onValueChange = { spokenText = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    if (!isListening) {
                        speechRecognizer.startListening(speechRecognizerIntent)
                        isListening = true
                        startRecording()
                    } else {
                        speechRecognizer.stopListening()
                        stopRecording()
                        isListening = false
                    }
                }
            ) {
                Text(if (isListening) "Stop Listening" else "Start Speaking")
            }

            Button(onClick = { saveTextToFile(spokenText.text) }) {
                Text("Save Text")
            }
        }
    }
}

// Language Selection Dropdown
@Composable
fun DropdownMenuDemo(onLanguageSelected: (String) -> Unit) {
    val languages = mapOf(
        "English" to "en-US",
        "Hindi" to "hi-IN",
        "French" to "fr-FR",
        "Spanish" to "es-ES"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("English") }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedText)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languages.forEach { (name, code) ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = name
                        onLanguageSelected(code)
                        expanded = false
                    },
                    text = {
                        Text(name)
                    },
                    modifier = TODO(),
                    leadingIcon = TODO(),
                    trailingIcon = TODO(),
                    enabled = TODO(),
                    colors = TODO(),
                    contentPadding = TODO(),
                )
            }
        }
    }
}
