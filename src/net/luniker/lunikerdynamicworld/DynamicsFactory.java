package net.luniker.lunikerdynamicworld;

import net.luniker.lunikerdynamicworld.Actions.Action;
import net.luniker.lunikerdynamicworld.Triggers.CommandTrigger;
import net.luniker.lunikerdynamicworld.Triggers.Trigger;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Pattern;

import static net.luniker.lunikerdynamicworld.DynamicsFactoryStatus.*;


public class DynamicsFactory implements Listener {
    Player player;
    DynamicsFactoryStatus status;

    Action currentAciton;
    Trigger currentTrigger;
    String currentName;
    int lastOption;


    public DynamicsFactory(Player player) {
        this.player = player;
        this.status = EXIT;
        this.currentTrigger = null;
        this.lastOption = 0;

        Bukkit.getServer().getPluginManager().registerEvents(this, LunikerDynamicWorld.instance);
    }

    /**
     * Handle the users command.
     * @param args Command arguments.
     * @return True if the execution was successful.
     */
    public boolean handleCommand(String[] args) {
        if(status.equals(EXIT)) {
            status = START;
            player.sendMessage(color("-- &6&lDynamics Factory&r --\n") +
                getCommands()
            );
        } else {
            player.sendMessage(color("&cYou are already in a Dynamics Factory. To exit type '>exit'&r"));
        }
        return true;
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if(!event.getPlayer().equals(player) || status.equals(EXIT)) {
            return;
        }
        event.setCancelled(true);

        if(event.getMessage().trim().charAt(0) == '>') {
            switch (event.getMessage().toLowerCase().trim()) {
                case ">exit":
                    cleanup(EXIT);
                    return;
                case ">restart":
                    cleanup(START);
                    return;
                case ">commands":
                    player.sendMessage(getCommands());
                    return;
                case ">new":
                    createNew();
                    return;
                case ">delete":
                    player.sendMessage("This command is not implemented yet...");
                    return;
                default:
                    player.sendMessage(color("&cUnknown command!&r"));
                    return;
            }
        }

        switch(status) {
            case NAME:
                setName(event.getMessage());
                return;
            case TRIGGER:
            case TRIGGER_OPTIONS:
                createTrigger(event.getMessage());
                return;
        }
    }

    private void cleanup(DynamicsFactoryStatus status) {
        this.status = status;
        /*
         * Stop the triggers.
         */
        currentTrigger = null;

        if(status.equals(EXIT)) {
            player.sendMessage(color("&aYou have exited the Dynamcis Factory&r"));
        } else {
            player.sendMessage(color("&aYou progress has been reset&r"));
        }
    }

    private void createNew() {
        if(!status.equals(START)) {
            player.sendMessage(color("&cYou cannot use this command right now. If you want to start over use the " +
                    "command: '>restart'&r"));
            return;
        }
        player.sendMessage(color("&7Step 1:&r &oSend the desired name for your new Dynamics Object&r"));
        status = NAME;
    }

    private void setName(String name) {
        if(!status.equals(NAME)) {
            return;
        }

        if(!Pattern.matches("[\\w_-]+", name)) {
            player.sendMessage(color("&cThe name of your Dynamic Object may only contain the following characters: " +
                    "a-z, A-Z, 0-9, - and _ (whitespace not included)&r"));
            return;
        }
        currentName = name;
        player.sendMessage(color("&aCreated new Dynamic Object with name: '" + name + "'.&r\n" +
                "&7Step 2:&r &oNow select one of the actions below by sending their associated number.&r\n") +
            getActions());
        status = ACTION;
    }

    private void createTrigger(String msg) {
        int n;
        if(status.equals(TRIGGER)) {
            try {
                n = Integer.parseInt(msg);
            } catch (NumberFormatException e) {
                player.sendMessage(color("&cYour input should only be a number.&r"));
                return;
            }

            player.sendMessage("You got here...");

            status = TRIGGER_OPTIONS;
            lastOption = n;
            switch(n) {
                case 1:
                    createTrigger(msg);
                    return;
                default:
                    player.sendMessage(color("&cThis trigger does not exist, choose a different one.&r"));
                    status = TRIGGER;
                    return;
            }
        } else if(status.equals(TRIGGER_OPTIONS)) {
            switch(lastOption) {
                case 1:
                    currentTrigger = new CommandTrigger(currentName, currentAciton);
                    break;
            }
            player.sendMessage(color("&aCreated a trigger.&r\n" +
                    "&7Step 3:&r &oNow select one of the actions below by sending their associated number.&r\n" +
                    getActions()));
        }
    }

    private static String getCommands() {
        return color("You can use the following commands:\n" +
                "&7>exit&r &oExit the Dynamics Factory (your progress will be lost)&r\n" +
                "&7>restart&r &oReset your currentTrigger progress&r\n" +
                "&7>commands&r &oPrint this command list&r\n" +
                "&7>new&r &oCreate a new Dynamic Object&r\n" +
                "&7>delete&r &oDelete an existing Dynamic Object&r");
    }

    private static String getTriggers() {
         return color("&71. command&r &oPlayer used /dt <name> command&r\n" +
                 "&72. Interaction&r &oPlayer interacts with a block or air.&r\n" +
                 "&73. Timer&r &oThe timer runs out.&r");
    }

    private static String getSpecialActions() {
        return color("&71. Single action&r &oExecute only one action&r\n" +
                "&72. Group action&r &oExecute multiple actions at once&r\n" +
                "&73. Iterator action&r &oExecute the next action every time it's triggered&r\n" +
                "&74. Random action&r &oExecute a random action every time it's triggered&r");
    }

    private static String getActions() {
        return color("&71. Broadcast&r &oBroadcast a message&r\n" +
                "&72. Player effect&r &oInfluence the player&r\n" +
                "&73. Replace blocks&r &oReplace&r");
    }

    private static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
