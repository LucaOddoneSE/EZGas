# GUI  Testing Documentation 

Authors: Group50

Date: 2 July

Version: 1.1

# GUI testing

This part of the document reports about testing at the GUI level. Tests are end to end, so they should cover the Use Cases, and corresponding scenarios.

## Coverage of Scenarios and FR


### 

| Scenario ID | Functional Requirements covered | GUI Test(s) |
| ----------- | ------------------------------- | ----------- | 
|  UC1     | FR1.1: Create User Account                             |     UC1        |             
|  UC2     | FR1.1: Modify user account                             |      UC2       |             
|    UC3    |      FR1.1: Delete user account                            |  UC3-1,UC3-2           |         
|    UC4      |        FR3.1: Create Gas Station                         |      UC4       |             
|      UC5    |           FR3.1: Modify Gas Station information                      |     UC5        |             
|         UC6 |                   FR3.2: Delete Gas Station              |       UC6      | 
|         UC7 |                   FR5.1: Report fuel price for a gas station              |     UC7        | 
|   UC8       |                   FR4.3: Obtain price of fuel for gas stations in a certain geographic area | UC8 | 
|    UC9    |                   FR5.2: Update trust level of price list              |       UC9-10      |             
|      UC10   |                   FR5.3: Evaluate price              |     UC9-10        |             


# REST  API  Testing

This part of the document reports about testing the REST APIs of the back end. The REST APIs are implemented by classes in the Controller package of the back end. 
Tests should cover each function of classes in the Controller package

## Coverage of Controller methods


<Report in this table the test cases defined to cover all methods in Controller classes >

| class.method name                                         | Functional Requirements covered |REST  API Test(s)                      | 
| --------------------------------------------------------- | -------------------------------  | -----------                          | 
| UserController.saveUser()                                 | FR1.1                            | testSaveUser()                        |    
| UserController.deleteUser()                               | FR1.2                            | testDeleteUser()                     |    
| UserController.testGetAllUsers()                          | FR1.3                            | testGetAllUsers()                    |    
| UserController.getUserById()                              | FR1.4                            | testGetUserById()                    |    
| UserController.login()                                    | FR2                              | testLogin()                          |    
| GasStationController.saveGasStation()                     | FR3.1                            | testSaveGasStation()                 |
| GasStationController.deleteGasStation()                   | FR3.2                            | testDeleteGasStation()               |
| GasStationController.getAllGasStations()                  | FR3.3                            | testGetAllGasStations()              |
| GasStationController.getGasStationById()                  | FR4                              | testGetGasStationById()              |
| GasStationController.getGasStationByProximity()           | FR4.1                            | testGetGasStationByProximity()       |
| GasStationController.getGasStationsWithCoordinates()      | FR4.2                            | testGetGasStationsWithCoordinates()  |
| -                                                         | FR4.3                            | -                                    |
| GasStationController.getGasStationsByGasolineType()       | FR4.4                            | testGetGasStationsByGasolineType()   |
| -                                                         | FR4.5                            | -                                    |
| GasStationController.setReport()                          | FR5                              | testSetReport()                      |
| UserController.increaseUserReputation()                   | FR5                              | testIncreaseUserReputation()         |
| UserController.decreaseUserReputation()                   | FR5                              | testdecreaseUserReputation()         |
| GasStationController.setReport()                          | FR5.1                            | testSetReport()                      |
| GasStationController.setReport()                          | FR5.2                            | testSetReport()                      |
| GasStationController.setReport()                          | FR5.3                            | testSetReport()                      |





N.B. the '-' means that the functional requirement is defined but the corresponding method in XClassController does not exist.
So for this reason it was impossible to map the functional requirement in a method and in a REST API Test. 
