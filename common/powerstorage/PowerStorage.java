package powerstorage;


import buildcraft.BuildCraftCore;
import buildcraft.BuildCraftFactory;
import buildcraft.BuildCraftTransport;
import buildcraft.api.fuels.IronEngineFuel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import powerstorage.core.MercuryOre;
import powerstorage.core.PSWorldGen;
import powerstorage.factory.BatteryBox;
import powerstorage.factory.MercuryPipe;
import powerstorage.factory.GuiHandler;
import powerstorage.factory.TileEntityBatteryBox;
import powerstorage.lib.Reference;
import powerstorage.liquid.BlockMercuryFlowing;
import powerstorage.liquid.BlockMercuryStill;
import powerstorage.liquid.ItemBucketMercury;
import powerstorage.liquid.MercuryBucketHandler;
import powerstorage.factory.MercuryCubeFull;
import powerstorage.core.MercuryIngot;
import powerstorage.factory.Condenser;
import powerstorage.factory.MercuryCubeEmpty;

/**
 * PowerStorage
 * 
 * @author Dacktar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */

@Mod(
        modid = Reference.MOD_ID, 
        name = Reference.MOD_NAME, 
        version = Reference.VERSION_NUMBER,
        dependencies = "required-after:Forge@[7.7.1.665,);" +
                "required-after:BuildCraft|Core;" +
               "required-after:BuildCraft|Transport;" +
                "required-after:BuildCraft|Builders;" +
                "required-after:BuildCraft|Silicon;" +
                "after:IC2;"
        )

@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class PowerStorage {

	// The instance of your mod that Forge uses.
	@Instance("PowerStorage")
	public static PowerStorage instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "powerstorage.client.ClientProxy", serverSide = "powerstorage.CommonProxy")
	public static CommonProxy proxy;

	 /***
     * This is code that is executed prior to your mod being initialized into of Minecraft
     * Examples of code that could be run here;
     * 
     * Initializing your items/blocks (you must do this here)
     * Setting up your own custom logger for writing log data to
     * Loading your language translations for your mod (if your mod has translations for other languages)
     * Registering your mod's key bindings and sounds
     * 
     * @param event The Forge ModLoader pre-initialization event
     */
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());

		config.load();
		// Block ID allocation
		int batteryBoxID = config.get(Configuration.CATEGORY_BLOCK,
				"batteryBox", 2500).getInt();

		int mercuryOreID = config.get(Configuration.CATEGORY_BLOCK,
				"mercuryOre", 2501).getInt();

		int mercuryPipeID = config.get(Configuration.CATEGORY_BLOCK,
				"mercuryPipe", 2502).getInt();

		int condenserID = config.get(Configuration.CATEGORY_BLOCK, 
				"condencer", 2505).getInt();

		int mercuryCubeEmptyID = config.get(Configuration.CATEGORY_BLOCK,
				"mercuryCubeEmpty", 2506).getInt();

		int mercuryCubeFullID = config.get(Configuration.CATEGORY_BLOCK,
				"mercuryCubeFull", 2507).getInt();

		// Item ID allocation
		int mercuryIngotID = config.get(Configuration.CATEGORY_ITEM,
				"mercuryIngot", 29667).getInt();

		// Mercury Liquid
		int bucketMercuryId = config.get(Configuration.CATEGORY_ITEM,
				"mercuryBucket", 29666).getInt();
		int mercuryStillId = config.get(Configuration.CATEGORY_BLOCK,
				"mercuryStill", 2504).getInt();
		int mercuryMovingId = config.get(Configuration.CATEGORY_BLOCK,
				"mercuryMoving", 2503).getInt();

		config.save();

		// Block creation
		batteryBox = new BatteryBox(batteryBoxID, 1);

		mercuryOre = new MercuryOre(mercuryOreID, Material.iron)
				.setResistance(10.0F).setUnlocalizedName("mercuryOre");

		mercuryPipe = buildcraft.BuildCraftTransport.createPipe(mercuryPipeID,
				MercuryPipe.class, "mercuryConductivePipe", null, null, null);
		
		
		condenser = new Condenser(condenserID, Material.iron)
				.setHardness(10F).setResistance(10.0F)
				.setStepSound(Block.soundGravelFootstep)
				.setUnlocalizedName("condenser");

		mercuryCubeEmpty = new MercuryCubeEmpty(mercuryCubeEmptyID, 
				Material.iron).setHardness(10F).setResistance(10.0F)
				.setStepSound(Block.soundGravelFootstep)
				.setUnlocalizedName("mercuryCubeEmpty");

		mercuryCubeFull = new MercuryCubeFull(mercuryCubeFullID, 
				Material.iron).setHardness(10F).setResistance(10.0F)
				.setStepSound(Block.soundGravelFootstep)
				.setUnlocalizedName("mercuryCubeFull");

		// Item Creation
		mercuryIngot = new MercuryIngot(mercuryIngotID).setMaxStackSize(64)
				.setCreativeTab(CreativeTabs.tabMisc)
				.setUnlocalizedName("mercuryIngot");

		// mercury liquid.
		mercuryStill = (new BlockMercuryStill(mercuryStillId, Material.water))
				.setUnlocalizedName("mercurySource").setLightValue(0.5F);

		mercuryMoving = (new BlockMercuryFlowing(mercuryMovingId,
				Material.water)).setUnlocalizedName("mercuryFlowing").setLightValue(0.5F);

		MinecraftForge.EVENT_BUS.register(new MercuryBucketHandler());

		bucketMercury = new ItemBucketMercury(bucketMercuryId)
				.setUnlocalizedName("bucketMercury")
				.setContainerItem(Item.bucketEmpty)
				.setMaxStackSize(1).setCreativeTab(CreativeTabs.tabMisc);
		LanguageRegistry.addName(bucketMercury, "Mercury Bucket");

		mercuryLiquid = LiquidDictionary.getOrCreateLiquid("Mercury",
				new LiquidStack(mercuryStill, 1));

		IronEngineFuel.fuels.add(new IronEngineFuel(LiquidDictionary.getLiquid(
				"Mercury", LiquidContainerRegistry.BUCKET_VOLUME), 9, 50000));

		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(
				LiquidDictionary.getLiquid("Mercury",
						LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(
						bucketMercury), new ItemStack(Item.bucketEmpty)));

	}

	public static PSWorldGen worldGen = new PSWorldGen();

	/***
     * This is code that is executed when your mod is being initialized in Minecraft
     * Examples of code that could be run here;
     * 
     * Registering your GUI Handler
     * Registering your different event listeners
     * Registering your different tile entities
     * Adding in any recipes you have 
     * 
     * @param event The Forge ModLoader initialization event
     */
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();

		initCraftingAndSmelting();
		initBasicItems();
		initBasicBlocks();
		initBasicPipes();
		initBasicLiquids();
		initWorldGen();

	}

	/***
     * This is code that is executed after all mods are initialized in Minecraft
     * This is a good place to execute code that interacts with other mods (ie, loads an addon module
     * of your mod if you find a particular mod).
     * 
     * @param event The Forge ModLoader post-initialization event
     */
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}

	private void initCraftingAndSmelting() {

		ItemStack glassStack = new ItemStack(Block.glass);
		ItemStack mercuryIngotStack = new ItemStack(mercuryIngot);
		ItemStack stoneStack = new ItemStack(Block.stone);
		ItemStack ingotGoldStack = new ItemStack(Item.ingotGold);
		ItemStack GoldPowerPipeStack = new ItemStack(
				BuildCraftTransport.pipePowerGold);
		ItemStack mercuryBucketStack = new ItemStack(bucketMercury);
		ItemStack emptyBucketStack = new ItemStack(Item.bucketEmpty);
		ItemStack ingotIronStack = new ItemStack(Item.ingotIron);

		// Battery Box
		GameRegistry.addRecipe(new ItemStack(batteryBox), "sss", "gmg", "DPD",
				'm', mercuryCubeFull, 'g', Item.diamond, 'D',
				BuildCraftCore.diamondGearItem, 's', stoneStack, 'P',
				mercuryPipe);

		// Condenser
		GameRegistry.addRecipe(new ItemStack(condenser), "sss", "tft", "GgG",
				't', BuildCraftFactory.tankBlock, 'f', Block.furnaceIdle, 'G',
				BuildCraftCore.goldGearItem, 's', stoneStack, 'g',
				GoldPowerPipeStack);

		// Mercury Cube Empty
		GameRegistry.addRecipe(new ItemStack(mercuryCubeEmpty), "GgG", "g g", "GgG",
				'G', ingotGoldStack, 'g', glassStack);

		// Mercury Ingots
		GameRegistry.addSmelting(mercuryOreID, mercuryIngotStack, 0.8F);
		GameRegistry.addRecipe(new ItemStack(bucketMercury, 1), "   ", "imi", " i ",
				'i', ingotIronStack, 'm', mercuryIngotStack);
		GameRegistry.addRecipe(new ItemStack(bucketMercury, 1), "   ", " m ", " i ",
				'i', emptyBucketStack, 'm', mercuryIngotStack);

		// Mercury Pipes
		GameRegistry.addRecipe(new ItemStack(mercuryPipe), "   ", "gmg", "   ",
				'm', mercuryIngotStack, 'g', ingotGoldStack);

		GameRegistry.addShapelessRecipe(new ItemStack(mercuryPipe, 1),
				GoldPowerPipeStack, mercuryBucketStack);

	}

	private void initBasicItems() {

		// Mercury Ingot Item details
		LanguageRegistry.addName(mercuryIngot, "Mercury Ingot");

	}

	private void initBasicBlocks() {

		// Battery Box Block details
		GameRegistry.registerBlock(batteryBox, "batteryBox");
		LanguageRegistry.addName(batteryBox, "Battery Box");
		MinecraftForge.setBlockHarvestLevel(batteryBox, "pickaxe", 2);
		GameRegistry.registerTileEntity(TileEntityBatteryBox.class,
				"containerBatteryBox");
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());

		// Mercury Ore Block details
		GameRegistry.registerBlock(mercuryOre, "mercuryOre");
		LanguageRegistry.addName(mercuryOre, "Mercury Ore");
		MinecraftForge.setBlockHarvestLevel(mercuryOre, "pickaxe", 3);

		// Conderncer details
		GameRegistry.registerBlock(condenser, "condenser");
		LanguageRegistry.addName(condenser, "Condenser");
		MinecraftForge.setBlockHarvestLevel(condenser, "pickaxe", 2);

		// Mercury Cube details
		GameRegistry.registerBlock(mercuryCubeEmpty, "mercuryCubeEmpty");
		LanguageRegistry.addName(mercuryCubeEmpty, "Mercury Cube Empty");
		MinecraftForge.setBlockHarvestLevel(mercuryCubeEmpty, "pickaxe", 2);
		GameRegistry.registerBlock(mercuryCubeFull, "mercuryCubeFull");
		LanguageRegistry.addName(mercuryCubeFull, "Mercury Cube Full");
		MinecraftForge.setBlockHarvestLevel(mercuryCubeFull, "pickaxe", 2);

	}

	private void initBasicPipes() {

		// Mercury Pipe Block details
		LanguageRegistry.addName(mercuryPipe, "Mercury Pipe");

	}

	private void initBasicLiquids() {

		LanguageRegistry.addName(mercuryStill, "Mercury Still");
		GameRegistry.registerBlock(mercuryStill, "mercuryStill");

		LanguageRegistry.addName(mercuryMoving, "Mercury Flowing");
		GameRegistry.registerBlock(mercuryMoving, "mercuryMoving");

		LanguageRegistry.addName(bucketMercury, "Mercury Bucket");

	}

	private void initWorldGen() {
		GameRegistry.registerWorldGenerator(new PSWorldGen());
	}

	public static int batteryBoxID;
	public static int mercuryOreID;
	public static int mercuryPipeID;
	public static int mercuryIngotID;
	public static int mercuryModel;
	public static int condenserID;
	public static int mercuryCubeEmptyID;
	public static int mercuryCubeFullID;

	public static Block batteryBox;
	public static Block mercuryOre;
	public static Block condenser;
	public static Block mercuryCubeEmpty;
	public static Block mercuryCubeFull;

	public static Item mercuryPipe;
	public static Item mercuryIngot;

	public static Item bucketMercury;
	public static Block mercuryStill;
	public static Block mercuryMoving;
	public static LiquidStack mercuryLiquid;
}