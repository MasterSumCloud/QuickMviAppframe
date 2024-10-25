package com.demo.quickmviappframe.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.CacheDiskStaticUtils
import com.demo.quickmviappframe.R
import com.demo.quickmviappframe.ext.toastShort
import com.demo.quickmviappframe.ui.act.AboutAct
import com.demo.quickmviappframe.ui.vm.SettingItem
import com.demo.quickmviappframe.ui.vm.SettingViewModel
import com.demo.quickmviappframe.ui.widget.CustomSwitch
import com.demo.quickmviappframe.util.MMKVUtil

@Composable
fun SettingPage(vm: SettingViewModel?) {

    val setList = remember { vm?.settingList }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.gray_f6))
    ) {
        LazyColumn {
            setList?.forEach { item ->
                item {
                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 0.5.dp, color = colorResource(R.color.gray_f6))
                    settingItem(item, setList)
                }
            }
        }
    }
}


@Composable
fun settingItem(item: SettingItem, setList: SnapshotStateList<SettingItem>) {
    val checkedState = remember {
        mutableStateOf(MMKVUtil.AppConfig.personalSuggest)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.White)
            .clickable {
                itemClick(item.leftTx, setList)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.leftTx, fontSize = 14.sp, modifier = Modifier.padding(start = 14.dp), color = colorResource(R.color.black_2C2C34))

        Box(modifier = Modifier.weight(1f))

        Text(
            text = item.rightTx,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 14.dp, end = 10.dp),
            color = colorResource(R.color.gray_98A0B0)
        )

        if (item.showArr) {
            Image(
                painter = painterResource(id = R.mipmap.arr_gray_row),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(12.dp),
            )
        } else if (item.showCheckBox) {
            CustomSwitch(
                60.dp, 28.dp, checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                    MMKVUtil.AppConfig.personalSuggest = it
                },
                Color(0xFF8566FF), Color(0xFFF8F8F8), Color.White,
                modifier = Modifier
                    .padding(end = 12.dp),
            )
        }
    }
}

private fun itemClick(funName: String, setList: SnapshotStateList<SettingItem>) {
    when (funName) {
        "清理缓存" -> {
            CacheDiskStaticUtils.clear()
            setList[0] = setList[0].copy(rightTx = "0K")
        }

        "检查更新" -> {
            "已经是最新版本".toastShort()
        }

        "关于我们" -> {
            ActivityUtils.startActivity(AboutAct::class.java)
        }
    }
}

@Preview
@Composable
private fun pv() {
    SettingPage(SettingViewModel())
}