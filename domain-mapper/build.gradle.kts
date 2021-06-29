plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("kapt")
    application
}

java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation(project(":domain-redis"))
    implementation(project(":domain-rds"))

    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")
    implementation("org.mapstruct.extensions.spring:mapstruct-spring-annotations:0.0.3")
    implementation("org.mapstruct.extensions.spring:mapstruct-spring-extensions:0.0.3")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true
