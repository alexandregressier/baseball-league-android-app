plugins {
    id("com.android.application") version "4.2.2"
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin") version "2.3.5"
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "31.0.0"

    defaultConfig {
        applicationId = "dev.gressier.abl"
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = sourceCompatibility
    }
    kotlinOptions {
        jvmTarget = "${compileOptions.sourceCompatibility}"
    }
}

dependencies {
    // Kotlin
    implementation(Kotlin.stdlib)

    // General
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(Google.android.material)
    implementation(AndroidX.constraintLayout)

    // Navigation
    implementation(AndroidX.navigation.fragmentKtx)
    implementation(AndroidX.navigation.uiKtx)

    // RecyclerView
    implementation(AndroidX.recyclerView)

    // Local Testing
    testImplementation(Testing.junit4)

    // Instrumented Testing
    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.test.espresso.core)
}