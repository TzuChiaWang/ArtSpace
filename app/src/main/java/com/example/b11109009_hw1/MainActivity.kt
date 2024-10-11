package com.example.b11109009_hw1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.b11109009_hw1.model.ArtSpaceImage
import com.example.b11109009_hw1.ui.theme.B11109009_HW1Theme
import com.example.b11109009_hw1.model.taiwantech
import com.example.b11109009_hw1.model.taiwantechciso
import com.example.b11109009_hw1.model.taiwantechim
import com.example.b11109009_hw1.model.taiwantechmain
import com.example.b11109009_hw1.model.taiwantechstudentdormitory
import com.example.b11109009_hw1.model.taiwantechsunrise
import com.example.b11109009_hw1.model.taiwantechsunset
import com.example.b11109009_hw1.model.taiwantechtigerduck
import com.example.b11109009_hw1.model.taiwantechturtlepool
import com.example.b11109009_hw1.model.taiwantechwater



class MainActivity : ComponentActivity(){

    private val images = arrayOf(taiwantech, taiwantechim, taiwantechtigerduck,taiwantechwater,
        taiwantechsunrise, taiwantechturtlepool, taiwantechstudentdormitory,taiwantechmain,taiwantechsunset ,
        taiwantechciso
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            B11109009_HW1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    B11109009_HW1App(images = images)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun B11109009_HW1App(
    images: Array<ArtSpaceImage> = arrayOf(taiwantech, taiwantechim, taiwantechtigerduck,taiwantechwater,
        taiwantechsunrise, taiwantechturtlepool, taiwantechstudentdormitory,taiwantechmain,taiwantechsunset ,
        taiwantechciso),
) {
    //建立照片列表大小
    val n = images.size
    //初始值為照片第一張
    var i by remember { mutableIntStateOf(0) }
    // 將當前照片賦予image
    val image = images[i]
    //用於將 i 的值改為前一張圖片的索引。如果當前已經是第一張圖片（i == 0），則將 i 的值設為最後一張圖片的索引（n - 1）；否則，將 i 的值設為前一個索引（(i - 1) % n）
    val previous: () -> Unit = { i = if (i == 0) (n - 1) else (i - 1) % n }
    //用於將 i 的值改為下一張圖片的索引。由於使用了模數運算符 %，所以當 i 的值到達 n 時，它會回到 0，實現了循環瀏覽的效果。
    val next: () -> Unit = { i = (i + 1) % n }
    Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
    ) {
        ArtSpaceImage(
            id = image.id,
            description = "${image.title}, ${image.year}",
            modifier = Modifier.weight(0.7f)
        )
        ArtSpaceImageDescription(
            title = image.title,
            locateName = image.locate,
            year = image.year,
            modifier = Modifier.weight(0.2f)
        )
        ArtSpaceControls(
            onPrevious = previous,
            onNext = next,
            modifier = Modifier.weight(0.1f)
        )
    }
}

@Composable
fun ArtSpaceImage(
    @DrawableRes id: Int,
    description: String,
    modifier: Modifier = Modifier
    ) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 20.dp, end = 20.dp)
    ) {
        Image(
            painter = painterResource(id),
            contentDescription = description,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth()
                .weight(0.9f)
                .border(width = 3.dp, color = Color.LightGray)
                .shadow(elevation = 10.dp)
                .background(Color.White)
        )
    }
}

@Composable
fun ArtSpaceImageDescription(
    title: String,
    locateName: String = "Artwork",
    year: String = "Year",
    modifier: Modifier = Modifier
    ) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp)
            .background(Color.Cyan)
            .padding(20.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 29.sp,
        )

        Row {
            Text(
                text = "$locateName ($year)",
                fontSize = 23.sp,
             )
        }
    }
}

@Composable
fun ArtSpaceControls(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(20.dp)
    ) {
           Button(
                onClick = onPrevious,

                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = "Previous",
                    fontSize = 25.sp,
                )
            }

        Spacer(modifier = Modifier.width(24.dp))
        Button(
            colors = ButtonDefaults.buttonColors(Color.Red),
            onClick = onNext,
            modifier = Modifier.weight(0.5f)
        ) {
            Text(text = "Next",
                    fontSize = 25.sp,
            )
        }
    }
}

