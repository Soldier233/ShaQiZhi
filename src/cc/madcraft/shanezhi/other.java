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
		qxdlore.add(ChatColor.translateAlternateColorCodes('&', "&a&l����:  &b&l���ĵ�"));
		qxdlore.add(ChatColor.translateAlternateColorCodes('&', "&a&l����:  &b&l����һ��ɱ��ֵ"));
		qxdlore.add(ChatColor.translateAlternateColorCodes('&', "&a&l      �Ҽ�ʹ��"));
		qxdlore.add(ChatColor.translateAlternateColorCodes('&', "&6&l&m                    "));
		ItemMeta im=qxd.getItemMeta();
		im.setLore(qxdlore);
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&l���ĵ�"));
		qxd.setItemMeta(im);
		//���ĵ�����
		jmllore.add("");
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&6&l&m                    "));
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&a&l����:  &b&l������"));
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&a&l����:  &b&lֱ�ӽ����ͷų���"));
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&c&l�����ڼ���ʱ������������Ը�"));
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&a&l      �Ҽ�ʹ��"));
		jmllore.add(ChatColor.translateAlternateColorCodes('&', "&6&l&m                    "));
		ItemMeta imjml=jml.getItemMeta();
		imjml.setLore(jmllore);
		imjml.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&l������"));
		jml.setItemMeta(imjml);
		//����������
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
