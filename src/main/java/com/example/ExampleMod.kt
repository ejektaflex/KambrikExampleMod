package com.example

import io.ejekta.kambrik.Kambrik
import io.ejekta.kambrikx.data.configData
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier

class ExampleMod : ModInitializer {

    internal companion object {
        const val ID = "example"
    }

    private val logger = Kambrik.Logging.createLogger(ID)

    override fun onInitialize() {
        logger.info("Kambrik Sample Mod Says Hello!")
    }

}