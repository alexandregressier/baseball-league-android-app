plugins {
    kotlin("jvm")
    `java-library`
}

group = "dev.gressier.abl"
version = "0.1.0-SNAPSHOT"

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib"))

    implementation(Square.retrofit2)
    implementation(Square.retrofit2.converter.moshi)
}