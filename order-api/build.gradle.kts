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

    implementation("org.springdoc:springdoc-openapi-ui:1.5.9")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.5.9")
}
