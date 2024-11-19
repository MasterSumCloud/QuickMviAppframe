package com.demo.quickmviappframe.ui.vm

import com.demo.quickmviappframe.base.BaseViewModel
import com.demo.quickmviappframe.base.IUiIntent
import com.demo.quickmviappframe.base.IUiState
import com.demo.quickmviappframe.ext.request
import com.demo.quickmviappframe.ext.toastLong
import com.demo.quickmviappframe.net.apiService
import com.demo.quickmviappframe.util.GeneralUtil
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ReportViewModel : BaseViewModel<ReportState, ReportIntent, Any>() {

    override fun initialState(): ReportState {
        return ReportState.Initial
    }

    fun setReportContent(content: String) {
        uiState.value.content = content
    }

    fun setReportContact(contact: String) {
        uiState.value.contact = contact
    }

    fun setReportPhotos(photos: List<String>) {
        uiState.value.photos = photos
    }

    fun postReport() {
        "提交的内容：${uiState.value.content}，联系方式：${uiState.value.contact}，图片：${uiState.value.photos}".toastLong()
        return
        val imageFiles = mutableListOf<MultipartBody.Part>()
        uiState.value.photos.forEach {
            if (it != "add") {
                val file = File(it)
                val fileType = "image/" + GeneralUtil.getFileType(it)
                val asRequestBody = file.asRequestBody(fileType.toMediaType())
                val part: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "file[]", GeneralUtil.getFileName(it), asRequestBody
                )
                imageFiles.add(part)
            }
        }
        request({ apiService.report(uiState.value.content, uiState.value.contact, imageFiles) }, {}, {})
    }

}

sealed class ReportState(var content: String, var contact: String, var photos: List<String>) : IUiState {
    data object Initial : ReportState("", "", mutableListOf("default"))
}

sealed class ReportIntent : IUiIntent {
    data object RequestPermission : ReportIntent()
}

