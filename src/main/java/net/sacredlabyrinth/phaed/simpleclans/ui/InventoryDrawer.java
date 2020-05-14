package net.sacredlabyrinth.phaed.simpleclans.ui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class InventoryDrawer {

	private InventoryDrawer() {
	}
	
	public static void open(SCFrame frame) {
		if (frame == null) return;

		new BukkitRunnable() {
			
			@Override
			public void run() {
				Inventory inventory = Bukkit.createInventory(frame.getViewer(), frame.getSize(), frame.getTitle());
				setComponents(inventory, frame);
				
				frame.getViewer().openInventory(inventory);
				InventoryController.register(frame);
			}
		}.runTask(SimpleClans.getInstance());
	}

	private static void setComponents(Inventory inventory, SCFrame frame) {
		if (frame.getComponents() == null || frame.getComponents().isEmpty()) {
			SimpleClans.getInstance().getLogger().warning(String.format("Frame %s has no components", frame.getTitle()));
			return;
		}
		for (SCComponent c : frame.getComponents()) {
			if (c.getSlot() >= frame.getSize()) {
				continue;
			}
			inventory.setItem(c.getSlot(), c.getItem());
		}
	}
	
}
