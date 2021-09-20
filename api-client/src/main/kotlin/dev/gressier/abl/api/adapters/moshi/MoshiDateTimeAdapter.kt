package dev.gressier.abl.api.adapters.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import dev.gressier.abl.api.formatters.dateTimeHourFormat
import dev.gressier.abl.api.typealiases.DateTime

class MoshiDateTimeAdapter {

    @FromJson fun fromJson(dateTimeString: String?): DateTime? =
        dateTimeString?.let { DateTime.parse(it, dateTimeHourFormat)}

    @ToJson fun toJson(dateTime: DateTime?): String? =
        dateTime?.let(dateTimeHourFormat::format)
}