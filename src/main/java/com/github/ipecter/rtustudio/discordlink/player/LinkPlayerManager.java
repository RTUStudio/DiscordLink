package com.github.ipecter.rtustudio.discordlink.player;

import com.github.ipecter.rtustudio.discordlink.DiscordLink;
import lombok.extern.java.Log;
import org.bukkit.entity.Player;

import java.util.*;

@Log
public class LinkPlayerManager {

    private final DiscordLink plugin;

    private final Map<UUID, LinkPlayer> map = new HashMap<>();


    public LinkPlayerManager(DiscordLink plugin) {
        this.plugin = plugin;
    }

    public List<LinkPlayer> getPlayers() {
        return new ArrayList<>(map.values());
    }

    public boolean contains(UUID uuid) {
        return map.containsKey(uuid);
    }

    public void addPlayer(Player player) {
        try {
            LinkPlayer lp = new LinkPlayer(plugin, player);
            map.put(player.getUniqueId(), lp);
        } catch (NotLinkedException e) {
            log.warning(e.getMessage());
        }
    }

    public void removePlayer(UUID uuid) {
        map.remove(uuid).close();
    }

    public LinkPlayer getPlayer(UUID uuid) {
        return map.get(uuid);
    }

}
