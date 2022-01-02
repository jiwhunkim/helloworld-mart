plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")

    application
}

java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.integration:spring-integration-redis")
    implementation("io.lettuce:lettuce-core")

    implementation("org.apache.commons:commons-lang3")
    implementation("org.apache.commons:commons-pool2")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

//sourceSets {
//    create("intTest") {
//        compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
//        runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
//
//        resources.srcDir(file("src/intTest/resources"))
//    }
//}
//
//val intTestImplementation: Configuration by configurations.getting {
//    extendsFrom(configurations.implementation.get(), configurations.testImplementation.get())
//}
//
//configurations["intTestImplementation"].extendsFrom(configurations.testImplementation.get())
//configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())
//
//val intTest = task<Test>("intTest") {
//    description = "Runs integration tests."
//    group = "verification"
//
//    testClassesDirs = sourceSets["intTest"].output.classesDirs
//    classpath = sourceSets["intTest"].runtimeClasspath
//    shouldRunAfter("test")
//}
//
//tasks.check { dependsOn(intTest) }
