package net.sacredlabyrinth.phaed.simpleclans.ui.frames;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import net.sacredlabyrinth.phaed.simpleclans.PermissionLevel;
import net.sacredlabyrinth.phaed.simpleclans.RankPermission;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.ui.InventoryController;
import net.sacredlabyrinth.phaed.simpleclans.ui.InventoryDrawer;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCComponent;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCComponentImpl;
import net.sacredlabyrinth.phaed.simpleclans.ui.SCFrame;

public class PlayerDetailsFrame implements SCFrame {

	private SimpleClans plugin = SimpleClans.getInstance();
	private Player viewer;
	private SCFrame parent;
	private OfflinePlayer subject;
	private HashSet<SCComponent> components = new HashSet<>();

	public PlayerDetailsFrame(Player viewer, SCFrame parent, OfflinePlayer subject) {
		this.viewer = viewer;
		this.parent = parent;
		this.subject = subject;
		createComponents();
	}
	
	private void createComponents() {
		String subjectName = subject.getName();
		for (int slot = 0; slot < 9; slot++) {
			if (slot == 4) continue;
			components.add(Components.getPanelComponent(slot));
		}
		
		components.add(Components.getBackComponent(getParent(), 4));
		components.add(Components.getPlayerComponent(this, viewer, subject, 13, false));
		
		SCComponent kick = new SCComponentImpl("Kick", null, Material.RED_WOOL, 28, RankPermission.KICK, PermissionLevel.LEADER);
		kick.setLeftClickListener(() -> InventoryController.runSubcommand(viewer, "kick " + subjectName));
		components.add(kick);
		
		SCComponent promoteDemote = new SCComponentImpl("Promote/Demote", Arrays.asList("Left-click to promote", "Right-click to demote"),
				Material.GUNPOWDER, 30, "simpleclans.leader.promote", PermissionLevel.LEADER);
		promoteDemote.setLeftClickListener(() -> InventoryController.runSubcommand(viewer, "promote " + subjectName));
		promoteDemote.setRightClickListener(() -> InventoryController.runSubcommand(viewer, "demote " + subjectName));
		components.add(promoteDemote);
		
		SCComponentImpl assignUnassign = new SCComponentImpl("Assign/Unassign rank", Arrays.asList("Left-click to assign", "Right-click to unassign"), Material.FEATHER, 32, "simpleclans.leader.rank.assign", PermissionLevel.LEADER);
		assignUnassign.setRightClickListener(() -> InventoryController.runSubcommand(viewer, "rank unassign " + subjectName));
		//TODO Implementar Rank Assign Frame
		assignUnassign.setLeftClickListener(() -> InventoryDrawer.open(null));
		components.add(assignUnassign);
		
		SCComponent trustUntrust = new SCComponentImpl("Trust/Untrust", Arrays.asList("Left-click to trust", "Right-click to untrust"), Material.CYAN_DYE, 34, "simpleclans.leader.trust", PermissionLevel.LEADER);
		trustUntrust.setLeftClickListener(() -> InventoryController.runSubcommand(viewer, "trust " + subjectName));
		trustUntrust.setRightClickListener(() -> InventoryController.runSubcommand(viewer, "untrust " + subjectName));
		components.add(trustUntrust);
		
	}
	
	@Override
	public String getTitle() {
		return plugin.getLangFormatted("gui.playerdetails.title", subject.getName());
	}

	@Override
	public Player getViewer() {
		return viewer;
	}

	@Override
	public SCFrame getParent() {
		return parent;
	}

	@Override
	public int getSize() {
		return 5 * 9;
	}

	@Override
	public Set<SCComponent> getComponents() {
		return components;
	}

}
