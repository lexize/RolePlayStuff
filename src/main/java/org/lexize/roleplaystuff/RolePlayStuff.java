package org.lexize.roleplaystuff;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.BaseComponentSerializer;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.lexize.roleplaystuff.commands.DiceCommand;
import org.lexize.roleplaystuff.commands.DoCommand;
import org.lexize.roleplaystuff.commands.MeCommand;
import org.lexize.roleplaystuff.commands.TryCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public final class RolePlayStuff extends JavaPlugin {
    public static final Random RANDOM_INSTANCE = new Random();

    private int commandRange;
    private Translation translation;

    @Override
    public void onEnable() {
        try {
            // Plugin startup logic
            saveDefaultConfig();
            commandRange = getConfig().getInt("rng", 25);
            translation = new Translation(getDataFolder().toPath().resolve("translation.yml").toFile());
            getCommand("me").setExecutor(new MeCommand(this));
            getCommand("do").setExecutor(new DoCommand(this));
            getCommand("try").setExecutor(new TryCommand(this));
            getCommand("dice").setExecutor(new DiceCommand(this));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public int getCommandRange() {
        return commandRange;
    }

    public Translation getTranslation() {
        return translation;
    }

    public static class Utils {
        public static void writeFile(File f, byte[] data) throws IOException {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(data);
            fos.close();
        }

        public static BaseComponent[] fromMinimessage(String source, TagResolver... resolvers) {
            Component c = MiniMessage.miniMessage().deserialize(source, resolvers);
            String jsonString = GsonComponentSerializer.gson().serialize(c);
            return ComponentSerializer.parse(jsonString);
        }
    }
}
