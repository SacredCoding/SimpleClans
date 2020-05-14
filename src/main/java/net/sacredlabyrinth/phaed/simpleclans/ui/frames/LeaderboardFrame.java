package net.sacredlabyrinth.phaed.simpleclans.ui.frames;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCComponent;
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
		for (int slot = 0; slot < 9; slot++) {
			if (slot == 4) continue;
			components.add(Components.getPanelComponent(slot));
		}
		components.add(Components.getBackComponent(getParent(), 4));

		int slot = 9;
		for (ClanPlayer cp : SimpleClans.getInstance().getClanManager().getAllClanPlayers()) {
			if (slot == getSize()) {
				break;
			}
			// TODO Ordenar por KDR
			// TODO Adicionar permission			
			components.add(Components.getPlayerComponent(this, player, cp, slot, true));
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
	public Set<SCComponent> getComponents() {
		return components;
	}

}
