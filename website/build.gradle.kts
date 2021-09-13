plugins {
    kotlin("js")
}

group = "dev.gressier.abl"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    implementation(enforcedPlatform(kotlinw("wrappers-bom:_")))

    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))

    implementation(kotlinw("styled"))
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
        binaries.executable()
    }
}