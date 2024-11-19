package com.demo.quickmviappframe.dialog

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.demo.quickmviappframe.R
import com.demo.quickmviappframe.base.BaseDialog

class TextDialog(context: Context) : BaseDialog(context) {
    private var showContent: CharSequence? = null
    var okBtnText: String = "确定"
    var cancelBtnText: String = "取消"
    private var keyListener: onTextDialogBtnListener? = null
    private var tvContent: TextView? = null
    private var tvTitle: TextView? = null
    private var okBtn: TextView? = null
    private var cancle: TextView? = null
    private var viewBg: View? = null


    override fun getLayoutId(): Int {
        return R.layout.dialog_text
    }

    override fun initStyle() {
        window?.let {
            it.setGravity(Gravity.CENTER)
            it.attributes.width = ScreenUtils.getScreenWidth() - SizeUtils.dp2px(40f)
        }
    }

    override fun initView() {
        okBtn = findViewById<TextView>(R.id.tv_confirm)
        cancle = findViewById<TextView>(R.id.tv_cancel)
        tvTitle = findViewById<TextView>(R.id.tv_title)
        tvContent = findViewById<TextView>(R.id.tv_content)
        viewBg = findViewById<View>(R.id.view_bg)

        tvContent?.text = showContent
        okBtn?.text = okBtnText
        cancle?.text = cancelBtnText

        okBtn?.setOnClickListener {
            keyListener?.onClickOk()
            dismiss()
        }


        cancle?.setOnClickListener {
            keyListener?.onClickCancel()
            dismiss()
        }
    }


    interface onTextDialogBtnListener {
        fun onClickOk()
        fun onClickCancel()
    }

    fun setOnKeyListener(keyListener: onTextDialogBtnListener) {
        this.keyListener = keyListener
    }

    fun showTextDialog(content: CharSequence, onKeyListener: onTextDialogBtnListener) {
        this.showContent = content
        this.keyListener = onKeyListener
        show()
    }

    fun showTextDialog(
        content: CharSequence,
        okBtnText: String,
        cancelBtnText: String,
        onKeyListener: onTextDialogBtnListener?
    ) {
        this.showContent = content
        this.okBtnText = okBtnText
        this.cancelBtnText = cancelBtnText
        if (onKeyListener != null) {
            this.keyListener = onKeyListener
        }
        show()
    }
}