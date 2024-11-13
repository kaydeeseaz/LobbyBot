package com.kaydeesea.bot.listeners;

import com.kaydeesea.bot.Bot;
import com.kaydeesea.bot.objects.Game;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        if(event.getChannel().getType() == ChannelType.TEXT && event.getChannel().asTextChannel().getParentCategory() == Bot.gamesCategory) {
            if(event.getMember().getId().equalsIgnoreCase(Bot.botID)) {
                System.out.println("Message from BOT:");
                String gameN = "";
                ArrayList<String> team1 = new ArrayList<>();
                ArrayList<String> team2 = new ArrayList<>();
                String map = "";
                for (MessageEmbed embed : event.getMessage().getEmbeds()) {
                    System.out.println(embed.getTitle()+"_"+embed.getDescription());
                    if(embed.getTitle() != null && embed.getTitle().contains("Map Information")) {
                        map = embed.getDescription().split("\\*\\*Name\\*\\*: ")[1].split(" ")[0].split("\n")[0];
                    }
                    if(embed.getTitle() != null && embed.getTitle().contains("Game ")) gameN = embed.getTitle().split("Game ")[1];
                    for (MessageEmbed.Field field : embed.getFields()) {
                        System.out.println(field.getName()+"_"+field.getValue());
                        if(field.getName() == null) continue;
                        if(field.getName().equalsIgnoreCase("Team 1")) {
                            String[] ts = field.getValue().split("\n");
                            for (String t : ts) {
                                String ID = t.replaceAll("\\D", "");
                                Member m = event.getGuild().getMemberById(ID);
                                if(m == null) continue;
                                String ign = getIgn(m);
                                team1.add(ign);
                            }
                        }
                        if(field.getName().equalsIgnoreCase("Team 2")) {
                            String[] ts = field.getValue().split("\n");
                            for (String t : ts) {
                                String ID = t.replaceAll("\\D", "");
                                Member m = event.getGuild().getMemberById(ID);
                                if(m == null) continue;
                                String ign = getIgn(m);
                                team2.add(ign);
                            }
                        }
                    }

                }
                if(!team1.isEmpty() && !team2.isEmpty()) {
                    System.out.println("Game number: " + gameN);
                    System.out.println("Team1: " + team1);
                    System.out.println("Team2: " + team2);
                    System.out.println("Map: " + map);

                    Game game = new Game(team1, team2, map);
                    game.startGame();
                }
            }
        }
    }

    private String getIgn(Member m) {
        if(m.getEffectiveName().contains("] ")) {
            if(m.getEffectiveName().contains(" | ")) {
                return m.getEffectiveName().split("] ")[1].split(" \\|")[0];
            } else {
                return m.getEffectiveName().split("] ")[1];
            }
        } else {
            if(m.getEffectiveName().contains(" | ")) {
                return m.getEffectiveName().split(" \\|")[0];
            } else {
                return m.getEffectiveName();
            }
        }
    }
}
