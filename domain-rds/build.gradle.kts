plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")

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
