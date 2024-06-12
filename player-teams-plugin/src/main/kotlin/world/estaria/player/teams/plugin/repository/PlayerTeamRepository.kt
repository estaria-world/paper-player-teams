package world.estaria.player.teams.plugin.repository

import dev.morphia.Datastore
import world.avionik.database.simplified.repository.AbstractMorphiaRepository

/**
 * @author Niklas Nieberler
 */

class PlayerTeamRepository(
    datastore: Datastore
) : AbstractMorphiaRepository<String, PlayerTeamEntity>(
    datastore,
    PlayerTeamEntity::class.java
) {

    init {
        findAll().thenAccept {
            if (it.isEmpty()) {
                save(PlayerTeamEntity())
            }
        }
    }

}