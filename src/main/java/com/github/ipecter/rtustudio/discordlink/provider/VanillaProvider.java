package com.github.ipecter.rtustudio.discordlink.provider;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import kr.rtuserver.framework.bukkit.api.RSPlugin;
import kr.rtuserver.framework.bukkit.api.platform.MinecraftVersion;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@RequiredArgsConstructor
public class VanillaProvider implements NickProvider{

    private final RSPlugin plugin;

    @Override
    public String getName(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) return null;
        return player.getDisplayName();
    }

    @Override
    public boolean setName(UUID uuid, String name) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) return false;
        if (MinecraftVersion.isPaper()) {
            player.displayName(Component.text(name));
        } else player.setDisplayName(name);
        return true;
    }
}
