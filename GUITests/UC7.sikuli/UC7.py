myApp = App("Chrome")
myApp.focus()
wait(1)
# login as user1
click("1591036426694.png")
wait(3)
find("1591036889381.png")
type("1591036985849.png","fereshteh.feizabadi70@gmail.com")
wait(1)
type("1591036993989.png","0014053772")
wait(1)
click("1590852943968.png")
wait(3)

# scroll down
for i in range(12):
    type(Key.DOWN)
    
# search for a gas station
type("1591037665305.png","Giacomo Balla")
wait(3)
type(Key.DOWN)
wait(2)
click("1590858853832.png")
wait(2)

# add report 
click("1591037757852.png")
wait(2)
for i in range(20):
    type(Key.DOWN)
wait(1)
type("1591037786176.png","1.5")
wait(1)
type("1591037796405.png","2")
wait(1)
type("1591037822050.png","1")
wait(1)
type("1591037832344.png","2.5")
wait(1)
type("1591037842228.png","1.75")
wait(3)
click("1591037863413.png")

# check the report
wait(3)
click("1590859887378.png")
wait(2)
find("1591037945787.png")