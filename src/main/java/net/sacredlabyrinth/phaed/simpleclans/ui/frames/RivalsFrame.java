package net.sacredlabyrinth.phaed.simpleclans.ui.frames;

import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.RankPermission;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.ui.*;
import net.sacredlabyrinth.phaed.simpleclans.utils.Paginator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static net.sacredlabyrinth.phaed.simpleclans.SimpleClans.lang;

public class RivalsFrame extends SCFrame {
	private final SimpleClans plugin = SimpleClans.getInstance();
	private Paginator paginator;
	private final Clan subject;

	public RivalsFrame(Player viewer, SCFrame parent, Clan subject) {
		super(parent, viewer);
		this.subject = subject;
	}

	@Override
	public void createComponents() {
		List<String> rivals = subject.getRivals();
		paginator = new Paginator(getSize() - 9, rivals.size());

		for (int slot = 0; slot < 9; slot++) {
			if (slot == 2 || slot == 4 || slot == 6 || slot == 7)
				continue;
			add(Components.getPanelComponent(slot));
		}

		add(Components.getBackComponent(getParent(), 2));

		SCComponent add = new SCComponentImpl(lang("gui.rivals.add.title"), null, Material.RED_WOOL, 4);
		add.setVerifiedOnly(ClickType.LEFT);
		add.setListener(ClickType.LEFT, () -> InventoryDrawer.open(new AddRivalFrame(this, getViewer(), subject)));
		add.setPermission(ClickType.LEFT, RankPermission.RIVAL_ADD);
		add(add);

		add(Components.getPreviousPageComponent(6, this::previousPage));
		add(Components.getNextPageComponent(7, this::nextPage));

		int slot = 9;
		for (int i = paginator.getMinIndex(); paginator.isValidIndex(i); i++) {

			Clan clan = plugin.getClanManager().getClan(rivals.get(i));
			if (clan == null)
				continue;
			SCComponent c = new SCComponentImpl(
					lang("gui.clanlist.clan.title", clan.getColorTag(), clan.getName()),
					Collections.singletonList(lang("gui.rivals.clan.lore")), Material.RED_BANNER, slot	);
			c.setListener(ClickType.RIGHT, () -> InventoryController.runSubcommand(getViewer(),
					String.format("rival %s %s", lang("remove"), clan.getTag()), false));
			c.setPermission(ClickType.RIGHT, RankPermission.RIVAL_REMOVE);
			add(c);
			slot++;
		}
	}

	private void previousPage() {
		if (paginator.previousPage()) {
			updateFrame();
		}
	}

	private void nextPage() {
		if (paginator.nextPage()) {
			updateFrame();
		}
	}

	private void updateFrame() {
		InventoryDrawer.update(this);
	}

	@Override
	public @NotNull String getTitle() {
		return lang("gui.rivals.title");
	}

	@Override
	public int getSize() {
		return 6 * 9;
	}
}
