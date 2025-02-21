import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import java.text.SimpleDateFormat
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
            baseName = "OasisShoreditch"
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
            implementation(libs.ktor.cio)
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
    namespace = libs.versions.app.pkg.get()
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.app.pkg.get()
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = libs.versions.version.code.get().toInt()
        versionName = libs.versions.version.name.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("src/androidMain/oasis-debug-keystore.jks")
            storePassword = "0as1s1"
            keyAlias = "oasis"
            keyPassword = "0as1s1"
        }
        create("release") {
            storeFile = rootProject.file(getSecret("ANDROID_KEYSTORE_FILENAME"))
            storePassword = getSecret("ANDROID_PASSWORD")
            keyAlias = getSecret("ANDROID_ALIAS")
            keyPassword = getSecret("ANDROID_PASSWORD")
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    applicationVariants.all {
        val sdf = SimpleDateFormat("ddMMyyyy_HHmmss")
        val currentDateAndTime = sdf.format(Date())
        val appName = libs.versions.app.pkg.get()
        val versionName = libs.versions.version.name.get()
        val versionCode = libs.versions.version.code.get().toInt()
        this.outputs
            .map { it as com.android.build.gradle.internal.api.ApkVariantOutputImpl }
            .forEach { output ->
                val variant = this.buildType.name
                val fileExtension = output.outputFileName.substringAfterLast('.', "")
                val apkName =
                    "${appName}-${variant}-${versionName}-${versionCode}-${currentDateAndTime}.${fileExtension}"
                output.outputFileName = apkName
            }

        this.outputs.forEach { _ ->
            val variant = this.buildType.name
            val bundleTaskName = "bundle${variant.replaceFirstChar { it.uppercase() }}"
            tasks.named(bundleTaskName).configure {
                doLast {
                    val newFileName = "${appName}-${variant}-${versionName}-${versionCode}-${currentDateAndTime}.aab"

                    val bundleOutputDir = file("build/outputs/bundle/${variant}")
                    val bundleFile = bundleOutputDir.listFiles()?.firstOrNull { it.extension == "aab" }

                    if (bundleFile != null) {
                        val newFile = File(bundleFile.parentFile, newFileName)
                        bundleFile.copyTo(newFile)
                    } else {
                        println("AAB file not found for variant '${variant}'")
                    }
                }
            }
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose {
    desktop {
        application {
            mainClass = "com.house_shoreditch.app.MainKt"
            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = libs.versions.app.name.get()
                packageVersion = libs.versions.version.name.get()
                vendor = libs.versions.app.vendor.get()
                description = libs.versions.app.description.get()

                macOS {
                    dockName = libs.versions.app.name.get()
                    iconFile.set(project.file("../media/appicon/MacOsIcon.icns"))
                    infoPlist {
                        extraKeysRawXml = """
            <key>NSOutgoingConnectionsUsageDescription</key>
            <string>This app requires internet access to load content.</string>
            <key>NSAppTransportSecurity</key>
            <dict>
                <key>NSAllowsArbitraryLoads</key>
                <true/>
                <key>NSAllowsArbitraryLoadsInWebContent</key>
                <true/>
                <key>NSExceptionDomains</key>
                <dict>
                    <key>raw.githubusercontent.com</key>
                    <dict>
                        <key>NSIncludesSubdomains</key>
                        <true/>
                        <key>NSTemporaryExceptionAllowsInsecureHTTPLoads</key>
                        <true/>
                        <key>NSTemporaryExceptionMinimumTLSVersion</key>
                        <string>TLSv1.2</string>
                    </dict>
                </dict>
            </dict>
        """.trimIndent()
                    }
                }

                windows {
                    iconFile.set(project.file("../media/appicon/icon_512.ico"))
                    msiPackageVersion = libs.versions.version.name.get()
                    shortcut = true
                    dirChooser = true
                    menu = true
                    menuGroup = libs.versions.app.menugroup.get()
                }

                linux {
                    iconFile.set(project.file("../media/appicon/icon_512.png"))
                    debMaintainer = libs.versions.app.vendor.get()
                    menuGroup = libs.versions.app.menugroup.get()
                    shortcut = true
                }
            }

            buildTypes.release.proguard {
                version.set("7.4.0")
                obfuscate.set(false)
                isEnabled = false
            }
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

tasks.register("generateBuildPropsClass") {
    doLast {
        val file = File("$projectDir/src/commonMain/kotlin/com/house_shoreditch/app/BuildProps.kt")
        print(file.absolutePath)
        file.parentFile.mkdirs()
        file.writeText(
            """
            package uk.co.sentinelweb.cuer.hub
            
            object BuildProps {
                val versionCode: Int = ${libs.versions.version.code.get().toInt()}
                val versionName: String = "${libs.versions.version.name.get()}"
            }
        """.trimIndent()
        )
    }
}

tasks.named("generateComposeResClass") {
    dependsOn("generateSecretsClass")
    dependsOn("generateBuildPropsClass")
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
