package net.luniker.lunikerdynamicworld.Actions;


import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GroupAction extends CollectionAction {
    private ArrayList<Action> actions = new ArrayList<>();

    GroupAction(String name) {
        super(name);
    }

    @Override
    public boolean execute(Player player) {
        boolean result = true;
        for(Action a : actions) {
            result = a.execute(player) & result;
        }
        return result;
    }

    @Override
    public boolean undo() {
        boolean result = true;
        for(Action a : actions) {
            result = a.undo() && result;
        }
        return result;
    }

    @Override
    public boolean reset() {
        boolean result = true;
        for(Action a : actions) {
            result = a.reset() && result;
        }
        return result;
    }

    @Override
    public Action[] getActions() {
        return (Action[]) actions.toArray();
    }

    @Override
    public boolean add(Action action) {
        return actions.add(action);
    }

    @Override
    public boolean remove(Action action) {
        return actions.remove(action);
    }

    @Override
    public boolean contains(Action action) {
        return actions.contains(action);
    }

    @Override
    public String toString() {
        return String.format("RandomAction: %s", name);
    }
}
