package cc.madcraft.shanezhi;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Events implements Listener
{
	@EventHandler
	public void OnInteract(PlayerInteractEvent e)
	{
		Action act=e.getAction();
		if(act.equals(Action.RIGHT_CLICK_AIR)||act.equals(Action.RIGHT_CLICK_BLOCK))
		{
			Player p=e.getPlayer();
			ItemStack item=p.getItemInHand();
			if(item.hasItemMeta()&&item.getItemMeta().hasLore())
			{
				if(item.getItemMeta().getLore().equals(Other.qxdlore))
				{
					if(Other.a.containsKey(p.getName()))
					{
						if(item.getAmount()>1)
						{
							item.setAmount(item.getAmount()-1);	
						}
						else
						{
							if(item.getAmount()==1)
							{
								p.setItemInHand(new ItemStack(Material.AIR));
							}
						}
						int b=Other.a.get(p.getName());
						int c=0;
						if(b==1)
						{
							Other.a.remove(p.getName());
							c=0;
						}
						else
						{
							Other.a.remove(p.getName());
							Other.a.put(p.getName(), b-1);
							c=b-1;
						}
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l��ɹ�ʹ����һ�����ĵ���ɱ��ֵ��1!"));
						if(c==0)
						{
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l�����ڵ�ɱ��ֵ��0"));
						}
						else
						{
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l�����ڵ�ɱ��ֵ��"+String.valueOf(c)));
						}
					}
					else
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l�Բ�������ɱ��ֵΪ0���޷�ʹ��"));
					}
				}
				
				if(item.getItemMeta().getLore().equals(Other.jmllore))
				{
					if(item.getAmount()>1)
					{
						item.setAmount(item.getAmount()-1);	
					}
					else
					{
						if(item.getAmount()==1)
						{
							p.setItemInHand(new ItemStack(Material.AIR));
						}
					}
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "jail "+p.getName());
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l���ѱ��ͷų�����"));
				}
			}
		}
	}
	@EventHandler
	public void OnSleepStart(PlayerBedEnterEvent e)
	{
		Player p=e.getPlayer();
		String name=e.getPlayer().getName();
		if(!Other.sleeped.contains(name))
		{
			Other.sleeped.add(name);
		}
		new BukkitRunnable()
		{
			public void run()
			{
				if(Bukkit.getOnlinePlayers().contains(p))
				{
					if(Other.sleeped.contains(name))
					{
						int vau=Integer.valueOf(Other.getvalue(name));
						if(vau>=1)
						{
							int a=vau-1;
							vau-=1;
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2&l��С˯��һ�����ɱ��Ҳ��ʼ����"));
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l�����ڵ�ɱ��ֵ��:"+String.valueOf(vau)));
							Other.a.remove(name);
							Other.a.put(name, vau);
							if(a==0)
							{
								Other.a.remove(name);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l���ɱ����ʧ��"));
								cancel();
							}
						}
					}
					else
					{
						cancel();
					}
				}
				else
				{
					cancel();
				}
			}
		}.runTaskTimer(Other.p, 600L, 600L);
	}
	@EventHandler
	public void OnSleepEnd(PlayerBedLeaveEvent e)
	{
		String name=e.getPlayer().getName();
		Other.sleeped.remove(name);
	}
	@EventHandler
	public void OnDeath(EntityDeathEvent e)
	{
		String worldname=e.getEntity().getWorld().getName();
		if(Other.worlds.contains(worldname))
		{
			if(e.getEntity().getType().equals(EntityType.PLAYER))
			{
				if(e.getEntity().getKiller()!=null)
				{
					Player killer=e.getEntity().getKiller();
					if(!Other.a.containsKey(killer.getName()))
					{
						Other.a.put(killer.getName(), 1);
					}
					else
					{
						int amount=Other.a.get(killer.getName());
						Other.a.remove(killer.getName());
						Other.a.put(killer.getName(), amount+1);
					}
					Player bk=(Player) e.getEntity();
					if(Other.a.containsKey(bk.getName()))
					{
						if(Other.a.get(bk.getName())>=Other.reached)
						{
							int va=Other.a.get(bk.getName());
							int d=va-Other.reached;
							int min=d*Other.mpu+Other.dt;
							if(va==Other.reached)
							{
								bk.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l�㱻ɱ���ˣ��������ɱ��ֵ����"+String.valueOf(Other.reached)+"�������㱻��ʱ�ؽ�����"));
								bk.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l����ʱ��:&4&l"+String.valueOf(min)+"����"));
								Other.jail(bk,min);
							}
							else
							{
								bk.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l�㱻ɱ���ˣ��������ɱ��ֵ����"+String.valueOf(Other.reached)+"�������㱻��ʱ�ؽ�����"));
								bk.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l����ʱ��:&4&l"+String.valueOf(min)+"����"));
								Other.jail(bk,min);
							}
						}
					}
					killer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l��ɱ����һ����ң����ɱ��ֵ��������Ŀǰ��ɱ��ֵΪ")+String.valueOf(Other.a.get(killer.getName())));
				}
			}
		}
	}
}
