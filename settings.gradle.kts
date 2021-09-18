pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
    resolutionStrategy {
        eachPlugin {
            with (requested) {
                when (id.id) {
                    "com.android.application" ->
                        useModule("com.android.tools.build:gradle:$version")
                    "androidx.navigation.safeargs.kotlin" ->
                        useModule("androidx.navigation:navigation-safe-args-gradle-plugin:$version")
                }
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

rootProject.name = "android-baseball-league"
include(
    "app",
    "api-client",
    "website",
)