import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import java.util.*

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.coil.ktor3.android)
            implementation(libs.koin.android)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.coil)
            implementation(libs.coil.ktor3)
            implementation(project.dependencies.enforcedPlatform(libs.koin.bom.get()))
            implementation(libs.koin.core)
            implementation(libs.koin.composeVM)
            implementation(libs.kotlinx.datetime)
        }

        wasmJsMain.dependencies {
            implementation(libs.coil.ktor3.wasmjs)
        }

        iosMain.dependencies {
            implementation(libs.coil.ktor3.ios)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.coil.ktor3.desktop)
        }
    }
}

android {
    namespace = "com.house_shoreditch.app"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.house_shoreditch.app"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {

    application {
        mainClass = "com.house_shoreditch.app.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Oasis shoreditch"
            packageVersion = "1.0.0"

            macOS {
                dockName = "Oasis Shoreditch"
                setDockNameSameAsPackageName = true
                iconFile.set(project.file("../media/appicon/MacOsIcon.icns"))
            }
            windows {
                iconFile.set(project.file("../media/appicon/icon_512.ico"))
            }
            linux {
                iconFile.set(project.file("../media/appicon/icon_512.png"))
            }
        }
        buildTypes.release.proguard {
            version.set("7.4.0")
            obfuscate.set(false)
            isEnabled = false
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.register("generateSecretsClass") {
    doLast {
        val file = File("$projectDir/src/commonMain/kotlin/com/house_shoreditch/app/Secrets.kt")
        print(file.absolutePath)
        file.parentFile.mkdirs()
        file.writeText(
            """
            package uk.co.sentinelweb.cuer.hub
            
            object Secrets {
                val email: String = "${getSecret("EMAIL")}"
                val phone: String = "${getSecret("PHONE")}"
            }
        """.trimIndent()
        )
    }
}

tasks.named("generateComposeResClass") {
    dependsOn("generateSecretsClass")
}

fun getSecret(propertyName: String): String {
    val secretsFile = rootProject.file("secrets.properties")
    if (secretsFile.exists()) {
        val properties = Properties()
        secretsFile.inputStream().use { properties.load(it) }
        val property = properties.getProperty(propertyName)
        return property
    } else return "invalid"
}
