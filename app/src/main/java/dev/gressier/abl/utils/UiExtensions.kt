package dev.gressier.abl.utils

import android.app.Application
import dev.gressier.abl.R
import dev.gressier.abl.data.BaseballRepository
import dev.gressier.abl.data.BaseballRepository.ResultStatus.*

fun BaseballRepository.ResultStatus.orGetErrorMessage(application: Application): String? =
    when (this) {
        NETWORK_EXCEPTION -> application.resources.getString(R.string.network_exception_message)
        REQUEST_EXCEPTION -> application.resources.getString(R.string.request_exception_message)
        GENERAL_EXCEPTION -> application.resources.getString(R.string.general_exception_message)
        else -> null
    }