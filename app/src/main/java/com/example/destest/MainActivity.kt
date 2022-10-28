package com.example.destest

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
import com.example.destest.core.main.compose.DESTestNavHost
import com.example.destest.ui.theme.DESTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DESTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DESTestNavHost()

//                    val viewModel: ContentViewModel = hiltViewModel()
//                    val state = viewModel.state.value
//                    val scaffoldState = rememberScaffoldState()
//
//                    LaunchedEffect(key1 = true) {
//                        viewModel.eventFlow.collectLatest { event ->
//                            when(event) {
//                                is ContentViewModel.UIEvent.ShowSnackBar -> {
//                                    Log.d("AERODEBUG", event.message)
//                                }
//                            }
//                        }
//                    }
//                    Scaffold(
//                        scaffoldState = scaffoldState
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .background(MaterialTheme.colorScheme.background)
//                        ) {
//                            LazyColumn(
//                                modifier = Modifier.fillMaxWidth()
//                            ) {
//                                items(state.content.size) { i ->
//                                    val content = state.content[i]
//                                    if(i > 0) {
//                                        Spacer(modifier = Modifier.height(8.dp))
//                                    }
//                                    Text("#${content.id}: ${content.getDate()} By: ${content.title.substring(0,10)}")
//                                }
//                            }
//                            if(state.isLoading) {
//                                Text("Loading....")
//                            }
//                        }
//                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DESTestTheme {
        Greeting("Android")
    }
}
