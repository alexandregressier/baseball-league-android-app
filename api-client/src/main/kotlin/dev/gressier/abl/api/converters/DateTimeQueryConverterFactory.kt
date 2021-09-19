package dev.gressier.abl.api.converters

import dev.gressier.abl.api.formatters.dateFormat
import dev.gressier.abl.api.formatters.dateTimeFormat
import dev.gressier.abl.api.typealiases.Date
import dev.gressier.abl.api.typealiases.DateTime
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class DateTimeQueryConverterFactory : Converter.Factory() {

    override fun stringConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? =
        when (type) {
            Date::class.java ->
                Converter<Date, String>(dateFormat::format)
            DateTime::class.java ->
                Converter<DateTime, String>(dateTimeFormat::format)
            else ->
                super.stringConverter(type, annotations, retrofit)
        }
}