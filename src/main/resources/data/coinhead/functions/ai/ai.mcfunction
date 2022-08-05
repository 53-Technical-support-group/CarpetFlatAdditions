execute if entity @s[scores={NPCTimer=300}] run scoreboard players set @s NPCTimer 0

execute if entity @s[scores={Animat=..13}] at @s if entity @a[distance=..64] run function coinhead:ai/chase

execute if entity @s[scores={Animat=..13}] at @s if entity @e[type=villager,distance=..64] run function coinhead:ai/chase2

execute if entity @s[scores={Animat=..13}] at @s if entity @a[distance=..16] run scoreboard players set @s Animat 20

execute if entity @s[scores={Animat=..13}] at @s if entity @e[type=villager,distance=..16] run scoreboard players set @s Animat 20

execute if entity @s[scores={Animat=20..35}] run data merge entity @s {Motion:[0.0d,0.0d,0.0d]}

execute if entity @s[scores={Animat=20..35}] at @s run tp @s ~ ~ ~ facing entity @p[distance=..16]

execute if entity @s[scores={Animat=20..35}] at @s run tp @s ~ ~ ~ facing entity @e[limit=1,sort=nearest,type=villager,distance=..16]

execute if entity @s[scores={Animat=35}] at @s run summon minecraft:zombie ~ ~1 ~ {Tags:[coin],ArmorItems:[{},{},{},{id:"minecraft:emerald",Count:1,tag:{CustomModelData:1150007.0f}}],ArmorDropChances:[0.0f,0.0f,0.0f,0.0f],ActiveEffects:[{Ambient:0,ShowIcon:0b,ShowParticles:0b,Id:14,Duration:2147483647,Amplifier:9}],NoAI:1b,Silent:1b,Invulnerable:1b,DeathLootTable:"minecraft:none"}

execute if entity @s[scores={Animat=35}] at @s as @e[limit=1,sort=nearest,nbt={Tags:[coin]}] run data modify entity @s Rotation set from entity @e[limit=1,sort=nearest,nbt={Tags:[coinhead]}] Rotation

execute if entity @s[scores={Animat=35}] at @s run playsound minecraft:minecraft.coin voice @a[distance=..24] ~ ~ ~ 1.0 1.0 1.0

execute if entity @s[scores={Animat=35}] run data merge entity @s {Tags:[coinhead2]}