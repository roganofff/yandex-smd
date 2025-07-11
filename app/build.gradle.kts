import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget("17")
        freeCompilerArgs.addAll(listOf("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode"))
    }
}

android {
    namespace = "quo.yandex.financialawareness"
    compileSdk = 35

    val localProps = Properties().apply {
        rootProject.file("local.properties")
            .takeIf { it.exists() }
            ?.inputStream()
            ?.use { load(it) }
    }

    val apiTokenFromLocal: String? = localProps.getProperty("TOKEN")

    defaultConfig {
        applicationId = "quo.yandex.financialawareness"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "MONEYTRACE_BASE_URL",
            "\"https://shmr-finance.ru/api/v1/\""
        )

        buildConfigField(
            "String",
            "TOKEN",
            "\"$apiTokenFromLocal\""
        )
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "MONEYTRACE_BASE_URL",
                "\"https://shmr-finance.ru/api/v1/\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

implementation(libs.androidx.core.ktx)
implementation(libs.androidx.lifecycle.runtime.ktx)
implementation(libs.androidx.activity.compose)
implementation(platform(libs.androidx.compose.bom))
implementation(libs.androidx.ui)
implementation(libs.androidx.ui.graphics)
implementation(libs.androidx.ui.tooling.preview)
implementation(libs.androidx.material3)
implementation(libs.androidx.navigation.compose.android)
implementation(libs.hilt.android)
implementation(libs.androidx.hilt.navigation.compose)
implementation(libs.okhttp)
implementation(libs.logging.interceptor)
implementation(libs.retrofit)
implementation(libs.converter.gson)
kapt(libs.hilt.android.compiler)
implementation(libs.lottie.compose)
testImplementation(libs.junit)
androidTestImplementation(libs.androidx.junit)
androidTestImplementation(libs.androidx.espresso.core)
androidTestImplementation(platform(libs.androidx.compose.bom))
androidTestImplementation(libs.androidx.ui.test.junit4)
debugImplementation(libs.androidx.ui.tooling)
debugImplementation(libs.androidx.ui.test.manifest)
}