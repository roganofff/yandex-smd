// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.gradle.secrets)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.androidx.room) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.gradle.secrets) apply false
    alias(libs.plugins.safe.args) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}

private val versionMajor = 1
private val versionMinor = 0

val versionName by extra(initialValue = "$versionMajor.$versionMinor")
val versionCode by extra(initialValue = versionMajor * 1000 + versionMinor * 10)