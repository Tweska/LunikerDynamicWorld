package net.luniker.lunikerdynamicworld;

import net.luniker.lunikerdynamicworld.Triggers.Trigger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Pattern;


public class DynamicsFactory implements Listener {
    Player player;
    DynamicObject object;


    public DynamicsFactory(Player player) {
        this.player = player;
        this.object = null;

        Bukkit.getServer().getPluginManager().registerEvents(this, LunikerDynamicWorld.instance);
    }

    /**
     * Handle the users command.
     * @param args Command arguments.
     * @return True if the execution was successful.
     */
    public boolean handleCommand(String[] args) {
        switch(args[0]) {
            case "new":     return handleNewCommand(args);
            case "edit":    return handleEditCommand(args);
            case "remove":  return handleRemoveCommand(args);
            case "pause:":  return handlePauseCommand(args);
            case "resume":  return handleResumeCommand(args);
            case "stop":    return handleStopCommand(args);
            case "list":    return handleListCommand(args);
            default:        return false;
        }
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if(!event.getPlayer().equals(player)) {
            return;
        }

        if(object == null) {
            player.sendMessage("You can send.");
            return;
        }

        event.setCancelled(true);
        if(!Pattern.matches("[0-9][0-9]* [A-Za-z0-9][A-Za-z0-9]*", event.getMessage())) {
            player.sendMessage("Passed the regex!");
        }
    }

    private boolean handleNewCommand(String[] args) {
        if(args.length < 2) {
            return false;
        }

        if(object != null) {
            player.sendMessage(String.format("You need to finish or remove Dynamics Object '%s' first.", object.getName()));
            return true;
        }

        object = new DynamicObject(args[1]);
        player.sendMessage(String.format(
                "-- " + ChatColor.GOLD + "Luniker Dynamic World: Dynamics Factory" + ChatColor.RESET + " --\n" +
                "Initialised a new Dynamic Object with name '%s'\n" +
                "You can now choose one of the following triggers:\n" +
                "1. Command: type '1 <command>' (the command should only contain alphabetic and numeric characters)\n" +
                "2. Player interaction: type '2 <action>' (some actions require you to select a block by " +
                    "right-clicking it with a brick)\n" +
                "3. Timer: type '3 <time> (Time in seconds, should be a positive number)",
                args[1]));
        return true;
    }

    private boolean handleEditCommand(String[] args) {
        return false;
    }

    private boolean handleRemoveCommand(String[] args) {
        return false;
    }

    private boolean handlePauseCommand(String[] args) {
        return false;
    }

    private boolean handleResumeCommand(String[] args) {
        return false;
    }

    private boolean handleStopCommand(String[] args) {
        return false;
    }

    private boolean handleListCommand(String[] args) {
        return false;
    }
}
