package world.estaria.player.teams.plugin.api

import org.bukkit.entity.Player
import world.avionik.database.simplified.morphia.createMorphiaDatastore
import world.estaria.player.teams.api.PlayerTeam
import world.estaria.player.teams.api.PlayerTeamsProvider
import world.estaria.player.teams.plugin.repository.PlayerTeamEntity
import world.estaria.player.teams.plugin.repository.PlayerTeamRepository

/**
 * @author Niklas Nieberler
 */

class PlayerTeamsProviderImpl : PlayerTeamsProvider {

    private val repository = PlayerTeamRepository(createMorphiaDatastore("player_teams"))
    private val playerTeamEntities = arrayListOf<PlayerTeamEntity>()

    init {
        updateTeamsCache()
    }

    override fun updateTeamsCache() {
        this.playerTeamEntities.clear()
        this.repository.findAll().thenAccept {
            this.playerTeamEntities.addAll(it)
        }
    }

    override fun getPlayerTeam(player: Player): PlayerTeam {
        return getPlayerTeams()
            .sortedBy { it.getSorting() }
            .firstOrNull { getInheritedGroupByRanks(player, it.getRanks()) } ?: return getDefaultTeam()
    }

    private fun getInheritedGroupByRanks(player: Player, ranks: List<String>): Boolean {
        return ranks.any { player.hasPermission("group.$it") }
    }

    override fun getPlayerTeams(): List<PlayerTeam> {
        return this.playerTeamEntities.map { PlayerTeamImpl(it) }
    }

}