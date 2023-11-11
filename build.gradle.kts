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
    implementation("org.apache.httpcomponents.client5:httpclient5:5.0")
    implementation(platform("software.amazon.awssdk:bom:2.21.1")) // AWS SDK v2 BOM
    implementation("software.amazon.awssdk:dynamodb")
}

tasks.test {
    useJUnitPlatform()
}