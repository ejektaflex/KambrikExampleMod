import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import net.fabricmc.loom.task.RemapSourcesJarTask

plugins {
	//id 'com.github.johnrengelman.shadow' version '6.1.0'
	kotlin("jvm") version "1.4.30"
	kotlin("plugin.serialization") version "1.4.30"
	id("fabric-loom") version "0.6-SNAPSHOT"
	`maven-publish`
	signing
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
	withSourcesJar()
	withJavadocJar()
}

val modId: String by project
val modVersion: String by project
val group: String by project
val minecraftVersion: String by project
val fabricVersion: String by project
val kotlinVersion: String by project
val loaderVersion: String by project
val yarnMappings: String by project
val pkgName: String by project
val pkgDesc: String by project
val pkgAuthor: String by project
val pkgEmail: String by project
val pkgHub: String by project

project.group = group
version = modVersion

repositories {
	mavenCentral()
	maven(url = "https://kotlin.bintray.com/kotlinx")
	maven(url = "http://maven.fabricmc.net/") {
		name = "Fabric"
	}
	maven(url = "https://maven.terraformersmc.com/") {
		name = "Mod Menu"
	}
}

minecraft {

}

dependencies {
	//to change the versions see the gradle.properties file
	minecraft("com.mojang:minecraft:${minecraftVersion}")
	mappings("net.fabricmc:yarn:${yarnMappings}:v2")
	modImplementation("net.fabricmc:fabric-loader:${loaderVersion}")

	modImplementation("io.ejekta:kambrik:0.2.0")
	modApi("io.ejekta:kambrik:0.2.0")

	implementation("com.google.code.findbugs:jsr305:3.0.2")

	modImplementation(group = "net.fabricmc", name = "fabric-language-kotlin", version = "1.4.30+build.2")

	// Fabric API. This is technically optional, but you probably want it anyway.
	modImplementation("net.fabricmc.fabric-api:fabric-api:${fabricVersion}")
}


tasks.getByName<ProcessResources>("processResources") {
	filesMatching("fabric.mod.json") {
		expand(
			mutableMapOf<String, String>(
				"modid" to modId,
				"version" to modVersion,
				"kotlinVersion" to kotlinVersion,
				"fabricApiVersion" to fabricVersion
			)
		)
	}
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "1.8"
	}
}
