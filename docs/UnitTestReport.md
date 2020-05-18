# Unit Testing Documentation

Authors: Group 50 

Date: 15 May

Version: 1

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)
- [GasStation](##GasStation)
- [User](##User)
- [LoginDto](##LoginDto)
- [IdPwDto](##IdPwDto)


- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

##GasStation Class

**Criteria for method *setGasStationId(Integer gasStationId)*:**
 	
 - sign 
 - Range

 
  
**Predicates for method *setGasStationId(Integer gasStationId)*:**

| Criteria | Predicate |
| -------- | :---------: |
|  Range    |  gasStationId ≥ maxint    |
|           |  gasStationId ≤ minint    |
|           | minint ≤ gasStationId ≤ maxint    |
|  Sign     |  Positive        |
|           |  Negative         |



**Boundaries for method *setGasStationId(Integer gasStationId)**:

| Criteria | Boundary values |
| -------- | --------------- |
|  Range    |  minint, maxint    |
|sign|Positive|



**Combination of predicates**:

| Type of parameter | sign | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|Positive|V|setGasStationId(1) --> valid GasStationId|testGasStationId|
|Negative|I|setGasStationId(-1) --> Exception|testGasStationId1|



### **Class *GasStation* - method *getGasStationId()***

**Criteria for method *getGasStationId()*:**
	
 - gasStationId is null
  
**Predicates for method *getGasStationId()*:**

| Criteria | Predicate |
| -------- | :---------: |
| gasStationId is null    |  True    |
|                                |  False   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:


| gasStationId is null| Valid / Invalid | Description of the test case | JUnit test case 
|:-------:|:-------:|-------|-------|
|True|Valid|getGasStationId()-> NULL| testGasStationId2 |
|False|Valid|getGasStationId()-> 4| testGasStationId3|





**Criteria for method *setGasStationName(String gasStationName)*:**
 	
 
 - Type of parameter
 - String Lenght 


 
  
**Predicates for method *setGasStationName(String gasStationName)*:**

| Criteria | Predicate |
| -------- | :---------: |
|  String length    |  0 < s.length < s.maxlength   |
|           | s.length > s.maxlength  |



**Boundaries for method *setGasStationName(String gasStationName)**:

| Criteria | Boundary values |
| -------- | --------------- |
|  String length    |  0 < s.length < s.maxlength   |





**Combination of predicates**:

|Type| Length| Valid / Invalid | Description of the test case | JUnit test case |
|:-------:|:-------:|:-------:|-------|
| 0 < s.length < s.maxlength  | Valid | setGasStationName("GiacomoBalla") -> valid|testGasStationName|
| s.length = 0  | Valid | setGasStationName("") -> ""|testGasStationName1|



### **Class *GasStation* - method *getGasStationName()***

**Criteria for method *getGasStationName()*:**
	
 - GasStationName is null
  
**Predicates for method *getGasStationName()*:**

| Criteria | Predicate |
| -------- | :---------: |
| GasStationName is null    |  True    |
|                                |  False   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:


| GasStationName is null| Valid / Invalid | Description of the test case | JUnit test case 
|:-------:|:-------:|-------|-------|
|True|Valid|getGasStationId()-> NULL| testGasStationName2 |
|False|Valid|getGasStationId()-> "GiacomoBalla"| testGasStationName3|




### **Class *GasStation* - method *setReportDependability(double reportDependability)***

**Criteria for method *setReportDependability(double reportDependability)*:**
	
 - Range
 - Sign
  
  
**Predicates for method *setReportDependability(double reportDependability)*:**

| Criteria | Predicate |
| -------- | :---------: |
|  Range    |  reportDependability ≥ maxint    |
|           |  reportDependability ≤ minint    |
|           | minint ≤ reportDependability ≤ maxint    |
|  Sign     |  reportDependability > 0         |
|           |  reportDependability < 0         |


**Boundaries**:

| Criteria | Boundary values |
| -------- | :---------: |
|  Range    |  minint, maxint    |
|  Sign     |  0        |



**Combination of predicates**:


| Range|Sign| Valid / Invalid | Description of the test case | JUnit test case
|:-------:|:-------:|:-------:|-------|-------|
|reportDependability ≥ maxint|reportDependability > 0 |Valid|setReportDependability(Double.MAX_VALUE+1)-> Double.MAX_VALUE| testReportDependability|
||reportDependability < 0 |Invalid|-| |
|reportDependability ≤ minint|reportDependability > 0 |Invalid|-| |
|minint ≤ reportDependability ≤ maxint|reportDependability > 0 |Valid|setReportDependability(4.2)-> 4.2| testReportDependability1|
||reportDependability < 0 |Valid|setReportDependability(-4.2)-> -4.2|testReportDependability2 |



### **Class *User* - method *setUserId(Integer userId)***

**Criteria for method *setUserId(Integer userId)*:**
	
 - Range
 - Sign
  
  
**Predicates for method *setUserId(Integer userId)*:**

| Criteria | Predicate |
| -------- | :---------: |
|  Range    |  userId ≥ maxint    |
|           |  userId ≤ minint    |
|           | minint ≤ userId ≤ maxint    |
|  Sign     |  userId > 0         |
|           |  userId < 0         |


**Boundaries**:

| Criteria | Boundary values |
| -------- | :---------: |
|  Range    |  minint, maxint    |
|  Sign     |  0        |



**Combination of predicates**:


| Range|Sign| Valid / Invalid | Description of the test case | JUnit test case |
|:-------:|:-------:|:-------:|-------|-------|
|userId ≥ maxint|userId > 0 |Valid|setUserId(Integer.MAX_VALUE+1)-> Integer.MIN_VALUE| testUserId|
||userId < 0 |Invalid|-| |
|userId ≤ minint|userId > 0 |Invalid|-| |
||userId < 0 |Valid|setUserId(Integer.MIN_VALUE-1)-> Integer.MAX_VALUE| testUserId|
|minint ≤ userId ≤ maxint|userId > 0 |Valid|setUserId(4)-> 4| testUserId|
||userId < 0 |Valid|setUserId(-4)-> -4| testUserId|



### **Class *GasStation* - method *getUserId()***

**Criteria for method *getUserId()*:**
	
 - userId is null
  
**Predicates for method *getUserId()*:**

| Criteria | Predicate |
| -------- | :---------: |
| userId is null    |  True    |
|                                |  False   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:


| userId is null| Valid / Invalid | Description of the test case | JUnit test case 
|:-------:|:-------:|-------|-------|
|True|Valid|getUserId()-> NULL| testUserId3|
|False|Valid|getUserId()-> 3| testUserId4|



### **Class *User* - method *getPassword()***

**Criteria for method *getPassword()*:**
	
 - password is null
  
**Predicates for method *getPassword()*:**

| Criteria | Predicate |
| -------- | :---------: |
| password is null    |  True    |
|                                |  False   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:


| password is null| Valid / Invalid | Description of the test case | JUnit test case 
|:-------:|:-------:|-------|-------|
|True|Valid|getPassword()-> NULL| testPassword2|
|False|Valid|getPassword()-> "testpass"| testPassword3|


### **Class *User* - *getEmail()***

**Criteria for *getEmail()*:**

 - Length of *email* string

**Predicates for method *getEmail()*:**

| Criteria | Predicate |
| -------- | --------- |
| Length of *email* string       | >0          |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Length of *email* string         | ""                |
|          | null                |

**Combination of predicates**:

| Length of *email* string | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|>0|Valid|Object initialized with "s274475@polito.it"; getEmail() -> "s274475@polito.it"|testEmail|
|=0|Valid|Object initialized with ""; getEmail() -> ""|testEmail|
|null|Valid|Object initialized with null; getEmail() -> null|testEmail|
|<0|Invalid|Object initialized with a string of negative length |Not feasible|
|>max array size|Invalid|Object initialized with a string of length > max array size|Not feasible|


 ### **Class *User* - *setEmail(String email)***

**Criteria for *setEmail(String email)*:**

 - Length of *email* string

**Predicates for method *setEmail(String email)*:**

| Criteria | Predicate |
| -------- | --------- |
| Length of *email* string       | >0          |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Length of *email* string | "" |
|          | null |

**Combination of predicates**:

| Length of *email* string | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|>0|Valid|setEmail("s274475@studenti.polito.it"); getEmail() -> "s274475@studenti.polito.it"|testEmail|
|=0|Valid|setEmail(""); getEmail() -> ""|testEmail|
|null|Valid|setEmail(null); getEmail() -> null|testEmail|
|<0|Invalid|Not feasible |Not feasible|
|>max array size|Invalid|str = string of length > max array size|setEmail(str) -> java.lang.OutOfMemoryError: Requested array size exceeds VM limit|



### **Class *User* - method *getReputation()***

**Criteria for method *getReputation()*:**

 - reputation is null
  
**Predicates for method *getReputation()*:**

| Criteria | Predicate |
| -------- | :---------: |
| reputation is null    |  True    |
|                       |  False   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:

| reputation is null| Valid / Invalid | Description of the test case | JUnit test case 
|:-------:|:-------:|-------|-------|
|True|Valid|getReputation()-> NULL| testReputation |
|False|Valid|getReputation()-> 1| testReputation|



### **Class *User* - method *setReputation(Integer reputation)***

**Criteria for method *setReputation(Integer reputation)*:**

   - Range
   - Sign
  
**Predicates for method *setReputation(Integer reputation)*:**

| Criteria | Predicate |
| -------- | :---------: |
| Range    |  reputation < -5    |
|                                |  -5 <= reputation <= 5   |
|                                |  reputation > 5   |
| Sign    |  reputation > 0    |
|                                |  reputation < 0   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | :---------: |
|  Range    |  -5, 5    |
|  Sign     |  0        |

**Combination of predicates**:

| Range|Sign| Valid / Invalid | Description of the test case | JUnit test case |
|:-------:|:-------:|:-------:|-------|-------|
|reputation ≥ 5|reputation > 0 |Valid|setReputation(5+1)-> 6| testUserReputation1 |
||reputation < 0 |Invalid|-| |
|reputation ≤ -5|reputation > 0 |Invalid|-| |
||reputation < 0 |Valid|setReputation(-5-1)-> -6|testUserReputation2|
|-5 ≤ reputation ≤ 5|reputation > 0 |Valid|setReputation(1) -> 1| testUserReputation3|
||reputation < 0 |Valid|setReputation(-1)-> -1| testUserReputation4|




### **Class *LoginDto* - *getUserName***



**Criteria for *getUserName*:**
	

 - Length of *userName* string




**Predicates for method *getUserName*:**

| Criteria | Predicate |
| -------- | --------- |
| Length of *userName* string       | >0          |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Length of *userName* string         | ""                |
|          | null                |



**Combination of predicates**:


| Length of *userName* string | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|>0|Valid|Object initialized with "Fereshteh Feizabadi"; getUserName() -> "Fereshteh Feizabadi"|testUserName|
|=0|Valid|Object initialized with ""; getUserName() -> ""|testUserName1|
|null|Valid|Object initialized with null; getUserName() -> null|testUserName2|
|<0|Invalid|Object initialized with a string of negative length |Not feasible|
|>max array size|Invalid|Object initialized with a string of length > max array size|Not feasible|




 ### **Class *LoginDto* - *setUserName***



**Criteria for *setUserName*:**
	

 - Length of *userName* string




**Predicates for method *setUserName*:**

| Criteria | Predicate |
| -------- | --------- |
| Length of *userName* string       | >0          |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Length of *userName* string | "" |
|          | null |



**Combination of predicates**:


| Length of *userName* string | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|>0|Valid|setUserName("Fereshteh Feizabadi"); getUserName() -> "Fereshteh Feizabadi"|testUserName|
|=0|Valid|setUserName(""); getUserName() -> ""|testUserName|
|null|Valid|setUserName(null); getUserName() -> null|testUserName|
|<0|Invalid|Not feasible |Not feasible|
|>max array size|Invalid|str = string of length > max array size|setUserName(str) -> java.lang.OutOfMemoryError: Requested array size exceeds VM limit|


### **Class *LoginDto* - *getAdmin***



**Criteria for *getAdmin*:**
	

 - Value of *admin* boolean




**Predicates for method *getAdmin*:**

| Criteria | Predicate |
| -------- | --------- |
| Value of *admin* boolean       | Uninitialized          |
|          | After setAdmin(null)          |
|          | After setAdmin(false)          |
|          | After setAdmin(true)          |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of *admin* boolean | null |
|          | false |
|          | true |


**Combination of predicates**:


| Value of *admin* boolean | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|Uninitialized|Valid| getAdmin() -> false|testAdmin|
|false|Valid| setAdmin(null); getAdmin() -> null | |
|false|Valid| setAdmin(false); getAdmin() -> false | testAdmin1|
|false|Valid| setAdmin(true); getAdmin() -> true | testAdmin|



### **Class *IdPw* - method *getUser()***

**Criteria for method *getUser()*:**
	
 - User is null
  
**Predicates for method *getUser()*:**

| Criteria | Predicate |
| -------- | :---------: |
| User is null    |  True    |
|                                |  False   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:

| User is null| Valid / Invalid | Description of the test case | JUnit test case 
|:-------:|:-------:|-------|-------|
|True|Valid|getUser()-> NULL| TestIdPwPass|
|False|Valid|getUser()-> "testuser"| TestIdPwPass1|


### **Class *IdPw* - method *setUser(String user)***

**Criteria for *setUser(String user)*:**

 - Length of *user* string

**Predicates for method *setUser(String user)*:**

| Criteria | Predicate |
| -------- | --------- |
| Length of *user* string       | >0          |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Length of *user* string | "" |
|          | null |

**Combination of predicates**:

| Length of *user* string | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|>0|Valid|setUser("testuser"); getUser() -> "testuser"|TestIdPwPass|
|=0|Valid|setUser(""); getUser() -> ""|TestIdPwPass2|
|null|Valid|setUser(null); getUser() -> null|TestIdPwPass1|
|<0|Invalid|Not feasible |Not feasible|
|>max array size|Invalid|str = string of length > max array size|setUser(str) -> java.lang.OutOfMemoryError: Requested array size exceeds VM limit|

### **Class *IdPw* - method *getPw()***

**Criteria for method *getPw()*:**
	
 - password is null
  
**Predicates for method *getPw()*:**

| Criteria | Predicate |
| -------- | :---------: |
| password is null    |  True    |
|                                |  False   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:

| password is null| Valid / Invalid | Description of the test case | JUnit test case 
|:-------:|:-------:|-------|-------|
|True|Valid|getPw()-> NULL| TestIdPwPass|
|False|Valid|getPw()-> "testpass"| TestIdPwPass|


### **Class *IdPw* - method *setPw(String pw)***

**Criteria for *setPw(String pw)*:**

 - Length of *pw* string

**Predicates for method *setPw(String pw)*:**

| Criteria | Predicate |
| -------- | --------- |
| Length of *pw* string       | >0          |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Length of *pw* string | "" |
|          | null |

**Combination of predicates**:

| Length of *pw* string | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|>0|Valid|setPw("testpass"); getPw() -> "testpass"|TestIdPwPass|
|=0|Valid|setPw(""); getPw() -> ""|TestIdPwPass1|
|null|Valid|setPw(null); getPw() -> null|TestIdPwPass2|
|<0|Invalid|Not feasible |Not feasible|
|>max array size|Invalid|str = string of length > max array size|setPw(str) -> java.lang.OutOfMemoryError: Requested array size exceeds VM limit|




# White Box Unit Tests

## Package Entity - Class GasStation

### Test cases definition

| Unit name | JUnit test case |
|--|--|
| getGasStationId() | testGasStationId() |
| getGasStationName() | testGasStationName() |
| getGasStationAddress() | testGasStationAddress() |
| getReportDependability() | testReportDependability() |

### Code coverage report

    Coverage panel:
    
    [![EclEmma.jpg](https://i.postimg.cc/26N2FW1g/EclEmma.jpg)](https://postimg.cc/wtFX97ck)
    
    Coverage methods:
    
    [![gs1.jpg](https://i.postimg.cc/pdXMCSpM/gs1.jpg)](https://postimg.cc/rDbHFQ1f)
    [![gs2.jpg](https://i.postimg.cc/Vvtx5QHn/gs2.jpg)](https://postimg.cc/9wW8NS2Q)
    [![gs3.jpg](https://i.postimg.cc/02RgFjbf/gs3.jpg)](https://postimg.cc/Btgw1q5L)
    [![gs4.jpg](https://i.postimg.cc/wj2S1kFf/gs4.jpg)](https://postimg.cc/SnXZ5CBC)


### Loop coverage analysis

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
| getGasStationId() | - | - | testGasStationId() |
| getGasStationName() | - | - | testGasStationName() |
| getGasStationAddress() | - | - | testGasStationAddress() |
| getReportDependability() | - | - | testReportDependability() |


## Package Entity - Class PriceReport

### Test cases definition

| Unit name | JUnit test case |
|--|--|
| getDieselPrice() | testDieselPrice() |
| getPriceReportId() | testPriceReportId() |

### Code coverage report

    Coverage methods:
    
    <img src="https://i.postimg.cc/pXdxMZ8B/pr1.jpg" alt="test" border="0">
    [![pr2.jpg](https://i.postimg.cc/vmrCVgfB/pr2.jpg)](https://postimg.cc/sB2nF2nd)
    
    <img src="https://i.postimg.cc/pXdxMZ8B/pr1.jpg" alt="test" border="0">

### Loop coverage analysis

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
| getDieselPrice() | - | - | testDieselPrice() |
| getPriceReportId() | - | - | testPriceReportId() |


## Package Entity - Class UserDto

### Test cases definition

| Unit name | JUnit test case |
|--|--|
| getUserId() | testUserId() |
| getUserName() | testUserName() |
| getPassword() | testPassword() |


### Code coverage report

    Coverage methods:
    
    [![u1.jpg](https://i.postimg.cc/PJP62Pp1/u1.jpg)](https://postimg.cc/ctGMJ1cJ)
    [![u2.jpg](https://i.postimg.cc/Xv4Qt2Bj/u2.jpg)](https://postimg.cc/tsv3xNzw)
    [![u3.jpg](https://i.postimg.cc/5yhmgpMM/u3.jpg)](https://postimg.cc/H8t5swxS)
    
    

### Loop coverage analysis

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
| getUserId() | - | - | testUserId() |
| getUserName() | - | - | testUserName() |
| getPassword() | - | - | testPassword() |


## Package Dto - Class LoginDto

### Test cases definition

| Unit name | JUnit test case |
|--|--|
| getAdmin() | testAdmin() |
| getReputation() | testReputation() |
| getEmail() | testEmail() |

### Code coverage report

    Coverage methods:
    
    [![l1.jpg](https://i.postimg.cc/N0CCT6LQ/l1.jpg)](https://postimg.cc/HJ5t17QN)
    [![l2.jpg](https://i.postimg.cc/FFLCdJvQ/l2.jpg)](https://postimg.cc/rzqN7szh)
    [![l3.jpg](https://i.postimg.cc/tCGShCRn/l3.jpg)](https://postimg.cc/yWnyKKZ7)
    
    

### Loop coverage analysis

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
| getAdmin() | - | - | testAdmin() |
| getReputation() | - | - | testReputation() |
| getEmail() | - | - | testEmail() |


## Package Dto - Class IdPwDto

### Test cases definition

| Unit name | JUnit test case |
|--|--|
| getUser() | TestIdPwUser() |
| getPw() | TestIdPwPass() |

### Code coverage report

    Coverage methods:
    
    [![i1.jpg](https://i.postimg.cc/13JvhGGD/i1.jpg)](https://postimg.cc/VJ0qXbHN)
    [![i2.jpg](https://i.postimg.cc/fbyBwVCp/i2.jpg)](https://postimg.cc/Fftjps2V)
    
    

### Loop coverage analysis

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
| getUser() | - | - | TestIdPwUser() |
| getPw() | - | - | TestIdPwPass() |


## Package Dto - Class PriceReportDto

### Test cases definition

| Unit name | JUnit test case |
|--|--|
| getDieselPrice() | TestDiselPrice() |
| getPriceReportIdPw() | TestPriceReportId() |

### Code coverage report

    Coverage methods:
    
    [![prd1.jpg](https://i.postimg.cc/k5LFqfBR/prd1.jpg)](https://postimg.cc/gwq6KyyG)
    [![prd2.jpg](https://i.postimg.cc/2jQFNdm3/prd2.jpg)](https://postimg.cc/YjSm1Wvw)
    
    

### Loop coverage analysis

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
| getDieselPrice() | - | - | TestDiselPrice() |
| getPriceReportIdPw() | - | - | TestPriceReportId() |





