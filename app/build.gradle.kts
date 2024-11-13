plugins {
    id("java")
    application
    checkstyle
    id("jacoco")
}

application {
    mainClass = "hexlet.code.App"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
checkstyle {
    toolVersion = "10.12.4"
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

jacoco {
    toolVersion = "0.8.7"  // Укажите необходимую версию Jacoco
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // Генерация отчета после тестов
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)   // Отчет в формате XML
        csv.required.set(false)  // Отключение отчета в формате CSV
        html.required.set(true)  // Отчет в формате HTML
    }
}
