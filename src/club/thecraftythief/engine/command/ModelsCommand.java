package club.thecraftythief.engine.command;

import club.thecraftythief.engine.Engine;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.github.lory24.mcuitils.McGUI;
import com.github.lory24.mcuitils.api.GUIButtonEvents;
import com.github.lory24.mcuitils.api.GUICustomItem;
import com.github.lory24.mcuitils.api.GUItem;
import com.github.lory24.mcuitils.utils.GuiLines;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandAlias("models")
public class ModelsCommand extends BaseCommand {
    @Default
    public void onCommand(Player runner) {
        McGUI gui = new McGUI("Models", GuiLines.SIX_LINES, Engine.getInstance());

        //9*6 because theres 9 total slots in one line and 6 total lines
        for(int i = 0; i < 9*6; i++) {
            ItemStack iStack = new ItemStack(Material.STICK);
            ItemMeta meta = iStack.getItemMeta();
            meta.setCustomModelData(i);
            iStack.setItemMeta(meta);
            GUItem item = new GUItem(Material.STICK) {
                @Override
                public ItemStack buildToItemStack() {
                    return iStack;
                }
            };
            gui.createButton(item, i, new GUIButtonEvents(() -> {
                //Left click actions
                runner.getInventory().addItem(iStack);
            }));
        }

        gui.openInventoryTo(runner);
    }
}
