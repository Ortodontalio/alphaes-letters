# AlPhAE's Letters
![GitHub license](https://img.shields.io/github/license/Ortodontalio/alphaes-letters.svg)
![GitHub issues](https://img.shields.io/github/issues/Ortodontalio/alphaes-letters.svg)
![GitHub tag](https://img.shields.io/github/v/tag/Ortodontalio/alphaes-letters.svg)
![GitHub release](https://img.shields.io/github/release/Ortodontalio/alphaes-letters.svg)

For this mod to work, you need to have a **Fabric API**.

# Preface
The idea of the mod came to me when I was building a map. I like traveling, so the map was connected to the railway and the cities that this road connected. Over time, the map grew, and a large number of road interchanges appeared, for which road signs were required. The idea with banners was not ideal for me, because I thought it didn't look very realistic, besides, not all the letters would have turned out beautifully (due to restrictions when creating banners), and there was a layer between each banner. Therefore, I decided to create new blocks, each block is a letter, and I chose the standard blue as the background, inherent in road signs in my region. It is my first mod written using the Fabric library. Please do not judge strictly😁

# Features
Initially, the mod adds blocks on which letters/symbols are drawn, so that by putting these blocks next to each other, you can design names for buildings, signs... In principle, for everything that the soul desires! However, now each symbol is a full-fledged block with its own 3D model, in accordance with the shape of this symbol. 

At the moment, the following symbols are implemented (provided that **the common letters for the alphabets are in the tab with the Latin alphabet**):

![All available symbols](src/main/resources/readme/letters_alphaes.png)
### Usage Ideas
In my opinion, the most important idea with which I created this small modification is the creation of road signs. 
The following screenshot shows an example of creating a road sign using the Latin alphabet:
![Road sign in English](src/main/resources/readme/english_sign.png)
The mod also provides an opportunity to build absolutely any signpost, of any complexity: 
a simple distance signpost, an arrow signpost, a signpost with a circle road and much more!
![Road sign in French](src/main/resources/readme/french_sign.png)
Another option for creating a road sign, but using the Russian language:
![Road sign in Russian](src/main/resources/readme/russian_sign.png)
Another idea for the first point is pointers of distances to cities. The screenshot shows an example of such a sign in 
the Byelorussian language:
![Road sign in Byelorussian](src/main/resources/readme/byelorussian_sign.png)
The mod also allows to design standard direction signs, indicating the distance if desired. The screenshot shows an 
example of such a sign in Ukrainian:
![Road sign in Ukrainian](src/main/resources/readme/ukrainian_sign.png)
The simplest road application of letter blocks is pointers of names of settlements:
![Village enter](src/main/resources/readme/city_enter.png)
But you can make a pointer to the end of settlements using a new block - strikethrough block:
![Village exit](src/main/resources/readme/city_exit.png)
There is also the possibility of building signs with the names of rivers - a block with the abbreviation of the river 
in Russian, Belarusian, Ukrainian, as well as English is also present in this mod:
![River sign](src/main/resources/readme/river.png)
It is not necessary to use blocks only for the construction of road signs. They can be used even when you just want to 
make a sign for your store or just number a house in your city. After all, all that is required of you is to put blocks 
with letters, forming words!

In addition to the usual signs, you can build absolutely any sculptures where letters are used:
![Stella](src/main/resources/readme/stella.png)

# How to use
It is worth saying that all the letters on the blocks have a 3D model:

![3D letter A](src/main/resources/readme/letter_3d.png)

In order to get each block-symbol, it is necessary to cut the **white concrete** in a stone cutter:

![Symbols' crafting](src/main/resources/readme/letter_craft.png)

Each block-symbol can be placed either separately or on any blocks:

![Hello, World!](src/main/resources/readme/hello_world.png)

Yes, it's worth noting that letter blocks can emit light! To do this, you need to click on the block, holding a
glowstone dust in your hand. Also, you can remove the highlighting with any axe:

![Glowstone usage](src/main/resources/readme/bySwetokamen.png)

Despite the fact that the blocks-symbols are made of white concrete, they can be repainted in different colors using any dye:

![Colormania](src/main/resources/readme/colorful.png)

However, the function of blocks-symbols, that existed initially in previous versions of the mod (the creation of road signs) has also been preserved.
To create a sign block, you first need to create a basic one - ferroconcrete. But to create this block, you need
to create a block of white concrete powder with bars. This is the first step on the way to creating letter blocks.

![Crafting concrete powder with bars](src/main/resources/readme/powder_craft.png)

An interesting fact is that after the destruction of the block, ordinary white concrete powder falls out, and bars remain 
in place of the block.

![Concrete powder with bars destroying](src/main/resources/readme/powder_destroying.png)

To get ferroconcrete from a block of concrete powder with bars, it is necessary to craft a dyeing machine:

![Dyeing machine craft](src/main/resources/readme/dyeing_machine_craft.png)

This machine accepts a bucket of water, any block of concrete powder and a dye at the entrance, and concrete of the
color of the dye is obtained at the output. The working of this machine can also be controlled by a redstone:
if the signal is active, the machine stops working, as well as collecting water.

![Dyeing machine capabilities](src/main/resources/readme/concrete_craft.png)

Depending on the amount of water in the internal tank, the appearance of the machine changes:

![Dyeing machine types](src/main/resources/readme/dyeing_machine_types.png)

We also need to get blue ferroconcrete, so we use a dyeing machine, putting concrete powder with bars and
blue dye in it:

![Letter Powder Craft](src/main/resources/readme/letter_powder_craft.png)

Interesting fact. If you pour water around concrete powder with bars, you can get white ferroconcrete.
But you won't be able to paint such concrete, unfortunately😔:

![White Ferroconcrete](src/main/resources/readme/byWater.png)

When a ferroconcrete block is received, it must be put into a stone cutter to get a cropped variant of the ferroconcrete:

![Cropped Ferroconcrete](src/main/resources/readme/crop_ferroconcrete.png)

Finally, you should put any block-symbol to it, after which the cropped ferroconcrete turns into a sign-block:

![Sign-block comparing Symbol-block](src/main/resources/readme/sign_block_complete.png)

To remove the block-symbol attached to the cropped ferroconcrete by mistake, it is necessary to use any hoe:

![Hoe usage](src/main/resources/readme/byHoe.png)

That's it! But these are not all available blocks! In the new tab "Technical blocks", there are also: iron fence,
iron gates, strikethrough block, which you can use to create more realistic road signs. For example, iron fence:

![Iron fence example](src/main/resources/readme/fence_example.png)

In addition to the construction of iron pens, it can be used for road sign posts. Fence crafting is next:

![Iron fence craft](src/main/resources/readme/fence_craft_new.png)

It is also possible to craft an iron gate:

![Iron gate craft](src/main/resources/readme/gate_craft_new.png)

Also, to craft a strikethrough block, you must have the following items:

![Strikethrough block craft](src/main/resources/readme/strike_craft.png)

It is important to note that strikethrough block can be repainted in any color:

![Strikethrough block colors](src/main/resources/readme/strike_color_block.png)

In addition to creating crossed-out letters, this block can have other uses, for example, the construction of strange
structures like this:

![Strikethrough strangeness](src/main/resources/readme/strange.png)

It is worth noting that all blocks use 16x16 standard resolution textures to avoid an obvious difference between standard blocks and blocks from the mod.
# License
The project is licensed under Apache 2.0. For more information, please see the license file.