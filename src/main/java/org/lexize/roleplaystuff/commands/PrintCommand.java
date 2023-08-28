package org.lexize.roleplaystuff.commands;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.lexize.roleplaystuff.RolePlayStuff;

import java.util.Collection;

abstract class PrintCommand implements CommandExecutor {
    protected final RolePlayStuff parent;

    public PrintCommand(RolePlayStuff parent) {
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p) {
            BaseComponent[] component = getComponent(p, strings);
            if (component == null) return false;
            int range = getRange();
            if (range <= 0) {
                for (Player pl :
                        p.getWorld().getEntitiesByClass(Player.class)) {
                    pl.spigot().sendMessage(component);
                }
            }
            else {
                Location l = p.getLocation();
                Collection<Entity> nearbyEntities = p.getWorld().getNearbyEntities(l, range, range, range);
                for (Entity e :
                        nearbyEntities) {
                    if (e.getLocation().distance(p.getLocation()) <= range && e instanceof Player pl) pl.spigot().sendMessage(component);
                }
            }
        }
        return true;
    }

    protected abstract BaseComponent[] getComponent(Player sourcePlayer, String[] arguments);
    protected int getRange() {
        return parent.getCommandRange();
    }
}
