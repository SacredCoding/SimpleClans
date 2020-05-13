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
	
	SCComponent getComponent(int slot);
	
	Set<SCComponent> getComponents();
	
}
