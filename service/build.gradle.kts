import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("org.flywaydb.flyway") version "8.5.13"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    api("jakarta.validation", "jakarta.validation-api")

    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.flywaydb:flyway-core")
    implementation("io.github.microutils", "kotlin-logging", "2.1.23")
    implementation(project(":word-counter-domain"))
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin")

    runtimeOnly("com.h2database", "h2")

    testImplementation(project(":word-counter-client"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("io.github.openfeign", "feign-core", "10.7.0")
    testImplementation("io.github.openfeign", "feign-jackson", "10.7.0")
    testImplementation("io.github.openfeign", "feign-httpclient", "10.7.0")
    testImplementation("org.mockito.kotlin", "mockito-kotlin", "4.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

flyway {
    url = "jdbc:h2:file:./data/db"
    user = "sa"
    password = "password"
    schemas = arrayOf("WORD_COUNTER")
}