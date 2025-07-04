plugins {
    id("com.github.ben-manes.versions") version "0.52.0"
    id ("org.sonarqube") version "6.2.0.5505"
    application
    checkstyle
}

sonar {
    properties {
        property("sonar.projectKey", "iujhiy_java-project-71")
        property("sonar.organisation", "iujhiy")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "hexlet.code.App"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.0")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.19.0")
    implementation ("info.picocli:picocli:4.7.7")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.7")
}

tasks.test {
    useJUnitPlatform()
}

