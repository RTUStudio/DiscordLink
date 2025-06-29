package com.github.ipecter.rtustudio.discordlink.player;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Member;
import kr.rtuserver.framework.bukkit.api.core.scheduler.ScheduledTask;
import kr.rtuserver.framework.bukkit.api.scheduler.CraftScheduler;
import com.github.ipecter.rtustudio.discordlink.DiscordLink;
import com.github.ipecter.rtustudio.discordlink.schedule.SyncScheduler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@Getter
@RequiredArgsConstructor
public class LinkPlayer {

    private final DiscordSRV discord = DiscordSRV.getPlugin();

    private final DiscordLink plugin;

    private final Player player;

    private final ScheduledTask scheduler;

    public LinkPlayer(DiscordLink plugin, Player player) throws NotLinkedException {
        this.plugin = plugin;
        this.player = player;
        String id = discord.getAccountLinkManager().getDiscordId(player.getUniqueId());
        Member member = discord.getMainGuild().getMemberById(id);
        if (member == null) throw new NotLinkedException();
        this.player.setDisplayName(member.getEffectiveName());
        this.scheduler = CraftScheduler.runTimerAsync(plugin, new SyncScheduler(plugin, player), 200L, 200L);
    }

    public void close() {
        this.scheduler.cancel();
    }
}
