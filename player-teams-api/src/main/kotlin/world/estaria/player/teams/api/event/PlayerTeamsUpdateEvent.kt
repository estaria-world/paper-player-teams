package world.estaria.player.teams.api.event

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author Niklas Nieberler
 */

class PlayerTeamsUpdateEvent : Event() {

    companion object {
        private val handlerList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList = handlerList
    }

    override fun getHandlers(): HandlerList = handlerList

}