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
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.integration:spring-integration-redis")
    implementation("io.lettuce:lettuce-core")

    implementation("org.apache.commons:commons-lang3")
    implementation("org.apache.commons:commons-pool2")
}

jacoco {
    toolVersion = "0.8.7"
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

val testConfig = configurations.create("testArtifacts") {
    extendsFrom(configurations["testImplementation"])
}

tasks.register("testJar", Jar::class.java) {
    dependsOn("testClasses")
    archiveClassifier.set("test")
    from(sourceSets["test"].output)
}

artifacts {
    add("testArtifacts", tasks.named<Jar>("testJar") )
}