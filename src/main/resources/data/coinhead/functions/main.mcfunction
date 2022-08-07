execute as @e[nbt={Tags:["coinhead"]}] run function coinhead:ai/animat
execute as @e[nbt={Tags:["coinhead"]}] run function coinhead:ai/ai

execute as @e[nbt={Tags:["coinhead2"]}] run function coinhead:ai/animat2
execute as @e[nbt={Tags:["coinhead2"]}] run function coinhead:ai/ai2

execute as @e[nbt={Tags:["coin"]}] run function coinhead:ai/coin

execute if score $value SpawnCount matches 1.. as @r run function coinhead:spawn/spawn

execute as @e[type=zombie,tag=ch] run effect give @s weakness 1000000 10 true
