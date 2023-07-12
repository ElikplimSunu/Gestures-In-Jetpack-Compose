package com.sunuerico.workingwithgestures

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.ImageButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReaderScreen() {
    val context = LocalContext.current

    val textToSpeech = TextToSpeech(context, null)

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (cardDesignLeft, cardDesignRight, textCard, playButton) = createRefs()
        Box(modifier = Modifier
            .constrainAs(cardDesignLeft) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
            .padding(top = 217.dp, bottom = 186.dp)
            .background(
                shape = RoundedCornerShape(bottomEnd = 20.dp),
                color = Color(0xFFA3AED0)
            )
            .width(48.dp)
            .height(600.dp))

        Box(modifier = Modifier
            .constrainAs(cardDesignRight) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(top = 217.dp, bottom = 186.dp)
            .background(
                shape = RoundedCornerShape(bottomStart = 20.dp),
                color = Color(0xFFA3AED0)
            )
            .width(48.dp)
            .height(600.dp))

        Card(modifier = Modifier
            .constrainAs(textCard) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(top = 60.dp, bottom = 211.dp)
            .background(shape = RoundedCornerShape(20.dp), color = Color.White)
            .padding(20.dp)
            .width(353.dp)
            .height(581.dp)) {

            Text(
                text = "Overview",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    background = Color.White
                ).copy(lineHeight = 32.sp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    text = "English Language",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF68769F),
                        background = Color.White
                    ).copy(lineHeight = 23.sp)
                )

                Text(
                    text = extractTextFromPdf("res/raw/dummy_text.pdf"), style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF1B2559),
                        background = Color.White
                    ).copy(lineHeight = 20.sp)
                )
0
            }

        }
        val readerText = extractTextFromPdf("res/raw/dummy_text.pdf")

        Box (modifier = Modifier
            .constrainAs(playButton) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(bottom = 106.dp)){
            PauseAndPlayButton(context, readerText, textToSpeech)
        }


//        IconButton(
//            onClick = {
//                textToSpeech.speak(
//                    readerText,
//                    TextToSpeech.QUEUE_FLUSH,
//                    null,
//                    "reader_text_placeholder"
//                )
//                if (textToSpeech.isSpeaking) {
//                    textToSpeech.stop()
//                }
//            },
//            modifier = Modifier
//                .constrainAs(playButton) {
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    bottom.linkTo(parent.bottom)
//                }
//                .padding(bottom = 106.dp)
//        ) {
//            Image(
//                painter = painterResource(R.drawable.play_button),
//                contentDescription = "My Icon",
//            )
        }
}

    fun extractTextFromPdf(path: String): String {
        // on below line we are creating a
        // variable for storing our extracted text
        var extractedText = ""

        // on below line we are running a try and catch block
        // to handle extract data operation.
        try {

            val pdfReader: PdfReader = PdfReader(path)

            val n = pdfReader.numberOfPages

            for (i in 0 until n) {
                // text from our pdf file using pdf reader.
                extractedText =
                    """
                 $extractedText${
                        PdfTextExtractor.getTextFromPage(pdfReader, i + 1).trim { it <= ' ' }
                    }
                  
                 """.trimIndent()
                // to extract the PDF content from the different pages
            }
            // closing our pdf reader.
            pdfReader.close()

        }
        // on below line we are handling our
        // exception using catch block
        catch (e: Exception) {
            e.printStackTrace()
        }

        return extractedText
    }

private const val playIcon = 1
private const val pauseIcon = 2

@Composable
fun PauseAndPlayButton(context: Context = LocalContext.current.applicationContext,
                       readerText: String, textToSpeech: TextToSpeech = TextToSpeech(context, null)
) {


    // This is used to remember the icon of the button
    // Its values are playIcon, loadingBar, and pauseIcon
    // Initially display the playIcon
    var buttonIcon by remember {
        mutableIntStateOf(playIcon)
    }

    OutlinedButton(
        modifier = Modifier
            .size(72.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(12.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 10.dp
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White
        ),
        border = BorderStroke(0.dp, Color.Transparent),
        onClick = {
            textToSpeech.speak(
                readerText,
                TextToSpeech.QUEUE_FLUSH,
                null,
                "reader_text_placeholder"
            )

                if (buttonIcon == playIcon) {
                    // If the current icon is play Icon
                    // change it to pause icon
                    buttonIcon = pauseIcon
                } else if (buttonIcon == pauseIcon) {
                    // If the current icon is pause icon
                    // change it to play icon
                    buttonIcon = playIcon
                }
        }
    ) {

        when (buttonIcon) {
            playIcon -> {
                // Set the play icon
                SetButtonIcons(
                    icon = R.drawable.play_button,
                    iconDescription = "Play Song"
                )

                // If the song is loaded, pause the actual song
                if (textToSpeech.isSpeaking) {
                    textToSpeech.stop()
                }
            }
            pauseIcon -> {
                // Set the pause icon
                SetButtonIcons(R.drawable.baseline_pause_circle_outline_24, iconDescription = "Pause speech")

                // If the song is loaded, play the actual song
                if (!(textToSpeech.isSpeaking)) {
                    textToSpeech.speak(
                        readerText,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        "reader_text_placeholder")
                }
            }
        }
    }
}


@Composable
private fun SetButtonIcons(
    icon: Int,
    iconDescription: String
) {
    Icon(
        modifier = Modifier
            .fillMaxSize(),
        painter = painterResource(id = icon),
        contentDescription = iconDescription,
        tint = Color(0xFF68769F)
    )
}




@Preview (showBackground = true)
@Composable
fun ReaderScreenPreview() {
    ReaderScreen()
}