package net.sacredlabyrinth.phaed.simpleclans.managers;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.PlaceholderHook;

import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

/**
 * {@link Class} to manage and hook the {@link SimpleClans} placeholders
 * 
 * @since 2.10.1
 * 
 * @author Peng1104
 */

public final class PlaceholdersManager {
	
	/**
	 * The char to create a new line
	 * 
	 * @since 2.10.1
	 */
	
	public static final char NEW_LINE = '\n';
	
	/**
	 * The {@link PlaceholdersManager} constructor
	 * 
	 * @since 2.10.1
	 */
	
	public PlaceholdersManager() {
		try {
			Class.forName("me.clip.placeholderapi.PlaceholderAPI");
			
			PlaceholderAPI.registerPlaceholderHook(SimpleClans.getInstance().getName(), new PlaceholderHook() {
				
				@Override
				public String onPlaceholderRequest(Player player, String identifier) {
					return onRequest(player, identifier);
				}
				
				@Override
				public String onRequest(OfflinePlayer player, String identifier) {
					if (player == null) return "";
					
					return setPlaceholders(SimpleClans.getInstance().getClanManager().getClanPlayer(player), identifier);
				}
			});
		}
		catch(ClassNotFoundException e) {
			 SimpleClans.log("[SimpleClans] PlaceholderAPI not found. No placeholders support.");
		}
	}
	
	/**
	 * Get the true value from the PlaceholderAPI config
	 * 
	 * @return The true value from the PlaceholderAPI config
	 * 
	 * @since 2.10.1
	 */
	
	public static String booleanTrue() {
		return PlaceholderAPIPlugin.booleanTrue();
	}
	
	/**
	 * Get the false value from the PlaceholderAPI config
	 * 
	 * @return The false value from the PlaceholderAPI config
	 * 
	 * @since 2.10.1
	 */
	
	public static String booleanFalse() {
		return PlaceholderAPIPlugin.booleanFalse();
	}
	
	/**
	 * Abstract method to get a playerholder for a {@link ClanPlayer}
	 * 
	 * @param player The {@link ClanPlayer} to request the placeholders value for
	 * @param identifier String that determine what value to return
	 * 
	 * @return value for the requested player and params
	 * 
	 * @since 2.10.1
	 */
	
	public String setPlaceholders(ClanPlayer player, String identifier) {
		if (player == null) return "";
		
		switch (identifier) {
			case "neutral_kills": {
				return String.valueOf(player.getNeutralKills());
			}
			case "rival_kills": {
				return String.valueOf(player.getRivalKills());
			}
			case "civilian_kills": {
				return String.valueOf(player.getCivilianKills());
			}
			case "total_kills": {
				return String.valueOf(player.getNeutralKills() + player.getRivalKills() + player.getCivilianKills());
			}
			case "weighted_kills": {
				return String.valueOf(player.getWeightedKills());
			}
			case "deaths": {
				return String.valueOf(player.getDeaths());
			}
			case "kdr": {
				return String.valueOf(player.getKDR());
			}
			case "in_clan": {
				return (player.getClan() != null) ? booleanTrue() : booleanFalse();
			}
			case "is_leader": {
				return player.isLeader() ? booleanTrue() : booleanFalse();
			}
			case "is_trusted": {
				return (!player.isLeader() && player.isTrusted()) ? booleanTrue() : booleanFalse();
			}
			case "is_member": {
				return (!player.isTrusted() && !player.isLeader() && player.getClan() != null) ? booleanTrue() : booleanFalse();
			}
			case "is_bb_enabled": {
				return player.isBbEnabled() ? booleanTrue() : booleanFalse();
			}
			case "is_usechatshortcut": {
				return player.isUseChatShortcut() ? booleanTrue() : booleanFalse();
			}
			case "is_allychat": {
				return player.isAllyChat() ? booleanTrue() : booleanFalse();
			}
			case "is_clanchat": {
				return player.isClanChat() ? booleanTrue() : booleanFalse();
			}
			case "is_globalchat": {
				return player.isGlobalChat() ? booleanTrue() : booleanFalse();
			}
			case "is_cape_enabled": {
				return player.isCapeEnabled() ? booleanTrue() : booleanFalse();
			}
			case "is_tag_enabled": {
				return player.isTagEnabled() ? booleanTrue() : booleanFalse();
			}
			case "is_friendlyfire_on": {
				return player.isFriendlyFire() ? booleanTrue() : booleanFalse();
			}
			case "is_muted": {
				return player.isMuted() ? booleanTrue() : booleanFalse();
			}
			case "is_mutedally": {
				return player.isMutedAlly() ? booleanTrue() : booleanFalse();
			}
			case "join_date": {
				return player.getJoinDateString();
			}
			case "inactive_days": {
				return String.valueOf(player.getInactiveDays());
			}
			case "lastseen": {
				return player.getLastSeenString();
			}
			case "lastseendays": {
				return player.getLastSeenDaysString();
			}
			case "tag": {
				return player.getTag();
			}
			case "tag_label": {
				return player.getTagLabel();
			}
			case "rank": {
				return player.getRank();
			}
			case "rank_displayname": {
				return player.getRankDisplayName();
			}
			default:
				break;
		}
		Clan c = player.getClan();
		
		if (c == null) return "";
		
		switch (identifier) {
			case "clan_total_neutral": {
				return String.valueOf(c.getTotalNeutral());
			}
			case "clan_total_civilian": {
				return String.valueOf(c.getTotalCivilian());
			}
			case "clan_total_rival": {
				return String.valueOf(c.getTotalRival());
			}
			case "clan_total_kills": {
				return String.valueOf(c.getTotalRival() + c.getTotalNeutral() + c.getTotalCivilian());
			}
			case "clan_total_deaths": {
				return String.valueOf(c.getTotalDeaths());
			}
			case "clan_total_kdr": {
				return String.valueOf(c.getTotalKDR());
			}
			case "clan_average_wk": {
				return String.valueOf(c.getAverageWK());
			}
			case "clan_leader_size": {
				return String.valueOf(c.getLeaders().size());
			}
			case "clan_balance": {
				return String.valueOf(c.getBalance());
			}
			case "clan_allow_withdraw": {
				return c.isAllowWithdraw() ? booleanTrue() : booleanFalse();
			}
			case "clan_allow_deposit": {
				return c.isAllowDeposit() ? booleanTrue() : booleanFalse();
			}
			case "clan_size": {
				return String.valueOf(c.getSize());
			}
			case "clan_name": {
				return c.getName();
			}
			case "clan_color_tag": {
				return c.getColorTag();
			}
			case "clan_tag": {
				return c.getTag();
			}
			case "clan_founded": {
				return c.getFoundedString();
			}
			case "clan_friendly_fire": {
				return c.isFriendlyFire() ? booleanTrue() : booleanFalse();
			}
			case "clan_is_unrivable": {
				return c.isUnrivable() ? booleanTrue() : booleanFalse();
			}
			case "clan_is_anyonline": {
				return c.isAnyOnline() ? booleanTrue() : booleanFalse();
			}
			case "clan_is_verified": {
				return c.isVerified() ? booleanTrue() : booleanFalse();
			}
			case "clan_capeurl": {
				return c.getCapeUrl();
			}
			case "clan_inactivedays": {
				return String.valueOf(c.getInactiveDays());
			}
			case "clan_onlinemembers_count": {
				return String.valueOf(c.getOnlineMembers().size());
			}
			case "clan_allies_count": {
				return String.valueOf(c.getAllies().size());
			}
			case "clan_rivals_count": {
				return String.valueOf(c.getRivals().size());
			}
			default:
				break;
		}
		return "";
	}
}