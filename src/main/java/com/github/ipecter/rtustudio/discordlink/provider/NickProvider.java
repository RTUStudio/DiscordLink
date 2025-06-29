package com.github.ipecter.rtustudio.discordlink.provider;

import java.util.UUID;

public interface NickProvider {

    String getName(UUID uuid);

    boolean setName(UUID uuid, String name);

}
