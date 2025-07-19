import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.safe.args)
    alias(libs.plugins.gradle.secrets)
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.fromTarget(libs.versions.java.get()))
        freeCompilerArgs.add("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode")
    }
}

android {
    namespace = "quo.yandex.financialawareness.network"
    compileSdk = libs.versions.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.sdk.min.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "FINANCIALAWARENESS_BASE_URL",
            "\"https://shmr-finance.ru/api/v1/\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(
            libs.versions.compatibility.source
                .get(),
        )
        targetCompatibility = JavaVersion.valueOf(
            libs.versions.compatibility.target
                .get(),
        )
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(path = ":common:util"))
    implementation(project(path = ":common:domain"))

    implementation (libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation (libs.gson)
    implementation (libs.okhttp)
    implementation(libs.http.logging.interceptor)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}