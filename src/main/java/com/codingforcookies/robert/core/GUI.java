package com.codingforcookies.robert.core;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import com.codingforcookies.robert.slot.ISlotAction;

public class GUI{

	private boolean allowClose = false;
	private int fixedScheduler;
	private boolean fixed = false;

	public int getFixedScheduler(){
		return fixedScheduler;
	}

	public boolean isFixed(){
		return this.fixed;
	}

	private String title = "[Unnamed GUI]";

	public String getTitle(){
		return this.title;
	}

	private InventoryType type = InventoryType.CHEST;

	public Inventory getInventory(){
		return this.inv;
	}

	public ISlotAction getSlot(int i){
		if(i >= this.slots.length) return null;
		if(i < 0){
			if((!this.fixed) || ((this.fixed) && (this.allowClose))) this.listener.onInventoryClosed(new InventoryCloseEvent(this.invView));
			return null;
		}
		return this.slots[i];
	}

	private Inventory inv;
	private InventoryView invView;
	private GUIListener listener;
	private ISlotAction[] slots;

	public GUI(String title){
		this.title = title;
		this.inv = Bukkit.createInventory(null, this.type, title);
		this.slots = new ISlotAction[this.inv.getSize()];
		this.listener = new GUIListener(this);
	}

	public GUI fixed(){
		this.fixed = true;
		return this;
	}

	public GUI type(InventoryType type){
		this.type = type;
		this.inv = Bukkit.createInventory(null, type, this.title);
		this.slots = new ISlotAction[this.inv.getSize()];
		return this;
	}

	public GUI type(int rows){
		this.type = InventoryType.CHEST;
		this.inv = Bukkit.createInventory(null, rows * 9, this.title);
		this.slots = new ISlotAction[this.inv.getSize()];
		return this;
	}

	public GUI slot(int slot, Material item, ISlotAction action){
		return slot(slot, new ItemStack(item), action);
	}

	public GUI slot(int x, int y, Material item, ISlotAction action){
		return slot(x, y, new ItemStack(item), action);
	}

	public GUI slot(int slot, ItemStack item, ISlotAction action){
		this.inv.setItem(slot, item);
		this.slots[slot] = action;
		return this;
	}

	public GUI slot(int x, int y, ItemStack item, ISlotAction action){
		return slot(x + y * 9, item, action);
	}

	public void open(Player p){
		this.listener.bind();
		this.invView = p.openInventory(this.inv);
	}

	public void close(Player p){
		this.allowClose = true;
		p.closeInventory();
	}

	public void onClose(Player p){}

	public void onClickPlayerInventory(GUI gui, Inventory inventory, int slot, Player p, ClickType clickType){}

	public InventoryView getInventoryView(){
		return invView;
	}

	public boolean allowClose(){
		return allowClose();
	}
}