plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.nano.modularapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nano.modularapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
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
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Testing
    testImplementation(libs.test.core.live.data)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.navigation.testing)

    //Retrofit
    implementation(libs.com.square)
    implementation(libs.com.gson)
    implementation(libs.com.interceptor)

    //Dependency Injection HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Coroutines
    implementation(libs.coroutine.android)

    //Lifecycle
    implementation(libs.viewmodel)
    implementation(libs.livedata)
    implementation(libs.lifecycle)

    //Navigation
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.navigation.ui)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}