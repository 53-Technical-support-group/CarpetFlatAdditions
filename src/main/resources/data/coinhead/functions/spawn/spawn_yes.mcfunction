summon minecraft:zombie ~ ~ ~ {Attributes:[{Base:30.0d,Name:"minecraft:generic.max_health"},{Base:0.3d,Name:"minecraft:generic.movement_speed"}],CustomName:'{"italic":false,"color":"blue","translate":"头币人"}',Tags:["coinhead","ch"],ArmorItems:[{},{},{},{id:"minecraft:emerald",Count:1,tag:{CustomModelData:1150000}}],ArmorDropChances:[0.0f,0.0f,0.0f,0.0f],ActiveEffects:[{Ambient:0,ShowIcon:0b,ShowParticles:0b,Id:14,Duration:2147483647,Amplifier:9}],Silent:1b,DeathLootTable:"minecraft:coinhead"}
scoreboard players remove $value SpawnCount 1

tp @s ~ ~-500 ~
