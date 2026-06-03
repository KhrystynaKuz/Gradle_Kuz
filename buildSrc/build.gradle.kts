plugins {
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("myCustomPlugin") {
            id = "org.example.stats"
            implementationClass = "org.example.MyPlugin"
        }
    }
}