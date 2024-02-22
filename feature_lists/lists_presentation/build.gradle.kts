plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id ("kotlin-android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.lists_presentation"
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
    implementation(project(":common:common_utils"))
    implementation(project(":feature_lists:lists_domain"))
    implementation(project(":feature_lists:lists_data"))

    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.androidMaterial)
    implementation(Deps.constraintLayout)
    implementation(Navigation.navigation_fragment)
    implementation(Navigation.navigation)
    implementation(Deps.fragment)
    implementation(DaggerHilt.hilt)
    kapt(DaggerHilt.hiltAndroidCompiler)
    //annotationProcessor(DaggerHilt.hiltCompiler)
    implementation(Coroutines.lifecycle_viewmodel)
    implementation(Coroutines.lifecycle_livedata)
            constraints {
                implementation(Coroutines.kotlin_stdlib_jdk7) {
                    because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
                }
                implementation(Coroutines.kotlin_stdlib_jdk8) {
                    because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
                }
            }
    implementation(Coroutines.coroutineCore)
    implementation(Coroutines.coroutineAndroid)
    implementation(CoroutinesLifecycleScope.lifecycleViewModel)
    implementation(CoroutinesLifecycleScope.lifeCycleRuntime)
    implementation (ViewModelDelegate.viewModelDeligate)
    implementation(platform(Deps.kotlin_bom))
    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)
}