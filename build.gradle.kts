plugins {
    kotlin("jvm") version "2.2.20"
    id("application")
    id("java")
}

application {
    mainClass.set("org.example.BankingAppMainKt")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17)) // Recommended: Use 17 or 21
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.mockito:mockito-core:5.12.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation(kotlin("test-junit5"))
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}


tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}