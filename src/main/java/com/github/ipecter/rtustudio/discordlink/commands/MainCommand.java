package com.github.ipecter.rtustudio.discordlink.commands;

import com.github.ipecter.rtustudio.discordlink.configuration.NameConfig;
import kr.rtuserver.framework.bukkit.api.command.RSCommand;
import kr.rtuserver.framework.bukkit.api.command.RSCommandData;
import com.github.ipecter.rtustudio.discordlink.DiscordLink;
import com.github.ipecter.rtustudio.discordlink.configuration.SyncConfig;
import org.bukkit.permissions.PermissionDefault;

public class MainCommand extends RSCommand<DiscordLink> {

    private final SyncConfig syncConfig;
    private final NameConfig nameConfig;

    public MainCommand(DiscordLink plugin) {
        super(plugin, "discordlink", PermissionDefault.OP);
        this.syncConfig = plugin.getSyncConfig();
        this.nameConfig = plugin.getNameConfig();
    }

    @Override
    public void reload(RSCommandData data) {
        syncConfig.reload();
        nameConfig.reload();
    }
}
