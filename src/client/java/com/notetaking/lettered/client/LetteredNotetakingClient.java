import com.mojang.blaze3d.platform.InputConstants;
import com.notetaking.lettered.LetteredNotetaking;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.fabricmc.api.ClientModInitializer;

public class LetteredNotetakingClient implements ClientModInitializer {
	private static KeyMapping printToConsoleKey;

		@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		printToConsoleKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
		"key.lettered-notetaking.print-to-console",
		InputConstants.Type.KEYBOARD,
		InputConstants.KEY_N,
		LETTERED_CATEGORY
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (printToConsoleKey.consumeClick()) {
				if (client.player != null) {
					client.player.sendOverlayMessage(
							Component.literal("Print a message to console")
					);
				}
			}
		});
	}
}
private static final KeyMapping.Category LETTERED_CATEGORY =
		KeyMapping.Category.register(LetteredNotetaking.id("print_to_console"));

void main() {

}
