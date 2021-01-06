import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "org.bricolages"
version = "1.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application {
    mainClassName = "org.bricolages.mys3dump.MyS3Dump"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("mysql:mysql-connector-java:8.0.22")
    implementation("com.amazonaws:aws-java-sdk-s3:1.11.932")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("com.jcraft:jsch:0.1.53")
    implementation("commons-cli:commons-cli:1.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.+")
    testImplementation("org.mockito:mockito-core:3.3.+")
    testImplementation("org.mockito:mockito-junit-jupiter:3.3.+")
}

tasks.compileJava {
    options.compilerArgs = listOf(
        "-Xlint:deprecation",
        "-Xlint:unchecked"
    )
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("skipped", "passed", "failed")
        showStandardStreams = true
        exceptionFormat = TestExceptionFormat.FULL
    }
}