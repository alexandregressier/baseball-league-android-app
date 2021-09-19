package dev.gressier.abl.api.adapters.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import dev.gressier.abl.api.formatters.dateTimeFormat
import dev.gressier.abl.api.typealiases.DateTime

class MoshiDateTimeAdapter {

    @FromJson fun fromJson(dateTimeString: String?): DateTime? =
        dateTimeString?.let { DateTime.parse(it, dateTimeFormat)}

    @ToJson fun toJson(dateTime: DateTime?): String? =
        dateTime?.let(dateTimeFormat::format)
}