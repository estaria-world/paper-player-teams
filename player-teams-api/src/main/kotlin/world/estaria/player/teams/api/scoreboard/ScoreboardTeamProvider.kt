package world.estaria.player.teams.api.scoreboard

import org.bukkit.entity.Player
import org.bukkit.scoreboard.Scoreboard
import world.estaria.player.teams.api.PlayerTeam

/**
 * @author Niklas Nieberler
 */

interface ScoreboardTeamProvider {

    /**
     * Adds the scoreboard teams to the player
     * @param player to add the teams
     * @param filterFunction to filter players
     */
    fun addScoreboardTeamsToPlayer(player: Player, filterFunction: (Player) -> Boolean = { true })

    /**
     * Adds an entry to the scoreboard team
     * @param scoreboard the scoreboard of the team
     * @param name of the entry
     * @param team the team to add
     */
    fun addScoreboardTeamToEntry(scoreboard: Scoreboard, name: String, team: PlayerTeam)

}