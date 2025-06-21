plugins {
    application
    alias(libs.plugins.kotlin)
    alias(libs.plugins.versions)
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

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
