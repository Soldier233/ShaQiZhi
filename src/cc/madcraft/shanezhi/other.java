package cc.madcraft.shanezhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class other
{
	public static Plugin p;
	public static int reached,mpu,dt;
	public static HashMap<String,Integer> a=new HashMap();
	public static List<String> qxdlore=new ArrayList();
	public static ItemStack qxd=new ItemStack(Material.SLIME_BALL,1);
	public static List<String> jmllore=new ArrayList();
	public static List<String> sleeped=new ArrayList();
	public static List<String> worlds=new ArrayList();
	public static ItemStack jml=new ItemStack(Material.PAPER,1);
	public static void init(Plugin pl)
	{
		p=pl;
		Bukkit.getPluginManager().registerEvents(new events(),p);
		qxdlore.add("");
		qxdlore.add(ChatColor.translateAlternateColorCodes('&', "&6&l&m                    "));
		qxdlore.add(ChatColor.translateAlternateColorCodes('&', "&a&l名称:  &b&l清心丹"));
		qxdlore.add(ChatColor.translateAlternateColorCodes('&', "&a&l功能:  &b&l消除一点杀气值"));
		qxdlore.add(ChatColor.translateAlternateColorCodes('&', "&a&l      右键使用"));
		qxdlore.add(ChatColor.translateAlternateColorCodes('&', "&6&l&m                    "));
		ItemMeta im=qxd.getItemMeta();
		im.setLore(qxdlore);
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&l清心丹"));
		qxd.setItemMeta(im);
		//清心丹内容
		jmllore.add("");
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&6&l&m                    "));
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&a&l名称:  &b&l减免令"));
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&a&l功能:  &b&l直接将您释放出狱"));
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&c&l请勿不在监狱时点击，否则后果自负"));
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&a&l      右键使用"));
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&6&l&m                    "));
		ItemMeta imjml=jml.getItemMeta();
		imjml.setLore(jmllore);
		imjml.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&l减免令"));
		jml.setItemMeta(imjml);
		//减免令内容
	}
	public static void jail(Player p,int time)
	{
		a.remove(p.getName());
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "jail "+p.getName()+" jail "+String.valueOf(time)+"m");
	}
	public static String getvalue(String name)
	{
		if(a.containsKey(name))
		{
			return String.valueOf(a.get(name));
		}
		else
		{
			return "0";
		}
	}
}
