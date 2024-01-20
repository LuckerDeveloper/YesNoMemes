package com.luckerdeveloper.yes_no_request

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import coil.size.SizeResolver

@Composable
fun GifImage(
    link: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context).data(
                data = link
            ).scale(Scale.FILL)
                .build(),
            imageLoader = imageLoader,
        ),
        contentScale = ContentScale.FillWidth,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Preview
@Composable
fun PreviewGifImage() {
    GifImage(link = "https://media.giphy.com/media/OgjQ3oHnlbAzdYmA9L/giphy.gif")
}