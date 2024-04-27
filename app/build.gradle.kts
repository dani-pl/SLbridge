import com.google.firebase.appdistribution.gradle.AppDistributionExtension

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
    // Add the App Distribution Gradle plugin
    id("com.google.firebase.appdistribution")
}

android {
    namespace = "com.danipl.slbridge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.danipl.slbridge"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            firebaseAppDistribution {
                artifactType = "APK"
                serviceCredentialsFile = "./slbridge-serviceaccount.json"
                releaseNotes = "Release notes placeholder"
                groups = "slbridgetesters"
            }
        }
        debug {
            firebaseAppDistribution {
                artifactType = "APK"
                serviceCredentialsFile = "./slbridge-serviceaccount.json"
                releaseNotes = "Release notes placeholder"
                groups = "slbridgetesters"
            }
        }
    }

    val environmentDimension = "environment"
    flavorDimensions += environmentDimension

    productFlavors {

        create("dev") {
            dimension = environmentDimension
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-DEV"


            configure<AppDistributionExtension> {
                groups = "slbridgetesters"
                releaseNotesFile = "Release notes placeholder"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = false
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.timber)
    implementation(platform(libs.firebase.bom))
    //noinspection UseTomlInstead
    implementation("com.google.firebase:firebase-analytics")
}