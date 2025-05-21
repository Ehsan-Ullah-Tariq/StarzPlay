package com.android.starzplay.presentation.ui.screens.details


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.starzplay.R
import com.android.starzplay.domain.models.MovieDomainDetails
import com.android.starzplay.presentation.components.CommonText
import com.android.starzplay.presentation.components.encodeUrl
import com.android.starzplay.utils.VIDEO_URL

@Composable
fun DetailsScreenContent(
    modifier: Modifier = Modifier,
    item: MovieDomainDetails,
    onPlayClick: (String) -> Unit
) {

    Column(modifier = modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(15.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .border(
                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(10.dp)
                ),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CommonText(value = stringResource(R.string.image), textSize = 28.sp)
            }
        }

        CommonText(value = "${item.title}", textSize = 18.sp)

        CommonText(value = "${item.overview}", textSize = 18.sp, maxLines = Int.MAX_VALUE)

        Button(
            onClick = {
                onPlayClick(VIDEO_URL.encodeUrl())
            },
        ) {
            CommonText(
                value = stringResource(R.string.play_video),
                textSize = 14.sp,
                textColor = Color.White
            )
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play")
        }
    }
}