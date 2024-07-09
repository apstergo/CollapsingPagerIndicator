@file:OptIn(ExperimentalFoundationApi::class)

package com.example.collapsingpagerindicator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.collapsin_pager_indicator_library.HorizontalCollapsingPagerIndicator
import com.example.collapsin_pager_indicator_library.VerticalCollapsingPagerIndicator
import com.example.collapsingpagerindicator.ui.theme.CollapsingPagerIndicatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CollapsingPagerIndicatorTheme {
                PagerExample()
            }
        }
    }
}

@Composable
fun PagerExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        val images = listOf(
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
            R.drawable.ic_android_first,
        )

        val pagerState = rememberPagerState {
            images.size
        }

        HorizontalPager(modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(300.dp), state = pagerState) { page ->
            Image(modifier = Modifier.fillMaxSize(), painter = painterResource(id = images[page]), contentDescription = null)
        }

        HorizontalCollapsingPagerIndicator(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .height(20.dp),
            currentItem = pagerState.currentPage,
            indicatorCount = 7,
            padding = 8.dp,
            itemCount = images.size,
            indicatorSize = 16.dp,
        )

        val pagerStateSecond = rememberPagerState {
            images.size
        }

        Spacer(modifier = Modifier.height(50.dp))

        HorizontalPager(modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(300.dp), state = pagerStateSecond) { page ->
            Image(modifier = Modifier.fillMaxSize(), painter = painterResource(id = images[page]), contentDescription = null)
        }

        HorizontalCollapsingPagerIndicator(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .padding(horizontal = 32.dp)
                .height(20.dp),
            currentItem = pagerStateSecond.currentPage,
            indicatorCount = 7,
            padding = 8.dp,
            itemCount = images.size,
            indicatorHeight = 12.dp,
            indicatorWidth = 32.dp,
            indicatorShape = RoundedCornerShape(0.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CollapsingPagerIndicatorTheme {
        PagerExample()
    }
}