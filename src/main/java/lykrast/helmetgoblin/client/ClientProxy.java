package lykrast.helmetgoblin.client;

import lykrast.helmetgoblin.common.CommonProxy;
import lykrast.helmetgoblin.common.EntityGoblin;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		RenderingRegistry.registerEntityRenderingHandler(EntityGoblin.class, RenderGoblin::new);
	}

}
