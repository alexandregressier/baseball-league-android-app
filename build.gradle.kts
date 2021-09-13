allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.wrapper {
    gradleVersion = "${project.extra["version.gradle"]}"
}