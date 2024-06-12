package world.estaria.player.teams.api

/**
 * @author Niklas Nieberler
 */

interface PlayerTeam {

    /**
     * @return true when this team a default group is
     */
    fun isDefault(): Boolean

    /**
     * Gets the team sorting string
     * @return sorting string
     */
    fun getSorting(): String

    /**
     * Gets list of all present rank and sub ranks
     * @return list of all ranks
     */
    fun getRanks(): List<String> {
        return listOf(getPresentRank(), *getSubRanks().toTypedArray())
    }

    /**
     * Gets the [TeamPatternType]
     * @return string from pattern
     */
    fun getPattern(type: TeamPatternType): String

    /**
     * Gets the present rank of this team
     * @return rank name
     */
    fun getPresentRank(): String

    /**
     * Gets list of all sub rank names
     * @return list of all sub rank names
     */
    fun getSubRanks(): List<String>

}