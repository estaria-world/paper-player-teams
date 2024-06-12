package world.estaria.player.teams.plugin.plugin

import org.bukkit.Bukkit
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin
import world.avionik.kotlin.paper.register
import world.estaria.player.teams.api.PlayerTeamsProvider
import world.estaria.player.teams.api.scoreboard.ScoreboardTeamProvider
import world.estaria.player.teams.plugin.api.PlayerTeamsProviderImpl
import world.estaria.player.teams.plugin.api.scoreboard.ScoreboardTeamProviderImpl

/**
 * @author Niklas Nieberler
 */

class SpigotPlugin : JavaPlugin() {

    override fun onEnable() {
        val servicesManager = Bukkit.getServicesManager()

        val playerTeamsProvider = PlayerTeamsProviderImpl()
        servicesManager.register<PlayerTeamsProvider>(
            playerTeamsProvider,
            this,
            ServicePriority.Highest,
        )

        servicesManager.register<ScoreboardTeamProvider>(
            ScoreboardTeamProviderImpl(playerTeamsProvider),
            this,
            ServicePriority.Highest,
        )
    }

}