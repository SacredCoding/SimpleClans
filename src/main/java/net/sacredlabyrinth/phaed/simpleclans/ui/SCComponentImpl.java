package net.sacredlabyrinth.phaed.simpleclans.ui;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.sacredlabyrinth.phaed.simpleclans.PermissionLevel;
import net.sacredlabyrinth.phaed.simpleclans.RankPermission;

public class SCComponentImpl extends SCComponent {
	
	private ItemStack item;
	private int slot;
	private RankPermission rankPermission;
	private String bukkitPermission;
	private PermissionLevel permissionLevel;

	private SCComponentImpl(String displayName, List<String> lore, Material material, int slot, PermissionLevel permissionLevel) {
		item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(displayName);
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		this.permissionLevel = permissionLevel;
		this.slot = slot;
	}
	
	public SCComponentImpl(String displayName, List<String> lore, Material material, int slot, String bukkitPermission,
			PermissionLevel permissionLevel) {
		this(displayName, lore, material, slot, permissionLevel);
		this.bukkitPermission = bukkitPermission;
	}
	
	public SCComponentImpl(String displayName, List<String> lore, Material material, int slot, RankPermission rankPermission,
			PermissionLevel permissionLevel) {
		this(displayName, lore, material, slot, permissionLevel);
		this.rankPermission = rankPermission;
	}
	
	@Override
	public ItemStack getItem() {
		return item;
	}

	@Override
	public int getSlot() {
		return slot;
	}

	@Override
	public RankPermission getRankPermission() {
		return rankPermission;
	}

	@Override
	public String getBukkitPermission() {
		return bukkitPermission;
	}

	@Override
	public PermissionLevel getPermissionLevel() {
		return permissionLevel;
	}
}
