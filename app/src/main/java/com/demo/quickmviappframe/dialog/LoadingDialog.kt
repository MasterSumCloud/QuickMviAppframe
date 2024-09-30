package com.demo.quickmviappframe.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.demo.quickmviappframe.R

class LoadingDialog(context: Context) : Dialog(context, R.style.DialogTipTheme), DefaultLifecycleObserver {
    override fun onCreate(savedInstanceState: Bundle?) {
        super<Dialog>.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        window?.let {
            it.setGravity(Gravity.CENTER)
            it.setDimAmount(0f)
        }
        setCancelable(true)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismiss()
    }
}