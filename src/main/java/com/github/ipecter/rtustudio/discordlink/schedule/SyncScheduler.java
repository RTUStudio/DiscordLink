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

    private final User user;
    private final Member member;

    private final SyncConfig syncConfig;
    private final NickProvider nickProvider;
    private final UserManager userManager;

    public SyncScheduler(DiscordLink plugin, Player player) {
        this.plugin = plugin;
        this.syncConfig = plugin.getSyncConfig();
        this.nickProvider = plugin.getNickProvider();
        this.userManager = plugin.getLuckPerms().getUserManager();
        this.player = player;
        UUID uuid = player.getUniqueId();
        this.user = userManager.getUser(uuid);
        DiscordSRV discordSRV = DiscordSRV.getPlugin();
        String id = discordSRV.getAccountLinkManager().getDiscordId(uuid);
        this.member = discordSRV.getMainGuild().getMemberById(id);
    }

    @Override
    public void run() {
        List<String> roles = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        if (!member.getEffectiveName().equals(nickProvider.getName(uuid))) {
            nickProvider.setName(uuid, member.getEffectiveName());
        }
        for (Role role : member.getRoles()) roles.add(role.getId());
        boolean needSave = false;
        for (Long id : syncConfig.getRoles().keySet()) {
            String group = syncConfig.getRoles().get(id);
            if (roles.contains(group)) {
                if (!hasPermission(group)) {
                    add(group);
                    needSave = true;
                }
            } else {
                if (hasPermission(group)) {
                    remove(group);
                    needSave = true;
                }
            }
        }
        if (needSave) save();
    }

    private boolean hasPermission(String group) {
        return player.hasPermission("group." + group);
    }

    private void add(String group) {
        user.data().add(Node.builder("group." + group).build());
    }

    private void remove(String group) {
        user.data().remove(Node.builder("group." + group).build());
    }

    private void save() {
        userManager.saveUser(user);
    }

}
