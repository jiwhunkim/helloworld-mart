import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version Libs.SpringBoot.version apply false
    id("io.spring.dependency-management") version Libs.Spring.DependencyManagement.version apply false
    kotlin("jvm") version Libs.Kotlin.version apply true
    kotlin("plugin.spring") version Libs.Kotlin.version apply false
    kotlin("plugin.jpa") version Libs.Kotlin.version apply false
    kotlin("kapt") version Libs.Kotlin.version apply false
    id("org.jlleitschuh.gradle.ktlint") version Libs.KtLint.version apply false
    id("org.sonarqube") version "3.0" apply true
    jacoco
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx/")
    }

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
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("java")
        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("jacoco")
    }

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8")
            mavenBom("org.testcontainers:testcontainers-bom:1.16.0")
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

    configure<JacocoPluginExtension> {
        toolVersion = "0.8.7"
    }

    tasks.withType<JacocoReport> {
        reports {
            html.isEnabled = true
            xml.isEnabled = true
            csv.isEnabled = false
        }
    }

    tasks.withType<JacocoCoverageVerification> {
        violationRules {
            rule {
                element = "BUNDLE"

                limit {
                    counter = "BRANCH"
                    value = "COVEREDRATIO"
                    minimum = "0.0".toBigDecimal()
                }
            }
        }
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

var intTestProjects = kotestProjects

configure(intTestProjects) {
    sourceSets {
        create("intTest") {
            compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
            runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output

            resources.srcDir(file("src/intTest/resources"))
        }
    }

    val intTestImplementation: Configuration by configurations.getting {
        extendsFrom(configurations.implementation.get(), configurations.testImplementation.get())
    }

    configurations["intTestImplementation"].extendsFrom(configurations.testImplementation.get())
    configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

    val intTest = task<Test>("intTest") {
        description = "Runs integration tests."
        group = "verification"

        testClassesDirs = sourceSets["intTest"].output.classesDirs
        classpath = sourceSets["intTest"].runtimeClasspath
        shouldRunAfter("test")
    }

    tasks.check { dependsOn(intTest) }

    tasks.jacocoTestReport {
        executionData.setFrom(fileTree(buildDir).include("/jacoco/*.exec"))
        shouldRunAfter(tasks.test, tasks.findByName("intTest")) // tests are required to run before generating the report
    }
}

configure(kotestProjects) {
    dependencies {
        "testImplementation"("io.kotest:kotest-runner-junit5:${Libs.Kotest.version}") // for kotest framework
        "testImplementation"("io.kotest:kotest-assertions-core:${Libs.Kotest.version}") // for kotest core jvm assertions
        "testImplementation"("io.kotest:kotest-property:${Libs.Kotest.version}") // for kotest property test
        "testImplementation"("io.kotest.extensions:kotest-extensions-spring:${Libs.Kotest.Extension.version}")
        "testImplementation"("io.kotest.extensions:kotest-extensions-testcontainers:${Libs.Kotest.Extension.version}")

        "testImplementation"("io.mockk:mockk")
        "testImplementation"("com.ninja-squad:springmockk:${Libs.SpringMockk.version}")
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
        "testFixturesImplementation"("io.kotest:kotest-runner-junit5:${Libs.Kotest.version}") // for kotest framework
        "testFixturesImplementation"("io.kotest:kotest-assertions-core:${Libs.Kotest.version}") // for kotest core jvm assertions
        "testFixturesImplementation"("io.kotest:kotest-property:${Libs.Kotest.version}") // for kotest property test
        "testFixturesImplementation"("io.kotest.extensions:kotest-extensions-spring:${Libs.Kotest.Extension.version}")
        "testFixturesImplementation"("io.kotest.extensions:kotest-extensions-testcontainers:${Libs.Kotest.Extension.version}")

        "testFixturesImplementation"("org.testcontainers:junit-jupiter")
        "testFixturesImplementation"("org.testcontainers:mysql")
        "testFixturesImplementation"("org.testcontainers:mariadb")
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", System.getenv()["SONAR_PROJECT_KEY"] ?: "helloworld-mart")
        property("sonar.host.url", System.getenv()["SONAR_HOST_URL"] ?: "http://localhost:9000")
    }
}

jacoco {
    toolVersion = "0.8.7"
}

task<JacocoReport>("jacocoRootReport") {
    dependsOn(subprojects.map { it.tasks.withType<JacocoReport>() })
    sourceDirectories.setFrom(subprojects.map { it.tasks.findByName("jacocoTestReport")!!.property("sourceDirectories") })
    classDirectories.setFrom(subprojects.map { it.tasks.findByName("jacocoTestReport")!!.property("classDirectories") })
    executionData.setFrom(
        project.fileTree(".") {
            include("**/build/jacoco/**.exec")
        }
    )
    onlyIf {
        true
    }
    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}
