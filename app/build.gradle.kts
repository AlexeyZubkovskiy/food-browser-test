plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.foodbrowser"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodbrowser"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// in real project versions should be better structured and used new things like version catalogs
dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation("androidx.activity:activity-compose:1.8.2")

    //Compose
    val composeVer = "2.7.6"
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    //implementation ("androidx.compose.runtime:runtime-livedata:$composeVer")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    //Navigation
    implementation ("androidx.navigation:navigation-compose:$composeVer")

    //Rx
    // used it here for just for speed, in modern project I'd prefer to use coroutines
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.21")
    //DI
    val koinVer = "3.5.0"
    implementation ("io.insert-koin:koin-android:$koinVer")
    implementation("io.insert-koin:koin-core:$koinVer")
    implementation("io.insert-koin:koin-androidx-compose:$koinVer")
    //Parsing
    implementation ("com.google.code.gson:gson:2.10.1")
    //Retrofit
    val retrofitVer = "2.9.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVer")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:$retrofitVer")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVer")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}