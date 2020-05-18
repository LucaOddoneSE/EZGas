# Unit Testing Documentation

Authors: Group 50 

Date: 15 May

Version: 1

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)
- [entity.GasStation](##entity.GasStation)
- [entity.PriceReport](##entity.PriceReport)
- [entity.User](##entity.User)
- [dto.LoginDto](##dto.LoginDto)
- [dto.GasStation](##dto.GasStation)
- [dto.IdPw](##dto.IdPw)
- [dto.PriceReport](##dto.PriceReport)

- [UserServiceimpl](##UserServiceimpl)
- [GasStationServiceimpl](##GasStationServiceimpl)

- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

 ## UserServiceimpl

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
|    sign of parameters passed      |     Positive      |
|          |     Negative      |

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


 ## GasStationServiceimpl

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
|1|Int|Positive|V|getGasStationById(23) --> valid GasStationId, GasStation found with this Id||
|1|Int|Positive|V|getGasStationById(455) --> Null, Not found GasStaion with this Id||
|1|Int|Negative|I|getGasStationById(-23) --> Exception||
|1|char|-|I|getGasStationById('A') --> Exception||
|1|float|-|I|getGasStationById(1.2) --> Exception||
|1|All other types|-|I|getGasStationById("1.2") --> Exception||
|0|-|-|I|getGasStationById() --> Exception||
|1<|-|-|I|getGasStationById(23,65) --> Exception||


 ### **Class *GasStationServiceimpl* - method *saveGasStation(GasStationDto gasStationDto)***
 - Number of Input parameters for saveGasStation(GasStationDto gasStationDto)
 - Type of parameter passed to saveGasStation(GasStationDto gasStationDto)


**Predicates for method *saveGasStation(GasStationDto gasStationDto)*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 1       |
|          |     2 and above      |
|     Type of parameters passed to method     |     GasStationDto      |
|          |     All other types      |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         1        |
|          |         <> Null      |
|      Type of parameters passed to method    |        GasStationDto         |



**Combination of predicates**:


| Number of Input parameters | Type of parameters passed to method | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|1|GasStationDto|V|saveGasStation(GasStationDto gasStationDto) --> GasStationDto stored in the DB||
|0|GasStationDto|I|saveGasStation() --> Error!||
|>1|GasStationDto|I|saveGasStation(GasStationDto gasStationDto1, GasStationDto gasStationDto2) --> Exception||
|1|All other types|I|saveUser(Integer gasStationDto) --> Exception||


### **Class *GasStationServiceimpl* - method *deleteGasStation(Integer gasStationId)***

**Criteria for method *deleteGasStation(Integer gasStationId)*:**
	

 - Number of Input parameters 
 - Type of parameters passed to method
 - sign of parameters passed 

**Predicates for method *deleteGasStation(Integer gasStationId)*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 1       |
|          |     2 and above      |
|     Type of parameters passed to method     |     Integer     |
|          |     All other types      |
|    sign of parameters passed      |     Positive      |
|         |     Negative      |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         1        |
|      Type of parameters passed to method    |        Integer         |
|sign of gasStationId|Positive|


**Combination of predicates**:


| Number of Input parameters | Type of parameters passed to method | sign of userId | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|1|Int|Positive|V|deleteGasStation(23) --> Deletes the GasStation with the given Id from the database||
|1|Int|Negative|I|deleteGasStation(-23) --> Exception||
|1|char|-|I|deleteGasStation('A') --> Exception||
|1|float|-|I|deleteGasStation(1.2) --> Exception||
|1|All other types|-|I|deleteGasStation("1.2") --> Exception||
|0|-|-|I|deleteGasStation() --> Exception||
|1<|-|-|I|deleteGasStation(23, 65) --> Exception||



### **Class *GasStationServiceimpl* - method *getGasStationsByGasolineType(String gasolinetype)***

**Criteria for method *getGasStationsByGasolineType(String gasolinetype)*:**
	

 - Number of Input parameters 
 - Type of parameter passed to method
 - Correct format 
 
 **Predicates for method *getGasStationsByGasolineType(String gasolinetype)*:**

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
|      Type of parameters passed to method    |        String         |



**Combination of predicates**:


| Number of Input parameters | Type of parameters passed to method | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|1|String|V|getGasStationsByGasolineType("Diesel") --> Returns all gas stations that provide this gasoline type||
|0|-|V|getGasStationsByGasolineType() --> Return Empty ArrayList||
|1|String|I|getGasStationsByGasolineType(23) --> Exception||
|1|Int||I|getGasStationsByGasolineType(-23) --> Exception||
|1|float|I|getGasStationsByGasolineType(1.2) --> Exception||
|1|All other types|I|getGasStationsByGasolineType("1.2") --> Exception||
|1<|-|I|getGasStationsByGasolineType(23, 65) --> Exception||




### **Class *GasStationServiceimpl* - method *getGasStationsByProximity(double lat, double lon)***

**Criteria for method *getGasStationsByProximity(double lat, double lon)*:**
	

 - Number of Input parameters 
 - Type of parameter passed to method
 - Range 
 - Sign of the number 


 **Predicates for method *getGasStationsByProximity(double lat, double lon)*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 2       |
|          |     3 and above      |
|     Type of parameters passed to method     |     Double    |
|          |     All other types      |
|     Range     |    (lat > -90 || lat < 90) || (lon > -180 || lon < 180)     |
|          |    (lat > -90 OR lat < 90) OR (lon > -180 OR lon < 180)     |
|     Sign of the number     |     Positive      |
|          |     Negative      |
|          |     Mix      |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         2        |
|      Type of parameters passed to method    |        Double         |
|     Range     |   (lat > -90 OR lat < 90) OR (lon > -180 OR lon < 180)     |
|     Sign of the number     |     Positive, Negative, Mix      |



**Combination of predicates**:


| Number of Input parameters | Type of parameters passed to method | Range |Sign of the number| Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|-------|
|2|Double|(lat > -90 OR lat < 90) OR (lon > -180 OR lon < 180)|Mix|V|getGasStationsByProximity(double lat, double lon) --> Returns all gas stations within 1km from the GeoPoint||
|2|Double|(lat < -90 OR lat > 90) OR (lon < -180 OR lon > 180)|Mix|V|getGasStationsByProximity(double lat, double lon) --> Exception, coordinates out of bounds!||
|2|All other types|-|-|I|getGasStationsByProximity(Int lat, Int lon) --> Exception||
|1|Double|-|-|I|getGasStationsByProximity(Int lat) --> Exception||
|0|Double|-|-|I|getGasStationsByProximity() --> Exception||
|>2|Double|-|-|I|getGasStationsByProximity(double lat, double lon,double x) --> Exception||


### **Class *GasStationServiceimpl* - method *getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,String carsharing)***

**Criteria for method *getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,String carsharing)*:**
	

 - Number of Input parameters 
 - Type of parameter passed to method
 - Range 
 - Sign of the number 
 - Gasolinetype
 


 **Predicates for method *getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,String carsharing)*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 4       |
|          |     5 and above      |
|     Type of parameters passed to method     |     Double and String    |
|          |     All other types      |
|     Range  Lat,Lon   |    (lat > -90 OR lat < 90) OR (lon > -180 OR lon < 180)     |
|          |    (lat > -90 OR lat < 90) OR (lon > -180 OR lon < 180)     |
|     Sign of the Lat,Lon     |     Positive      |
|          |     Negative      |
|          |     Mix      |
|    Gasolinetype      |   one of these:  diesel,methane,gas,super,superplus      |
|          |    Not supported     |
|          |    Null     |
|          |  <>  Null     |
|    carsharing     |     <> Null      |
|         |      Null      |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         4        |
|      Type of parameters passed to method    |        Double and String         |
|     Range Lat,Lon     |   (lat > -90 OR lat < 90) OR (lon > -180 OR lon < 180)     |
|     Sign of the Lat,Lon     |     Positive, Negative, Mix      |
|    Gasolinetype      |     diesel,methane,gas,super,superplus      |
|    carsharing     |     <> Null      |


**Combination of predicates**:


| Number of Input parameters | Type of parameters passed to method | Range |Sign of the number|Gasolinetype|carsharing| Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
|4|Double,String|(lat > -90 OR lat < 90) OR (lon > -180 OR lon < 180)|Mix|One of supported types|<>Null|V|getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,String carsharing) --> Returns all gas stations within 1km from the GeoPoint, with gasolinetype and a carsharing value parameters ||
|4|Double,String|(lat < -90 OR lat > 90) OR (lon < -180 OR lon > 180)|Mix|One of supported types|<>Null|I|getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,String carsharing) --> Exception, coordinates out of bounds! ||
|3|Double,String|(lat > -90 OR lat < 90) OR (lon > -180 OR lon < 180)|Mix|<> Supported Gasolinetype|<>Null|I|getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,String carsharing) --> Exception ||
|4|Double,String|(lat > -90 OR lat < 90) OR (lon > -180 OR lon < 180)|Mix|One of supported types|Null|I|getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,Null) --> Exception ||
|2|String|Null|-|One of supported types|<>Null|I|getGasStationsWithCoordinates(String gasolinetype,String carsharing) --> Exception ||
|2|Double|(lat > -90 OR lat < 90) OR (lon > -180 OR lon < 180)|Mix|Null|Null|I|getGasStationsWithCoordinates(double lat, double lon) --> Exception ||



### **Class *GasStationServiceimpl* - method *getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,String carsharing)***

**Criteria for method *getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,String carsharing)*:**
	

 - Number of Input parameters 
 - Type of parameter passed to method
 - Gasolinetype
 


 **Predicates for method *getGasStationsWithoutCoordinates(String gasolinetype,String carsharing)*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 2       |
|          |     3 and above      |
|     Type of parameters passed to method     |      String    |
|          |     All other types      |
|    Gasolinetype      |   one of these:  diesel,methane,gas,super,superplus      |
|          |    Not supported     |
|          |    Null     |
|          |  <>  Null     |
|    carsharing     |     <> Null      |
|         |      Null      |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         2        |
|      Type of parameters passed to method    |       String         |
|    Gasolinetype      |     diesel,methane,gas,super,superplus      |
|    carsharing     |     <> Null      |


**Combination of predicates**:


| Number of Input parameters | Type of parameters passed to method | Gasolinetype|carsharing| Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|-------|
|2|String|One of supported types|<>Null|V|getGasStationsWithoutCoordinates(String gasolinetype,String carsharing) --> Valid ||
|2|String|<> One of supported types|<>Null|I|getGasStationsWithoutCoordinates(String gasolinetype,String carsharing) --> Exception, Gas Type not supported! ||
|1|String|One of supported types|Null|I|getGasStationsWithoutCoordinates(String gasolinetype,Null) --> Exception ||
|1|String|Null|<>Null|I|getGasStationsWithoutCoordinates(Null,String carsharing) --> Exception ||
|0|String|Null|Null|I|getGasStationsWithoutCoordinates() --> Exception ||
|>2|String|One of supported types|<>Null|I|getGasStationsWithoutCoordinates(String gasolinetype,String carsharing,String X) --> Exception ||


### **Class *GasStationServiceimpl* - method *setReport()***

**Criteria for method *setReport()*:**
	

 - Number of Input parameters 
 - Type of parameter passed to method
 - Sign of the Price, UserId

 
 
 **Predicates for method *setReport()*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 8       |
|          |     9 and above      |
|     Type of parameters passed to method     |      Double, Integer    |
|          |     All other types      |
|    Sign of the Price,UserId      |     Positive      |
|          |     Negative      |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         8        |
|      Type of parameters passed to method    |       Double, Integer         |
|    Sign of the Price, UserId      |     Positive      |


**Combination of predicates**:


| Number of Input parameters | Type of parameters passed to method | Sign of the Price,UserId| Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|--------------|-------|-------|
|8|Double, Integer|Positive|V|setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, Integer userId) --> Valid ||
|8|Double, Integer|Negative|I|setReport(Integer gasStationId, -25.4, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, Integer userId) --> Exception, Negative Price! ||
|8|Double, Integer|Negative|I|setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, -20) --> Exception, Negative UserId! ||
|<8|Double, Integer|Positive|I|setReport(Integer gasStationId, Integer userId) --> Exception, FuelPrices are missed! ||
|<8|Double, Integer|Positive|I|setReport(double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, Integer userId) --> Exception, GasStationId is missed! ||
|<8|Double, Integer|Positive|I|setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice) --> Exception, userId is missed! ||
|>8|Double, Integer|Positive|I|setReport(more than 8 input parameters) --> Exception ||

 ## GasStationDto

### **Class * GasStationDto****

**Criteria for all methods*:**
	
 - Number of Input parameters 
 - Type of parameter passed to method
 - Range for Integer
 - valid format of string



 **Conditions (predicates on criteria) for all methods*:**

| Criteria | Predicate |
| -------- | --------- |
|  Number of Input parameters        |    0 to 19       |
|          |     20 and above      |
|     Type of parameters passed to method     |      Boolean, String, Double, Integer    |
|          |     All other types      |
|    Range      |   >=MinInt and  <= MaxInt      |
|          |     > MaxInt      |
|          |     < MinInt      |
|   valid format of string       |     "Polito"      |
|          |     "8?#@"      |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     Number of Input parameters     |         19        |
|      Type of parameters passed to method    |        Boolean, String, Double, Integer         |


**Combination of predicates (define test cases) for GasStationDto()**:

| Number of Input parameters | Type of parameters passed to method |  Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|--------------|-------|
|19| Boolean, String, Double, Integer|V|GasStationDto(All input parameters) --> Valid ||


**Combination of predicates (define test cases) for getGasStationName()**:

| Number of Input parameters | Type of parameters passed to method |  Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|--------------|-------|
|-|-|V|getGasStationName() --> Valid ||


**Combination of predicates (define test cases) for setGasStationName()**:

| Number of Input parameters | Type of parameters passed to method | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|--------------|-------|
|1|String|V|setGasStationName("GiacomoBalla") --> Valid ||
|1|String|I|setGasStationName("") --> Exception ||
|0|String|I|setGasStationName() --> Exception ||
|1|String|I|setGasStationName("?@3*") --> Exception, Invalid Name format ||
|1|Integer|I|setGasStationName(234) --> Exception, Invalid Input type! ||
|1|Double|I|setGasStationName(2.34) --> Exception, Invalid Input type! ||
|>1|String|I|setGasStationName("GiacomoBalla","Polito") --> Exception, 2 Input parameters! ||


# White Box Unit Tests

## Package Entity - Class GasStation

### Test cases definition
    
    <JUnit test classes must be in src/test/java/it/polito/ezgas>
    <Report here all the created JUnit test cases, and the units/classes under test >
    <For traceability write the class and method name that contains the test case>

| Unit name | JUnit test case |
|--|--|
| getGasStationId() | testGasStationId() |
| getGasStationName() | testGasStationName() |
| getGasStationAddress() | testGasStationAddress() |
| getReportDependability() | testReportDependability() |

### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >
    Coverage panel
    [](https://ibb.co/61zRKf8)
    
    Coverage methods
    [](https://ibb.co/xX69Kpb)
    [](https://ibb.co/sm9xpNT)
    [](https://ibb.co/dcDcwtH)
    [](https://ibb.co/SsWXr2F)

### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
| getGasStationId() | - | - | testGasStationId() |
| getGasStationName() | - | - | testGasStationName() |
| getGasStationAddress() | - | - | testGasStationAddress() |
| getReportDependability() | - | - | testReportDependability() |



