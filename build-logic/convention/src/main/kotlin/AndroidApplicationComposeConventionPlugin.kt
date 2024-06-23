
import com.android.build.api.dsl.ApplicationExtension
import com.fanimo.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

//class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
//    override fun apply(target: Project) {
//        with(target) {
//            pluginManager.apply("com.android.application")
//            // Screenshot Tests
//            pluginManager.apply("io.github.takahirom.roborazzi")
//
//            val extension = extensions.getByType<ApplicationExtension>()
//            configureAndroidCompose(extension)
//        }
//    }
//
//}
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }

}
