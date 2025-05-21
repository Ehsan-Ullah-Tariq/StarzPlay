package com.android.starzplay.presentation.ui.screens.main


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.starzplay.R
import com.android.starzplay.presentation.components.CommonText
import com.android.starzplay.presentation.components.Toolbar_Size

@Composable
fun MainScreenTopBar(onLanguageClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Toolbar_Size), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CommonText(stringResource(R.string.starz_play), textSize = 18.sp)
        Card(
            modifier = Modifier
                .clickable { onLanguageClick() }
                .background(Color.White)
                .border(
                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(10.dp)
                ), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommonText(value = stringResource(R.string.change_language), textSize = 18.sp)
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = stringResource(
                        R.string.dropdown
                    )
                )
            }
        }
    }
}