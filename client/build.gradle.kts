group = "com.example.wordcounter"

plugins {
    kotlin("jvm") version "1.6.21"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.github.openfeign", "feign-core", "10.7.0")
    implementation(project(":word-counter-domain"))
}