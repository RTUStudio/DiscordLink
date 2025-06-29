package com.github.ipecter.rtustudio.discordlink;

import com.github.ipecter.rtustudio.discordlink.provider.CMIProvider;
import com.github.ipecter.rtustudio.discordlink.provider.EssentialProvider;
import com.github.ipecter.rtustudio.discordlink.provider.NickProvider;
import com.github.ipecter.rtustudio.discordlink.provider.VanillaProvider;
import kr.rtuserver.framework.bukkit.api.RSPlugin;
import com.github.ipecter.rtustudio.discordlink.commands.MainCommand;
import com.github.ipecter.rtustudio.discordlink.configuration.NameConfig;
import com.github.ipecter.rtustudio.discordlink.configuration.SyncConfig;
import com.github.ipecter.rtustudio.discordlink.listeners.PlayerJoinQuit;
import com.github.ipecter.rtustudio.discordlink.player.LinkPlayerManager;
import lombok.Getter;
import lombok.Setter;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class DiscordLink extends RSPlugin {

    @Getter
    private static DiscordLink instance;
    @Getter
    private SyncConfig syncConfig;
    @Getter
    private NameConfig nameConfig;
    @Getter
    private LinkPlayerManager linkPlayerManager;
    @Getter
    private LuckPerms luckPerms;
    @Getter
    private NickProvider nickProvider;

    @Override
    public void load() {
        instance = this;
    }

    @Override
    public void enable() {
        syncConfig = new SyncConfig(this);
        nameConfig = new NameConfig(this);

        reloadProvider();

        linkPlayerManager = new LinkPlayerManager(this);

        registerEvent(new PlayerJoinQuit(this));

        registerCommand(new MainCommand(this), true);

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServer().getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) luckPerms = provider.getProvider();
    }

    public void reloadProvider() {
        switch (nameConfig.getType()) {
            case ESSENTIALS -> nickProvider = new EssentialProvider(this);
            case CMI -> nickProvider = new CMIProvider(this);
            case DISPLAY_NAME -> nickProvider = new VanillaProvider(this);
        }

        console("Nick Provider: " + nameConfig.getType());
        console("닉네임 제공자: " + nameConfig.getType());
    }
}
