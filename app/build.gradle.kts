plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.wesign"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.wesign"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner =  "com.example.wesign.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    signingConfigs {
//        create("debug") {
//            storeFile =file("D:/Tutorial/app_key.jks")
//            storePassword = "12345679"
//            keyAlias = "key0"
//            keyPassword = "12345679"
//        }
        create("release") {
            storeFile =file("D:/Tutorial/app_key.jks")
            storePassword = "12345679"
            keyAlias = "key0"
            keyPassword = "12345679"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
//        debug {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//            signingConfig = signingConfigs.getByName("debug")
//        }

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.core)
    implementation(libs.androidx.ui.test.android)
    implementation(libs.androidx.runner)
    testImplementation(libs.junit)
//    testImplementation (libs.kotlinx.coroutines.test)
//    testImplementation (libs.mockito.kotlin)
//    testImplementation (libs.mockito.core)
//    testImplementation (libs.mockito.inline)
//    testImplementation (libs.mockk)
//    testImplementation (libs.junit.jupiter.api)
//    testImplementation (libs.junit.jupiter.engine)
//    testImplementation (libs.androidx.core.testing.v220)
//    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.core.testing)
    kaptTest(libs.hilt.android.compiler.v2511)
    testAnnotationProcessor(libs.hilt.android.compiler.v2511)
    androidTestImplementation(libs.hilt.android.testing.v2461)
    kaptAndroidTest(libs.hilt.android.compiler.v2511)
    androidTestAnnotationProcessor(libs.hilt.android.compiler.v2511)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.android.database.sqlcipher)
    implementation (libs.accompanist.navigation.animation)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.preferences)
    implementation (libs.androidx.constraintlayout.compose)
    implementation (libs.lottie.compose)
    implementation (libs.accompanist.permissions)
    implementation(libs.dagger.android)
    kapt(libs.dagger.compiler)
    implementation(libs.hilt.agp)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.moshi.converter)
    implementation(libs.gson.convertor)
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.livedata)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.constraint.layout)
    implementation(libs.compose.material.icons.core)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.splashscreen)
    implementation(libs.coil.kt.compose)

    implementation ("androidx.media3:media3-exoplayer:1.4.1")
    implementation ("androidx.media3:media3-ui:1.4.1")

}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
