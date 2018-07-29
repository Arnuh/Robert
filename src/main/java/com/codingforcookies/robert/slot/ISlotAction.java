package com.codingforcookies.robert.slot;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import com.codingforcookies.robert.core.GUI;

public abstract interface ISlotAction{

	public abstract void doAction(GUI paramGUI, Player paramPlayer, ClickType paramClickType);
}
