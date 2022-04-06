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
