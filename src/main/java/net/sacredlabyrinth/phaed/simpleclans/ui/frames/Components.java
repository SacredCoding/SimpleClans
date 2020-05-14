package net.sacredlabyrinth.phaed.simpleclans.ui.frames;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.Helper;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.ui.InventoryDrawer;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCComponent;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCComponentImpl;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCFrame;

public class Components {

	private Components() {
	}

	public static SCComponent getPlayerComponent(SCFrame frame, Player viewer, OfflinePlayer subject, int slot,
			boolean openDetails) {
		ClanPlayer cp = SimpleClans.getInstance().getClanManager().getCreateClanPlayer(subject.getUniqueId());

		return getPlayerComponent(frame, viewer, cp, slot, openDetails);
	}

	public static SCComponent getPlayerComponent(SCFrame frame, Player viewer, ClanPlayer cp, int slot,
			boolean openDetails) {
		SimpleClans pl = SimpleClans.getInstance();

		String status = cp.getClan() == null ? pl.getLang("free.agent")
				: (cp.isLeader() ? pl.getLang("leader")
						: (cp.isTrusted() ? pl.getLang("trusted") : pl.getLang("untrusted")));
		SCComponent c = new SCComponentImpl(pl.getLangFormatted("gui.playerdetails.player.title", cp.getName()),
				Arrays.asList(
						cp.getClan() == null ? pl.getLang("gui.playerdetails.player.lore.noclan")
								: pl.getLangFormatted("gui.playerdetails.player.lore.clan", cp.getClan().getColorTag(),
										cp.getClan().getName()),
						pl.getLangFormatted("gui.playerdetails.player.lore.rank",
								Helper.parseColors(cp.getRankDisplayName())),
						pl.getLangFormatted("gui.playerdetails.player.lore.status", status),
						pl.getLangFormatted("gui.playerdetails.player.lore.kdr",
								new DecimalFormat("#.#").format(cp.getKDR())),
						pl.getLangFormatted("gui.playerdetails.player.lore.kill.totals", cp.getRivalKills(),
								cp.getNeutralKills(), cp.getCivilianKills()),
						pl.getLangFormatted("gui.playerdetails.player.lore.deaths", cp.getDeaths()),
						pl.getLangFormatted("gui.playerdetails.player.lore.join.date", cp.getJoinDateString()),
						pl.getLangFormatted("gui.playerdetails.player.lore.last.seen", cp.getLastSeenString()),
						pl.getLangFormatted("gui.playerdetails.player.lore.past.clans", cp.getPastClansString(", ")),
						pl.getLangFormatted("gui.playerdetails.player.lore.inactive", cp.getInactiveDays(),
								pl.getSettingsManager().getPurgePlayers())),
				Material.PLAYER_HEAD, slot, (String) null, null);
		SkullMeta itemMeta = (SkullMeta) c.getItemMeta();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(cp.getUniqueId());
		itemMeta.setOwningPlayer(offlinePlayer);
		c.setItemMeta(itemMeta);
		if (openDetails) {
			c.setLeftClickListener(() -> InventoryDrawer.open(new PlayerDetailsFrame(viewer, frame, offlinePlayer)));
		}
		return c;
	}

	public static SCComponent getBackComponent(SCFrame parent, int slot) {
		// TODO Pegar nome da config

		SCComponent back = new SCComponentImpl("Back", null, Material.ARROW, slot, (String) null, null);
		back.setLeftClickListener(() -> InventoryDrawer.open(parent));
		return back;
	}

	public static SCComponent getPanelComponent(int slot) {
		return new SCComponentImpl(" ", null, Material.GRAY_STAINED_GLASS_PANE, slot, (String) null, null);
	}
}
