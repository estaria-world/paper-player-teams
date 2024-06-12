package world.estaria.player.teams.plugin.listener

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import world.avionik.minecraft.common.extension.text
import world.estaria.player.teams.api.PlayerTeamsProvider
import world.estaria.player.teams.api.TeamPatternType
import world.estaria.player.teams.api.event.PlayerJoinScoreboardTeamEvent
import world.estaria.player.teams.api.scoreboard.ScoreboardTeamProvider

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

        val playerJoinScoreboardTeamEvent = PlayerJoinScoreboardTeamEvent(player)
        Bukkit.getPluginManager().callEvent(playerJoinScoreboardTeamEvent)
        if (playerJoinScoreboardTeamEvent.isCancelled)
            return

        this.scoreboardTeamProvider.addScoreboardTeamsToPlayer(player)

        val teamFormat = this.playerTeamsProvider.getPlayerTeam(player)
        val pattern = text(teamFormat.getPattern(TeamPatternType.TABLIST_PREFIX).replace("%player%", player.name))
        player.playerListName(pattern)
    }

}