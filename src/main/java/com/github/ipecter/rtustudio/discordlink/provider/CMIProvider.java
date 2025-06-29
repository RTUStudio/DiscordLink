package com.github.ipecter.rtustudio.discordlink.provider;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.CMIUser;
import kr.rtuserver.framework.bukkit.api.RSPlugin;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CMIProvider implements NickProvider {

    private final RSPlugin plugin;
    private final CMI cmi = CMI.getInstance();

    @Override
    public String getName(UUID uuid) {
        CMIUser user = cmi.getPlayerManager().getUser(uuid);
        if (user == null) return null;
        return user.getNickName();
    }

    @Override
    public boolean setName(UUID uuid, String name) {
        CMIUser user = cmi.getPlayerManager().getUser(uuid);
        if (user == null) return false;
        user.setNickName(name, true);
        return true;
    }
}
