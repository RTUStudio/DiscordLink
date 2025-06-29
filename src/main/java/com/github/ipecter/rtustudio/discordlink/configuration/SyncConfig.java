package com.github.ipecter.rtustudio.discordlink.configuration;

import kr.rtuserver.framework.bukkit.api.configuration.RSConfiguration;
import com.github.ipecter.rtustudio.discordlink.DiscordLink;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SyncConfig extends RSConfiguration<DiscordLink> {

    private final Map<Long, String> roles = new HashMap<>();

    public SyncConfig(DiscordLink plugin) {
        super(plugin, "Sync.yml", null);
        setup(this);
    }

    private void init() {
        roles.clear();
        for (String key : getConfig().getKeys(false)) {
            try {
                long roleId = Long.parseLong(key);
                roles.put(roleId, getConfig().getString(key));
            } catch (NumberFormatException ignored) {
            }
        }
    }
}
