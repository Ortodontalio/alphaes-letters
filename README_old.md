# Preface
The idea of the mod came to me when I was building a map. I like traveling, so the map was connected to the railway and 
the cities that this road connected. Over time, the map grew, and a large number of road interchanges appeared, for 
which road signs were required. The idea with banners was not ideal for me, because I thought it didn't look very 
realistic, besides, not all the letters would have turned out beautifully (due to restrictions when creating banners), 
and there was a layer between each banner. Therefore, I decided to create new blocks, each block is a letter, and I 
chose the standard blue as the background, inherent in road signs in my region. It is my first mod written using the 
Fabric library. Please do not judge strictly😁

# Features
Initially, the mod adds blocks on which letters/symbols are drawn, so that by putting these blocks next to each other, 
you can design names for buildings, signs... In principle, for everything that the soul desires! However, now each symbol is a full-fledged block with its own 3D model, in accordance with the shape of this symbol.

At the moment, the following symbols are implemented (provided that **the common letters for the alphabets are in the 
tab with the Latin alphabet**):

![All available symbols](main/resources/readme/letters_alphaes.png?raw=true)
### Usage Ideas
In my opinion, the most important idea with which I created this small modification is the creation of road signs. 
The following screenshot shows an example of creating a road sign using the Latin alphabet:
##### Before 0.7.0
![Road sign in English](https://i.ibb.co/r4w6QSD/2022-07-09-14-46-58.png)
##### 0.7.0
![Road sign in English](src/main/resources/readme/english_sign.png)
The mod also provides an opportunity to build absolutely any signpost, of any complexity:
a simple distance signpost, an arrow signpost, a signpost with a circle road and much more!
![Road sign in French](https://i.ibb.co/p4LpNPN/french-sign.png)
Another option for creating a road sign, but using the Russian language:
##### Before 0.7.0
![Road sign in Russian](https://i.ibb.co/h2sPRKJ/2022-07-09-11-09-22.png)
##### 0.7.0
![Road sign in Russian](src/main/resources/readme/russian_sign.png)
Another idea for the first point is pointers of distances to cities. The screenshot shows an example of such a sign in 
the Byelorussian language:
##### Before 0.7.0
![Road sign in Byelorussian](https://i.ibb.co/WsLQ9Hm/2022-07-09-15-22-26.png)
##### 0.7.0
![Road sign in Byelorussian](src/main/resources/readme/byelorussian_sign.png)
The mod also allows to design standard direction signs, indicating the distance if desired. The screenshot shows an 
example of such a sign in Ukrainian:
##### Before 0.7.0
![Road sign in Ukrainian](https://i.ibb.co/Q6RC1cr/2022-07-09-15-31-18.png)
##### 0.7.0
![Road sign in Ukrainian](src/main/resources/readme/ukrainian_sign.png)
The simplest road application of letter blocks is pointers of names of settlements:
##### 0.7.0
![Village enter sign](src/main/resources/readme/city_enter.png)
But you can make a pointer to the end of settlements using a new block - **strikethrough block**!
##### 0.7.0
![Village exit sign](src/main/resources/readme/city_exit.png)
There is also the possibility of building signs with the names of rivers - a block with the abbreviation of the river in 
Russian, Belarusian, Ukrainian, as well as English is also present in this mod:
![River sign](src/main/resources/readme/river.png)
It is not necessary to use blocks only for the construction of road signs. They can be used even when you just want to 
make a sign for your store or just number a house in your city. After all, all that is required of you is to put blocks 
with letters, forming words!
##### 1.0
In addition to the usual signs, you can build absolutely any sculptures where letters are used:
![Stella](src/main/resources/readme/stella.png)
# How to use - > 1.19 (v1.0)
**It is worth saying that all the letters on the blocks since 1.0 have a separate 3D model!**
![3D letter A](https://i.ibb.co/cg45thj/letter-3d.png)
In order to get each block-symbol, it is necessary to cut the **white concrete** in a stone cutter:

![Symbols' crafting](src/main/resources/readme/letter_craft.png)

Each block-symbol can be placed either separately or on any blocks:

![Hello, World!](src/main/resources/readme/hello_world.png)

Yes, it's worth noting that letter blocks can emit light! To do this, you need to click on the block, holding a
glowstone dust in your hand. Also, you can remove the highlighting with any axe:

![Glowstone usage](src/main/resources/readme/bySwetokamen.png)

Despite the fact that the blocks-symbols are made of white concrete, they can be repainted in different colors using any dye:

![Colormania](src/main/resources/readme/colorful.png)

Interesting fact. If you pour water around concrete powder with bars, you can get white ferroconcrete.
But you won't be able to paint such concrete, unfortunately😔:

![White Ferroconcrete](https://i.ibb.co/841dWzK/byWater.png)

Also, you can combine symbols with ferroconcrete by putting any block-symbol to it, after which the cropped 
ferroconcrete turns into a sign-block:

![Sign-block comparing Symbol-block](https://i.ibb.co/cD77w8t/sign-block-complete.png)

To remove the block-symbol attached to the cropped ferroconcrete by mistake, it is necessary to use any hoe:

![Hoe usage](https://i.ibb.co/ZTtM4zB/byHoe.png)

# How to use - > 1.18 (v.0.7.0)
**It is worth saying that all the letters on the blocks since 0.7.0 have a 3D model!**
![3D letter example](src/main/resources/readme/letter_3d.png)

So far, to create each block, you first need to create a basic one - ferroconcrete. But to create this block, you need
to create a block of white concrete powder with bars. This is the first step on the way to creating letter blocks.

![Crafting concrete powder with bars](src/main/resources/readme/powder_craft.png)

An interesting fact is that after the destruction of the block, ordinary white concrete powder falls out, and bars remain
in place of the block.

![Concrete powder with bars destroying](https://i.ibb.co/P4bpFrC/powder-destroying.png)

To get ferroconcrete from a block of concrete powder with bars, it is necessary to craft a dyeing machine:
##### Before 0.7.0
![Dyeing machine craft](https://i.ibb.co/NtRLHM1/dyeing-machine-craft.png)
##### 0.7.0
![Dyeing machine craft](src/main/resources/readme/dyeing_machine_craft.png)

This machine accepts a bucket of water, any block of concrete powder and a dye at the entrance, 
and concrete of the color of the dye is obtained at the output. The working of this machine can also be controlled by a 
redstone: if the signal is active, the machine stops working, as well as collecting water.

![Dyeing machine capabilities](src/main/resources/readme/concrete_craft.png)

Depending on the amount of water in the internal tank, the appearance of the machine changes:

![Dyeing machine types](src/main/resources/readme/dyeing_machine_types.png)

We also need to get blue ferroconcrete, so we use a dyeing machine, putting concrete powder with bars and
blue dye in it:

![Letter Powder Craft](src/main/resources/readme/powder_craft.png)

When a ferroconcrete block is received, it must be put into a stone cutter to get any variant of the letters blocks:
![Stone cutting](src/main/resources/readme/letter_craft.png)

That's it! But these are not all available blocks **in 0.7.0**! In the new tab "Technical blocks", there are also: iron fence, iron gates, strikethrough block, which you can use to create more realistic road signs. For example, iron fence:

![Iron fence example](src/main/resources/readme/fence_example.png)

In addition to the construction of iron pens, it can be used for road sign posts. Fence crafting is next:
##### Before 1.20.4
![Iron fence craft](src/main/resources/readme/fence_craft.png)
##### 1.20.4
![Iron fence craft](src/main/resources/readme/fence_craft_new.png)

It is also possible to craft an iron gate:
##### Before 1.20.4
![Iron gate craft](src/main/resources/readme/gate_craft.png)
##### 1.20.4
![Iron gate craft](src/main/resources/readme/gate_craft_new.png)

Also, to craft a strikethrough block, you must have the following items:

![Strikethrough block craft](src/main/resources/readme/strike_craft.png)

In addition to creating crossed-out letters, this block can have other uses, for example, the construction of strange structures like this:

![Strange construction](src/main/resources/readme/strange.png)
#  How to use - 1.18 (before 0.7.0)
So far, to create each block, you first need to create a basic one - ferroconcrete. Below is the crafting of this block. 
It is worth noting that the crafting and appearance of the base block are planned to be changed in the future.<br/>
![Crafting ferroconcrete](https://i.ibb.co/hfXQBBs/craft.png)

When a ferroconcrete block is received, it must be put into a stone cutter to get any variant of the letters blocks:<br/>
![Stone cutting](https://i.ibb.co/VqbfGF3/stonecutter.png)

That's it! In the future, it is planned to add a special block to create letter blocks for greater realism.

It is worth noting that all blocks use 16x16 standard resolution textures to avoid an obvious difference between standard blocks and blocks from the mod.

# Do you have a question or proposal?
If you have any suggestions (for example, support for new languages), ideas, and also bugs found, please email me - 
**ortodontalio@gmail.com**, I will try to answer you as soon as possible, and I will be glad to receive your emails. 
**Please do not write spam or insults, otherwise you will be blocked.**
