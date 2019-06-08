package lykrast.helmetgoblin.client;

import lykrast.helmetgoblin.HelmetGoblin;
import lykrast.helmetgoblin.common.EntityGoblin;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderGoblin extends RenderBiped<EntityGoblin> {
	public static final ResourceLocation TEXTURES = HelmetGoblin.loc("textures/entities/helmet_goblin.png");

	public RenderGoblin(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelGoblin(), 0.5F);
		this.addLayer(new LayerBipedArmor(this) {
			@Override
			protected void initArmor() {
				this.modelLeggings = new ModelGoblin(0.5F);
				this.modelArmor = new ModelGoblin(1.0F);
			}
		});
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityGoblin entity) {
		return TEXTURES;
	}

}
