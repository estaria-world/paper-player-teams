package world.estaria.player.teams.plugin.plugin

import org.bukkit.Bukkit
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin
import world.avionik.kotlin.paper.plugin.registerEvents
import world.avionik.kotlin.paper.register
import world.estaria.paper.command.kit.PaperCommandKit
import world.estaria.player.teams.api.PlayerTeamsProvider
import world.estaria.player.teams.api.scoreboard.ScoreboardTeamProvider
import world.estaria.player.teams.plugin.api.PlayerTeamsProviderImpl
import world.estaria.player.teams.plugin.api.scoreboard.ScoreboardTeamProviderImpl
import world.estaria.player.teams.plugin.command.UpdateTeamsCommand
import world.estaria.player.teams.plugin.listener.PlayerJoinListener
import world.estaria.player.teams.plugin.listener.PlayerTeamsUpdateListener

/**
 * @author Niklas Nieberler
 */

class SpigotPlugin : JavaPlugin() {

    override fun onEnable() {
        val servicesManager = Bukkit.getServicesManager()

        val playerTeamsProvider = PlayerTeamsProviderImpl()
        val scoreboardTeamProvider = ScoreboardTeamProviderImpl(playerTeamsProvider)

        servicesManager.register<PlayerTeamsProvider>(
            playerTeamsProvider,
            this,
            ServicePriority.Highest,
        )

        servicesManager.register<ScoreboardTeamProvider>(
            scoreboardTeamProvider,
            this,
            ServicePriority.Highest,
        )

        PaperCommandKit.create(this)
            .parseCommand(UpdateTeamsCommand())

        Bukkit.getPluginManager().registerEvents(
            this,
            PlayerJoinListener(playerTeamsProvider, scoreboardTeamProvider),
            PlayerTeamsUpdateListener(playerTeamsProvider)
        )
    }

}