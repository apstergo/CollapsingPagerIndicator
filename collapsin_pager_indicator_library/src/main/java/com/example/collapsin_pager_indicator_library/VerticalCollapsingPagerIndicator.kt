package com.example.collapsin_pager_indicator_library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.ceil
import kotlin.math.max

@Deprecated("Not completed")
@Composable
fun VerticalCollapsingPagerIndicator(
    modifier: Modifier = Modifier,
    currentItem: Int,
    indicatorCount: Int = 5,
    itemCount: Int,
    indicatorSize: Dp = 6.dp,
    indicatorShape: Shape = CircleShape,
    padding: Dp = 8.dp,
    activeColor: Color = Color(0xffEC407A),
    inActiveColor: Color = Color.LightGray,
    ) {

    VerticalCollapsingPagerIndicator(
        modifier = modifier,
        currentItem = currentItem,
        indicatorCount = indicatorCount,
        itemCount = itemCount,
        indicatorHeight = indicatorSize,
        indicatorWidth = indicatorSize,
        indicatorShape = indicatorShape,
        padding = padding,
        activeColor = activeColor,
        inActiveColor = inActiveColor,
    )
}

@Deprecated("Not completed")
@Composable
fun VerticalCollapsingPagerIndicator(
    modifier: Modifier = Modifier,
    currentItem: Int,
    indicatorCount: Int = 5,
    itemCount: Int,
    indicatorHeight: Dp = 6.dp,
    indicatorWidth: Dp = 6.dp,
    indicatorShape: Shape = CircleShape,
    padding: Dp = 8.dp,
    activeColor: Color = Color(0xffEC407A),
    inActiveColor: Color = Color.LightGray,
) {
    val listState = rememberLazyListState()

    val widthInPx = LocalDensity.current.run { indicatorHeight.toPx() + indicatorWidth.toPx() }

    val height =
        indicatorCount * (indicatorHeight.value + padding.value) + ((indicatorHeight.value + padding.value) * 2) + padding.value

    LaunchedEffect(key1 = currentItem) {
        val viewportSize = listState.layoutInfo.viewportSize
        listState.animateScrollToItem(
            currentItem,
            (widthInPx / 2 - viewportSize.width / 2).toInt()
        )
    }

    LazyColumn(
        modifier = modifier.height(height.dp),
        state = listState,
        userScrollEnabled = false,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(itemCount) { index ->

            val isSelected = (index == currentItem)
            val centerItemIndex = ceil(indicatorCount / 2.0).toInt() - 1

            val itemsBefore = max(centerItemIndex - currentItem, 0)
            val itemsAfter = max(currentItem - itemCount + 1 + centerItemIndex, 0)

            val leftMiddleItem = if (currentItem > centerItemIndex) {
                currentItem - centerItemIndex - itemsAfter
            } else {
                -1
            }
            val rightMiddleItem = if (currentItem < itemCount - centerItemIndex - 1) {
                currentItem + centerItemIndex + itemsBefore
            } else {
                itemCount + 1
            }

            val isDefaultItem = index in (leftMiddleItem + 1)..<rightMiddleItem
            val isMiddleItem = index == leftMiddleItem || index == rightMiddleItem

            Box(
                modifier = Modifier
                    .padding(vertical = padding)
                    .graphicsLayer {
                        val scale = if (isDefaultItem) {
                            1f
                        } else if (isMiddleItem) {
                            0.43f
                        } else {
                            0.17f
                        }
                        scaleX = scale
                        scaleY = scale
                    }
                    .clip(indicatorShape)
                    .height(indicatorHeight)
                    .width(indicatorWidth)
                    .background(
                        if (isSelected) activeColor else inActiveColor,
                        indicatorShape
                    )
            )
        }
    }
}