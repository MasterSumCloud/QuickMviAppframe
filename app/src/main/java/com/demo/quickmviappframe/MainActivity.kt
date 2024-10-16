package com.demo.quickmviappframe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.demo.quickmviappframe.ui.router.MainRouter
import com.demo.quickmviappframe.ui.theme.QuickMVIAppFrameTheme

class MainActivity : ComponentActivity() {
    private var navController: NavHostController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickMVIAppFrameTheme {
                Surface {
                    navController = rememberNavController()
                    MainRouter(navController!!)
                }
            }
        }
    }
}


@Preview
@Composable
private fun preView() {

}