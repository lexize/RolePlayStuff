package org.lexize.roleplaystuff.commands;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.lexize.roleplaystuff.RolePlayStuff;

public final class MeCommand extends PrintCommand {
    public MeCommand(RolePlayStuff parent) {
        super(parent);
    }

    @Override
    protected BaseComponent[] getComponent(Player sourcePlayer, String[] arguments) {
        String action = String.join(" ", arguments);
        if (action.isEmpty() || action.isBlank()) return null;
        return RolePlayStuff.Utils.fromMinimessage(parent.getTranslation().get("me"),
                Placeholder.unparsed("player_name", sourcePlayer.getName()),
                Placeholder.unparsed("action", action)
                );
    }
}
