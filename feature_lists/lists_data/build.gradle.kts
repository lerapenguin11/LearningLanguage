import Versions.lifecycle_version

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.lists_data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common:common_utils"))
    implementation(project(":feature_lists:lists_domain"))

    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.androidMaterial)
    implementation(Deps.constraintLayout)
    implementation(Firebase.firebase_bom)
    implementation(Firebase.firebase_analytics)
    //implementation(platform(Firebase.firebase_firestore))
    implementation(platform(Deps.kotlin_bom))
    implementation(DaggerHilt.hilt)
    implementation("com.google.firebase:firebase-firestore-ktx:24.10.3")
    kapt(DaggerHilt.hiltAndroidCompiler)
    implementation(Room.room)
    implementation(Room.roomCompiler)
    implementation(Libraries.gson)
    implementation(Room.room_runtime)
    //kapt(DaggerHilt.hiltCompiler)
    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)
}