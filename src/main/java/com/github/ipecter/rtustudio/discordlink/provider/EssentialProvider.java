package com.github.ipecter.rtustudio.discordlink.provider;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import kr.rtuserver.framework.bukkit.api.RSPlugin;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class EssentialProvider implements NickProvider{

    private final RSPlugin plugin;
    private final Essentials essentials = Essentials.getPlugin(Essentials.class);

    @Override
    public String getName(UUID uuid) {
        User user = essentials.getUser(uuid);
        if (user == null) return null;
        return user.getNickname();
    }

    @Override
    public boolean setName(UUID uuid, String name) {
        User user = essentials.getUser(uuid);
        if (user == null) return false;
        user.setNickname(name);
        return true;
    }
}
