pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx")
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Yes No Memes"
include(":app")
include(":network")
include(":yes-no-request")
