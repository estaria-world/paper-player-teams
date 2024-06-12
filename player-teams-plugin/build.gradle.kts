import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    // player-teams dependencies
    api(project(":player-teams-api"))

    // estaria dependencies
    compileOnly("world.estaria:paper-command-kit:1.0.6")

    // paper dependencies
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}