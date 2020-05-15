# Unit Testing Documentation

Authors: Group 50 

Date: 15 May

Version: 1

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)

- [UserServiceimpl Class](#UserServiceimpl-Class)
- [GasStationServiceimpl Class](#GasStationServiceimpl-Class)

- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and (traceability) the correspondence with the JUnit test case writing the 
    class and method name that contains the test case>
    <JUnit test classes must be in src/test/java/it/polito/ezgas   You find here, and you can use,  class EZGasApplicationTests.java that is executed before 
    the set up of all Spring components
    >

 ## UserServiceimpl Class

 ### **Class *UserServiceimpl* - method *getUserById(Integer userId)***



**Criteria for method *getUserById(Integer userId)*:**
	

 - Number of Input parameters for getUserById(Integer userId)
 - Type of parameters passed to getUserById(Integer userId)
 - sign of userId





**Predicates for method *getUserById(Integer userId)*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 1       |
|          |     2 and above      |
|     Type of parameters passed to method     |     Integer      |
|          |     All other types      |
|     sign of userId     |   Positive        |
|          |     Negative      |
|          |     Mixed      |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         1        |
|      Type of parameters passed to method    |        Integer         |
|sign of userId|Positive|


**Combination of predicates**:


| Number of Input parameters | Type of parameters passed to method | sign of userId | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|1|Int|Positive|V|getUserById(23) --> valid userId||
|1|Int|Negative|I|getUserById(-23) --> Exception||
|1|char|-|I|getUserById('A') --> Exception||
|1|float|-|I|getUserById(1.2) --> Exception||
|1|All other types|-|I|getUserById("1.2") --> Exception||
|0|-|-|I|getUserById() --> Exception||
|1<|-|-|I|getUserById(23, 65) --> Exception||



 ### **Class *UserServiceimpl* - method *saveUser(UserDto userDto)***

**Criteria for method *saveUser(UserDto userDto)*:**
	

 - Number of Input parameters 
 - Type of parameters passed 



**Predicates for method *saveUser(UserDto userDto)*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 1       |
|          |     2 and above      |
|     Type of parameters passed to method     |     UserDto      |
|          |     All other types      |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         1        |
|          |         <> Null      |
|      Type of parameters passed to method    |        UserDto         |



**Combination of predicates**:


| Number of Input parameters | Type of parameters passed to method | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|1|UserDto|V|saveUser(UserDto userDto) --> User Correctly saved!||
|0|UserDto|I|saveUser() --> Exception||
|>1|UserDto|I|saveUser(UserDto userDto1, UserDto userDto2) --> Exception||
|1|All other types|I|saveUser(Integer userDto) --> Exception||


 ### **Class *UserServiceimpl* - method *deleteUser(Integer userId)***

**Criteria for method *deleteUser(Integer userId)*:**
	

 - Number of Input parameters 
 - Type of parameters passed to method
 - sign of parameters passed 

**Predicates for method *deleteUser(Integer userId)*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 1       |
|          |     2 and above      |
|     Type of parameters passed to method     |     Integer     |
|          |     All other types      |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         1        |
|      Type of parameters passed to method    |        Integer         |
|sign of userId|Positive|


**Combination of predicates**:


| Number of Input parameters | Type of parameters passed to method | sign of userId | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|1|Int|Positive|V|deleteUser(23) --> Deletes the user with the given Id from the database||
|1|Int|Negative|I|deleteUser(-23) --> Exception||
|1|char|-|I|deleteUser('A') --> Exception||
|1|float|-|I|deleteUser(1.2) --> Exception||
|1|All other types|-|I|deleteUser("1.2") --> Exception||
|0|-|-|I|deleteUser() --> Exception||
|1<|-|-|I|deleteUser(23, 65) --> Exception||


 ### **Class *UserServiceimpl* - method *login(IdPw credentials)***

**Criteria for method *login(IdPw credentials)*:**

 - Number of Input parameters 
 - Type of parameters passed to method

**Predicates for method *login(IdPw credentials)*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 1       |
|          |     2 and above      |
|     Type of parameters passed to method     |     String     |
|          |     All other types      |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         1        |
|          |         <> Null      |
|      Type of parameters passed to method    |        IdPw         |


**Combination of predicates**:

| Number of Input parameters | Type of parameters passed to method | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|1|IdPw|V|login(IdPw credentials) --> Valid||
|0|IdPw|I|login() --> Exception||
|>1|IdPw|I|login(IdPw credential1,IdPw credential2) --> Exception||
|1|All other types|I|login(Integer credentials) --> Exception||


 ### **Class *UserServiceimpl* - method *increaseUserReputation(Integer userId) , decreaseUserReputation(Integer userId)***

**Criteria for method *increaseUserReputation(Integer userId) , decreaseUserReputation(Integer userId)*:**

 - Number of Input parameters 
 - Type of parameters passed to method
 - sign of parameters passed 

**Predicates for method *increaseUserReputation(Integer userId) , decreaseUserReputation(Integer userId)*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 1       |
|          |     2 and above      |
|     Type of parameters passed to method     |     Integer     |
|          |     All other types      |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         1        |
|      Type of parameters passed to method    |        Integer         |
|sign of userId|Positive|

**Combination of predicates**:

| Number of Input parameters | Type of parameters passed to method | sign of userId | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|1|Int|Positive|V|increaseUserReputation(23) --> Increases by 1 the user Reputation||
|1|Int|Negative|I|increaseUserReputation(-23) --> Exception||
|1|char|-|I|increaseUserReputation('A') --> Exception||
|1|float|-|I|increaseUserReputation(1.2) --> Exception||
|1|All other types|-|I|increaseUserReputation("1.2") --> Exception||
|0|-|-|I|increaseUserReputation() --> Exception||
|1<|-|-|I|increaseUserReputation(23, 65) --> Exception||


 ## GasStationServiceimpl Class

 ### **Class *GasStationServiceimpl* - method *getGasStationById(Integer gasStationId)***


 - Number of Input parameters for getGasStationById(Integer gasStationId)
 - Type of parameters passed to getGasStationById(Integer gasStationId)
 - sign of gasStationId





**Predicates for method *getGasStationById(Integer gasStationId)*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 1       |
|          |     2 and above      |
|     Type of parameters passed to method     |     Integer      |
|          |     All other types      |
|     sign of userId     |   Positive        |
|          |     Negative      |
|          |     Mixed      |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         1        |
|      Type of parameters passed to method    |        Integer         |
|sign of GasStationId|Positive|


**Combination of predicates**:


| Number of Input parameters | Type of parameters passed to method | sign of userId | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|1|Int|Positive|V|getGasStationById(23) --> valid GasStationId||
|1|Int|Positive|V|getGasStationById(455) --> Null, Not found GasStaion with this Id||
|1|Int|Negative|I|getGasStationById(-23) --> Exception||
|1|char|-|I|getGasStationById('A') --> Exception||
|1|float|-|I|getGasStationById(1.2) --> Exception||
|1|All other types|-|I|getGasStationById("1.2") --> Exception||
|0|-|-|I|getGasStationById() --> Exception||
|1<|-|-|I|getGasStationById(23,65) --> Exception||





# White Box Unit Tests

### Test cases definition
    
    <JUnit test classes must be in src/test/java/it/polito/ezgas>
    <Report here all the created JUnit test cases, and the units/classes under test >
    <For traceability write the class and method name that contains the test case>


| Unit name | JUnit test case |
|--|--|
|||
|||
||||

### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >


### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||



