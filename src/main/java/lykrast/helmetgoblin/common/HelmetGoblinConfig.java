package lykrast.helmetgoblin.common;

import static lykrast.helmetgoblin.HelmetGoblin.MODID;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresMcRestart;;

@Config(modid = MODID)
@LangKey("config." + MODID + ".title")
public class HelmetGoblinConfig {
	@RangeInt(min = 0)
	@RequiresMcRestart
	@LangKey("config." + MODID + ".weight")
	@Comment({"Spawn weight of Helmet Goblins", "0 disables natural spawn"})
	public static int weight = 60;
	@RangeInt(min = 1)
	@RequiresMcRestart
	@LangKey("config." + MODID + ".group.min")
	@Comment({"Minimum group size for Helmet Goblins"})
	public static int minGroup = 3;
	@RangeInt(min = 1)
	@RequiresMcRestart
	@LangKey("config." + MODID + ".group.max")
	@Comment({"Maximum group size for Helmet Goblins"})
	public static int maxGroup = 6;

}
