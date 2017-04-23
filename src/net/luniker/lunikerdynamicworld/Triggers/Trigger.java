package net.luniker.lunikerdynamicworld.Triggers;

import net.luniker.lunikerdynamicworld.Actions.Action;

public abstract class Trigger {
    String name;
    Action action;

    Trigger(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }

    public String toString() {
        return String.format(   "Trigger: %s\n" +
                                "  ->%s",
                                name,
                                action.toString());
    }
}
