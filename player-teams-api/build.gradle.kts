plugins {
    `maven-publish`
}

publishing {
    repositories {
        maven {
            name = "estaria"
            url = uri("https://repo.estaria.world/releases")
            credentials(PasswordCredentials::class.java)
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}
