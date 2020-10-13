object ApplicationId {
    val id = "com.themovies.sample"
}

object Modules {
    val app = ":app"
    val creator = ":features:creator"
    val home = ":features:home"
    val common = ":common"
    val core = ":core"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val gradle = "4.0.2"
    val compileSdk = 30
    val minSdk = 21
    val targetSdk = 30
    val kotlin = "1.4.10"
    val coreKtx = "1.3.2"
    val appCompat = "1.2.0"
    val constraintLayout = "2.0.2"
    val recyclerview = "1.0.0"
    val glide = "4.11.0"
    val materialDesign = "1.1.0"
    val retrofit = "2.9.0"
    val junit = "4.13"
    val legacySupport = "1.0.0"
    val androidxJunit = "1.1.2"
    val espresso = "3.3.0"
    val multidex = "2.0.1"
    val cardview = "1.0.0"
    val room = "2.2.5"
    val koin = "2.1.6"
    val coroutines = "1.3.9"
    val loggingInterceptor = "4.9.0"
    val lifecycle = "2.2.0"
}

object Libraries {
    //glide
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    //koin
    val koinCore = "org.koin:koin-core:${Versions.koin}"
    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
    //retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val okhttp = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    //room
    val room = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomTesting = "androidx.room:room-testing:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    //coroutines
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object KotlinLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object AndroidLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val materialDesign  = "com.google.android.material:material:${Versions.materialDesign}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    val jUnit = "junit:junit:${Versions.junit}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val lifecycle = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
}
