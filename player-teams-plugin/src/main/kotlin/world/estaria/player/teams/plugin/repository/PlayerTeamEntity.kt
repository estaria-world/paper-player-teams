package world.estaria.player.teams.plugin.repository

import dev.morphia.annotations.Entity
import dev.morphia.annotations.Id
import world.estaria.player.teams.api.TeamPatternType

/**
 * @author Niklas Nieberler
 */

@Entity
class PlayerTeamEntity(
    @Id val presentRank: String = "default",
    val subRanks: List<String> = emptyList(),
    val default: Boolean = true,
    val sorting: String = "a",
    val patterns: Map<TeamPatternType, String> = hashMapOf(
        Pair(TeamPatternType.CHAT, ""),
        Pair(TeamPatternType.COLOR_CODE, ""),
        Pair(TeamPatternType.TABLIST_PREFIX, ""),
        Pair(TeamPatternType.COLOR_CODE, "")
    )
)