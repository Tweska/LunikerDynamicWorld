package net.luniker.lunikerdynamicworld.Actions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class BroadcastAction extends Action {
    private String message;
    private boolean global;

    public BroadcastAction(String name, String message) {
        this(name, message, true);
    }

    public BroadcastAction(String name, String message, boolean global) {
        super(name);
        this.message = message;
        this.global = global;
    }

    @Override
    public boolean execute(Player player) {
        if(global) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(message);
            }
        } else {
            player.sendMessage(message);
        }
        return true;
    }

    @Override
    public boolean undo() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("BroadcastAction: %s", name);
    }
}
