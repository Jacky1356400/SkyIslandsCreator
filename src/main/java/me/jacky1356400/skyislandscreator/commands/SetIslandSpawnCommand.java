package me.jacky1356400.skyislandscreator.commands;

import me.jacky1356400.skyislandscreator.island.IslandUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

public class SetIslandSpawnCommand extends CommandBase implements ICommand {

    private List<String> aliases;

    public SetIslandSpawnCommand() {
        aliases = new ArrayList<>();
        aliases.add("skyislands_setspawn");
        aliases.add("skyisland_setspawn");
    }

    @Override
    public List getAliases() {
        return aliases;
    }

    @Override
    public String getName() {
        return aliases.get(0);
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "skyislands_setspawn <IslandName> or skyislands_setspawn <IslandName> <X> <Y> <Z>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] input) {
        String islandName = input[0];
        if (input.length == 4) {
            int x = Integer.parseInt(input[1]);
            int y = Integer.parseInt(input[2]);
            int z = Integer.parseInt(input[3]);
            IslandUtils.setSpawnForIsland(islandName, new BlockPos(x, y, z));
            sender.sendMessage(new TextComponentString("Island Spawn set."));
        } else if (input.length == 1) {
            IslandUtils.setSpawnForIsland(islandName, sender.getPosition());
            sender.sendMessage(new TextComponentString("Island Spawn set."));
        } else {
            sender.sendMessage(new TextComponentString("Invalid arguments!"));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

}
