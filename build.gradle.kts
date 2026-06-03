plugins {
    id("java")
    id("org.example.stats")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("sizeStats") {
    group = "statistics"
    description = "Displays the size of each Java file and the total count."

    doLast {
        val sourceDir = file("src/main/java")

        if (!sourceDir.exists()) {
            logger.warn("Java files are not found")
            return@doLast
        }

        println("----------------------------------------")
        println("SIZE STATISTICS")
        println("----------------------------------------")

        var totalBytes: Long = 0

        sourceDir.walkTopDown().forEach { file ->
            if (file.isFile && file.name.endsWith(".java")) {
                val bytesCount = file.length()
                totalBytes += bytesCount

                println("File: ${file.name} -> Size: $bytesCount bytes")
            }
        }

        println("----------------------------------------")
        println("TOTAL JAVA FILES SIZE: $totalBytes bytes")
        println("----------------------------------------")
    }
}