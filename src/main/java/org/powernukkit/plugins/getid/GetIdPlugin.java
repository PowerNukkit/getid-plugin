package org.powernukkit.plugins.getid;

import cn.nukkit.command.PluginCommand;
import cn.nukkit.plugin.PluginBase;

public class GetIdPlugin extends PluginBase {
    @Override
    public void onEnable() {
        new GetIdCommand(((PluginCommand<?>) getCommand("getid")));
    }
}
