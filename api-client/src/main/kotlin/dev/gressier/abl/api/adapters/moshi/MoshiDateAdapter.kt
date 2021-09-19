package dev.gressier.abl.api.adapters.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import dev.gressier.abl.api.formatters.dateFormat
import dev.gressier.abl.api.typealiases.Date

class MoshiDateAdapter {

    @FromJson fun fromJson(dateString: String?): Date? =
        dateString?.let { Date.parse(it, dateFormat)}

    @ToJson fun toJson(date: Date?): String? =
        date?.let(dateFormat::format)
}