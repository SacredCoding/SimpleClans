package net.sacredlabyrinth.phaed.simpleclans.commands;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.sacredlabyrinth.phaed.simpleclans.ChatBlock;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.Helper;
import net.sacredlabyrinth.phaed.simpleclans.Rank;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;
import net.sacredlabyrinth.phaed.simpleclans.uuid.UUIDMigration;

/**
*
* @author RoinujNosde
*/
public class RankCommand {
	private SimpleClans plugin;
	private Player player;
	private ClanPlayer clanPlayer;
	private Clan clan;
	
	/**
     * Execute the command
     *
     * @param player
     * @param arg
     */
	public void execute(Player player, String[] args) {
		this.player = player;
		plugin = SimpleClans.getInstance();
		ClanManager clanManager = plugin.getClanManager();
		
		// TODO Adicionar comando para mostrar permissões disponíveis
		// TODO Verificar se a permissão é válida antes de adicionar
		// TODO Check for permissions
		
		clanPlayer = clanManager.getClanPlayer(player);
		if (clanPlayer == null) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("not.a.member.of.any.clan"));
            return;
        }

        clan = clanPlayer.getClan();
        if (!clan.isVerified()) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("clan.is.not.verified"));
            return;
        }
        
        if (!clanPlayer.isLeader()) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("no.leader.permissions"));
            return;
        }
		
		if (args.length > 0) {
			String subCommand = args[0].toLowerCase();
			args = Helper.removeFirst(args);
			
			switch (subCommand) {
				case "assign":
					assignRank(args);
					return;
				case "create":
					createRank(args);
					return;
				case "list":
					listRanks();
					return;
				case "delete":
					deleteRank(args);
					return;
				case "permissions":
					permissions(args);
					return;
				case "setdisplayname":
					setDisplayName(args);
					return;
			}
		}
		
        ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(plugin.getLang("usage.0.rank"),
        		plugin.getSettingsManager().getCommandClan()));
		return;
	}
	
	private void assignRank(String[] args) {
		if (args.length != 2) {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(plugin.getLang("usage.0.rank.assign"),
            		plugin.getSettingsManager().getCommandClan()));
			return;
		}
		UUID uuid = UUIDMigration.getForcedPlayerUUID(args[0]);
        if (uuid == null || (!clan.isMember(uuid) && !clan.isLeader(uuid))) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("no.player.matched"));
            return;
        }
        String rank = args[1];
		if (!clan.hasRank(rank)) {
			ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("rank.does.not.exist"));
			return;
		}
        
        ClanPlayer cpTarget = plugin.getClanManager().getClanPlayer(uuid);
		cpTarget.setRank(rank);
        plugin.getStorageManager().updateClanPlayer(cpTarget);
        ChatBlock.sendMessage(player, ChatColor.AQUA + plugin.getLang("player.rank.changed"));
	}

	private void createRank(String[] args) {
		if (args.length != 1) {
			// TODO Replace 0
			ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("usage.0.rank.create"));
			return;
		}
		
		String rank = args[0];
		if (clan.hasRank(rank)) {
			ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("rank.already.exists"));
			return;
		}
		
		clan.createRank(rank);
		plugin.getStorageManager().updateClan(clan, true);
		ChatBlock.sendMessage(player, ChatColor.AQUA + plugin.getLang("rank.created"));
	}

	private void listRanks() {
		List<Rank> ranks = clan.getRanks();
		
		if (ranks.isEmpty()) {
			ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("no.ranks"));
			return;
		}
		
		ranks.sort(Comparator.reverseOrder());
		ChatBlock.sendMessage(player, ChatColor.AQUA + plugin.getLang("clans.ranks"));
		int count = 1;
		for (Rank rank : ranks) {
			ChatBlock.sendMessage(player, ChatColor.AQUA + MessageFormat.format(plugin.getLang("ranks.list.item"), count, Helper.parseColors(rank.getDisplayName()) + ChatColor.AQUA, rank.getName()));
			count++;
		}
	}

	private void deleteRank(String[] args) {
		if (args.length != 1) {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(plugin.getLang("usage.0.rank.delete"),
            		plugin.getSettingsManager().getCommandClan()));
			return;
		}
		String rank = args[0];
		if (!clan.hasRank(rank)) {
			ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("rank.does.not.exist"));
			return;
		}
		clan.deleteRank(rank);
		plugin.getStorageManager().updateClan(clan, true);
		ChatBlock.sendMessage(player, ChatColor.AQUA + MessageFormat.format(plugin.getLang("rank.0.deleted"), rank));
	}
	
	private void setDisplayName(String[] args) {
		if (args.length != 2) {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(plugin.getLang("usage.0.rank.setdisplayname"),
            		plugin.getSettingsManager().getCommandClan()));
            return;
		}
		Rank rank = clan.getRank(args[0]);
		String dn = Helper.toMessage(Helper.removeFirst(args));
		if (dn.contains("&") && !plugin.getPermissionsManager().has(player, "simpleclans.leader.coloredrank")) {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("you.cannot.set.colored.ranks"));
        	return;
        }
		rank.setDisplayName(dn);
		plugin.getStorageManager().updateClan(clan, true);
		ChatBlock.sendMessage(player, ChatColor.AQUA + plugin.getLang("rank.displayname.updated"));
	}

	private void permissions(String[] args) {
		if (args.length > 0) {
			String rank = args[0];
			if (!clan.hasRank(rank)) {
				ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("rank.does.not.exist"));
				return;
			}
			Set<String> permissions = clan.getRank(rank).getPermissions();
			
			if (args.length == 1) {
				// TODO Permissions vazia
				ChatBlock.sendMessage(player, ChatColor.AQUA + MessageFormat.format(plugin.getLang("rank.0.permissions"), rank));
				ChatBlock.sendMessage(player, ChatColor.AQUA + Helper.toMessage(permissions.toArray(new String[0]), ","));
				return;
			}
			if (args.length == 3) {
				String subCommand = args[1].toLowerCase();
				String permission = args[2];
				
				boolean changed = false;
				
				switch (subCommand) {
					case "add":
						permissions.add(permission);
						ChatBlock.sendMessage(player, ChatColor.AQUA + MessageFormat.format(plugin.getLang("permission.0.added.to.rank.1"), permission, rank));
						changed = true;
						break;
					case "remove":
						permissions.remove(permission);
						ChatBlock.sendMessage(player, ChatColor.AQUA + MessageFormat.format(plugin.getLang("permission.0.removed.from.rank.1"), permission, rank));
						changed = true;
						break;
				}
				
				if (changed) {
					plugin.getStorageManager().updateClan(clan, true);
					return;
				}
			}
		}
		ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(plugin.getLang("usage.0.rank.permissions"),
        		plugin.getSettingsManager().getCommandClan()));
	}
}
