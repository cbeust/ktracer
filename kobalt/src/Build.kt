import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.*
import com.beust.kobalt.plugin.application.*
import com.beust.kobalt.plugin.kotlin.*

val p = project {
    name = "ktracer-tracer"
    group = "com.example"
    artifactId = name
    version = "0.1"

    dependencies {
        compile("org.jetbrains.kotlin:kotlin-stdlib:1.2.70")
    }

    dependenciesTest {
        compile("org.testng:testng:6.13.1",
                "org.assertj:assertj-core:3.5.2")
    }

    assemble {
        jar {
        }
    }

    application {
        mainClass = "com.beust.ktracer.MainKt"
    }
}
