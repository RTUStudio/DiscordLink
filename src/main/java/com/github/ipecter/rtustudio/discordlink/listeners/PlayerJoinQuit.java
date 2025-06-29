package com.github.ipecter.rtustudio.discordlink.listeners;

import com.github.ipecter.rtustudio.discordlink.DiscordLink;
import kr.rtuserver.framework.bukkit.api.listener.RSListener;
import com.github.ipecter.rtustudio.discordlink.player.LinkPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuit extends RSListener<DiscordLink> {

    private final LinkPlayerManager lpm;

    public PlayerJoinQuit(DiscordLink plugin) {
        super(plugin);
        this.lpm = plugin.getLinkPlayerManager();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
       lpm.addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        lpm.removePlayer(e.getPlayer().getUniqueId());
    }

}
