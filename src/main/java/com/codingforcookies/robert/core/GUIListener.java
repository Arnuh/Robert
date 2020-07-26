package com.codingforcookies.robert.core;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.scheduler.BukkitRunnable;

import com.codingforcookies.robert.slot.ISlotAction;

public class GUIListener implements org.bukkit.event.Listener{

	private final GUI gui;

	public GUIListener(GUI gui){
		this.gui = gui;
	}

	public void bind(){
		Robert.getInstance().registerGUI(this);
	}

	public void unbind(){
		InventoryClickEvent.getHandlerList().unregister(this);
		InventoryDragEvent.getHandlerList().unregister(this);
		InventoryCloseEvent.getHandlerList().unregister(this);
	}

	private boolean isGUI(Inventory inventory){
		return this.gui.getInventoryView().getTopInventory() == inventory;
	}

	private boolean isGUI(InventoryView view){
		return this.gui.getInventoryView() == view;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		if(!isGUI(event.getInventory())){
			return;
		}
		event.setCancelled(true);
		event.setResult(Event.Result.DENY);
		if(event.getClickedInventory().equals(event.getWhoClicked().getInventory())){
			this.gui.onClickPlayerInventory(this.gui, event.getClickedInventory(), event.getSlot(), (Player) event.getWhoClicked(), event.getClick());
			return;
		}
		ISlotAction action = this.gui.getSlot(event.getSlot());
		if(action != null){
			action.doAction(this.gui, (Player) event.getWhoClicked(), event.getClick());
		}
	}
	
	@EventHandler
	public void onInventoryDrag(InventoryDragEvent event){
		if(!isGUI(event.getInventory())){
			return;
		}
		event.setCancelled(true);
		event.setResult(Event.Result.DENY);
	}

	@EventHandler
	public void onInventoryClosed(final InventoryCloseEvent event){
		if(!isGUI(event.getView())){
			return;
		}
		this.gui.onClose((Player) event.getPlayer());
		unbind();
		if(this.gui.isFixed() && !this.gui.allowClose()){
			new BukkitRunnable(){

				public void run(){
					gui.open((Player) event.getPlayer());
				}
			}.runTaskLater(Robert.getInstance().getPlugin(), 1L);
		}
	}
}
