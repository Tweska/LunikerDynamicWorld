package net.luniker.lunikerdynamicworld.Actions;

import org.bukkit.entity.Player;

public abstract class Action {
    String name;

    Action(String name) {
        this.name = name;
    }

    public abstract boolean execute(Player player);
    public abstract boolean undo();

    public boolean reset() {
        return undo();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("Action: %s", name);
    }
}
