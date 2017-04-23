package net.luniker.lunikerdynamicworld;

import net.luniker.lunikerdynamicworld.Actions.Action;
import net.luniker.lunikerdynamicworld.Triggers.Trigger;


public class DynamicObject {
    String name;
    Trigger trigger;
    Action action;

    public DynamicObject(String name) {
        this(name, null, null);
    }

    public DynamicObject(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public boolean isComplete() {
        if(trigger == null || action == null) {
            return false;
        }
        return true;
    }
}
