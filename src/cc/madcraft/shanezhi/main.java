package cc.madcraft.shanezhi;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin
{
    public File conf=new File(getDataFolder(),"config.yml");
	public FileConfiguration config=load(conf);
	public FileConfiguration load(File file)
	{
		if(!file.exists())
		{
			try
			{
				file.createNewFile();
	        }
	        catch(IOException e)
	        {
	        	e.printStackTrace();
	        }
	    }
	    return YamlConfiguration.loadConfiguration(file);
	}
	public FileConfiguration load(String path)
	{
		File file=new File(path);
		if(!file.exists())
		{
			try
			{
				file.createNewFile();
	        }
	        catch(IOException e)
	        {
	        	e.printStackTrace();
	        }
	    }
	    return YamlConfiguration.loadConfiguration(file);
	}
	public void onEnable()
	{
		if(!getDataFolder().exists())
		{ 
			getDataFolder().mkdir();
			for(World w:Bukkit.getWorlds())
			{
				String name=w.getName();
				config.set("Config.enable."+name, Boolean.valueOf(true));
			}
			config.set("Config.general.reached", 3);
			config.set("Config.general.defaulttime",30);
			config.set("Config.general.minsperup", 10);
			try
			{
				config.save(conf);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			other.reached=config.getInt("Config.general.reached");
			other.dt=config.getInt("Config.general.defaulttime");
			other.mpu=config.getInt("Config.general.minsperup");
			for(World w:Bukkit.getWorlds())
			{
				if(config.getBoolean("Config.enable."+w.getName()))
				{
					other.worlds.add(w.getName());
				}
			}
		}
		else
		{
			for(World w:Bukkit.getWorlds())
			{
				String name=w.getName();
				if(!config.contains("Config.enable."+name))
				{
					config.set("Config.enable."+name, Boolean.valueOf(true));
				}
			}
			other.reached=config.getInt("Config.general.reached");
			other.dt=config.getInt("Config.general.defaulttime");
			other.mpu=config.getInt("Config.general.minsperup");
			for(World w:Bukkit.getWorlds())
			{
				if(config.getBoolean("Config.enable."+w.getName()))
				{
					other.worlds.add(w.getName());
				}
			}
		}
		other.init(this);
		getLogger().info("杀气值插件加载成功");
		getLogger().info("插件问题可以咨询QQ 1224954468");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equals("shaqizhi"))
		{
			if(args.length==0)
			{
				sendhelp(sender);
			}
			else
			{
				if(args[0].equals("qxd"))
				{
					Player p=(Player) sender;
					if(p.isOp())
					{
						Player bo=Bukkit.getPlayer(args[1]);
						if(bo.isOnline())
						{
							int am=Integer.valueOf(args[2]);
							while(am>0)
							{
								bo.getInventory().addItem(other.qxd);
								am-=1;
							}
						}
						else
						{
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c该玩家不在线！"));
						}
					}
					else
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&l对不起，权限不足！"));
					}
				}
				if(sender instanceof Player)
				{
					if(args[0].equals("me"))
					{
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l&m    &6&l&m    &e&l&m    &2&l&m    &3&l&m    &b&l&m    &1&l&m    "));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l你的杀气值:&6&l"+String.valueOf(other.getvalue(sender.getName()))));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l可以使用清心丹降低杀气值"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l若杀气值大于"+other.reached+"，被人杀死会囚禁最少"+other.dt+"分钟"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l杀气值越多，囚禁时间越长"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l&m    &6&l&m    &e&l&m    &2&l&m    &3&l&m    &b&l&m    &1&l&m    "));
					}
				}
				else
				{
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l只有玩家才能执行该命令"));
				}
				if(args[0].equals("jml"))
				{
					Player p=(Player) sender;
					if(p.isOp())
					{
						Player bo=Bukkit.getPlayer(args[1]);
						if(bo.isOnline())
						{
							int am=Integer.valueOf(args[2]);
							while(am>0)
							{
								bo.getInventory().addItem(other.jml);
								am-=1;
							}
						}
						else
						{
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c该玩家不在线！"));
						}
					}
					else
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&l对不起，权限不足！"));
					}
				}
			}
		}
		return true;
	}
	public void sendhelp(CommandSender sender)
	{
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&l&m  &6&l&m  &e&l&m  &2&l&m  &3&l&m  &b&l&m  &1&l&m  &a&l杀气值&1&l&m  &b&l&m  &3&l&m  &2&l&m  &e&l&m  &6&l&m  &c&l&m  "));
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l/shaqizhi me &6&l查询自己的杀气值情况"));
		if(sender.isOp())
		{
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l/shaqizhi qxd 玩家名 数量 &6&l获取清心丹"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l/shaqizhi jml 玩家名 数量 &6&l获取减免令"));
		}
		
	}
}
