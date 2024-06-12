package world.estaria.player.teams.plugin.command

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.Permission
import world.estaria.player.teams.api.event.PlayerTeamsUpdateEvent

/**
 * @author Niklas Nieberler
 */

class UpdateTeamsCommand {

    @Command("updateTeams")
    @Permission("player.teams.command.updateTeams")
    fun execute(sender: CommandSender) {
        sender.sendMessage("updating player teams cache...")
        Bukkit.getPluginManager().callEvent(PlayerTeamsUpdateEvent())
    }

}