package me.jacky1356400.skyislandscreator.commands;

import me.jacky1356400.skyislandscreator.island.IslandCreator;
import me.jacky1356400.skyislandscreator.proxy.CommonProxy;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListIslandsCommand extends CommandBase implements ICommand {

    private List<String> aliases;

    public ListIslandsCommand() {
        aliases = new ArrayList<>();
        aliases.add("skyislands_list");
        aliases.add("skyisland_list");
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
        return "skyislands_list";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        final EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        try {
            for (Map.Entry<String, IslandCreator.IslandPos> entry : CommonProxy.getIslands().entrySet()) {
                String key = entry.getKey();
                player.sendMessage(new TextComponentString(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
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
