import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.apollographql.apollo3").version("3.7.4")
}

apollo {
    service("service") {
        packageName.set("com.eva")
    }
}

android {
    namespace = "com.eva.githubprofileviewer"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.eva.githubprofileviewer"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())


        buildConfigField("String", "TOKEN", "\"${properties.getProperty("ACCESS_TOKEN")}\"")
    }


    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_9
        targetCompatibility = JavaVersion.VERSION_1_9
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    implementation("androidx.compose.material3:material3:1.0.0-alpha11")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0")
    //apollo
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.4")
    implementation("androidx.compose.material:material-icons-extended:1.3.1")
    //navigation compose
    implementation("androidx.navigation:navigation-compose:2.5.3")
    //di
    implementation("io.insert-koin:koin-android:3.3.3")
    implementation ("io.insert-koin:koin-androidx-compose:3.4.2")
    //coil
    implementation("io.coil-kt:coil-compose:2.2.2")


}