plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.android.mydata"
    compileSdk = 35

    defaultConfig {
        minSdk = 28

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    api(libs.androidx.navigation.compose)


    //Network Libs
    api(libs.moshi)
    api(libs.moshi.adapters)
    api(libs.moshi.kotlin)
    api(libs.converter.moshi)
    api(libs.logging.interceptor)
    api(libs.retrofit)
    api(libs.okio)

    //Timber
    api(libs.timber)

    //exoplayer
    api(libs.androidx.media3.exoplayer)
    api(libs.androidx.media3.ui)

    //DataStore
    api(libs.androidx.datastore.preferences)
}