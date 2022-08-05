scoreboard players add @s NPCTimer 1

execute if entity @s[scores={NPCTimer=1..10}] at @s run tp @s ^ ^0.25 ^0.5

execute if entity @s[scores={NPCTimer=11..20}] at @s run tp @s ^ ^-0.25 ^0.3

execute if entity @s[scores={NPCTimer=21..30}] at @s run tp @s ^ ^-0.5 ^0.2

execute if entity @s[scores={NPCTimer=31..40}] at @s run tp @s ^ ^-1 ^0.1

execute if entity @s[scores={NPCTimer=41..50}] at @s run tp @s ^ ^-2 ^

execute if entity @s[scores={NPCTimer=50}] run function coinhead:ai/boom

execute at @s unless block ~ ~ ~ minecraft:air run function coinhead:ai/boom