object ApplicationId {
    const val id = "com.redveloper.gameing"
}

object Modules {
    val app = ":app"
    val creator = ":features:creator"
    val home = ":features:home"
    val core = ":core"
    val common = ":common"
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
    val buildTools = "30.0.2"
    val kotlin = "1.4.10"
    val coreKtx = "1.3.2"
    val appCompat = "1.2.0"
    val constraintLayout = "2.0.2"
    val recyclerview = "1.0.0"
    val glide = "4.10.0"
    val materialDesign = "1.1.0"
    val retrofit = "2.9.0"
    val junit = "4.13"
    val legacySupport = "1.0.0"
    val androidxJunit = "1.1.2"
    val espresso = "3.3.0"
    val multidex = "2.0.1"
    val cardview = "1.0.0"
    val room = "2.3.0-alpha02"
    val koin = "2.1.6"
    val coroutines = "1.3.9"
    val loggingInterceptor = "4.9.0"
    val lifecycle = "2.2.0"
    val navigation = "2.3.0"
    val pagedList = "3.0.0-alpha03"
    val timber = "4.7.1"
    val circleImage = "3.1.0"
}

object Libraries {
    //glide
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
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
    //navigation
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val pagedList = "androidx.paging:paging-runtime:${Versions.pagedList}"
    //timber
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    //circle image
    val circleImage = "de.hdodenhof:circleimageview:${Versions.circleImage}"
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
