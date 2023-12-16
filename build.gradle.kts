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
    implementation("com.amazonaws:aws-java-sdk-dynamodb:1.11.163")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")

    // Dagger Dependencies
    implementation("com.google.dagger:dagger:2.48")
    annotationProcessor("com.google.dagger:dagger-compiler:2.48")

    implementation("ch.qos.logback:logback-classic:1.2.6")

    // Lombok Dependency
    implementation("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    // Jackson Dependency
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.5")
}

tasks.register<Zip>("buildZip") {
    dependsOn(tasks.named("jar"))
    into("lib") {
        from(tasks.named("jar"))
        from(configurations.getByName("runtimeClasspath"))
    }
}