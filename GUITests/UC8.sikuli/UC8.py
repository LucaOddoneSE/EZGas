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
find("1591038252917.png")