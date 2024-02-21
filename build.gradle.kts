plugins {
    id("java")
    id("application")
}

group = "me.dovias.sourceryacademy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass = "me.dovias.sourceryacademy.Application"
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = application.mainClass
        }
    }
}
