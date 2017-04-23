package net.luniker.lunikerdynamicworld.Actions;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;


public class RandomAction extends CollectionAction {
    private ArrayList<Action> actions = new ArrayList<>();
    private Stack<Action> history = new Stack<>();

    public RandomAction(String name) {
        this(name, new Action[] {});
    }

    public RandomAction(String name, Action[] actions) {
        super(name);
        Collections.addAll(this.actions, actions);
    }

    @Override
    public boolean execute(Player player) {
        if(actions.size() == 0) {
            return false;
        }

        Action a = actions.get(new Random().nextInt(actions.size()));
        if(!a.execute(player)) {
            return false;
        }

        history.add(a);
        return true;
    }

    @Override
    public boolean undo() {
        return history.size() != 0 && history.pop().undo();
    }

    @Override
    public boolean reset() {
        boolean result = true;
        while(history.size() > 0) {
            result = history.pop().reset() && result;
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
