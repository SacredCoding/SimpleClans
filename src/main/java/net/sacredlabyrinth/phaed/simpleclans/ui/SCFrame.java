package net.sacredlabyrinth.phaed.simpleclans.ui;

import java.util.Set;

import org.bukkit.entity.Player;

/**
 * 
 * @author RoinujNosde
 *
 */
public interface SCFrame {

	String getTitle();

	Player getViewer();

	SCFrame getParent();

	int getSize();

	default SCComponent getComponent(int slot) {
		if (getComponents() != null) {
			for (SCComponent c : getComponents()) {
				if (c.getSlot() == slot) {
					return c;
				}
			}
		}
		return null;
	}

	Set<SCComponent> getComponents();

}
