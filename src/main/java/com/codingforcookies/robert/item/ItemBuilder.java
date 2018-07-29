package com.codingforcookies.robert.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder{

	private ItemStack stack;
	private String name;
	private List<String> lore;
	private List<ItemFlag> flags;

	public ItemBuilder(Material material){
		this(new ItemStack(material));
	}

	public ItemBuilder(ItemStack stack){
		this.stack = stack;
		this.lore = new ArrayList<>();
		this.flags = new ArrayList<>();
	}

	public ItemBuilder setName(String name){
		this.name = (ChatColor.WHITE + name);
		return this;
	}

	public ItemBuilder addLore(String... lines){
		for(String line : lines)
			this.lore.add(ChatColor.GRAY + line);
		return this;
	}

	public ItemBuilder addFlag(ItemFlag... flags){
		for(ItemFlag flag : flags)
			this.flags.add(flag);
		return this;
	}

	public ItemStack create(){
		ItemMeta meta = this.stack.getItemMeta();
		if(((this.name == "") || (this.name == null)) && (this.lore.size() > 0)){
			meta.setDisplayName((String) this.lore.get(0));
			this.lore.remove(0);
			meta.setLore(this.lore);
		}else{
			meta.setDisplayName(ChatColor.RESET + this.name);
			meta.setLore(this.lore);
		}
		for(ItemFlag flag : this.flags){
			meta.addItemFlags(new ItemFlag[]{flag});
		}
		this.stack.setItemMeta(meta);
		return this.stack;
	}
}
