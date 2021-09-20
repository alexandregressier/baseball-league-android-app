package dev.gressier.abl.api.formatters

import java.time.format.DateTimeFormatter

val dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd")
val dateTimeFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
val dateTimeHourFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHH")