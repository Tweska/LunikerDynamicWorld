package net.luniker.lunikerdynamicworld.Commands;

import net.luniker.lunikerdynamicworld.DynamicsFactory;
import net.luniker.lunikerdynamicworld.LunikerDynamicWorld;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class LdwFactoryCommand extends CommandModule {
    public LdwFactoryCommand() {
        super("ldwfactory", 1, 2, true);
    }

    /**
     * Find the players current DynamicsFactory and let it handle the command.
     * @param args Command arguments.
     * @return True if the execution was successful.
     */
    @Override
    public boolean run(CommandSender sender, String[] args) {
        return getFactory((Player) sender).handleCommand(args);
    }

    /**
     * Get the players current DynamicsFactory.
     * @return The DynamicsFactory.
     */
    private DynamicsFactory getFactory(Player player) {
        if(LunikerDynamicWorld.dynamicsFactories.containsKey(player)) {
            return LunikerDynamicWorld.dynamicsFactories.get(player);
        }

        DynamicsFactory factory = new DynamicsFactory(player);
        LunikerDynamicWorld.dynamicsFactories.put(player, factory);
        return factory;
    }
}
