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
https://user-images.githubusercontent.com/120308888/225307507-2921a6db-27da-4b98-80ef-279273ec3f41.mp4

