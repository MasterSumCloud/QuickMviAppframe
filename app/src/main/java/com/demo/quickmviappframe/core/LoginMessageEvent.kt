package com.demo.quickmviappframe.core

import com.demo.quickmviappframe.base.BaseMessageEvent


class LoginMessageEvent(tag: String?, message: String?, type: Int) : BaseMessageEvent(tag,message) {
    var type: Int? = type
}