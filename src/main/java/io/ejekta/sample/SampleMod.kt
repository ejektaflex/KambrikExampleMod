package io.ejekta.sample

import io.ejekta.kambrik.Kambrik
import net.fabricmc.api.ModInitializer

class SampleMod : ModInitializer {

    internal companion object {
        const val ID = "sample"
    }

    private val logger = Kambrik.Logging.createLogger(ID)

    override fun onInitialize() {
        logger.info("Kambrik Sample Mod Says Hello!")
    }


}