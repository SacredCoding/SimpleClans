/*
 * Copyright (C) 2012 p000ison
 * 
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-NoDerivs 3.0 Unported License. To view a copy of
 * this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/ or send
 * a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco,
 * California, 94105, USA.
 * 
 */
package net.sacredlabyrinth.phaed.simpleclans.api.events;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Max
 */
class ClanPlayerEvent extends Event
{

    private static final HandlerList handlers = new HandlerList();
    private ClanPlayer cp;

    public ClanPlayerEvent(ClanPlayer cp)
    {
        this.cp = cp;
    }

    public ClanPlayer getClanPlayer()
    {
        return cp;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
