package com.demo.quickmviappframe.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.demo.quickmviappframe.R
import com.demo.quickmviappframe.ui.widget.HorizontalSpace

@Composable
fun LoginYSZCDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            colors = CardColors(
                containerColor = Color.White,
                contentColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.White
            ),
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text("同意用户政策和隐私协议方可继续", fontSize = 15.sp, color = Color.Black)

                Text(
                    "您需要同意”用户协议”和”隐私政策”,我们才能继续为您提供服务。",
                    fontSize = 13.sp,
                    color = colorResource(R.color.gray_C6C5CC),
                    modifier = Modifier.padding(top = 12.dp)
                )

                HorizontalDivider(modifier = Modifier.padding(top = 20.dp), thickness = 0.5.dp, color = colorResource(R.color.gray_C6C5CC))

                Row(modifier = Modifier.padding(top = 15.dp)) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonColors(
                            containerColor = Color.White,
                            contentColor = colorResource(R.color.blue_51A0FF),
                            disabledContentColor = Color.White,
                            disabledContainerColor = Color.Gray
                        ),
                        border = BorderStroke(1.dp, colorResource(R.color.blue_51A0FF)),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .height(34.dp)
                            .weight(1f)
                    ) {
                        Text("不同意", fontSize = 13.sp)
                    }

                    HorizontalSpace(16.dp)

                    Button(
                        onClick = onConfirm, colors = ButtonColors(
                            containerColor = colorResource(R.color.blue_51A0FF),
                            contentColor = Color.White,
                            disabledContentColor = Color.White,
                            disabledContainerColor = Color.Gray
                        ),
                        border = BorderStroke(1.dp, colorResource(R.color.blue_51A0FF)),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .height(34.dp)
                            .weight(1f)
                    ) {
                        Text("同意并登录", fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun prev() {
    LoginYSZCDialog({}, {})
}