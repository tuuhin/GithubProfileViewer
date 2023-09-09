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
    compileSdk = 34

    defaultConfig {
        applicationId = "com.eva.githubprofileviewer"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        val fieldType = "String"
        val fieldName = "TOKEN"

        buildConfigField(fieldType, fieldName, "\"${properties.getProperty("ACCESS_TOKEN")}\"")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation(platform("androidx.compose:compose-bom:2023.09.00"))

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    androidTestImplementation(platform("androidx.compose:compose-bom:2023.09.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // apollo
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.4")
    implementation("com.apollographql.apollo3:apollo-normalized-cache-sqlite:3.7.4")
    // extra icons
    implementation("androidx.compose.material:material-icons-extended:1.5.1")
    // navigation compose
    implementation("androidx.navigation:navigation-compose:2.7.1")
    // di
    implementation("io.insert-koin:koin-android:3.4.3")
    implementation("io.insert-koin:koin-androidx-compose:3.4.6")
    // coil
    implementation("io.coil-kt:coil-compose:2.4.0")
    // splash
    implementation("androidx.core:core-splashscreen:1.0.1")
}
