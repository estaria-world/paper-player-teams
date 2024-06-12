package world.estaria.player.teams.plugin

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import world.avionik.minecraft.common.extension.text
import world.estaria.player.teams.api.PlayerTeamsProvider
import world.estaria.player.teams.api.TeamPatternType
import world.estaria.player.teams.api.event.PlayerJoinScoreboardTeamEvent
import world.estaria.player.teams.api.scoreboard.ScoreboardTeamProvider

/**
 * @author Niklas Nieberler
 */

object PlayerTeamsDesignHelper {

    fun updateTeams(
        player: Player,
        playerTeamsProvider: PlayerTeamsProvider,
        scoreboardTeamProvider: ScoreboardTeamProvider
    ) {
        val playerJoinScoreboardTeamEvent = PlayerJoinScoreboardTeamEvent(player)
        Bukkit.getPluginManager().callEvent(playerJoinScoreboardTeamEvent)
        if (playerJoinScoreboardTeamEvent.isCancelled)
            return

        scoreboardTeamProvider.addScoreboardTeamsToPlayer(player)

        val teamFormat = playerTeamsProvider.getPlayerTeam(player)
        val pattern = text(teamFormat.getPattern(TeamPatternType.TABLIST_PREFIX).replace("%player%", player.name))
        player.playerListName(pattern)
    }

}