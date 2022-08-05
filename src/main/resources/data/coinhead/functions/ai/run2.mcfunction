execute at @s run data modify entity @s Rotation set from entity @e[limit=1,sort=nearest,type=villager] Rotation

data merge entity @s {Motion:[0.0d,-1.0d,0.0d]}

execute unless block ^ ^ ^1 minecraft:air run data merge entity @s {Motion:[0.0d,0.5d,0.0d]}

execute at @s run execute store result score #temp0 Speed1 run data get entity @e[limit=1,sort=nearest,type=villager] Pos[0] 100
execute store result score #temp1 Speed1 run data get entity @s Pos[0] 100
execute store result entity @s Motion[0] double 0.0004 run scoreboard players operation #temp1 Speed1 -= #temp0 Speed1
execute at @s run execute store result score #temp0 Speed1 run data get entity @e[limit=1,sort=nearest,type=villager] Pos[2] 100
execute store result score #temp1 Speed1 run data get entity @s Pos[2] 100
execute store result entity @s Motion[2] double 0.0004 run scoreboard players operation #temp1 Speed1 -= #temp0 Speed1