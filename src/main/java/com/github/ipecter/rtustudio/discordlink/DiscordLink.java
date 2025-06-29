package com.github.ipecter.rtustudio.discordlink;

import com.github.ipecter.rtustudio.discordlink.commands.MainCommand;
import com.github.ipecter.rtustudio.discordlink.configuration.SyncConfig;
import com.github.ipecter.rtustudio.discordlink.listeners.PlayerJoinQuit;
import com.github.ipecter.rtustudio.discordlink.player.LinkPlayerManager;
import com.github.ipecter.rtustudio.discordlink.provider.CMIProvider;
import com.github.ipecter.rtustudio.discordlink.provider.EssentialProvider;
import com.github.ipecter.rtustudio.discordlink.provider.NickProvider;
import com.github.ipecter.rtustudio.discordlink.provider.VanillaProvider;
import kr.rtuserver.framework.bukkit.api.RSPlugin;
import lombok.Getter;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class DiscordLink extends RSPlugin {

    @Getter
    private static DiscordLink instance;
    @Getter
    private SyncConfig syncConfig;
    @Getter
    private LinkPlayerManager linkPlayerManager;
    @Getter
    private NickProvider nickProvider;

    @Override
    public void load() {
        instance = this;
    }

    @Override
    public void enable() {
        syncConfig = new SyncConfig(this);

        reloadProvider();

        linkPlayerManager = new LinkPlayerManager(this);

        registerEvent(new PlayerJoinQuit(this));

        registerCommand(new MainCommand(this), true);
    }

    public void reloadProvider() {
        switch (syncConfig.getType()) {
            case ESSENTIALS -> nickProvider = new EssentialProvider(this);
            case CMI -> nickProvider = new CMIProvider(this);
            case DISPLAY_NAME -> nickProvider = new VanillaProvider(this);
        }

        console("Nick Provider: " + syncConfig.getType());
        console("닉네임 제공자: " + syncConfig.getType());
    }
}
