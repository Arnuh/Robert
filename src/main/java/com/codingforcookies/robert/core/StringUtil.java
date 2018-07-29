package com.codingforcookies.robert.core;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;

public class StringUtil{

	private static Map<Integer, String> charList = new HashMap<>();
	static{
		charList.put(-6, "§");
		charList.put(2, "!.,:;i|");
		charList.put(3, "'`l");
		charList.put(4, " I[]t");
		charList.put(5, "\"()*<>fk{}");
		charList.put(7, "@~");
	}
	private static final int SPACE_WIDTH = pxLen(' ');

	private static int pxLen(char ch){
		int l = 6;
		for(Map.Entry<Integer, String> entry : charList.entrySet()){
			if(entry.getValue().indexOf(ch) >= 0){
				l = entry.getKey();
			}
		}
		return l;
	}

	public static int getStringWidth(String str){
		int width = 0;
		for(char c : str.toCharArray()){
			width += pxLen(c);
		}
		return width;
	}

	public static String center(int width, String str){
		int strWidth = getStringWidth(ChatColor.stripColor(str).replace("\n", ""));
		while(strWidth + SPACE_WIDTH * 2 <= width){
			str = " " + str + " ";
			strWidth = getStringWidth(ChatColor.stripColor(str).replace("\n", ""));
		}
		if(strWidth + SPACE_WIDTH <= width){
			str = " " + str;
		}
		return str;
	}
}
