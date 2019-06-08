package lykrast.helmetgoblin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lykrast.helmetgoblin.common.CommonProxy;
import lykrast.helmetgoblin.common.EntityGoblin;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = HelmetGoblin.MODID, 
	name = HelmetGoblin.NAME, 
	version = HelmetGoblin.VERSION, 
	acceptedMinecraftVersions = "[1.12, 1.13)")
@Mod.EventBusSubscriber
public class HelmetGoblin {
	public static final String MODID = "helmetgoblin";
	public static final String NAME = "Helmet Goblin";
	public static final String VERSION = "@VERSION@";

	public static Logger logger = LogManager.getLogger(MODID);
	
	@SidedProxy(clientSide = "lykrast.helmetgoblin.client.ClientProxy", serverSide = "lykrast.helmetgoblin.common.CommonProxy")
	public static CommonProxy proxy;
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		EntityEntryBuilder<?> builder = EntityEntryBuilder.create()
				.entity(EntityGoblin.class)
				.name(MODID + ".helmet_goblin")
				.id(loc("helmet_goblin"), 1)
				.tracker(64, 3, true)
				.egg(0x97C87B, 0x77673C);
		builder.spawn(EnumCreatureType.MONSTER, 80, 3, 5, ForgeRegistries.BIOMES.getValuesCollection());
		event.getRegistry().register(builder.build());
		
		LootTableList.register(EntityGoblin.LOOT);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}
	
	public static final ResourceLocation loc(String name) {
		return new ResourceLocation(MODID, name);
	}
}
