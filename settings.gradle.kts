rootProject.name = "wordcounter"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include("service", "client", "domain")
project(":service").name = "word-counter"
project(":client").name = "word-counter-client"
project(":domain").name = "word-counter-domain"
