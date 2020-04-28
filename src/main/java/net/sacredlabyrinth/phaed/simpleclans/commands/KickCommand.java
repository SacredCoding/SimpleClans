package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.ChatBlock;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.Helper;
import net.sacredlabyrinth.phaed.simpleclans.PermissionLevel;
import net.sacredlabyrinth.phaed.simpleclans.RankPermission;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.UUID;

import net.sacredlabyrinth.phaed.simpleclans.uuid.UUIDMigration;

/**
 * @author phaed
 */
public class KickCommand {
    public KickCommand() {
    }

    /**
     * Execute the command
     *
     * @param player
     * @param arg
     */
    public void execute(Player player, String[] arg) {
        SimpleClans plugin = SimpleClans.getInstance();

        if (!plugin.getPermissionsManager().has(player, "simpleclans.leader.kick")) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("insufficient.permissions"));
            return;
        }

        ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

        if (cp == null) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("not.a.member.of.any.clan"));
            return;
        }

        Clan clan = cp.getClan();

        if (!plugin.getPermissionsManager().has(player, RankPermission.KICK, PermissionLevel.LEADER, true)) {
        	return;
        }

        if (arg.length != 1) {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(plugin.getLang("usage.kick.player"), plugin.getSettingsManager().getCommandClan()));
            return;
        }

        UUID kicked = UUIDMigration.getForcedPlayerUUID(arg[0]);
        if (kicked == null) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("no.player.matched"));
            return;
        }
        if (kicked.equals(player.getUniqueId())) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("you.cannot.kick.yourself"));
            return;
        }
        if (!clan.isMember(kicked)) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("the.player.is.not.a.member.of.your.clan"));
            return;
        }
        if (clan.isLeader(kicked)) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("you.cannot.kick.another.leader"));
            return;
        }

        clan.addBb(player.getName(), ChatColor.AQUA + MessageFormat.format(plugin.getLang("has.been.kicked.by"), arg[0], player.getName()));
        clan.removePlayerFromClan(kicked);
    }
}
