plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.study_presentation"
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.androidMaterial)
    implementation(Deps.constraintLayout)
    implementation(Navigation.navigation_fragment)
    implementation(Navigation.navigation)
    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)
}