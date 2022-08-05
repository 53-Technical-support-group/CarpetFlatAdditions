scoreboard players add @s Animat 1

execute if entity @s[nbt={Motion:[0d,0d,0d]}] run scoreboard players set @s Animat 0

execute if entity @s[scores={Animat=0}] run item modify entity @s armor.head coinhead:timer/timer10

execute if entity @s[scores={Animat=1..2}] run item modify entity @s armor.head coinhead:timer/timer11

execute if entity @s[scores={Animat=3..4}] run item modify entity @s armor.head coinhead:timer/timer12

execute if entity @s[scores={Animat=5..6}] run item modify entity @s armor.head coinhead:timer/timer13

execute if entity @s[scores={Animat=7..8}] run item modify entity @s armor.head coinhead:timer/timer14

execute if entity @s[scores={Animat=9..10}] run item modify entity @s armor.head coinhead:timer/timer15

execute if entity @s[scores={Animat=11..12}] run item modify entity @s armor.head coinhead:timer/timer16

execute if entity @s[scores={Animat=13..}] run scoreboard players set @s Animat 0