package com.kaydeesea.bot.listeners;

import com.kaydeesea.bot.Bot;
import com.kaydeesea.bot.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class ReadyListener extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);
        Guild g = event.getJDA().getGuildById(Main.config.getString("guildID"));
        if(g == null) {
            System.out.println("The guildID has not been set. Shutting down bot...");
            Bukkit.getServer().getPluginManager().disablePlugin(Main.instance);
            return;
        }
        Bot.guild = g;
        Bot.gamesCategory = g.getCategoryById(Main.config.getString("games-category"));
        Bot.botID = Main.config.getString("botID");
    }
}
