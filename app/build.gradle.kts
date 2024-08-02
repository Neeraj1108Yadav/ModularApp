plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
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

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.nano.modularapp.HiltTestRunner"
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

    //Dependencies for local unit test
    testImplementation(libs.junit)
    testImplementation(libs.androidx.arch.core)
    testImplementation(libs.androidx.navigation.testing)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(libs.androidx.espresso.runner)

    //Dependencies for JVM Test
    //testImplementation(libs.androidx.test.core.ktx)
    testImplementation(libs.androidx.test.ext.junit.ktx)
    testImplementation(libs.androidx.test.rules)
    //debugImplementation(libs.androidx.fragment.manifest)
    debugImplementation(libs.androidx.fragment.testing)
    //implementation(libs.androidx.fragment)
    implementation(libs.androidx.core)

    //Dependencies for Instrument Test
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.core.ktx)
    androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.test.ext.junit.ktx)
    androidTestImplementation(libs.androidx.arch.core)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.runner)
    androidTestImplementation(libs.androidx.navigation.testing)
    //androidTestImplementation(libs.androidx.fragment)
    //androidTestImplementation(libs.androidx.fragment.testing)

    //Dependency Injection HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Hilt Testing
    androidTestImplementation(libs.hilt.instrument.test)
    kaptAndroidTest(libs.hilt.compiler)

    //Retrofit
    implementation(libs.com.square)
    implementation(libs.com.gson)
    implementation(libs.squareup.okhttp)
    testImplementation(libs.squareup.okhttp.mockwebserver)

    //Coroutines
    implementation(libs.coroutine.android)

    //Architecture Components
    implementation(libs.viewmodel)
    implementation(libs.livedata)
    implementation(libs.lifecycle)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.navigation.ui)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}