scoreboard players add @s NPCTimer 1

execute if entity @s[scores={NPCTimer=100}] at @s positioned ~ ~ ~25 if predicate minecraft:random run function coinhead:ai/spawn_yes

execute if entity @s[scores={NPCTimer=100}] at @s positioned ~ ~ ~-25 if predicate minecraft:random run function coinhead:ai/spawn_yes

execute if entity @s[scores={NPCTimer=100}] at @s positioned ~25 ~ ~ if predicate minecraft:random run function coinhead:ai/spawn_yes

execute if entity @s[scores={NPCTimer=100}] at @s positioned ~-25 ~ ~ if predicate minecraft:random run function coinhead:ai/spawn_yes

execute if entity @s[scores={NPCTimer=200}] run scoreboard players set @s NPCTimer 0
