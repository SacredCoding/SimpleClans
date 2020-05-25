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

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.RankPermission;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.managers.PermissionsManager;
import net.sacredlabyrinth.phaed.simpleclans.ui.frames.WarningFrame;
import org.jetbrains.annotations.NotNull;

/**
 * 
 * @author RoinujNosde
 *
 */
public class InventoryController implements Listener {
	private static final Map<UUID, SCFrame> frames = new HashMap<>();

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

		Runnable listener = component.getListener(event.getClick());
		if (listener == null) {
			return;
		}

		if (component.isVerifiedOnly(event.getClick()) && !isClanVerified((Player) entity)) {
			InventoryDrawer.open(new WarningFrame(frame, (Player) entity, null));
			return;
		}
		
		Object permission = component.getPermission(event.getClick());
		if (permission != null) {
			if (!hasPermission((Player) entity, permission)) {
				InventoryDrawer.open(new WarningFrame(frame, (Player) entity, permission));
				return;
			}
		}

		listener.run();
	}


	/**
	 * Checks if the Player's Clan is verified
	 * @param player the Player
	 * @return if the Clan is verified
	 */
	private boolean isClanVerified(@NotNull Player player) {
		SimpleClans plugin = SimpleClans.getInstance();
		ClanPlayer cp = plugin.getClanManager().getAnyClanPlayer(player.getUniqueId());

		return cp != null && cp.getClan() != null && cp.getClan().isVerified();
	}

	/**
	 * Checks if the player has the permission
	 * 
	 * @param player the Player
	 * @param permission the permission
	 * @return true if they have permission
	 *
	 * @author RoinujNosde
	 */
	private boolean hasPermission(@NotNull Player player, @NotNull Object permission) {
		SimpleClans plugin = SimpleClans.getInstance();
		PermissionsManager pm = plugin.getPermissionsManager();
		if (permission instanceof String) {
			boolean leaderPerm = ((String) permission).contains("simpleclans.leader");
			ClanPlayer cp = plugin.getClanManager().getAnyClanPlayer(player.getUniqueId());
			
			return pm.has(player, (String) permission) && (!leaderPerm || cp.isLeader());
		}
		return pm.has(player, (RankPermission) permission, false);
	}

	/**
	 * Registers the frame in the InventoryController
	 * @param frame the frame
	 *
	 * @author RoinujNosde
	 */
	public static void register(@NotNull SCFrame frame) {
		frames.put(frame.getViewer().getUniqueId(), frame);
	}

	/**
	 * Checks if the Player is registered
	 *
	 * @param player the Player
	 * @return if they are registered
	 */
	public static boolean isRegistered(@NotNull Player player) {
		return frames.containsKey(player.getUniqueId());
	}

	/**
	 * Runs a subcommand for the Player
	 * @param player the Player
	 * @param subcommand the subcommand
	 * @param update whether to update the inventory instead of closing
	 *
	 * @author RoinujNosde
	 */
	public static void runSubcommand(@NotNull Player player, @NotNull String subcommand, boolean update) {
		String baseCommand = SimpleClans.getInstance().getSettingsManager().getCommandClan();

		new BukkitRunnable() {
			
			@Override
			public void run() {
				player.performCommand(String.format("%s %s", baseCommand, subcommand));
				if (!update) {
					player.closeInventory();
				} else {
					InventoryDrawer.update(frames.get(player.getUniqueId()));
				}
			}
		}.runTask(SimpleClans.getInstance());
	}
}
