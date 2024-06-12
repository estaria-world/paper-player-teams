package world.estaria.player.teams.plugin.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import world.estaria.player.teams.api.PlayerTeamsProvider
import world.estaria.player.teams.api.scoreboard.ScoreboardTeamProvider
import world.estaria.player.teams.plugin.PlayerTeamsDesignHelper

/**
 * @author Niklas Nieberler
 */

class PlayerJoinListener(
    private val playerTeamsProvider: PlayerTeamsProvider,
    private val scoreboardTeamProvider: ScoreboardTeamProvider
) : Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    fun handleJoin(event: PlayerJoinEvent) {
        val player = event.player
        PlayerTeamsDesignHelper.updateTeams(player, this.playerTeamsProvider, this.scoreboardTeamProvider)
    }

}