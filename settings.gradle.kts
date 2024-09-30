pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
//        maven("https://maven.aliyun.com/repository/google")
//        maven("https://maven.aliyun.com/repository/jcenter")
        maven("https://jitpack.io")
        maven("https://repo1.maven.org/maven2")
        maven("https://maven.aliyun.com/repository/releases")
        mavenCentral()
        google()
        flatDir {
            dirs("libs")
        }
    }
}

rootProject.name = "QuickMVIAppFrame"
include(":app")
 