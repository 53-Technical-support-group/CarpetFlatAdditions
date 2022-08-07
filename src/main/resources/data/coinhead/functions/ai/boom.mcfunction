kill @e[limit=1,sort=nearest,nbt={Tags:["coinpoint"]}]

execute at @s run summon minecraft:creeper ^ ^1 ^ {CustomName:'{"italic":false,"color":"blue","translate":"硬币"}',Fuse:0,ExplosionRadius:4b}

kill @s