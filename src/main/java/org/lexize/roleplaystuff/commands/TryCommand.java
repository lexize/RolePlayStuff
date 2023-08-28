package org.lexize.roleplaystuff.commands;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.lexize.roleplaystuff.RolePlayStuff;

public final class TryCommand extends PrintCommand {
    public TryCommand(RolePlayStuff parent) {
        super(parent);
    }

    @Override
    protected BaseComponent[] getComponent(Player sourcePlayer, String[] arguments) {
        return RolePlayStuff.Utils.fromMinimessage(parent.getTranslation().get(
                RolePlayStuff.RANDOM_INSTANCE.nextBoolean() ? "try_successful": "try_unsuccessful"),
                Placeholder.unparsed("player_name", sourcePlayer.getName()),
                Placeholder.unparsed("action", String.join(" ", arguments))
        );
    }
}
