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
        if (arguments.length == 0) return null;
        int sides_count;
        try {
            sides_count  = Integer.parseInt(arguments[0]);
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
