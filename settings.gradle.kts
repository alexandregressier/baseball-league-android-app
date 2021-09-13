pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.android.application") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
            if (requested.id.id == "androidx.navigation.safeargs.kotlin") {
                useModule("androidx.navigation:navigation-safe-args-gradle-plugin:${requested.version}")
            }
        }
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.21.0"
}

refreshVersions {
    rejectVersionIf {
        candidate.stabilityLevel.isLessStableThan(current.stabilityLevel)
    }
}

rootProject.name = "Android Baseball League"
include(
    ":app",
)