package net.sacredlabyrinth.phaed.simpleclans.threads;

import java.util.UUID;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.events.PlayerLoadSucessEvent;
import org.bukkit.entity.Player;

/**
 *
 * @author NeT32
 */
public class ThreadReloadOnePlayer extends Thread {

    final Player player;
    final UUID playerUniqueId;

    public ThreadReloadOnePlayer(Player player, UUID playerUniqueId)
    {
        this.player = player;
        this.playerUniqueId = playerUniqueId;
    }

    @Override
    public void run()
    {
        final ClanPlayer cpAsync = SimpleClans.getInstance().getStorageManager().retrieveOneClanPlayer(this.playerUniqueId);
        if (cpAsync != null)
        {
            final Clan clanAsync = SimpleClans.getInstance().getStorageManager().retrieveOneClan(cpAsync.getAsyncTag());
            SimpleClans.getInstance().getServer().getScheduler().runTask(SimpleClans.getInstance(), new Runnable() {
                @Override
                public void run()
                {
                    Player pOn = SimpleClans.getInstance().getServer().getPlayer(playerUniqueId);
                    if (pOn != null)
                    {
                        if (cpAsync.getAsyncTag() != null)
                        {
                            return;
                        }
                        Clan clan = SimpleClans.getInstance().getClanManager().getClan(cpAsync.getAsyncTag());
                        if (clan != null)
                        {
                            clan.setFlags(clanAsync.getFlags());
                            clan.setVerified(clanAsync.isVerified());
                            clan.setFriendlyFire(clanAsync.isFriendlyFire());
                            clan.setTag(clanAsync.getTag());
                            clan.setColorTag(clanAsync.getColorTag());
                            clan.setName(clanAsync.getName());
                            clan.setPackedAllies(clanAsync.getPackedAllies());
                            clan.setPackedRivals(clanAsync.getPackedRivals());
                            clan.setPackedBb(clanAsync.getPackedBb());
                            clan.setCapeUrl(clanAsync.getCapeUrl());
                            clan.setFounded(clanAsync.getFounded());
                            clan.setLastUsed(clanAsync.getLastUsed());
                            clan.setBalance(clanAsync.getBalance());
                            cpAsync.setClan(clan);
                        }
                        else
                        {
                            SimpleClans.getInstance().getClanManager().importClan(clanAsync);
                            clanAsync.validateWarring();
                            Clan newclan = SimpleClans.getInstance().getClanManager().getClan(clanAsync.getTag());
                            if (newclan != null)
                            {
                                cpAsync.setClan(newclan);
                            }
                        }
                        SimpleClans.getInstance().getClanManager().deleteClanPlayerFromMemory(player.getUniqueId());
                        Clan tm = cpAsync.getClan();
                        if (tm != null)
                        {
                            tm.importMember(cpAsync);
                        }
                        SimpleClans.getInstance().getClanManager().importClanPlayer(cpAsync);
                        SimpleClans.log("[SimpleClans] ClanPlayer Reloaded: " + player.getName() + ", UUID: " + player.getUniqueId().toString());
                        SimpleClans.getInstance().getServer().getPluginManager().callEvent(new PlayerLoadSucessEvent(pOn));
                    }
                }
            });
        }
    }
}
