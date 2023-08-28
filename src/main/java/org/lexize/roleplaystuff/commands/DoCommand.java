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
        return RolePlayStuff.Utils.fromMinimessage(parent.getTranslation().get("do"),
                Placeholder.unparsed("player_name", sourcePlayer.getName()),
                Placeholder.unparsed("action", String.join(" ", arguments))
                );
    }
}
