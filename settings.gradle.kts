val projectName: String by settings
rootProject.name = projectName

pluginManagement {
    val kotlinVersion: String by settings
    val detektVersion: String by settings
    plugins {
        kotlin("multiplatform") version kotlinVersion
        id("io.gitlab.arturbosch.detekt") version detektVersion
    }
}
