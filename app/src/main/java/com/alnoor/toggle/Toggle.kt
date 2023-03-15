package com.alnoor.toggle

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun Toggle(
    modifier: Modifier = Modifier,
    state: ToggleState,
    size: Dp = 50.dp,
    offStateColor: Color = MaterialTheme.colorScheme.errorContainer,
    onStateColor: Color = MaterialTheme.colorScheme.primaryContainer,
    borderWidth: Dp = 3.dp,
    thumbOnColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    thumbOffColor: Color = MaterialTheme.colorScheme.onErrorContainer,
    onChange: (ToggleState)-> Unit,
    animSpec: AnimationSpec<Float> = tween(durationMillis = 1000)
){

    val animValue = animateFloatAsState(
        targetValue = if (state is ToggleState.Left) 0f else 0.66f,
        animationSpec = animSpec
    )

    val animthumbColor = animateColorAsState(
        targetValue =  if (state is ToggleState.Left) thumbOffColor else thumbOnColor,
        animationSpec = tween(durationMillis = 1000)
    )

    val animBcColor = animateColorAsState(
        targetValue =  if (state is ToggleState.Left) offStateColor else onStateColor,
        animationSpec = tween(durationMillis = 1000)
    )

    ConstraintLayout(
        modifier = modifier
            .border(
                color = animBcColor.value.copy(alpha = 0.7f),
                width = borderWidth,
                shape = RoundedCornerShape(50)
            )
            .padding(6.dp)
            .size(
                width = size * 3, height = size
            )
            .clip(RoundedCornerShape(50))
            .background(animBcColor.value)
            .clickable {
                if (state is ToggleState.Left) onChange(ToggleState.Right)
                else onChange(ToggleState.Left)
            }
    ){

        val startGuideLine = createGuidelineFromStart(animValue.value)
        val thumb = createRef()

        Box(
          modifier = Modifier
              .size(size)
              .clip(RoundedCornerShape(50))
              .background(animthumbColor.value)
              .constrainAs(thumb) {
                  start.linkTo(startGuideLine)
              }
        ){}

    }


}

sealed class ToggleState {
    object Left: ToggleState()
    object Right: ToggleState()
}

@Preview
@Composable
fun PreviewSwitch(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center){

        val state = remember { mutableStateOf<ToggleState>(ToggleState.Right) }

        Toggle(
            modifier = Modifier,
            state = state.value,
            size = 50.dp,
            onChange = {
                state.value = it
            }
        )

    }
}