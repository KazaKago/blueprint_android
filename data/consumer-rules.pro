# ========= START Kotlin Serialization =========
# https://github.com/Kotlin/kotlinx.serialization#android
-if @kotlinx.serialization.Serializable class **
-keepclassmembers class <1> {
    static <1>$Companion Companion;
}
-if @kotlinx.serialization.Serializable class ** {
    static **$* *;
}
-keepclassmembers class <2>$<3> {
    kotlinx.serialization.KSerializer serializer(...);
}
-if @kotlinx.serialization.Serializable class ** {
    public static ** INSTANCE;
}
-keepclassmembers class <1> {
    public static <1> INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault
# ========= END Kotlin Serialization =========

# ========= START OkHttp =========
# https://square.github.io/okhttp/features/r8_proguard/
# https://github.com/square/okhttp/issues/6258
-dontwarn javax.annotation.**
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**
# ========= END OkHttp =========

# ========= START SLF4J =========
# https://proguard-rules.blogspot.com/2017/07/android-slf4j-proguard-rules.html
-dontwarn org.slf4j.**
# ========= END SLF4J =========
