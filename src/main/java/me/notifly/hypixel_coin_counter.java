package me.notifly;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Dan on 31/01/2017.
 */

@Mod(modid = hypixel_coin_counter.MODID, version = hypixel_coin_counter.VERSION, name = hypixel_coin_counter.NAME)
public class hypixel_coin_counter {
    public static final String MODID = "hypixel_coin_counter";
    public static final String VERSION = "1.0";
    public static final String NAME = "Hypixel Coin Counter";
    public static String message = "";
    public static String inGame = "";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event) {

    }

    public static void displayMsg(String msg) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        message = event.message.getUnformattedText();

        if (message.contains("Sending you to mini") & message.contains("!")) {
            inGame = "True";
        }

        if (inGame == "True") {
            displayMsg("Hi");
        }
    }
}

