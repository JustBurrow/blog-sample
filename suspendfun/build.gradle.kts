plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "kr.lul.test"
version = "0.0.1"

kotlin {
    jvmToolchain(17)
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
