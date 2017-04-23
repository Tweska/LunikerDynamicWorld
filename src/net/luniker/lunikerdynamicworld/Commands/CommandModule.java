package net.luniker.lunikerdynamicworld.Commands;

import net.luniker.lunikerdynamicworld.LunikerDynamicWorld;
import org.bukkit.command.CommandSender;


public abstract class CommandModule {
    public String label;
    public int minArgs;
    public int maxArgs;
    public boolean playerOnly;

    public CommandModule(String label, int minArgs, int maxArgs) {
        this(label, minArgs, maxArgs, false);
    }

    /**
     * @param label The label of the command.
     * @param minArgs The minimum amount of arguments.
     * @param maxArgs The maximum amount of arguments.
     * @param playerOnly Set to true if only players should be able to use this command.
     */
    public CommandModule(String label, int minArgs, int maxArgs, boolean playerOnly) {
        this.label = label;
        this.minArgs = minArgs;
        this.maxArgs = maxArgs;
        this.playerOnly = playerOnly;

        LunikerDynamicWorld.instance.getCommand(label).setExecutor(new CmdExecutor());
        LunikerDynamicWorld.commands.put(label, this);
    }

    public abstract boolean run(CommandSender sender, String[] args);
}
