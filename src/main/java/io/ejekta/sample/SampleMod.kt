package io.ejekta.sample

import io.ejekta.kambrik.Kambrik
import net.fabricmc.api.ModInitializer

class SampleMod : ModInitializer {

    val logger = Kambrik.Logging.createLogger(ID)

    override fun onInitialize() {
        logger.info("Kambrik Sample Mod Says Hello!")
    }

    internal companion object {
        const val ID = "sample"
    }

}