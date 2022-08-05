execute at @s if entity @a[distance=..8] run function coinhead:ai/run

execute at @s if entity @e[type=villager,distance=..8] run function coinhead:ai/run2

execute at @s unless entity @a[distance=..8] unless entity @e[type=villager,distance=..8] run data merge entity @s {Motion:[0.0d,0.0d,0.0d]}

scoreboard players add @s NPCTimer 1

execute if entity @s[scores={NPCTimer=300}] at @s run playsound minecraft:minecraft.coin voice @a[distance=..24] ~ ~ ~ 1.0 1.0 1.0

execute if entity @s[scores={NPCTimer=300}] run data merge entity @s {Tags:[coinhead]}