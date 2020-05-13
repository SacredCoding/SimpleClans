package net.sacredlabyrinth.phaed.simpleclans.ui;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.sacredlabyrinth.phaed.simpleclans.PermissionLevel;
import net.sacredlabyrinth.phaed.simpleclans.RankPermission;

/**
 * 
 * @author RoinujNosde
 *
 */
public abstract class SCComponent {
	
	private Runnable rightClickListener;
	private Runnable leftClickListener;
	private Runnable middleClickListener;
	
	public abstract ItemStack getItem();
	
	public abstract int getSlot();
	
	public abstract RankPermission getRankPermission();
	
	public abstract String getBukkitPermission();
	
	public abstract PermissionLevel getPermissionLevel();
	
	public ItemMeta getItemMeta() {
		if (getItem() != null) {
			return getItem().getItemMeta();
		}
		return null;
	}
	
	public void setItemMeta(ItemMeta itemMeta) {
		if (getItem() != null) {
			getItem().setItemMeta(itemMeta);
		}
	}
	
	public Runnable getRightClickListener() {
		return rightClickListener;
	}

	public void setRightClickListener(Runnable listener) {
		this.rightClickListener = listener;
	}

	public Runnable getLeftClickListener() {
		return leftClickListener;
	}

	public void setLeftClickListener(Runnable listener) {
		this.leftClickListener = listener;
	}

	public Runnable getMiddleClickListener() {
		return middleClickListener;
	}

	public void setMiddleClickListener(Runnable listener) {
		this.middleClickListener = listener;
	}
}
