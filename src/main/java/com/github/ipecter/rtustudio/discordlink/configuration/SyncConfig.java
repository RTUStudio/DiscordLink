package com.github.ipecter.rtustudio.discordlink.configuration;

import com.github.ipecter.rtustudio.discordlink.DiscordLink;
import kr.rtuserver.framework.bukkit.api.configuration.RSConfiguration;
import lombok.Getter;

@Getter
public class SyncConfig extends RSConfiguration<DiscordLink> {

    private Type type = Type.DISPLAY_NAME;
    private String whiteSpace = " ";

    public SyncConfig(DiscordLink plugin) {
        super(plugin, "Sync.yml", null);
        setup(this);
    }

    private void init() {
        String typeStr = getString("type", type.toString(), """
                Nick provider type. Available options: DISPLAY_NAME, CMI, ESSENTIALS
                닉네임 제공자 타입. 지원 가능한 포멧: DISPLAY_NAME, CMI, ESSENTIALS""");
        try {
            type = Type.valueOf(typeStr.toUpperCase());
        } catch (Exception e) {
            type = Type.DISPLAY_NAME;
        }
        whiteSpace = getString("whiteSpace", whiteSpace, """
                White space for nickname
                닉네임의 공백 대체 문자""");
    }

    public enum Type {
        DISPLAY_NAME, CMI, ESSENTIALS
    }
}
