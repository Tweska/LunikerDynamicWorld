package net.luniker.lunikerdynamicworld.Commands;

import net.luniker.lunikerdynamicworld.LunikerDynamicWorld;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CmdExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!LunikerDynamicWorld.commands.containsKey(label)) {
            return false;
        }

        if(!sender.hasPermission(String.format("lunikerdynamicworld.%s", label))) {
            sender.sendMessage(" Looks like you are lacking some perms.");
            return true;
        }

        CommandModule mod = LunikerDynamicWorld.commands.get(label);

        if(mod.playerOnly && !(sender instanceof Player)) {
            sender.sendMessage("This command is for players only.");
            return true;
        }

        if(args.length < mod.minArgs || args.length > mod.maxArgs) {
            return false;
        }

        return mod.run(sender, args);
    }
}
