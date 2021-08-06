package club.thecraftythief.engine;

import club.thecraftythief.engine.command.EditorCommand;
import club.thecraftythief.engine.command.ModelsCommand;
import club.thecraftythief.engine.command.SpawnCommand;
import club.thecraftythief.engine.map.RoomMgr;
import club.thecraftythief.engine.model.ModelEventListener;
import club.thecraftythief.engine.model.ModelMgr;
import club.thecraftythief.engine.party.PartyMgr;
import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Engine extends JavaPlugin {

    private static Engine instance;
    public static Engine getInstance() {
        return instance;
    }

    private static RoomMgr roomMgr;
    public static RoomMgr getRoomMgr() {
        return roomMgr;
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

        new ModelMgr();
        new PartyMgr();
        roomMgr = new RoomMgr();

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new ModelsCommand());
        manager.registerCommand(new SpawnCommand());
        manager.registerCommand(new EditorCommand());

        Bukkit.getPluginManager().registerEvents(new ModelEventListener(), this);

        getLogger().info("Enabled TCT-Engine");
    }

    public ModelMgr getModelManager() {
        return ModelMgr.getInstance();
    }

    public PartyMgr getPartyManager() {
        return PartyMgr.getInstance();
    }
}