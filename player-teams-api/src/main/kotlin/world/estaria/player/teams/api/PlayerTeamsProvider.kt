package world.estaria.player.teams.api

import org.bukkit.entity.Player

/**
 * @author Niklas Nieberler
 */

interface PlayerTeamsProvider {

    /**
     * Updates the cached player teams list
     */
    fun updateTeamsCache()

    /**
     * Gets a cached [PlayerTeam] by a team name
     * @param name of the team
     */
    fun getPlayerTeam(name: String): PlayerTeam {
        return getPlayerTeams().firstOrNull { it.getPresentRank() == name }
            ?: getDefaultTeam()
    }

    /**
     * Gets a cached [PlayerTeam] by a player
     * @param player the player
     */
    fun getPlayerTeam(player: Player): PlayerTeam

    /**
     * Gets a cached default [PlayerTeam]
     */
    fun getDefaultTeam(): PlayerTeam {
        return getPlayerTeams().firstOrNull { it.isDefault() }
            ?: throw IllegalArgumentException("failed to find default team format")
    }

    /**
     * Gets all player teams from a database
     * @return cached list of all teams
     */
    fun getPlayerTeams(): List<PlayerTeam>

}