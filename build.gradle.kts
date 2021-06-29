import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.0" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    kotlin("jvm") version "1.5.0" apply false
    kotlin("plugin.spring") version "1.5.0" apply false
    kotlin("plugin.jpa") version "1.5.0" apply false
    kotlin("kapt") version "1.5.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "10.1.0" apply false
}
allprojects {
    group = "com.helloworld"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {
    repositories {
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx/")
    }

    apply {
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("java")
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8")
            mavenBom("org.testcontainers:testcontainers-bom:1.15.3")
        }
    }

    dependencies {
        "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin")
        "implementation"("org.jetbrains.kotlin:kotlin-reflect")
        "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        "implementation"("org.springframework.boot:spring-boot-starter")
        "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        disabledRules.add("no-wildcard-imports")
    }

}

var kotestProjects = listOf(
    project("domain-redis"),
    project("domain-rds"),
    project("domain"),
    project("order-api")
)
var testcontainerProjects = listOf(
    project("domain-redis"),
    project("domain-rds"),
    project("domain"),
    project("order-api")
)

var testfixtureProjects = listOf(
    project("domain-redis"),
    project("domain-rds")
)

object kotest {
    val version = "4.4.3"

    object extension {
        val version = "1.0.0"
    }
}

configure(kotestProjects) {
    dependencies {
        "testImplementation"("io.kotest:kotest-runner-junit5:${kotest.version}") // for kotest framework
        "testImplementation"("io.kotest:kotest-assertions-core:${kotest.version}") // for kotest core jvm assertions
        "testImplementation"("io.kotest:kotest-property:${kotest.version}") // for kotest property test
        "testImplementation"("io.kotest.extensions:kotest-extensions-spring:${kotest.extension.version}")
        "testImplementation"("io.kotest.extensions:kotest-extensions-testcontainers:${kotest.extension.version}")

        "testImplementation"("io.mockk:mockk")
        "testImplementation"("com.ninja-squad:springmockk:3.0.1")
    }
}

configure(testcontainerProjects) {
    dependencies {
        "testImplementation"("org.testcontainers:junit-jupiter")
        "testImplementation"("org.testcontainers:mysql")
        "testImplementation"("org.testcontainers:mariadb")
    }
}

configure(testfixtureProjects) {
    apply {
        plugin("java-test-fixtures")
    }
    dependencies {
        "testFixturesImplementation"("io.kotest:kotest-runner-junit5:${kotest.version}") // for kotest framework
        "testFixturesImplementation"("io.kotest:kotest-assertions-core:${kotest.version}") // for kotest core jvm assertions
        "testFixturesImplementation"("io.kotest:kotest-property:${kotest.version}") // for kotest property test
        "testFixturesImplementation"("io.kotest.extensions:kotest-extensions-spring:${kotest.extension.version}")
        "testFixturesImplementation"("io.kotest.extensions:kotest-extensions-testcontainers:${kotest.extension.version}")

        "testFixturesImplementation"("org.testcontainers:junit-jupiter")
        "testFixturesImplementation"("org.testcontainers:mysql")
        "testFixturesImplementation"("org.testcontainers:mariadb")
    }
}