package com.github.ipecter.rtustudio.discordlink.configuration;

import com.github.ipecter.rtustudio.discordlink.DiscordLink;
import kr.rtuserver.framework.bukkit.api.configuration.RSConfiguration;
import lombok.Getter;

@Getter
public class NameConfig extends RSConfiguration<DiscordLink> {

    private Type type = Type.DISPLAY_NAME;

    public NameConfig(DiscordLink plugin) {
        super(plugin, "Name.yml", null);
        setup(this);
    }

    private void init() {
        try {
            type = Type.valueOf(getString("type", "DISPLAY_NAME").toUpperCase());
        } catch (Exception e) {
            type = Type.DISPLAY_NAME;
        }
    }

    public enum Type {
        DISPLAY_NAME, CMI, ESSENTIALS
    }
}
