plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
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

        multiDexEnabled = true
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
    implementation(project(":common:common_utils"))
    implementation(project(":feature_lists:lists_data"))
    implementation(project(":feature_word:word_data"))

    implementation(Deps.core)
    implementation(platform(Deps.kotlin_bom))
    implementation(Deps.appCompat)
    implementation(Deps.androidMaterial)
    implementation(Deps.constraintLayout)
    implementation(Navigation.navigation_fragment)
    implementation(Navigation.navigation)
    implementation(Firebase.firebase_bom)
    implementation(Firebase.firebase_analytics)
    implementation(platform(Firebase.firebase_firestore))
    implementation(DaggerHilt.hilt)
    kapt(DaggerHilt.hiltAndroidCompiler)
    implementation(Room.room)
    kapt(Room.roomCompiler)
//    kapt(DaggerHilt.hiltCompiler)
    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)
    implementation("com.android.support:multidex:1.0.3")
}