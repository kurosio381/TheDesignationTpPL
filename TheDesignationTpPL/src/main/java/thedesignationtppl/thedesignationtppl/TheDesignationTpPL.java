package thedesignationtppl.thedesignationtppl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TheDesignationTpPL extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("tdtp")) {
            if (args.length <= 0) {
                return false;
            }
            if (args[0].equalsIgnoreCase("set")) {
                if (args.length <= 1) {
                    return false;
                }
                String name0 = args[1];
                if (name0 == null) return false;
                if (args[1].equalsIgnoreCase(name0)){
                    Location loc = p.getLocation();
                    World world = loc.getWorld();
                    if (world == null) return false;
                    String name = world.getName();
                    int x = loc.getBlockX();
                    int y = loc.getBlockY();
                    int z = loc.getBlockZ();
                    int yaw = (int) loc.getYaw();
                    int pitch = (int) loc.getPitch();
                    getConfig().set(name0,name + "," + x + "," + y + "," + z + "," + yaw + "," + pitch);
                    saveConfig();
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("warp")) {
                if (args.length <= 1) {
                    return false;
                }
                if (args[1].equalsIgnoreCase(args[1])) {
                    String data = getConfig().getString(args[1]);
                    if (data == null) return false;
                    String[] loc = data.split(",");
                    World world = Bukkit.getServer().getWorld(loc[0]);
                    double x = Double.parseDouble(loc[1]);
                    double y = Double.parseDouble(loc[2]);
                    double z = Double.parseDouble(loc[3]);
                    int yaw = (int) Double.parseDouble(loc[4]);
                    int pitch = (int) Double.parseDouble(loc[5]);
                    Location location = new Location(world, x, y, z);
                    location.setPitch(pitch);
                    location.setYaw(yaw);
                    p.teleport(location);
                    return true;
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                reloadConfig();
                sender.sendMessage("configリロードしました");
                return true;
            }
            if (args[0].equalsIgnoreCase("remove")) {
                if (args.length <= 1) {
                    return false;
                }
                if (args[1].equalsIgnoreCase(args[1])) {
                    String data = getConfig().getString(args[1]);
                    if (data == null) return false;
                    getConfig().set(data, null);
                    saveConfig();
                    return true;
                }
                return true;
            }
        }
        return true;
    }
}
