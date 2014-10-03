package net.sacredlabyrinth.phaed.simpleclans.threads;

import java.text.MessageFormat;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.Helper;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 *
 * @author NeT32
 */
public class ThreadEntityDeathEvent extends Thread {

    final ClanPlayer acp;
    final ClanPlayer vcp;

    public ThreadEntityDeathEvent(ClanPlayer acp, ClanPlayer vcp)
    {
        this.acp = acp;
        this.vcp = vcp;
    }

    @Override
    public void run()
    {
        final int strifemax = SimpleClans.getInstance().getSettingsManager().getStrifeLimit();
        SimpleClans.getInstance().getStorageManager().addStrife(this.acp.getClan(), this.vcp.getClan(), 1);
        if (SimpleClans.getInstance().getStorageManager().retrieveStrifes(acp.getClan(), vcp.getClan()) >= strifemax)
        {
            Bukkit.getScheduler().runTask(SimpleClans.getInstance(), new Runnable() {
                @Override
                public void run()
                {
                    acp.getClan().addWarringClan(vcp.getClan());
                    vcp.getClan().addWarringClan(acp.getClan());
                    acp.getClan().addBb(acp.getName(), ChatColor.AQUA + MessageFormat.format(SimpleClans.getInstance().getLang("you.are.at.war"), Helper.capitalize(acp.getClan().getName()), vcp.getClan().getColorTag()));
                    vcp.getClan().addBb(vcp.getName(), ChatColor.AQUA + MessageFormat.format(SimpleClans.getInstance().getLang("you.are.at.war"), Helper.capitalize(vcp.getClan().getName()), acp.getClan().getColorTag()));
                }
            });
            SimpleClans.getInstance().getStorageManager().addStrife(acp.getClan(), vcp.getClan(), -strifemax);
        }
    }
}
