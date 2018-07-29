package com.codingforcookies.robert.window;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import com.codingforcookies.robert.core.GUI;
import com.codingforcookies.robert.item.ItemBuilder;
import com.codingforcookies.robert.slot.ISlotAction;

public abstract class WindowYesNo extends GUI{

	public WindowYesNo(){
		this(null);
	}

	public WindowYesNo(String title){
		super(title == null ? "Are you sure?" : title);
		fixed();
		type(org.bukkit.event.inventory.InventoryType.HOPPER);
		slot(1, new ItemBuilder(Material.SLIME_BALL).setName(org.bukkit.ChatColor.GREEN + "Accept").create(), new ISlotAction(){

			public void doAction(GUI gui, Player p, ClickType type){
				gui.close(p);
				WindowYesNo.this.onAccept(gui, p);
			}
		});
		slot(3, new ItemBuilder(Material.MAGMA_CREAM).setName(org.bukkit.ChatColor.RED + "Decline").create(), new ISlotAction(){

			public void doAction(GUI gui, Player p, ClickType type){
				gui.close(p);
				WindowYesNo.this.onDecline(gui, p);
			}
		});
	}

	public abstract void onAccept(GUI paramGUI, Player paramPlayer);

	public abstract void onDecline(GUI paramGUI, Player paramPlayer);
}
