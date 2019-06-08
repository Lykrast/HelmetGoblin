package lykrast.helmetgoblin.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelGoblin extends ModelBiped {

	public ModelGoblin() {
		this(0.0F);
	}

	public ModelGoblin(float modelSize) {
		super(modelSize, 0.0F, 64, 32);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, modelSize);
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, modelSize + 0.5F);
        this.bipedHeadwear.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 18);
        this.bipedBody.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 10, 4, modelSize);
        this.bipedRightArm = new ModelRenderer(this, 40, 18);
        this.bipedRightArm.setRotationPoint(-5.0F, 8.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 10, 4, modelSize);
        this.bipedLeftArm = new ModelRenderer(this, 40, 18);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 8.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 10, 4, modelSize);
        this.bipedRightLeg = new ModelRenderer(this, 0, 20);
        this.bipedRightLeg.setRotationPoint(-2.0F, 16.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, modelSize);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 20);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.setRotationPoint(2.0F, 16.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, modelSize);
	}
	

    @Override
    @SuppressWarnings("incomplete-switch")
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	//Copied from ModelBiped
		bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
		bipedHead.rotateAngleX = headPitch * 0.017453292F;

		bipedBody.rotateAngleY = 0.0F;

		bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		bipedRightArm.rotateAngleZ = 0.0F;
		bipedLeftArm.rotateAngleZ = 0.0F;
		bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		bipedLeftLeg.rotateAngleY = 0.0F;
		bipedRightLeg.rotateAngleZ = 0.0F;
		bipedLeftLeg.rotateAngleZ = 0.0F;

		if (isRiding) {
			bipedRightArm.rotateAngleX += -((float) Math.PI / 5F);
			bipedLeftArm.rotateAngleX += -((float) Math.PI / 5F);
			bipedRightLeg.rotateAngleX = -1.4137167F;
			bipedRightLeg.rotateAngleY = ((float) Math.PI / 10F);
			bipedRightLeg.rotateAngleZ = 0.07853982F;
			bipedLeftLeg.rotateAngleX = -1.4137167F;
			bipedLeftLeg.rotateAngleY = -((float) Math.PI / 10F);
			bipedLeftLeg.rotateAngleZ = -0.07853982F;
		}

		bipedRightArm.rotateAngleY = 0.0F;
		bipedRightArm.rotateAngleZ = 0.0F;

		switch (leftArmPose) {
			case EMPTY:
				bipedLeftArm.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				bipedLeftArm.rotateAngleX = bipedLeftArm.rotateAngleX * 0.5F - 0.9424779F;
				bipedLeftArm.rotateAngleY = 0.5235988F;
				break;
			case ITEM:
				bipedLeftArm.rotateAngleX = bipedLeftArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				bipedLeftArm.rotateAngleY = 0.0F;
		}

		switch (rightArmPose) {
			case EMPTY:
				bipedRightArm.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.9424779F;
				bipedRightArm.rotateAngleY = -0.5235988F;
				break;
			case ITEM:
				bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				bipedRightArm.rotateAngleY = 0.0F;
		}

		if (swingProgress > 0.0F) {
			EnumHandSide enumhandside = getMainHand(entityIn);
			ModelRenderer modelrenderer = getArmForSide(enumhandside);
			float f1 = swingProgress;
			bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

			if (enumhandside == EnumHandSide.LEFT) {
				bipedBody.rotateAngleY *= -1.0F;
			}

//			this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
//			this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
//			this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
//			this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
			bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
			bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
			bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
			f1 = 1.0F - swingProgress;
			f1 = f1 * f1;
			f1 = f1 * f1;
			f1 = 1.0F - f1;
			float f2 = MathHelper.sin(f1 * (float) Math.PI);
			float f3 = MathHelper.sin(swingProgress * (float) Math.PI) * -(bipedHead.rotateAngleX - 0.7F)
					* 0.75F;
			modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
			modelrenderer.rotateAngleY += bipedBody.rotateAngleY * 2.0F;
			modelrenderer.rotateAngleZ += MathHelper.sin(swingProgress * (float) Math.PI) * -0.4F;
		}

		bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

		if (rightArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
			bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
			bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
			bipedRightArm.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
			bipedLeftArm.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
		} else if (leftArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
			bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
			bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
			bipedRightArm.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
			bipedLeftArm.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
		}

		copyModelAngles(this.bipedHead, this.bipedHeadwear);
	}

}
