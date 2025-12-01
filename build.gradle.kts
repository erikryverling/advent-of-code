plugins {
    application
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ktlint)
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("se.yverling.advent.CalendarKt")
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.reflect)
}

ktlint {
    ignoreFailures = true
}
