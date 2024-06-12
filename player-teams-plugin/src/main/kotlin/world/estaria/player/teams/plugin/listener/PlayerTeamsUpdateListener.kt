package world.estaria.player.teams.plugin.listener

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import world.avionik.kotlin.paper.load
import world.estaria.player.teams.api.PlayerTeamsProvider
import world.estaria.player.teams.api.event.PlayerTeamsUpdateEvent

/**
 * @author Niklas Nieberler
 */

class PlayerTeamsUpdateListener(
    private val playerTeamsProvider: PlayerTeamsProvider
) : Listener {

    @EventHandler
    fun handlePlayerTeamsUpdate(event: PlayerTeamsUpdateEvent) {
        this.playerTeamsProvider.updateTeamsCache()
    }

}