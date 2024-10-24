package com.demo.quickmviappframe.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.demo.quickmviappframe.R
import kotlinx.coroutines.launch

@Composable
fun MainPage(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val titles = listOf(
        TabItem("首页", Icons.Filled.Home),
        TabItem("会员", Icons.Filled.Star),
        TabItem("我的", Icons.Filled.Person)
    )

    val pagerState = rememberPagerState(pageCount = {
        titles.size
    })

    Column(modifier = Modifier.background(color = Color.White)) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            when (page) {
                0 -> HomePage(navController)
                1 -> VipPage(navController)
                2 -> MyPage(navController)
            }
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 0.5.dp, color = colorResource(R.color.gray_C4C8D1))
        TabRow(selectedTabIndex = pagerState.currentPage, containerColor = Color.White) {
            titles.forEachIndexed { index, tab ->
                Tab(
                    icon = { Icon(imageVector = tab.icon, contentDescription = tab.title, modifier = Modifier.size(19.dp)) },
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = tab.title, fontSize = 12.sp) },
                    selectedContentColor = colorResource(R.color.blue_51A0FF),
                    unselectedContentColor = colorResource(R.color.gray_C4C8D1)
                )
            }
        }
    }
}

data class TabItem(val title: String, val icon: ImageVector)