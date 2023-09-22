# RegenManager
My first plugin.\
This plugin allows you to configure the player's natural health regeneration.\
\
In modern Minecraft, health regeneration occurs every 4 seconds (except for peaceful difficulty). This plugin allows the player to regenerate at a specified interval, and does not depend on the level of hunger.\
For example, this can be useful when creating a server with a mini-game with fast regeneration and no hunger.\
\
You can download it directly from [SpigotMC](https://www.spigotmc.org/resources/regenmanager.112749/).\
\
Tested versions: 1.17, 1.18, 1.19, 1.20.
## Features
- Regeneration period (the time interval between the regeneration of each half of the heart)
- Regeneration delay (the time interval between taking damage and the start of regeneration itself)
- Hunger disabling (ability to disable hunger reduction)
- Available languages: en_US, uk_UA, ru_RU
## Installation
Just place the .jar file in the plugins folder of your server.
## Commands
`/rm` - main command\
`/rm period <amount>` - set regeneration period\
`/rm delay <amount>` - set regeneration delay\
`/rm hunger <enable/disable>` - change hunger state\
`/rm reload` - reload configuration file
## Permissions
`regenmanager.admin` - access to all commands
## Credits
The plugin was created by EastRane specifically for its unofficial tournament BedWars server as part of the HardShard community
