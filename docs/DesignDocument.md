# Design Document 




Authors: Group 50

Date: 3 May

Version: 1


# Contents

- [High level design](#package-diagram)
- [Low level design](#class-diagram)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)

# Instructions

The design must satisfy the Official Requirements document (see EZGas Official Requirements.md ). <br>
The design must comply with interfaces defined in package it.polito.ezgas.service (see folder ServicePackage ) <br>
UML diagrams **MUST** be written using plantuml notation.

# High level design 

The style selected is client - server. Clients can be smartphones, tablets, PCs.
The choice is to avoid any development client side. The clients will access the server using only a browser. 

The server has two components: the frontend, which is developed with web technologies (JavaScript, HTML, Css) and is in charge of collecting user inputs to send requests to the backend; the backend, which is developed using the Spring Framework and exposes API to the front-end.
Together, they implement a layered style: Presentation layer (front end), Application logic and data layer (back end). 
Together, they implement also an MVC pattern, with the V on the front end and the MC on the back end.



```plantuml
@startuml
package "Backend" {

}

package "Frontend" {

}


Frontend -> Backend
@enduml


```


## Front End

The Frontend component is made of: 

Views: the package contains the .html pages that are rendered on the browser and that provide the GUI to the user. 

Styles: the package contains .css style sheets that are used to render the GUI.

Controller: the package contains the JavaScript files that catch the user's inputs. Based on the user's inputs and on the status of the GUI widgets, the JavaScript controller creates REST API calls that are sent to the Java Controller implemented in the back-end.


```plantuml
@startuml
package "Frontend" {

    package "it.polito.ezgas.resources.views" {

    }


package "it.polito.ezgas.resources.controller" {

    }


package "it.polito.ezgas.resources.styles" {

    }



it.polito.ezgas.resources.styles -down-> it.polito.ezgas.resources.views

it.polito.ezgas.resources.views -right-> it.polito.ezgas.resources.controller


}
@enduml

```

## Back End

The backend  uses a MC style, combined with a layered style (application logic, data). 
The back end is implemented using the Spring framework for developing Java Entrerprise applications.

Spring was selected for its popularity and relative simplicity: persistency (M and data layer) and interactions are pre-implemented, the programmer needs only to add the specific parts.

See in the package diagram below the project structure of Spring.

For more information about the Spring design guidelines and naming conventions:  https://medium.com/the-resonant-web/spring-boot-2-0-project-structure-and-best-practices-part-2-7137bdcba7d3



```plantuml
@startuml
package "Backend" {

package "it.polito.ezgas.service"  as ps {
   interface "GasStationService"
   interface "UserService"
} 


package "it.polito.ezgas.controller" {

}

package "it.polito.ezgas.converter" {

}

package "it.polito.ezgas.dto" {

}

package "it.polito.ezgas.entity" {

}

package "it.polito.ezgas.repository" {

}

    
}
note "see folder ServicePackage" as n
n -- ps
```



The Spring framework implements the MC of the MVC pattern. The M is implemented in the packages Entity and Repository. The C is implemented in the packages Service, ServiceImpl and Controller. The packages DTO and Converter contain classes for translation services.



**Entity Package**

Each Model class should have a corresponding class in this package. Model classes contain the data that the application must handle.
The various models of the application are organised under the model package, their DTOs(data transfer objects) are present under the dto package.

In the Entity package all the Entities of the system are provided. Entities classes provide the model of the application, and represent all the data that the application must handle.




**Repository Package**

This package implements persistency for each Model class using an internal database. 

For each Entity class, a Repository class is created (in a 1:1 mapping) to allow the management of the database where the objects are stored. For Spring to be able to map the association at runtime, the Repository class associated to class "XClass" has to be exactly named "XClassRepository".

Extending class JpaRepository provides a lot of CRUD operations by inheritance. The programmer can also overload or modify them. 



**DTO package**

The DTO package contains all the DTO classes. DTO classes are used to transfer only the data that we need to share with the user interface and not the entire model object that we may have aggregated using several sub-objects and persisted in the database.

For each Entity class, a DTO class is created (in a 1:1 mapping).  For Spring the Dto class associated to class "XClass" must be called "XClassDto".  This allows Spring to find automatically the DTO class having the corresponding Entity class, and viceversa. 




**Converter Package**

The Converter Package contains all the Converter classes of the project.

For each Entity class, a Converter class is created (in a 1:1 mapping) to allow conversion from Entity class to DTO class and viceversa.

For Spring to be able to map the association at runtime, the Converter class associated to class "XClass" has to be exactly named "XClassConverter".




**Controller Package**

The controller package is in charge of handling the calls to the REST API that are generated by the user's interaction with the GUI. The Controller package contains methods in 1:1 correspondance to the REST API calls. Each Controller can be wired to a Service (related to a specific entity) and call its methods.
Services are in packages Service (interfaces of services) and ServiceImpl (classes that implement the interfaces)

The controller layer interacts with the service layer (packages Service and ServieImpl) 
 to get a job done whenever it receives a request from the view or api layer, when it does it should not have access to the model objects and should always exchange neutral DTOs.

The service layer never accepts a model as input and never ever returns one either. This is another best practice that Spring enforces to implement  a layered architecture.



**Service Package**


The service package provides interfaces, that collect the calls related to the management of a specific entity in the project.
The Java interfaces are already defined (see file ServicePackage.zip) and the low level design must comply with these interfaces.


**ServiceImpl Package**

Contains Service classes that implement the Service Interfaces in the Service package.










# Low level design

<Based on the official requirements and on the Spring Boot design guidelines, define the required classes (UML class diagram) of the back-end in the proper packages described in the high-level design section.>

```plantuml 

@startuml

scale 8192*1536 
left to right direction

package "it.polito.ezgas.entity" {
   class User {
    Integer userId
    String userName
    String password
    String email
    Integer reputation
    boolean admin
    __
    +Integer getUserId()
    +void setUserId(Integer userId)
    +String getUserName()
    +void setUserName(String userName)
    +void setPassword(String password)
    +String getPassword()
    +String getEmail()
    +void setEmail(String email)
    +Integer getReputation()
    +void setReputation(Integer reputation)
    +boolean getAdmin()
    +void setAdmin(boolean admin)
  }
  class GasStation {
    Integer gasStationId
    String gasStationName
    String gasStationAddress
    boolean hasDiesel
    boolean hasSuper
    boolean hasSuperPlus
    boolean hasGas
    booelan hasMethane
    String carSharing
    double lat
    double lon
    double dieselPrice
    double superPrice
    double superPlusPrice
    double gasPrice
    double methanePrice
    Integer reportUser
    String reportTimestamp
    double reportDependability
    User user
    __
    +Integer getGasStationId()
    +void setGasStationId(Integer gasStationId)
    +String getGasStationName()
    +void setGasStationName(String gasStationName)
    +String getGasStationAddress()
    +void setGasStationAddress(String gasStationAddress)
    +double getReportDependability()
    +void setReportDependability(double reportDependability)
    +Integer getReportUser()
    +void setReportUser(Integer reportUser)
    +String getReportTimestamp()
    +void setReportTimestamp(String reportTimestamp)
    +boolean getHasDiesel()
    +void setHasDiesel(boolean hasDiesel)
    +boolean getHasSuper()
    +void setHasSuper(boolean hasSuper)
    +boolean getHasSuperPlus()
    +void setHasSuperPlus(boolean hasSuperPlus)
    +boolean getHasGas()
    +void setHasGas(boolean hasGas)
    +double getLat()
    +void setLat(double lat)
    +double getLon()
    +void setLon(double lon)
    +double getDieselPrice()
    +void setDieselPrice(double dieselPrice)
    +double getSuperPrice()
    +void setSuperPrice(double superPrice)
    +double getSuperPlusPrice()
    +void setSuperPlusPrice(double superPlusPrice)
    +double getGasPrice()
    +void setGasPrice(double gasPrice)
    +User getUser()
    +void setUser(User user)
    +boolean getHasMethane()
    +void setHasMethane(boolean hasMethane)
    +double getMethanePrice()
    +void setMethanePrice(double methanePrice)
    +String getCarSharing()
    +void setCarSharing(String carSharing)
  }
  class PriceReport {
    +Integer priceReportID
    +User user
    +double dieselPrice
    +double superPrice
    +double superPlusPrice
    +double gasPrice
    __
    +User getUser()
    +void setUser(User user)
    +double getDieselPrice()
    +void setDieselPrice(double dieselPrice)
    +double getSuperPrice()
    +void setSuperPrice(double superPrice)
    +double getSuperPlusPrice()
    +void setSuperPlusPrice(double superPlusPrice)
    +double getGasPrice()
    +void setGasPrice(double gasPrice)
    +Integer getPriceReportId()
    +void setPriceReportId(Integer priceReportId)
  }
}

package "it.polito.ezgas.dto" {
  class UserDto {
    Integer userId
    String userName
    String password
    String email
    Integer reputation
    boolean admin
    __
    +Integer getUserId()
    +void setUserId(Integer userId)
    +String getUserName()
    +void setUserName(String userName)
    +void setPassword(String password)
    +String getPassword()
    +String getEmail()
    +void setEmail(String email)
    +Integer getReputation()
    +void setReputation(Integer reputation)
    +boolean getAdmin()
    +void setAdmin(boolean admin)
  }
  class GasStationDto {
    Integer gasStationId
    String gasStationName
    String gasStationAddress
    boolean hasDiesel
    boolean hasSuper
    boolean hasSuperPlus
    boolean hasGas
    booelan hasMethane
    String carSharing
    double lat
    double lon
    double dieselPrice
    double superPrice
    double superPlusPrice
    double gasPrice
    double methanePrice
    Integer reportUser
    String reportTimestamp
    double reportDependability
    UserDto userDto
    __
    +Integer getGasStationId()
    +void setGasStationId(Integer gasStationId)
    +String getGasStationName()
    +void setGasStationName(String gasStationName)
    +String getGasStationAddress()
    +void setGasStationAddress(String gasStationAddress)
    +double getReportDependability()
    +void setReportDependability(double reportDependability)
    +Integer getReportUser()
    +void setReportUser(Integer reportUser)
    +String getReportTimestamp()
    +void setReportTimestamp(String reportTimestamp)
    +boolean getHasDiesel()
    +void setHasDiesel(boolean hasDiesel)
    +boolean getHasSuper()
    +void setHasSuper(boolean hasSuper)
    +boolean getHasSuperPlus()
    +void setHasSuperPlus(boolean hasSuperPlus)
    +boolean getHasGas()
    +void setHasGas(boolean hasGas)
    +double getLat()
    +void setLat(double lat)
    +double getLon()
    +void setLon(double lon)
    +double getDieselPrice()
    +void setDieselPrice(double dieselPrice)
    +double getSuperPrice()
    +void setSuperPrice(double superPrice)
    +double getSuperPlusPrice()
    +void setSuperPlusPrice(double superPlusPrice)
    +double getGasPrice()
    +void setGasPrice(double gasPrice)
    +User getUserDto()
    +void setUserDto(User userDto)
    +boolean getHasMethane()
    +void setHasMethane(boolean hasMethane)
    +double getMethanePrice()
    +void setMethanePrice(double methanePrice)
    +String getCarSharing()
    +void setCarSharing(String carSharing)
  }
  class LoginDto {
    Integer userId
    String userName
    String token
    String email
    Integer reputation
    boolean admin
    __
    +Integer getUserId()
    +void setUserId(Integer userId)
    +String getUserName()
    +void setUserName(String userName)
    +String getToken()
    +void setToken(String token)
    +String getEmail()
    +void setEmail(String email)
    +Integer getReputation()
    +void setReputation(Integer reputation)
    +boolean getAdmin()
    +void setAdmin(Boolean admin)
  }
  class IdPw {
    String user
    String pw
    __
    +String getUser()
    +void setUser(String user)
    +String getPw()
    +void setPw(String pw)
  }
}

package "it.polito.ezgas.controller" {
   class UserController {
    +UserDto getUserById(Integer userId)
    +List<UserDto> getAllUsers()
    +UserDto saveUser(UserDto userDto)
    +boolean deleteUser(Integer userId)
    +Integer increaseUserReputation(Integer userId)
    +Integer decreaseUserReputation(userId)
    +LoginDto login(IdPw credentials)
  }
  class GasStationController {
    +GasStationDto getGasStationById(Integer gasStationId)
    +List<GasStationDto> getAllGasStations()
    +void saveGasStation(GasStationDto gasStationDto)
    +void deleteGasStation(Integer gasStationId)
    +List<GasStationDto> getGasStationsByGasolineType(String gasolinetype)
    +List<GasStationDto> getGasStationsByProximity(double myLat,double myLon)
    +List<GasStationDto> getGasStationsWithCoordinates(double myLat,double myLon,String gasolineType,String carSharing)
    +void setGasStationReport(Integer gasStationId,double dieselPrice,double superPrice,double superPlusPrice,double gasPrice,double methanePrice,Integer userId)
  }
  class HomeController {
    +String admin()
    +String index()
    +String map()
    +String login()
    +String update()
    +String signup()
  }
}

package "it.polito.ezgas.service" {
   interface UserService {
     +UserDto getUserById(Integer userId)
     +UserDto saveUser(UserDto userDto)
     +List<UserDto> getAllUsers()
     +boolean deleteUser(Integer userId)
     +LoginDto login(IdPw credentials)
     +Integer increaseUserReputation(Integer userId)
     +Integer decreaseUserReputation(Integer userId)
   }
   note right: This interface just contains the methods related to User entity.\nThe implementation of these methods will be done in the "UserServiceImpl" class
  
   interface GasStationService {
     +GasStationDto getGasStationById(Integer gasStationId)
     +GasStationDto saveGasStation(GasStationDto gasStationDto)
     +List<GasStationDto> getAllGasStations()
     +boolean deleteGasStation(Integer gasStationId)
     +List<GasStationDto> getGasStationsByGasolineType(String gasolinetype)
     +List<GasStationDto> getGasStationsByProximity(double lat, double lon)
     +List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype, String carsharing)
     +List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
     +void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, Integer userId)
     +List<GasStationDto> getGasStationByCarSharing(String carSharing)
   }
  note right: This interface just contains the methods related to GasStation entity.\nThe implementation of these methods will be done in the "GasStationServiceImpl" class
}

package "it.polito.ezgas.repository" {
   class UserRepository {
     +save(User user)
     +findOne(Integer userId)
     +findAll()
     +exists(Integer UserId)
     +delete(Integer UserId)
   }
   class GasStationRepository {
     +save(GasStation gasStation)
     +findOne(Integer gasStationId)
     +findAll()
     +exists(Integer gasStationId)
     +delete(Integer gasStationId)
   }
}
package "it.polito.ezgas.serviceImpl" {
   class UserServiceImpl {
     +UserDto getUserById(Integer userId)
     +UserDto saveUser(UserDto userDto)
     +List<UserDto> getAllUsers()
     +boolean deleteUser(Integer userId)
     +LoginDto login(IdPw credentials)
     +Integer increaseUserReputation(Integer userId)
     +Integer decreaseUserReputation(Integer userId)
  }
  class GasStationServiceImpl {
     +GasStationDto getGasStationById(Integer gasStationId)
     +GasStationDto saveGasStation(GasStationDto gasStationDto)
     +List<GasStationDto> getAllGasStations()
     +boolean deleteGasStation(Integer gasStationId)
     +List<GasStationDto> getGasStationsByGasolineType(String gasolinetype)
     +List<GasStationDto> getGasStationsByProximity(double lat, double lon)
     +List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype, String carsharing)
     +List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
     +void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, Integer userId)
     +List<GasStationDto> getGasStationByCarSharing(String carSharing)
  } 
}

package "it.polito.ezgas.converter" {
   class UserConverter {
     User user
     UserDto userDto
     __
     +UserDto toUserDto(User userSource)
     +User toUser(UserDto userDtoSource)
 }
   class GasStationConverter {
     GasStation gasStation
     GasStationDto gasStationDto
     UserConverter userConverter
     __
     +GasStationDto toGasStationDto(GasStation gasStationSource)
     +GasStation toGasStation(GasStationDto gasStationDtoSource)
 }
}


User "1" -- "1" UserDto
GasStation "1" -- "1" GasStationDto
GasStationDto "0..*" -- "1" GasStationServiceImpl
UserDto "0..*" -- "1" UserServiceImpl
GasStationController "1" -- "1" GasStationServiceImpl
UserController "1" -- "1" UserServiceImpl
UserController "1" -- "0..*" UserDto
UserController "1" -- "0..*" LoginDto
GasStationController "1" -- "0..*" GasStationDto
UserController "1" -- "1" HomeController
GasStationController "1" -- "1" HomeController
UserConverter "1" -- "0..*" User
UserConverter "1" -- "0..*" UserDto
GasStationConverter "1" -- "0..*" GasStation
GasStationConverter "1" -- "0..*" GasStationDto
User "0..*" -- "1" UserRepository
GasStation "0..*" -- "1" GasStationRepository
GasStationRepository "1" -- "1" UserRepository
UserController "1" -- "1" UserRepository
GasStationController "1" -- "1" GasStationRepository
UserConverter "1" -- "1" UserController
GasStationConverter "1" -- "1" GasStationController
IdPw "0..*" -- "1" UserController
IdPw "1" -- "1" LoginDto

UserService <|-- UserServiceImpl
GasStationService <|-- GasStationServiceImpl

it.polito.ezgas.converter -right[hidden]-> it.polito.ezgas.serviceImpl
it.polito.ezgas.converter -right[hidden]-> it.polito.ezgas.service

@enduml
```



# Verification traceability matrix


|       | GasStationServiceImpl | UserServiceImpl | User | GasStation | LoginDTO | IdPw |
|-------|:---------------------:|:---------------:|:----:|:----------:|:--------:|:----:|
| FR1.1 |                       |        X        |   X  |            |          |      |
| FR1.2 |                       |        X        |   X  |            |          |      |
| FR1.3 |                       |        X        |   X  |            |          |      |
| FR1.4 |                       |        X        |   X  |            |          |      |
| FR2   |                       |        X        |   X  |            |     X    |   X  |
| FR3.1 |           X           |                 |      |      X     |          |      |
| FR3.2 |           X           |                 |      |      X     |          |      |
| FR3.3 |           X           |                 |      |      X     |          |      |
| FR4.1 |           X           |                 |      |      X     |          |      |
| FR4.2 |           X           |                 |      |      X     |          |      |
| FR4.3 |           X           |                 |      |      X     |          |      |
| FR4.4 |           X           |                 |      |      X     |          |      |
| FR4.5 |           X           |                 |      |      X     |          |      |
| FR5.1 |           X           |                 |   X  |      X     |          |      |
| FR5.2 |           X           |                 |   X  |      X     |          |      |
| FR5.3 |           X           |                 |   X  |      X     |          |      |

The involvment of a class in the it.polito.ezgas.entity package implies the use of the respective class in the converter, dto, repository and controller packages.









# Verification sequence diagrams 
\<select key scenarios from the requirement document. For each of them define a sequence diagram showing that the scenario can be implemented by the classes and methods in the design>

Scennario 10.1:

```plantuml
@startuml
UserController <- HomeController : 1: login(credentials)
HomeController -> GasStationController : 2: getGasStationByProximity(mylat, mylon)
UserController <- HomeController : 3: getUserById(u2)
UserController <- HomeController : 4: increaseUserReputation(u2)
@enduml
```

Scennario 10.2:

```plantuml
@startuml
UserController <- HomeController : 1: login(credentials)
HomeController -> GasStationController : 2: getGasStationByProximity(mylat, mylon)
UserController <- HomeController : 3: getUserById(u2)
UserController <- HomeController : 4: decreaseUserReputation(u2)
@enduml
```

