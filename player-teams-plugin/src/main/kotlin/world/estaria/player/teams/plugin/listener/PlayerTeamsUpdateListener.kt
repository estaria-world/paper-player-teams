package world.estaria.player.teams.plugin.listener

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import world.avionik.kotlin.paper.load
import world.avionik.minecraft.common.extension.text
import world.avionik.space.spigot.api.task.task
import world.estaria.player.teams.api.PlayerTeamsProvider
import world.estaria.player.teams.api.TeamPatternType
import world.estaria.player.teams.api.event.PlayerJoinScoreboardTeamEvent
import world.estaria.player.teams.api.event.PlayerTeamsUpdateEvent
import world.estaria.player.teams.api.scoreboard.ScoreboardTeamProvider
import world.estaria.player.teams.plugin.PlayerTeamsDesignHelper

/**
 * @author Niklas Nieberler
 */

class PlayerTeamsUpdateListener(
    private val playerTeamsProvider: PlayerTeamsProvider,
    private val scoreboardTeamProvider: ScoreboardTeamProvider
) : Listener {

    @EventHandler
    fun handlePlayerTeamsUpdate(event: PlayerTeamsUpdateEvent) {
        this.playerTeamsProvider.updateTeamsCache()

        task {
            Bukkit.getOnlinePlayers().forEach { player ->
                PlayerTeamsDesignHelper.updateTeams(player,  this.playerTeamsProvider, this.scoreboardTeamProvider)
            }
        }.runTaskLater(20)
    }

}