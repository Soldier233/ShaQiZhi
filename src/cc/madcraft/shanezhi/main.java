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
		getLogger().info("ɱ��ֵ������سɹ�");
		getLogger().info("������������ѯQQ 1224954468");
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
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c����Ҳ����ߣ�"));
						}
					}
					else
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&l�Բ���Ȩ�޲��㣡"));
					}
				}
				if(sender instanceof Player)
				{
					if(args[0].equals("me"))
					{
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l&m    &6&l&m    &e&l&m    &2&l&m    &3&l&m    &b&l&m    &1&l&m    "));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l���ɱ��ֵ:&6&l"+String.valueOf(other.getvalue(sender.getName()))));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l����ʹ�����ĵ�����ɱ��ֵ"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l��ɱ��ֵ����"+other.reached+"������ɱ������������"+other.dt+"����"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lɱ��ֵԽ�࣬����ʱ��Խ��"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l&m    &6&l&m    &e&l&m    &2&l&m    &3&l&m    &b&l&m    &1&l&m    "));
					}
				}
				else
				{
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lֻ����Ҳ���ִ�и�����"));
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
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c����Ҳ����ߣ�"));
						}
					}
					else
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&l�Բ���Ȩ�޲��㣡"));
					}
				}
			}
		}
		return true;
	}
	public void sendhelp(CommandSender sender)
	{
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&l&m  &6&l&m  &e&l&m  &2&l&m  &3&l&m  &b&l&m  &1&l&m  &a&lɱ��ֵ&1&l&m  &b&l&m  &3&l&m  &2&l&m  &e&l&m  &6&l&m  &c&l&m  "));
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l/shaqizhi me &6&l��ѯ�Լ���ɱ��ֵ���"));
		if(sender.isOp())
		{
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l/shaqizhi qxd ����� ���� &6&l��ȡ���ĵ�"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l/shaqizhi jml ����� ���� &6&l��ȡ������"));
		}
		
	}
}
