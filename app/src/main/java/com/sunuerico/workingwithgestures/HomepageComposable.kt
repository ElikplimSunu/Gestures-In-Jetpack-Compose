package com.sunuerico.workingwithgestures

import android.content.Intent
import android.os.Vibrator
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sunuerico.workingwithgestures.ui.theme.WorkingWithGesturesTheme


@Composable
fun HomepageScreen() {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFF8FAFB))
//            .padding(start = 20.dp, end = 20.dp)
        ) {
//        //val (homePageHeader, homePageBody) = createRefs()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
//            .constrainAs(homePageHeader) {
//                top.linkTo(parent.top)
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//            }
                    .background(color = Color.White)
                    .padding(start = 20.dp, end = 20.dp, top = 60.dp, bottom = 25.dp)
            ) {
                Text(
                    text = "Homepage",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ).copy(lineHeight = 32.sp)
                )

                Text(
                    text = "Choose your course and start learning",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF68769F)
                    ).copy(lineHeight = 21.sp)
                )
            }

            Courses()

        }
//    }


    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Courses() {

    val context = LocalContext.current

    val textToSpeech = TextToSpeech(context, null)

    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFFF8FAFB))
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp), //this seems to be the padding between each chunk
//            contentPadding = PaddingValues(vertical = 20.dp),
//            .constrainAs(homePageBody) {
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//            bottom.linkTo(parent.bottom)
//        },

        state = lazyListState
    ) {

        items(subjects) { chunk ->
            Column(modifier = Modifier.background(color = Color(0xFFF8FAFB))) {
//                    chunk.forEach { item ->
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 20.dp
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                            .combinedClickable(
                                onClick = {
                                    Toast
                                        .makeText(
                                            context,
                                            "One Tap",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                },
                                onDoubleClick = {
                                    Toast
                                        .makeText(
                                            context,
                                            "Double Tap",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()

                                    context.startActivity(
                                        Intent(
                                            context,
                                            CourseActivity::class.java
                                        )
                                    )
                                },
                                onLongClick = {
                                    Toast
                                        .makeText(context, "Long Tap", Toast.LENGTH_SHORT)
                                        .show()
                                    getSystemService(
                                        context,
                                        Vibrator::class.java
                                    )?.vibrate(100)

                                    textToSpeech.speak(
                                        chunk,
                                        TextToSpeech.QUEUE_FLUSH,
                                        null,
                                        ""
                                    )

                                }
                            )
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(5f), text = chunk, style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1B2559)
                            ).copy(lineHeight = 23.sp)
                        )
                        Image(
                            modifier = Modifier.weight(1f),
                            painter = painterResource(id = R.drawable.play_button),
                            contentDescription = "Play course"
                        )
                    }

                }
            }
        }
    }
}


val subjects = listOf(
    "English",
    "Mathematics",
    "Social Studies",
    "Integrated Science",
    "Religious and Moral Education",
    "French",
    "ICT",
    "Biology",
    "Chemistry",
    "Physics",
    "Economics",
)

val topics = listOf(
    "Sentence Structure",
    "Parts of Speech",
    "Tenses",
    "Pronouns",
    "Verbs",
    "Nouns",
    "Adjectives",
    "Adverbs",
    "Prepositions"
)


@Preview(showBackground = true)
@Composable
fun HomepageScreenPreview() {
    WorkingWithGesturesTheme {
        HomepageScreen()
    }
}
