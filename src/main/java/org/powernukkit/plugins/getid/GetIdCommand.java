package org.powernukkit.plugins.getid;

import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.item.ItemID;
import cn.nukkit.lang.TranslationContainer;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class GetIdCommand implements CommandExecutor {
    GetIdCommand(PluginCommand<?> command) {
        command.setExecutor(this);
        command.getCommandParameters().clear();
        command.getCommandParameters().put("default", new CommandParameter[] {
                new CommandParameter("blockOrItemName", false,
                        Stream.of(BlockID.class, ItemID.class)
                                .map(Class::getDeclaredFields)
                                .flatMap(Arrays::stream)
                                .filter(f-> f.getModifiers() == (Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL))
                                .map(f-> f.getName().toLowerCase())
                                .toArray(String[]::new)        
                )
        });
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (args.length < 1 || args[0].trim().isEmpty()) {
            commandSender.sendMessage(new TranslationContainer("commands.generic.usage", command.getUsage()));
        }
        String name = args[0].trim();
        OptionalInt id = findId(ItemID.class, name);
        if (!id.isPresent()) {
            id = findId(BlockID.class, name);
            if (id.isPresent()) {
                int blockId = id.getAsInt();
                if (blockId > 255) {
                    id = OptionalInt.of(255 - blockId);
                }
            }
        }
        
        if (!id.isPresent()) {
            commandSender.sendMessage(name+" was not found");
        } else {
            commandSender.sendMessage(name + " = " + id.getAsInt());
        }
        return true;
    }
    
    private OptionalInt findId(Class<?> c, String name) {
        return Arrays.stream(c.getDeclaredFields())
                .filter(f -> f.getModifiers() == (Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL))
                .filter(f -> f.getName().equalsIgnoreCase(name))
                .mapToInt(f -> {
                    try {
                        return f.getInt(null);
                    } catch (IllegalAccessException e) {
                        throw new InternalError(e);
                    }
                }).findFirst();
    }
}
