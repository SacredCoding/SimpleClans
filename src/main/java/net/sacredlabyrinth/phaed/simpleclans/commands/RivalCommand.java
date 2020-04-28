package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 * @author phaed
 */
public class RivalCommand {
    public RivalCommand() {
    }

    /**
     * Execute the command
     *
     * @param player
     * @param arg
     */
    public void execute(Player player, String[] arg) {
        SimpleClans plugin = SimpleClans.getInstance();

        if (!plugin.getPermissionsManager().has(player, "simpleclans.leader.rival")) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("insufficient.permissions"));
            return;
        }

        ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

        if (cp == null) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("not.a.member.of.any.clan"));
            return;
        }

        Clan clan = cp.getClan();

        if (!clan.isVerified()) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("clan.is.not.verified"));
            return;
        }
        if (clan.isUnrivable()) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("your.clan.cannot.create.rivals"));
            return;
        }
        if (arg.length != 2) {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(plugin.getLang("usage.rival"), plugin.getSettingsManager().getCommandClan()));
            return;
        }

        if (clan.getSize() < plugin.getSettingsManager().getClanMinSizeToRival()) {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(plugin.getLang("min.players.rivalries"), plugin.getSettingsManager().getClanMinSizeToRival()));
            return;
        }

        String action = arg[0];
        Clan rival = plugin.getClanManager().getClan(arg[1]);

        if (rival == null) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("no.clan.matched"));
            return;
        }
        if (clan.getTag().equals(rival.getTag()))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("you.cannot.rival.your.own.clan"));
            return;
        }
            
        if (plugin.getSettingsManager().isUnrivable(rival.getTag())) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("the.clan.cannot.be.rivaled"));
            return;
        }
        if (!rival.isVerified()) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("cannot.rival.an.unverified.clan"));
            return;
        }

        if (action.equals(plugin.getLang("add"))) {
            if (!plugin.getPermissionsManager().has(player, RankPermission.RIVAL_ADD, PermissionLevel.LEADER, true)) {
            	return;
            }
            if (!clan.reachedRivalLimit()) {
                if (!clan.isRival(rival.getTag())) {
                    clan.addRival(rival);
                    rival.addBb(player.getName(), ChatColor.AQUA + MessageFormat.format(plugin.getLang("has.initiated.a.rivalry"), clan.getName(), rival.getName()), false);
                    clan.addBb(player.getName(), ChatColor.AQUA + MessageFormat.format(plugin.getLang("has.initiated.a.rivalry"), player.getName(), rival.getName()));
                } else {
                    ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("your.clans.are.already.rivals"));
                }
            } else {
                ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("rival.limit.reached"));
            }
            return;
        }

        if (action.equals(plugin.getLang("remove"))) {
            if (!plugin.getPermissionsManager().has(player, RankPermission.RIVAL_REMOVE, PermissionLevel.LEADER, true)) {
            	return;
            }
            if (clan.isRival(rival.getTag())) {
                plugin.getRequestManager().addRivalryBreakRequest(cp, rival, clan);
                ChatBlock.sendMessage(player, ChatColor.AQUA + MessageFormat.format(plugin.getLang("leaders.asked.to.end.rivalry"), rival.getName()));
            } else {
                ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("your.clans.are.not.rivals"));
            }
            return;
        }

        ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(plugin.getLang("usage.rival"), plugin.getSettingsManager().getCommandClan()));
    }
}







