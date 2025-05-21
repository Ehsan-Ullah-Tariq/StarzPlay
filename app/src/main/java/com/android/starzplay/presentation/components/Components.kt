package com.android.starzplay.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle


@Composable
fun CommonTopBar(modifier: Modifier = Modifier,showBackBtn: Boolean,onBackClick:()-> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        if (showBackBtn){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.clickable{
                    onBackClick()
                }
            )
        }

        Spacer(modifier.width(15.dp))

        CommonText(value = "Starz Play", textSize = 16.sp)

    }

}


fun String.encodeUrl(): String = java.net.URLEncoder.encode(this, "UTF-8")
fun String.decodeUrl(): String = java.net.URLDecoder.decode(this, "UTF-8")

inline fun <reified T> SavedStateHandle.update(key: String, update: (T) -> T) {
    val current = this[key] ?: return
    this[key] = update(current)
}


val Toolbar_Size = 40.dp

@Composable
fun CommonText(
    value: String?,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 18.sp,
    textStyle: TextStyle = TextStyle(),
    fontWeight: FontWeight = FontWeight.Normal,
    textColor: Color = Color.Black,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = 1,
    textAlign: TextAlign = TextAlign.Start,
    minLines: Int = 1,
    fontFamily: FontFamily? = null
) {
    value?.let {
        Text(
            text = value,
            color = textColor,
            fontSize = textSize,
            fontWeight = fontWeight,
            modifier = modifier,
            style = textStyle,
            maxLines = maxLines,
            overflow = overflow,
            textAlign = textAlign,
            minLines = minLines,
            fontFamily = fontFamily
        )
    }
}
