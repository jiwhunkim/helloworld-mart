plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation(project(":domain-redis"))
    testImplementation(project(":domain-redis", "testArtifacts"))
    implementation(project(":domain-rds"))
    testImplementation(project(":domain-rds", "testArtifacts"))
    implementation(project(":domain"))
    implementation(project(":domain-mapper"))
}
