package com.demo.quickmviappframe.ui.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.demo.quickmviappframe.base.BaseViewModel

class ReportViewModel : BaseViewModel() {

    var state by mutableStateOf(ReportState())
        private set

    fun setReportContent(content: String) {
        state = state.copy(reportContent = content)
    }

    fun setReportContact(contact: String) {
        state = state.copy(reportContact = contact)
    }

    fun setReportPhotos(photos: List<String>) {
        state = state.copy(reportPhotos = photos)
    }

}

data class ReportState(
    val reportContent: String = "",
    val reportContact: String = "",
    val reportPhotos: List<String> = emptyList()
)