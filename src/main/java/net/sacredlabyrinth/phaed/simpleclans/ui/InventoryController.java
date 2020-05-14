package net.sacredlabyrinth.phaed.simpleclans.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

/**
 * 
 * @author RoinujNosde
 *
 */
public class InventoryController implements Listener {
	private static Map<UUID, SCFrame> frames = new HashMap<>();

	@EventHandler(ignoreCancelled = true)
	public void onClose(InventoryCloseEvent event) {
		HumanEntity entity = event.getPlayer();
		if (!(entity instanceof Player)) {
			return;
		}
		
		frames.remove(entity.getUniqueId());
	}

	@EventHandler(ignoreCancelled = true)
	public void onInteract(InventoryClickEvent event) {
		HumanEntity entity = event.getWhoClicked();
		if (!(entity instanceof Player)) {
			return;
		}

		SCFrame frame = frames.get(entity.getUniqueId());
		if (frame == null) {
			return;
		}
		
		event.setCancelled(true);
		
		if (event.getClickedInventory() == null || event.getClickedInventory().getType() == InventoryType.PLAYER) {
			return;
		}
		
		SCComponent component = frame.getComponent(event.getSlot());
		if (component == null) {
			return;
		}
		
		switch (event.getClick()) {
			case LEFT:
				run(component.getLeftClickListener());
				break;
			case RIGHT:
				run(component.getRightClickListener());
				break;
			case MIDDLE:
				run(component.getMiddleClickListener());
				break;
			default:
				break;
		}
	}

	private void run(Runnable listener) {
		if (listener != null) listener.run();
	}

	public static void register(SCFrame frame) {
		frames.put(frame.getViewer().getUniqueId(), frame);
	}
	
	public static void runSubcommand(Player player, String subcommand) {
		String baseCommand = SimpleClans.getInstance().getSettingsManager().getCommandClan();

		new BukkitRunnable() {
			
			@Override
			public void run() {
				player.closeInventory();
				player.performCommand(String.format("%s %s", baseCommand, subcommand));
			}
		}.runTask(SimpleClans.getInstance());
	}
}
