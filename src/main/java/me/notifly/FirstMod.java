package me.notifly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import sun.font.FontResolver;

@Mod(modid = FirstMod.MODID, version = FirstMod.VERSION, name = FirstMod.NAME)
public class FirstMod {
    public static final String MODID = "first_mod";
    public static final String VERSION = "1.0";
    public static final String NAME = "First Mod";
    public static String message = "";
    public static String game = "";
    public static Integer totalDamage = 0;
    public static Integer damageDigit = 0;
    public static Integer damage = 0;
    public static String warlords = "False";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void init(FMLPostInitializationEvent event) {

    }

    public static void displayMsg(String msg) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
         String message = event.message.getUnformattedText();

        // Warlords Damage/Health counter

        if (message.contains("The gates will fall in "))  {
            totalDamage = 0;
            warlords = "True";
        }

        if (message.contains("TOP DAMAGE")) {
            warlords = "False";
        }

        if (warlords == "True") {

            if (message.contains("You") && message.contains(" hit ") && message.contains(" for ") && message.contains(" damage.")) {
                String game = "warlords";

                if (!message.contains(" critical ")) {
                    String endOfMsg = message.substring(message.length() - 12, (message.length()));
                    if (Character.isDigit(endOfMsg.charAt(0))) {
                        damageDigit = 1000; // damage > 1000
                        int damage = Integer.parseInt(endOfMsg.substring(0, 4));
                        totalDamage = totalDamage + damage;
                    }

                    if (Character.isWhitespace(endOfMsg.charAt(0))) {
                        damageDigit = 100; // damage >= 100, damage < 1000
                        int damage = Integer.parseInt(endOfMsg.substring(1, 4));
                        totalDamage = totalDamage + damage;
                    }

                    if (endOfMsg.startsWith("r")) {
                        damageDigit = 10; // damage >= 10, damage < 100
                        int damage = Integer.parseInt(endOfMsg.substring(2, 4));
                        totalDamage = totalDamage + damage;
                    }

                    if (endOfMsg.startsWith("o")) {
                        damageDigit = 1; // damage < 10
                        int damage = Integer.parseInt(endOfMsg.substring(3, 4));
                        totalDamage = totalDamage + damage;
                    }
                }

                if (message.contains("! critical damage.")) {
                    String endOfMsg = message.substring(message.length() - 22, (message.length()));

                    if (Character.isDigit(endOfMsg.charAt(0))) {
                        damageDigit = 1000; // damage > 1000
                        int damage = Integer.parseInt(endOfMsg.substring(0, 4));
                        totalDamage = totalDamage + damage;
                    }

                    if (Character.isWhitespace(endOfMsg.charAt(0))) {
                        damageDigit = 100; // damage >= 100, damage < 1000
                        int damage = Integer.parseInt(endOfMsg.substring(1, 4));
                        totalDamage = totalDamage + damage;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {
        if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
        fRender.drawString(EnumChatFormatting.DARK_GREEN + "Damage: " + EnumChatFormatting.BOLD + totalDamage, 5, 5, 0, true);
    }
}

