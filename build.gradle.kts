plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("io.gitlab.arturbosch.detekt")
}

group = "ch.lettercode"
version = "0.1.3"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        jvmToolchain(8)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        nodejs()
    }
    mingwX86()
    mingwX64()
    linuxArm64()
    linuxArm32Hfp()
    linuxX64()
    macosArm64()
    macosX64()
    iosArm32()
    iosArm64()
    iosSimulatorArm64()
    iosX64()
    watchosArm32()
    watchosArm64()
    watchosSimulatorArm64()
    watchosX86()
    watchosX64()
    tvosArm64()
    tvosSimulatorArm64()
    tvosX64()

    // Configure all compilations of all targets:
    targets.all {
        compilations.all {
            kotlinOptions {
                allWarningsAsErrors = true
            }
        }
    }
    explicitApi()

    sourceSets {
        named("commonMain") {}
        named("commonTest") {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

    publishing {
        publications.withType<MavenPublication> {
            pom {
                name.set("")
                description.set("")
                url.set("https://github.com/lettercode/kotlin-event")

                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/lettercode/kotlin-event/issues")
                }
                ciManagement {
                    system.set("GitHub Actions")
                    url.set("https://github.com/lettercode/kotlin-event/actions")
                }
                licenses {
                    license {
                        name.set("Apache-2.0 license")
                        url.set("https://github.com/lettercode/kotlin-event/blob/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("lettercode")
                    }
                }
                scm {
                    url.set("https://github.com/lettercode/kotlin-event.git")
                }
            }
        }
    }
}

detekt {
    buildUponDefaultConfig = true
    parallel = true
    allRules = true
    config = files("config/detekt.yml")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    setSource(project.files(project.projectDir.resolve("src/commonMain")))
    include("**/*.kt")
    reports {
        html.required.set(true)
        xml.required.set(true)
    }
}
