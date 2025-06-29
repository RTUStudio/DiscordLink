package com.github.ipecter.rtustudio.discordlink.schedule;

import com.github.ipecter.rtustudio.discordlink.provider.NickProvider;
import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Member;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Role;
import kr.rtuserver.framework.bukkit.api.platform.MinecraftVersion;
import com.github.ipecter.rtustudio.discordlink.DiscordLink;
import com.github.ipecter.rtustudio.discordlink.configuration.SyncConfig;
import net.kyori.adventure.text.Component;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.node.Node;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SyncScheduler implements Runnable {

    private final DiscordLink plugin;

    private final Player player;

    private final Member member;

    private final SyncConfig syncConfig;
    private final NickProvider nickProvider;

    public SyncScheduler(DiscordLink plugin, Player player) {
        this.plugin = plugin;
        this.syncConfig = plugin.getSyncConfig();
        this.nickProvider = plugin.getNickProvider();
        this.player = player;
        UUID uuid = player.getUniqueId();
        DiscordSRV discordSRV = DiscordSRV.getPlugin();
        String id = discordSRV.getAccountLinkManager().getDiscordId(uuid);
        this.member = discordSRV.getMainGuild().getMemberById(id);
    }

    @Override
    public void run() {
        List<String> roles = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        String nickname = member.getEffectiveName().replace(" ", syncConfig.getWhiteSpace());
        if (!nickname.equals(nickProvider.getName(uuid))) {
            nickProvider.setName(uuid, nickname);
        }
    }
}
