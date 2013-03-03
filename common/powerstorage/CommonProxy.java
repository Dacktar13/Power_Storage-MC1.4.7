package powerstorage;

public class CommonProxy {

	public static String TEXTURE_PATH_GUI = "/gfx/powerstorage/gui";
	public static String TEXTURE_PATH_BLOCKS = "/gfx/powerstorage/blocks";
	public static String TEXTURE_PATH_ITEMS = "/gfx/powerstorage/items";
	public static String TEXTURE_PATH_ENTITIES = "/gfx/powerstorage/entities";

	public static String TEXTURE_BLOCKS = TEXTURE_PATH_BLOCKS + "/blocks.png";
	public static String TEXTURE_ITEMS = TEXTURE_PATH_BLOCKS + "/items.png";
	public static String TEXTURE_ICONS = TEXTURE_PATH_GUI + "/icons.png";
	public static String TEXTURE_TRIGGERS = TEXTURE_PATH_GUI + "/triggers.png";

	// Client stuff
	public void registerRenderers() {
		// Nothing here as the server doesn't render graphics!
	}
}