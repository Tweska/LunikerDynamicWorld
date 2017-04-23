package net.luniker.lunikerdynamicworld.Actions;


public abstract class CollectionAction extends Action {
    CollectionAction(String name) {
        super(name);
    }

    abstract Action[] getActions();
    abstract boolean add(Action action);
    abstract boolean remove(Action action);
    abstract boolean contains(Action action);

    public String toString() {
        return String.format("CollectionAction: %s", name);
    }
}
