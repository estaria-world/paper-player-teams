import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    // player-teams dependencies
    api(project(":player-teams-api"))

    // estaria dependencies
    compileOnly("world.estaria:paper-command-kit:1.0.6")
}