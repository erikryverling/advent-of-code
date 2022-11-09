import org.gradle.kotlin.dsl.*

plugins {
    application
    id("org.jetbrains.kotlin.jvm") version "1.7.21"
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("se.yverling.advent._2020.CalendarKt")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}
