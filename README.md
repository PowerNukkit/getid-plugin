# GetID Plugin
This is a tiny plugin that adds the `/getid` command to PowerNukkit.

It allows you to show the numeric universal item and block id, including the negative ids for that is assigned to new blocks.

Example usage:
- /getid bow
- /getid campfire
- /getid light_block
- /getid smoker

Permission:
- getid.cmd.use (default: true)

## Cloning and importing
1. Just do a normal `git clone https://github.com/PowerNukkit/getid-plugin.git` (or the URL of your own git repository)
2. Import the `pom.xml` file with your IDE, it should do the rest by itself

## Debugging
1. Create a zip file containing only your `plugin.yml` file
2. Rename the zip file to change the extension to jar
3. Create an empty folder anywhere, that will be your server folder.  
   <small>_Note: You don't need to place the PowerNukkit jar in the folder, your IDE will load it from the maven classpath._</small>
4. Create a folder named `plugins` inside your server folder  
   <small>_Note: It is needed to bootstrap your plugin, your IDE will load your plugin classes from the classpath automatically,
   so it needs to have only the `plugin.yml` file._</small>
5. Move the jar file that contains only the `plugin.yml` to the `plugins` folder
6. Create a new Application run configuration setting the working directory to the server folder and the main class to:  `cn.nukkit.Nukkit`  
![](https://i.imgur.com/NUrrZab.png)
7. Now you can run in debug mode. If you change the `plugin.yml` you will need to update the jar file that you've made.
