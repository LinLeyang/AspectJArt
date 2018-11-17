# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#-keep class org.aspectj.** {*;}

#-keep class com.penta.aspectjart.aspect.** {*;}
#-keep class com.penta.aspectjart.internal.** {*;}

#-keep public class * extends android.support.v4.**
#-keep public class * extends android.support.v7.**
#-keep public class * extends android.support.annotation.**

-dontwarn com.squareup.okhttp3.**

-keep class com.squareup.okhttp3.** { *;}

-dontwarn okio.**
-dontwarn okhttp3.**

-keep @interface com.penta.nnotation.ApjLog
-keep @com.penta.annotation.ApjLog class *
-keepclassmembers class * {
    @com.penta.annotation.ApjLog *;
}