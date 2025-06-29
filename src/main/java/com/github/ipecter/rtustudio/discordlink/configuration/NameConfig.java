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
        String typeStr = getString("type", "DISPLAY_NAME", """
                Nick provider type. Available options: DISPLAY_NAME, CMI, ESSENTIALS
                닉네임 제공자 타입. 지원 가능한 포멧: DISPLAY_NAME, CMI, ESSENTIALS""");
        try {
            type = Type.valueOf(typeStr.toUpperCase());
        } catch (Exception e) {
            type = Type.DISPLAY_NAME;
        }
    }

    public enum Type {
        DISPLAY_NAME, CMI, ESSENTIALS
    }
}
