package org.lexize.roleplaystuff.commands;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.lexize.roleplaystuff.RolePlayStuff;

public class DiceCommand extends PrintCommand {
    public DiceCommand(RolePlayStuff parent) {
        super(parent);
    }

    @Override
    protected BaseComponent[] getComponent(Player sourcePlayer, String[] arguments) {
        int sides_count = 6;
        if (arguments.length > 0) try {
            // There is a lot of dices, max I've found is 256, and min is 1, but it is a bit pointless, so min value is 2
            sides_count = Math.min(Math.max(2, Integer.parseInt(arguments[0])), 256);
        }
        catch (NumberFormatException e) {
            return null;
        }
        int result = RolePlayStuff.RANDOM_INSTANCE.nextInt(sides_count)+1;
        return RolePlayStuff.Utils.fromMinimessage(parent.getTranslation().get("dice"),
                Placeholder.unparsed("player_name", sourcePlayer.getName()),
                Placeholder.unparsed("result", Integer.toString(result)),
                Placeholder.unparsed("sides_count", Integer.toString(sides_count))
        );
    }
}
