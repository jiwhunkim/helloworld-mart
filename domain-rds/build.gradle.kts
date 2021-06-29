plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    id("jacoco")

    application
}

java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") {
        exclude(module = "hibernate-core")
    }
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("org.hibernate:hibernate-core")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    implementation("com.querydsl:querydsl-jpa")
    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")

    implementation("org.flywaydb:flyway-core:7.9.1")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
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

// val testConfig = configurations.create("testArtifacts") {
//    extendsFrom(configurations["testImplementation"])
// }
//
// tasks.register("testJar", Jar::class.java) {
//    dependsOn("testClasses")
//    archiveClassifier.set("test")
//    from(sourceSets["test"].output)
// }
//
// artifacts {
//    add("testArtifacts", tasks.named<Jar>("testJar") )
// }
