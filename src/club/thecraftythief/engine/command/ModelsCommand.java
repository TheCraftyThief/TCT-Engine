package club.thecraftythief.engine.command;

import club.thecraftythief.engine.Engine;
import club.thecraftythief.engine.model.ModelData;
import club.thecraftythief.engine.model.ModelMgr;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.github.lory24.mcuitils.McGUI;
import com.github.lory24.mcuitils.api.GUIButtonEvents;
import com.github.lory24.mcuitils.api.GUItem;
import com.github.lory24.mcuitils.utils.GuiLines;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandAlias("model|models")
public class ModelsCommand extends BaseCommand {
    @Default
    @CommandPermission("tct.engine.model.give")
    public void onCommand(Player runner) {
        //Lory pls make ur api better
        McGUI gui = new McGUI("Models", GuiLines.SIX_LINES, Engine.getInstance());

        //9*6 because theres 9 total slots in one line and 6 total lines
        for (int i = 0; i < 9 * 6; i++) {
            ModelMgr modelMgr = ModelMgr.getInstance();
            ModelData model = modelMgr.getModelByID(i);
            ItemStack iStack;
            if (model == null) {
                runner.sendMessage("No model found for ID: " + i);
                //Code to help model testing on client. TCT won't know its a model, but at least the client can display it.
                iStack = new ItemStack(Material.STICK);
                ItemMeta meta = iStack.getItemMeta();
                meta.setCustomModelData(i);
                iStack.setItemMeta(meta);
            } else {
                //Hooray, the model exists, use its ItemStack instead since it has proper meta!
                iStack = model.getItemStack();
            }
            //Lory pls make ur API better
            GUItem item = new GUItem(Material.STICK) {
                @Override
                public ItemStack buildToItemStack() {
                    return iStack;
                }
            };
            //Lory, good API
            gui.createButton(item, i, new GUIButtonEvents(() -> {
                //Left click actions
                runner.getInventory().addItem(iStack);
            }));
        }

        gui.openInventoryTo(runner);
    }

    @Subcommand("spawn")
    @CommandPermission("tct.engine.model.spawn")
    public void onSpawn(Player runner, String modelName) {
        ModelMgr modelMgr = ModelMgr.getInstance();
        try {
            modelMgr.spawnModelByName(modelName, runner.getLocation());
            runner.sendMessage("Model spawned!");
        } catch (Exception ex) {
            runner.sendMessage(ex.getMessage());
        }
    }
}
