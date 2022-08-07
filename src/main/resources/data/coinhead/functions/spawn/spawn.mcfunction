scoreboard players add @s NPCTimer 1

execute if score $value SpawnCount matches 1.. if entity @s[scores={NPCTimer=100}] as @e[type=zombie,limit=1,sort=random,distance=..50,tag=!ch] if predicate minecraft:random at @s run function coinhead:spawn/spawn_yes

execute if entity @s[scores={NPCTimer=200}] run scoreboard players set @s NPCTimer 0
