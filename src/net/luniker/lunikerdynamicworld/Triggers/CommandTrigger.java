package net.luniker.lunikerdynamicworld.Triggers;

import net.luniker.lunikerdynamicworld.Actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandTrigger extends Trigger implements CommandExecutor {
    String cmd;

    public CommandTrigger(String name, Action action) {
        this(name, action, name);
    }

    public CommandTrigger(String name, Action action, String cmd) {
        super(name, action);
        this.cmd = cmd;
        Bukkit.getPluginCommand("cmdtrigger").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player) || args.length == 0 || !args[0].equals(this.cmd)) {
            return true;
        }

        action.execute((Player) sender);
        return true;
    }

    public String toString() {
        return String.format("Command%s", super.toString());
    }
}
