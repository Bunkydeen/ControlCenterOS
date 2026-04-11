package com.controlcenter.shizuku;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.PluginMethod;

@CapacitorPlugin(name = "Shell")
public class ShellPlugin extends Plugin {

    @PluginMethod
    public void exec(PluginCall call) {
        String cmd = call.getString("command");
        ShizukuRunner.run(cmd);
        call.resolve();
    }
}
