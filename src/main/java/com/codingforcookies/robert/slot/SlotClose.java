package com.codingforcookies.robert.slot;

import com.codingforcookies.robert.core.GUI;

public class SlotClose implements ISlotAction{

	public void doAction(GUI gui, org.bukkit.entity.Player p, org.bukkit.event.inventory.ClickType type){
		gui.close(p);
	}
}
