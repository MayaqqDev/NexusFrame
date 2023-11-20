# `NexusFrame`

[![Modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@2/assets/cozy/available/modrinth_64h.png)](https://modrinth.com/mod/nexusframe/versions)
[![Curseforge](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@2/assets/cozy/available/curseforge_64h.png)](https://www.curseforge.com/minecraft/mc-mods/nexusframe)
[![Github](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@2/assets/cozy/available/github_64h.png)](https://github.com/MayaqqDev/nexusframe/)


## `Bisect Partnership`

[![Promo Code](https://www.bisecthosting.com/partners/custom-banners/3af862e4-2c3a-4ae5-9caf-cc9f80d19620.png)](https://bisecthosting.com/mayaqq)
## `Information`

A server-side library mod adding Multiblock api with a custom preview!

## `Usage`

You can use the modrinth maven to add this to your project. Add this to your repositories.

```
repositories {
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = "https://api.modrinth.com/maven"
            }
        }
        filter {
            includeGroup "maven.modrinth"
        }
    }
}
```
And this to your dependencies. Don't forget to change "VERSION" to the latest released version number on modrinth. [Click here for more information about Modrinth Maven](https://docs.modrinth.com/docs/tutorials/maven/)
```
dependencies {
    modImplementation "maven.modrinth:nexusframe:VERSION"
}
```
For example implementation, you can look at [the test mod code](https://github.com/MayaqqDev/NexusFrame/blob/testmod/src/main/java/dev/mayaqq/nexustestmod/mixin/AnvilBlockMixin.java)

## `Community`

For any questions or support you can join my discord by clicking the button below!

[![Discord Server](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@2/assets/cozy/social/discord-singular_64h.png)](https://discord.gg/w7PpGax9Bq)
