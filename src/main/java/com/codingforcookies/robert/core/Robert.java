package com.codingforcookies.robert.core;

import org.bukkit.plugin.java.JavaPlugin;

public class Robert extends JavaPlugin{

	private static Robert robert;
	private JavaPlugin plugin;

	public static Robert getInstance(){
		return robert;
	}

	public JavaPlugin getPlugin(){
		return this.plugin;
	}

	public void onEnable(){
		robert = this;
		plugin = this;
	}

	public static void enablePortable(JavaPlugin plugin){
		if(robert != null) return;

		robert = new Robert();
		robert.plugin = plugin;
	}

	public void registerGUI(GUIListener guiListener){
		plugin.getServer().getPluginManager().registerEvents(guiListener, this.plugin);
	}
}
