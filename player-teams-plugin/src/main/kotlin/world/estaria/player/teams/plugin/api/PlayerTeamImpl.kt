package world.estaria.player.teams.plugin.api

import world.estaria.player.teams.api.PlayerTeam
import world.estaria.player.teams.api.TeamPatternType
import world.estaria.player.teams.plugin.repository.PlayerTeamEntity

/**
 * @author Niklas Nieberler
 */

class PlayerTeamImpl(
    private val entity: PlayerTeamEntity
) : PlayerTeam {

    override fun isDefault(): Boolean {
        return this.entity.default
    }

    override fun getSorting(): String {
        return this.entity.sorting
    }

    override fun getPattern(type: TeamPatternType): String {
        return this.entity.patterns[type] ?: "<red>ZERO"
    }

    override fun getPresentRank(): String {
        return this.entity.presentRank
    }

    override fun getSubRanks(): List<String> {
        return this.entity.subRanks
    }

}