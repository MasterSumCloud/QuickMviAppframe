package com.demo.quickmviappframe.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.launch

@Composable
fun MainPage() {
    val scope = rememberCoroutineScope()
    val titles = listOf(
        TabItem("首页", Icons.Filled.Home),
        TabItem("会员", Icons.Filled.Star),
        TabItem("我的", Icons.Filled.Person)
    )

    val pagerState = rememberPagerState(pageCount = {
        titles.size
    })

    Column {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            when (page) {
                0 -> HomePage()
                1 -> VipPage()
                2 -> MyPage()
            }
        }
        TabRow(selectedTabIndex = pagerState.currentPage) {
            titles.forEachIndexed { index, tab ->
                Tab(
                    icon = { Icon(imageVector = tab.icon, contentDescription = tab.title) },
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = tab.title) },
                    selectedContentColor = Color.Blue,
                    unselectedContentColor = Color.LightGray
                )
            }
        }
    }
}

data class TabItem(val title: String, val icon: ImageVector)