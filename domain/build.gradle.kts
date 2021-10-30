plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    id("jacoco")

    application
}

java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") {
        exclude(module = "hibernate-core")
    }

    implementation("com.querydsl:querydsl-jpa")
    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")

    implementation(project(":domain-redis"))
    testImplementation(testFixtures(project(":domain-redis")))
    implementation(project(":domain-rds"))
    testImplementation(testFixtures(project(":domain-rds")))
}

jacoco {
    toolVersion = "0.8.7"
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

tasks.jacocoTestReport {
    executionData.setFrom(fileTree(buildDir).include("/jacoco/*.exec"))
    shouldRunAfter(tasks.test, intTest) // tests are required to run before generating the report
}

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
