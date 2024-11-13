package com.kaydeesea.bot;

import com.kaydeesea.bot.listeners.MessageListener;
import com.kaydeesea.bot.listeners.ReadyListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Bot {
    public static Guild guild;
    public static Category gamesCategory;
    public static String botID;
    public static void load() {
        JDABuilder jda = JDABuilder.createDefault(Main.config.getString("token"));
        jda.enableIntents(
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_VOICE_STATES
        );
        jda.setStatus(OnlineStatus.valueOf(Main.config.getString("status").toUpperCase()));
        jda.setChunkingFilter(ChunkingFilter.ALL);
        jda.setMemberCachePolicy(MemberCachePolicy.ALL);
        jda.addEventListeners(
                new ReadyListener(),
                new MessageListener()
        );
        jda.build();
    }
}
