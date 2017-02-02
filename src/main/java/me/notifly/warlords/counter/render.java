package me.notifly.warlords.counter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Dan on 02/02/2017.
 */
public class render {

    @SubscribeEvent
    public void renderMethod(RenderGameOverlayEvent event){
        if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
        fRender.drawString(EnumChatFormatting.RED + "Damage: " + EnumChatFormatting.BOLD + damage.totalDamage, 5, 5, 0, true);
        fRender.drawString(EnumChatFormatting.GREEN + "Healing: " + EnumChatFormatting.BOLD + healing.totalHealing, 5, 20, 0, true);
    }

}