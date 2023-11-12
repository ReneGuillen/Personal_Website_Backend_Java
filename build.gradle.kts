plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Apache dependencies
    implementation("org.apache.httpcomponents.client5:httpclient5:5.0")

    // SDK Dependencies
    implementation(platform("software.amazon.awssdk:bom:2.21.1"))
    implementation("software.amazon.awssdk:dynamodb")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")

    // Dagger Dependencies
    implementation("com.google.dagger:dagger:2.48")
    annotationProcessor("com.google.dagger:dagger-compiler:2.48")

    // Log4j Dependencies
    implementation("org.apache.logging.log4j:log4j-api:2.4")
    implementation("org.apache.logging.log4j:log4j-core:2.4")
}

tasks.test {
    useJUnitPlatform()
}