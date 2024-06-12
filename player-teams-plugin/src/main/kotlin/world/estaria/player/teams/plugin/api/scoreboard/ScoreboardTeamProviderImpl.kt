package world.estaria.player.teams.plugin.api.scoreboard

import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team
import world.avionik.minecraft.common.extension.text
import world.estaria.player.teams.api.PlayerTeam
import world.estaria.player.teams.api.PlayerTeamsProvider
import world.estaria.player.teams.api.TeamPatternType
import world.estaria.player.teams.api.scoreboard.ScoreboardTeamProvider

/**
 * @author Niklas Nieberler
 */

class ScoreboardTeamProviderImpl(
    private val playerTeamsProvider: PlayerTeamsProvider
) : ScoreboardTeamProvider {

    override fun addScoreboardTeamsToPlayer(player: Player, filterFunction: (Player) -> Boolean) {
        registerTeams(player)
        Bukkit.getOnlinePlayers().filter(filterFunction).forEach {
            addPlayerToTeam(player.scoreboard, it)
            addPlayerToTeam(it.scoreboard, player)
        }
    }

    override fun addScoreboardTeamToEntry(scoreboard: Scoreboard, name: String, team: PlayerTeam) {
        scoreboard.getTeam(team.getSorting())?.addEntry(name)
    }

    private fun registerTeams(player: Player) {
        val scoreboard = player.scoreboard
        this.playerTeamsProvider.getPlayerTeams()
            .filter { scoreboard.getTeam(it.getSorting()) == null }
            .forEach { registerOrUpdateTeam(scoreboard, it) }
    }

    private fun addPlayerToTeam(scoreboard: Scoreboard, player: Player) {
        val teamFormat = this.playerTeamsProvider.getPlayerTeam(player)
        addScoreboardTeamToEntry(scoreboard, player.name, teamFormat)
    }

    private fun registerOrUpdateTeam(scoreboard: Scoreboard, team: PlayerTeam) {
        val pattern = team.getPattern(TeamPatternType.UNDER_PLAYER_PREFIX)
        val scoreboardTeam = scoreboard.getTeam(team.getSorting())
            ?: scoreboard.registerNewTeam(team.getSorting())
        scoreboardTeam.color(NamedTextColor.GRAY)
        scoreboardTeam.prefix(text(pattern))
        scoreboardTeam.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER)
    }

}