package dev.gressier.abl.api.services

import com.squareup.moshi.Moshi
import dev.gressier.abl.api.adapters.moshi.MoshiDateAdapter
import dev.gressier.abl.api.adapters.moshi.MoshiDateTimeAdapter
import dev.gressier.abl.api.converters.DateTimeQueryConverterFactory
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun getDefaultAblService(baseUrl: String = "https://abl.mfazio.dev/api/"): AndroidBaseballLeagueService =
    Retrofit.Builder()
        .apply {
            listOf(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .apply {
                            listOf(MoshiDateAdapter(), MoshiDateTimeAdapter())
                                .forEach(this::add)
                        }.build()
                ),
                DateTimeQueryConverterFactory(),
            )
                .forEach<Converter.Factory>(this::addConverterFactory)
        }
        .baseUrl(baseUrl)
        .build()
        .create(AndroidBaseballLeagueService::class.java)