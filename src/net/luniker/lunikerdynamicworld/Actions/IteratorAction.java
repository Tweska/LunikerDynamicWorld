package net.luniker.lunikerdynamicworld.Actions;

import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;


public class IteratorAction extends CollectionAction {
    private PriorityQueue<Action> actions = new PriorityQueue<>();
    private Stack<Action> history = new Stack<>();

    public IteratorAction(String name) {
        this(name, new Action[] {});
    }

    public IteratorAction(String name, Action[] actions) {
        super(name);
        Collections.addAll(this.actions, actions);
    }

    @Override
    public boolean execute(Player player) {
        if(actions.size() == 0) {
            return false;
        }

        Action a = actions.remove();
        actions.add(a);
        history.add(a);
        return a.execute(player);
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
        return String.format("IteratorAction: %s", name);
    }
}
