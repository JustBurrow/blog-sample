plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "kr.lul.test"
version = "0.0.1"

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotlinx.coroutines.test)
}

tasks.test {
    useJUnitPlatform()
}
