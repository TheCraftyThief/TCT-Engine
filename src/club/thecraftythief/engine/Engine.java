package club.thecraftythief.engine;

import club.thecraftythief.engine.command.ModelsCommand;
import club.thecraftythief.engine.model.ModelMgr;
import co.aikar.commands.PaperCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Engine extends JavaPlugin {

    private static Engine instance;

    public static Engine getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {

        instance = this;

        super.onLoad();

        getLogger().info("Loaded TCT-Engine");
    }

    @Override
    public void onEnable() {
        getLogger().info("Enabling... TCT-Engine");
        super.onEnable();

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new ModelsCommand());

        new ModelMgr();

        getLogger().info("Enabled TCT-Engine");
    }
}