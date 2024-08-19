plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.eabmodel.casopracticoexamenandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.eabmodel.casopracticoexamenandroid"
        minSdk = 31
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
}

dependencies {
    implementation(libs.appcompat.v161) // Reemplaza con la versión adecuada
    implementation(libs.stetho) // Reemplaza con la versión adecuada
    implementation(libs.material.v190) // Reemplaza con la versión adecuada
    implementation(libs.activity.ktx) // Reemplaza con la versión adecuada
    implementation(libs.constraintlayout) // Reemplaza con la versión adecuada
    implementation(libs.lifecycle.livedata.ktx) // Reemplaza con la versión adecuada
    implementation(libs.lifecycle.viewmodel.ktx) // Reemplaza con la versión adecuada
    implementation(libs.navigation.fragment.ktx) // Reemplaza con la versión adecuada
    implementation(libs.navigation.ui.ktx)
    implementation(libs.activity) // Reemplaza con la versión adecuada
    testImplementation(libs.junit) // Reemplaza con la versión adecuada
    implementation(libs.retrofit)
    implementation(libs.material) // Reemplaza con la versión adecuada
    implementation(libs.drawerlayout) // Reemplaza con la versión adecuada
    implementation(libs.core.ktx) // Reemplaza con la versión adecuada
    implementation(libs.glide) // Reemplaza con la versión adecuada
    annotationProcessor(libs.compiler) // Reemplaza con la versión adecuada
    implementation(libs.converter.gson)
    androidTestImplementation(libs.junit.v115) // Reemplaza con la versión adecuada
    androidTestImplementation(libs.espresso.core.v351) // Reemplaza con la versión adecuada
}