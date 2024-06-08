package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainLayout()
                }
            }
        }
    }
}
@Composable
fun MainLayout(){
    Box {
        Image(modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop ,
            painter = painterResource(id = R.drawable.app_background),
            contentDescription = null)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainText()
            }

            Spacer(modifier = Modifier.weight(0.5f))


                ContactInfo(modifier = Modifier)

        }
    }
}

@Composable
fun MainText(modifier: Modifier = Modifier){
    Column (modifier = Modifier
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        val logo = painterResource(id = R.drawable.android_developer_png)
        Image(painter = logo,
            contentDescription = null,
            Modifier.size(130.dp)

        )
        Text(text = stringResource(R.string.name),
            fontSize = 35.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 28.dp, bottom = 8.dp)

        )
        Text(text = stringResource(R.string.title),
            color = Color(0xFF18B860),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }

}
@Composable
fun ContactInfo(modifier: Modifier){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 70.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start) {
        Row {
            Icon(
                modifier = Modifier
                    .padding(3.dp)
                    .size(23.dp),
                painter = painterResource(R.drawable.icon_call),
                tint = Color.White,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(R.string.phone_number),
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
        Row {
            Icon(
                modifier = Modifier
                    .padding(3.dp)
                    .size(22.dp),
                painter = painterResource(R.drawable.icon_public),
                tint = Color.White,
                contentDescription = null
            )
            val annotatedString = buildAnnotatedString {
                val text = "@Social_media"
                append(text)

                val start = text.indexOf("@Social_media")
                val end = start + "@Social_media".length

                addStyle(
                    SpanStyle(
                        fontSize = 17.sp,
                        color = Color.White,
                        textDecoration = TextDecoration.Underline
                    ),
                    start,
                    end
                )
                addStringAnnotation(
                    "url",
                    "https://bento.me/meebar-ranjan",
                    start,
                    end
                )
            }
            val uriHandler = LocalUriHandler.current
            ClickableText(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                text = annotatedString, onClick = { offset ->
                    val uri = annotatedString
                        .getStringAnnotations("url", offset, offset).firstOrNull()?.item
                    if (uri != null) {
                        uriHandler.openUri(uri)
                    }
                })
        }
        Row {
            Icon(
                modifier = Modifier
                    .padding(3.dp)
                    .size(23.dp),
                painter = painterResource(R.drawable.icon_mail),
                tint = Color.White,
                contentDescription = null
            )
            Text(                text = "meebarranjan@gmail.com",
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true,
//    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        MainLayout()
    }
}