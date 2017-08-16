# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Applications/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Retrofit
# https://github.com/square/retrofit
-dontwarn okio.**
-dontwarn javax.annotation.**
# https://square.github.io/retrofit/
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions

# Picasso
# https://github.com/square/picasso
-dontwarn com.squareup.okhttp.**

# Gson
# https://github.com/google/gson/blob/master/examples/android-proguard-example/proguard.cfg
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# MyClass for Gson
-keep class com.kazakago.cleanarchitecture.domain.model.** { *; }
-keep class com.kazakago.cleanarchitecture.data.entity.** { *; }
-keep class com.kazakago.cleanarchitecture.web.entity.** { *; }

# Kodein
# https://salomonbrys.github.io/Kodein/
-keepattributes Signature