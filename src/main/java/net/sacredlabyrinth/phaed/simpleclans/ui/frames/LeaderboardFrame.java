package net.sacredlabyrinth.phaed.simpleclans.ui.frames;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.ui.InventoryDrawer;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCComponent;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCComponentImpl;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCFrame;

public class LeaderboardFrame implements SCFrame {

	private HashSet<SCComponent> components = new HashSet<>();
	private Player player;
	private SCFrame parent;

	public LeaderboardFrame(Player player, SCFrame parent) {
		this.player = player;
		this.parent = parent;

		createComponents();
	}

	private void createComponents() {
		// Material.LIGHT_GRAY_STAINED_GLASS_PANE
		SCComponent back = new SCComponentImpl("Voltar", null, Material.ARROW, 4, (String) null, null);
		back.setLeftClickListener(() -> InventoryDrawer.open(getParent()));
		components.add(back);

		int slot = 9;
		for (ClanPlayer cp : SimpleClans.getInstance().getClanManager().getAllClanPlayers()) {
			if (slot == getSize()) {
				break;
			}
			// TODO Ordenar por KDR
			// TODO Adicionar permission
			SCComponent c = new SCComponentImpl(cp.getName(), Arrays.asList("KDR: " + cp.getKDR()),
					Material.PLAYER_HEAD, slot, (String) null, null);
			SkullMeta itemMeta = (SkullMeta) c.getItemMeta();
			itemMeta.setOwningPlayer(Bukkit.getOfflinePlayer(cp.getUniqueId()));
			c.setItemMeta(itemMeta);
			components.add(c);
		}

	}

	@Override
	public String getTitle() {
		return "Leaderboard";
	}

	@Override
	public Player getViewer() {
		return player;
	}

	@Override
	public SCFrame getParent() {
		return parent;
	}

	@Override
	public int getSize() {
		return 6 * 9;
	}

	@Override
	public SCComponent getComponent(int slot) {
		for (SCComponent c : getComponents()) {
			if (c.getSlot() == slot) {
				return c;
			}
		}
		return null;
	}

	@Override
	public Set<SCComponent> getComponents() {
		return components;
	}

}
