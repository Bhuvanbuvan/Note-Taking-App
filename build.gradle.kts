// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    repositories {
        google()
    }
    dependencies {
        fun nav_version() = "2.5.3"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${nav_version()}")

    }
}



plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.android.library") version "7.3.1" apply false
}
