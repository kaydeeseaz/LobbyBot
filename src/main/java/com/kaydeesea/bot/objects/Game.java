package com.kaydeesea.bot.objects;

import com.andrei1058.bedwars.proxy.api.CachedArena;
import com.andrei1058.bedwars.proxy.arenamanager.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {
    private final ArrayList<String> team1;
    private final ArrayList<String> team2;
    private final String map;

    public Game(ArrayList<String> team1, ArrayList<String> team2, String map) {
        this.team1 = team1;
        this.team2 = team2;
        this.map = map.toLowerCase();
    }

    public void startGame() {
        ArrayList<Player> players = new ArrayList<>();
        for (String s : team1) {
            Player player = Bukkit.getPlayer(s);
            if(player != null) players.add(player);
        }
        for (String s : team2) {
            Player player = Bukkit.getPlayer(s);
            if(player != null) players.add(player);
        }
        CachedArena arena = null;
        for (CachedArena cachedArena : ArenaManager.getArenas()) {
            if(cachedArena.getMaxInTeam() == team1.size()) {
                if(cachedArena.getArenaName().toLowerCase().contains(map))
                    arena = cachedArena;
            }
        }
        if(arena != null) {
            for (Player player : players) {
                arena.addPlayer(player, null);
            }
        }
    }
}
