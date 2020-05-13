package net.sacredlabyrinth.phaed.simpleclans.ui.frames;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import net.sacredlabyrinth.phaed.simpleclans.ui.InventoryController;
import net.sacredlabyrinth.phaed.simpleclans.ui.InventoryDrawer;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCComponent;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCComponentImpl;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCFrame;

public class MainFrame implements SCFrame {

	private HashSet<SCComponent> components = new HashSet<>();
	private Player player;

	public MainFrame(Player player) {
		this.player = player;
		createComponents();
	}

	private void createComponents() {
		SCComponent leaderboard = new SCComponentImpl("Leaderboard", Arrays.asList("Shows the leaderboard"), Material.PAINTING, 4,
				"simpleclans.anyone.leaderboard", null);
		leaderboard.setLeftClickListener(() -> InventoryDrawer.open(new LeaderboardFrame(player, this)));
		components.add(leaderboard);
		
		SCComponent resetKdr = new SCComponentImpl("Reset KDR", Arrays.asList(String.format("Click here to reset your KDR%nTeste")), Material.RED_WOOL, 6,
				"simpleclans.member.resetkdr", null);
		resetKdr.setLeftClickListener(() -> InventoryController.runSubcommand(player, "resetkdr"));
		components.add(resetKdr);
		
	}
	
	@Override
	public Player getViewer() {
		return player;
	}

	@Override
	public String getTitle() {
		return "Teste";
	}

	@Override
	public SCFrame getParent() {
		return null;
	}

	@Override
	public int getSize() {
		return 9;
	}

	@Override
	public SCComponent getComponent(int index) {
		for (SCComponent c : components) {
			if (c.getSlot() == index) {
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
