package com.nukkitx.exampleplugin;

import com.nukkitx.api.Server;
import com.nukkitx.api.event.Listener;
import com.nukkitx.api.event.player.PlayerJoinEvent;
import com.nukkitx.api.event.server.ServerInitializationEvent;
import com.nukkitx.api.event.server.ServerShutdownEvent;
import com.nukkitx.api.event.server.ServerStartEvent;
import com.nukkitx.api.message.TextFormat;
import com.nukkitx.api.message.TipMessage;
import com.nukkitx.api.plugin.Plugin;
import com.nukkitx.api.plugin.PluginDescription;
import com.nukkitx.exampleplugin.generator.DiscoChunkGenerator;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.nio.file.Path;

@Plugin(id = "ExamplePlugin", authors = {"NukkitX Team"}, version = "1.0.0")
public class ExamplePlugin {
    private final Logger logger;
    private final PluginDescription description;
    private final Path dataFolder;
    private final Server server;

    @Inject
    private ExamplePlugin(Logger logger, PluginDescription description, Path dataFolder, Server server) {
        this.logger = logger;
        this.description = description;
        this.dataFolder = dataFolder;
        this.server = server;
    }

    /*
     * This event is called before the server has fully loaded.
     */
    @Listener
    public void onInitialization(ServerInitializationEvent event) {
        logger.info(TextFormat.DARK_GREEN + description.getId() + " initialization!");
        server.getGeneratorRegistry().register("DISCO", DiscoChunkGenerator::new);
    }

    /*
     * This event is called after the server is fully loaded.
     */
    @Listener
    public void onStart(ServerStartEvent event) {
        logger.info(TextFormat.GREEN + description.getId() + " started!");

    }

    /*
     * This event is called before the server has fully shuts down.
     */
    @Listener
    public void onShutdown(ServerShutdownEvent event) {
        logger.info(TextFormat.DARK_RED + description.getId() + " shutting down!");
    }

    @Listener
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(new TipMessage("Welcome to the test server! This is experimental server software so there may be bugs."));
    }
}
