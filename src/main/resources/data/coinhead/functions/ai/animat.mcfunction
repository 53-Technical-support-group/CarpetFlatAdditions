scoreboard players add @s Animat 1

execute if entity @s[scores={Animat=..13},nbt={Motion:[0d,0d,0d]}] run scoreboard players set @s Animat 0

execute if entity @s[scores={Animat=0}] run item modify entity @s armor.head coinhead:timer/timer0

execute if entity @s[scores={Animat=1..2}] run item modify entity @s armor.head coinhead:timer/timer1

execute if entity @s[scores={Animat=3..4}] run item modify entity @s armor.head coinhead:timer/timer2

execute if entity @s[scores={Animat=5..6}] run item modify entity @s armor.head coinhead:timer/timer3

execute if entity @s[scores={Animat=7..8}] run item modify entity @s armor.head coinhead:timer/timer4

execute if entity @s[scores={Animat=9..10}] run item modify entity @s armor.head coinhead:timer/timer5

execute if entity @s[scores={Animat=11..12}] run item modify entity @s armor.head coinhead:timer/timer6

execute if entity @s[scores={Animat=13}] run scoreboard players set @s Animat 0

execute if entity @s[scores={Animat=20..24}] run item modify entity @s armor.head coinhead:timer/timert1

execute if entity @s[scores={Animat=25..29}] run item modify entity @s armor.head coinhead:timer/timert2

execute if entity @s[scores={Animat=30..34}] run item modify entity @s armor.head coinhead:timer/timert3

execute if entity @s[scores={Animat=35}] run item modify entity @s armor.head coinhead:timer/timert4