package org.lexize.roleplaystuff.commands;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.lexize.roleplaystuff.RolePlayStuff;

public final class DoCommand extends PrintCommand {
    public DoCommand(RolePlayStuff parent) {
        super(parent);
    }

    @Override
    protected BaseComponent[] getComponent(Player sourcePlayer, String[] arguments) {
        String action = String.join(" ", arguments);
        if (action.isEmpty() || action.isBlank()) return null;
        char c =  action.charAt(0);
        // Making first character uppercase to make it look better in chat
        if (Character.isLowerCase(c)) {
            action = Character.toString(Character.toUpperCase(action.charAt(0))).concat(
                    action.substring(1)
            );
        }
        return RolePlayStuff.Utils.fromMinimessage(parent.getTranslation().get("do"),
                Placeholder.unparsed("player_name", sourcePlayer.getName()),
                Placeholder.unparsed("action", action)
        );
    }
}
