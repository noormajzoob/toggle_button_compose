# Toggle Button

Simple animated toggle button using jetpack compose.


>Example
>-
> val state = remember { mutableStateOf<ToggleState>(ToggleState.Right) }
>
>                    Toggle(
>                      modifier = Modifier,
>                      state = state.value,
>                      size = 50.dp,
>                      onChange = {
>                            state.value = it
>                       }
>                   )

## Screen Record
![](toggle_screen_rec.mp4)
