pluginManagement {
    repositories {
        google()
        mavenCentral()
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

rootProject.name = "LearningLanguage"
include(":app")
include(":feature_word:word_data")
include(":feature_word:word_domain")
include(":feature_study:study_data")
include(":feature_study:study_domain")
include(":feature_study:study_presentation")
include(":feature_lists:lists_data")
include(":feature_lists:lists_domain")
include(":feature_lists:lists_presentation")
include(":feature_word:word_presentation")
