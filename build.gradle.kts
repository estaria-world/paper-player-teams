import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm") version "1.9.22"
}

allprojects {
    group = "world.estaria"
    version = "1.1.0"

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("com.github.johnrengelman.shadow")
    }

    repositories {
        mavenCentral()

        // jitpack repositories
        maven("https://jitpack.io/")

        // minecraft repositories
        maven("https://repo.papermc.io/repository/maven-public/")

        // estaria repositories
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/estaria-world/paper-command-kit")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/estaria-world/rabbitmq-protobuf-kit")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/estaria-world/event-manager")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }

        // protobuf repositories
        maven {
            name = "buf"
            url = uri("https://buf.build/gen/maven")
        }
    }
}

subprojects {
    dependencies {
        compileOnly(kotlin("stdlib"))

        // avionik dependencies
        compileOnly("world.avionik:minecraft-common:1.0.1")
        compileOnly("world.avionik:database-simplified-kit:1.1.0")

        // estaria dependencies
        compileOnly("world.estaria:event-manager:1.0.2")
        compileOnly("world.estaria:rabbitmq-protobuf-kit:1.0.2")

        // adventure dependencies
        val adventureVersion = "4.16.0"
        compileOnly("net.kyori:adventure-api:$adventureVersion")
        compileOnly("net.kyori:adventure-text-minimessage:$adventureVersion")

        // protobuf dependencies
        compileOnly("build.buf.gen:estaria_proto-specs_grpc_kotlin:1.4.1.1.20240503190031.f5084f726ba8")
        compileOnly("com.google.protobuf:protobuf-java:3.16.3")

        // database dependencies
        compileOnly("dev.morphia.morphia:morphia-core:2.3.2")
        compileOnly("com.rabbitmq:amqp-client:5.21.0")

        // log4j dependencies
        val log4jVersion = "2.23.1"
        compileOnly("org.apache.logging.log4j:log4j-core:$log4jVersion")
        runtimeOnly("org.apache.logging.log4j:log4j-core:$log4jVersion")
        compileOnly("org.apache.logging.log4j:log4j-slf4j-impl:$log4jVersion")
        runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:$log4jVersion")
    }

    tasks.named("shadowJar", ShadowJar::class) {
        mergeServiceFiles()
    }
}