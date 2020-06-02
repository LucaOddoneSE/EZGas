# GUI  Testing Documentation 

Authors: Luca Oddone

Date: 06/02/2020

Version: 1.0

# GUI testing

This part of the document reports about testing at the GUI level. Tests are end to end, so they should cover the Use Cases, and corresponding scenarios.

## Coverage of Scenarios and FR

```
<Complete this table (from IntegrationApiTestReport.md) with the column on the right. In the GUI Test column, report the name of the .py  file with the test case you created.>
```

### 

| Scenario ID | Functional Requirements covered | GUI Test(s) |
| ----------- | ------------------------------- | ----------- | 
| 1           | FRx                             |             |             
| 2           | FRy                             |             |             
| ...         |                                 |             |         
| ...         |                                 |             |             
| ...         |                                 |             |             
| ...         |                                 |             |             


# REST  API  Testing

This part of the document reports about testing the REST APIs of the back end. The REST APIs are implemented by classes in the Controller package of the back end. 
Tests should cover each function of classes in the Controller package

## Coverage of Controller methods


<Report in this table the test cases defined to cover all methods in Controller classes >

| class.method name                                         | Functional Requirements covered |REST  API Test(s)                      | 
| --------------------------------------------------------- | -------------------------------  | -----------                          | 
| UserController.saveUser()                                 | FR1.1                            | testSavUser()                        |    
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
