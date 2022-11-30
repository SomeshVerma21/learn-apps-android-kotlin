package test.compose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import test.compose.app.ui.theme.ChatAnsGradientBush
import test.compose.app.ui.theme.ChatGradientBush
import test.compose.app.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Screen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        Screen()
    }
}


data class ChatItem(
    val title: String,
    val isAnswer: Boolean,
)

val list = mutableStateListOf<ChatItem>(
    ChatItem("Chat Answer", false),
    ChatItem("flfdsl sdfsdfsdf", true),
    ChatItem("Chat Answer", false),
    ChatItem("flfdslf", true),
    ChatItem("Chasdfdsft Answer", false),
    ChatItem("flfdsl sdfdsfdsfs dff", true),
    ChatItem(" Answer", false),
    ChatItem("flfdslf sdfdsfsfdfsd", true),
    ChatItem("Chat Answer", false),
    ChatItem("flfdsl  s dsf", true),
    ChatItem("Chat sd fdsf", false),
    ChatItem("flfdslf", true),
    ChatItem("Chat Answer", false),
    ChatItem("dfdsdfdsf sdf ds fdsfsd f sdfdsfdsfsdf sddsfss fd", true),
    ChatItem("Chasdfdsft Answer", false),
    ChatItem("flfdslf", true),
)

@Composable
fun Screen(){
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var inputText by remember {
        mutableStateOf("")
    }

    fun sendMessage(isAnswer: Boolean){
        list.add(ChatItem(inputText, isAnswer)).also {
            inputText = ""
            coroutineScope.launch {
                listState.animateScrollToItem(list.lastIndex, 0)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
    ){
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ){
            items(items = list){ item ->
                CustomText(
                    title = item.title,
                    isAnswer = item.isAnswer
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = {inputText = it},
                textStyle = TextStyle(
                    color = MaterialTheme.colors.surface,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif
                ),
                maxLines = 1,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 10.dp, vertical = 3.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray
            ))
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send Message",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        if (inputText.isNotEmpty()) {
                            sendMessage(
                                !inputText.first().isUpperCase()
                            )
                        }
                    },
                tint = if (inputText.isNotEmpty()) Color.Gray else Color.LightGray
            )
        }
    }
}


@Composable
fun CustomText(title:String, isAnswer:Boolean){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isAnswer) Arrangement.End else Arrangement.Start
    ){
        Box(
            modifier = Modifier
                .widthIn(0.dp , 200.dp)
                .padding(10.dp)
                .background(
                    brush = if (isAnswer) ChatAnsGradientBush else ChatGradientBush,
                    shape = if (isAnswer) RoundedCornerShape(15, 15, 15, 0)
                    else RoundedCornerShape(15, 15, 0, 15)
                )
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                color = MaterialTheme.colors.surface
            )
        }

    }
}
