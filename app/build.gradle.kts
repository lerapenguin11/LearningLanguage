plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.learninglanguage"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.learninglanguage"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(project(":feature_study:study_presentation"))
    implementation(project(":feature_lists:lists_presentation"))
    implementation(project(":feature_word:word_presentation"))

    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.androidMaterial)
    implementation(Deps.constraintLayout)
    implementation(Navigation.navigation_fragment)
    implementation(Navigation.navigation)
    implementation(Firebase.firebase_firestore)
    implementation(Firebase.firebase_analytics)
    implementation(platform(Firebase.firebase_firestore))
    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)
}