// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("maven-publish")
}

publishing{
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.apstergo"
            artifactId = "CollapsingPagerIndicator"
            version = "1.0.10"

            afterEvaluate {
                from(components.findByName("release"))
            }
        }
    }
}