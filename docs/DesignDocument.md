# Design Document 


Authors:

Date:

Version:


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

scale 8192*2002 
left to right direction

package "it.polito.ezgas.entity" {
   class User {
    String firstName
    String lastName
    String account_name
    String account_pwd
    String email
    int trust_level
    __
    +void setFirstName(String)
    +void setLastName(String)
    +void setEmail(String)
    +void setAccount_Name(String)
    +void setAccount_Pwd(String)
    +String getFirstName()
    +String getLastname()
    +String getAccount_Name()
    +String getAccount_Pwd()
    +String getEmail()
    +int getTrust_Level()
  }
  class GasStation {
    int ID
    String name
    String address
    String brand
    boolean hasDiesel
    boolean hasGasoline
    boolean hasPremiumDiesel
    boolean hasPremiumGasoline
    boolean hasLPG
    booelan hasMethane
    __
    +void setName(String)
    +void setAddress(String)
    +void setBrand(String)
    +String getName()
    +String getAddress()
    +boolean getHasDiesel()
    +boolean getHasGasoline()
    +boolean getHasPremiumDiesel()
    +boolean getHasPremiumGasoline()
    +booelan getHasLPG()
    +booelan getHasMethane()
  }
  class Administrator {
    
  }
  class PriceList {
    Date time_tag
    double dieselPrice
    double gasolinePrice
    double premiumDieselPrice
    double premiumGasolinePrice
    double LPGPrice
    double methanePrice
    int trust_level
    GasStation gasStation
    User user
    __
    +void setTime_tag(Date)
    +void setDieselPrice(double)
    +void setGasolinePrice(double)
    +void setPremiumDieselPrice(double)
    +void setPremiumGasolinePrice(double)
    +void setLPGPrice(double)
    +void setMethanePrice(double)
    +void setTrust_Level(int)
    +Date getTime_tag()
    +double getDieselPrice()
    +double getGasolinePrice()
    +double getPremiumDieselPrice()
    +double getPremiumGasolinePrice()
    +double getLPGPrice()
    +double getMethanePrice()
    +int getTrust_Level()
  }
  class GeoPoint {
    double latitude
    double longitude
    __
    +void setLatitude(double)
    +void setLongitude(double)
    +double getLatitude()
    +double getLongitude()
  }
  class CarSharingCompany {
    String name
    __
    +void setName(String)
    +String getName()
  }
  class EZGas {
    +boolean run()
    +boolean notRun()
  }
  class AnonymousUser {
  }
}

package "it.polito.ezgas.dto" {
   class UserDto {
    String firstName
    String lastName
    String account_name
    String account_pwd
    String email
    int trust_level
    __
    +void setFirstName(String)
    +void setLastName(String)
    +void setEmail(String)
    +void setAccount_Name(String)
    +void setAccount_Pwd(String)
    +String getFirstName()
    +String getLastname()
    +String getAccount_Name()
    +String getAccount_Pwd()
    +String getEmail()
    +int getTrust_Level()
  }
  class GasStationDto {
    int ID
    String name
    String address
    String brand
    boolean hasDiesel
    boolean hasGasoline
    boolean hasPremiumDiesel
    boolean hasPremiumGasoline
    boolean hasLPG
    booelan hasMethane
    __
    +void setName(String)
    +void setAddress(String)
    +void setBrand(String)
    +String getName()
    +String getAddress()
    +boolean getHasDiesel()
    +boolean getHasGasoline()
    +boolean getHasPremiumDiesel()
    +boolean getHasPremiumGasoline()
    +booelan getHasLPG()
    +booelan getHasMethane()
  }
  class AdministratorDto {
  }
  class PriceListDto {
    Date time_tag
    double dieselPrice
    double gasolinePrice
    double premiumDieselPrice
    double premiumGasolinePrice
    double LPGPrice
    double methanePrice
    int trust_level
    GasStation gasStation
    User user
    __
    +void setTime_tag(Date)
    +void setDieselPrice(double)
    +void setGasolinePrice(double)
    +void setPremiumDieselPrice(double)
    +void setPremiumGasolinePrice(double)
    +void setLPGPrice(double)
    +void setMethanePrice(double)
    +void setTrust_Level(int)
    +Date getTime_tag()
    +double getDieselPrice()
    +double getGasolinePrice()
    +double getPremiumDieselPrice()
    +double getPremiumGasolinePrice()
    +double getLPGPrice()
    +double getMethanePrice()
    +int getTrust_Level()
  }
  class GeoPointDto {
    double latitude
    double longitude
    __
    +void setLatitude(double)
    +void setLongitude(double)
    +double getLatitude()
    +double getLongitude()
  }
  class CarSharingCompanyDto {
    String name
    __
    +void setName(String)
    +String getName()
  }
  class EZGasDto {
  }
  class AnonymousUserDto {
  }
}

package "it.polito.ezgas.controller" {
   class UserController {
    +UserDto createAccount(UserDto)
    +UserDto login(UserDto)
    +UserDto deleteUser(UserDto)
    +UserDto modifyUser(UserDto)
    +List<GasStationDto> searchGasStationWithRadiusAddress(String)
    +List<GasStationDto> showGasStationsAndFuelsOnMap(double,double)
    +List<GasStationDto> getGasStationsByFuelType(String)
    +List<GasStationDto> sortGasStationsByFuelPrice(double,double,String)
    +List<GasStationDto> sortGasStationByDistance(double,double,String)
    +boolean isCorrect(double)
  }
  class GasStationController {
    +GasStationDto createGasStation(GasStationDto)
    +GasStationDto modifyGasStation(GasStationDto)
    +GasStationDto DeleteGasStation(GasStationDto)
  }
  class AdministratorController {
    +UserDto createAccount(UserDto)
    +UserDto login(UserDto)
    +UserDto deleteUser(UserDto)
    +List<UserDto> listAllUsers()
    +UserDto searchUser(UserDto)
    +UserDto modifyUser(UserDto)
    +GasStationDto createGasStation(GasStationDto)
    +GasStationDto modifyGasStation(GasStationDto)
    +GasStationDto deleteGasStation(GasStationDto)
    +List<GasStationDto> getAllGasStations()
    +List<GasStationDto> searchGasStationWithRadiusAddress(String)
    +List<GasStationDto> showGasStationsAndFuelsOnMap(double,double)
    +List<GasStationDto> getGasStationsByFuelType(String)
    +List<GasStationDto> sortGasStationsByFuelPrice(double,double,String)
    +List<GasStationDto> sortGasStationByDistance(double,double,String)
  }
  class PriceListController {
    +PriceListDto createPriceList(<UserDto>,<GasStation>)
    +PriceListDto updateTrustLevel(<UserDto>,<GasStatio>)
    +PriceListDto evaluatePriceList(<UserDto>,<GasStation>)
    +UserDto getLastUserUpdate(UserDto)
  }
  class GeoPointController {
    +List<GasStationDto> searchGasStationWithRadiusGeoPoint(double,double)
  }
  class CarSharingCompanyController {
    +List<GasStationDto> getGasStationByCarSharing(String)
  }
  class EZGasController {
    +boolean startApplication()
    +boolean stopApplication()
  }
  class AnonymousUserController {
    +List<GasStationDto> searchGasStationWithRadiusAddress(String)
    +List<GasStationDto> showGasStationsAndFuelsOnMap(double,double)
    +List<GasStationDto> getGasStationsByFuelType(String)
    +List<GasStationDto> sortGasStationsByFuelPrice(double,double,String)
    +List<GasStationDto> sortGasStationByDistance(double,double,String) 
  }
}

package "it.polito.ezgas.service" {
   interface UserService {
     +UserDto getUserById(int)
     +UserDto saveUser(UserDto)
     +List<UserDto> getAllUsers()
     +boolean deleteUser(int)
     +LoginDto login(IdPw)
     +int increaseUserReputation(int)
     +int decreaseUserReputation(int)
   }
   note right: This interface just contains the methods related to User entity.\nThe implementation of these methods will be done in the "UserServiceImpl" class
  
   interface GasStationService {
     +GasStationDto getGasStationById(int)
     +GasStationDto saveGasStation(GasStationDto)
     +List<GasStationDto> getAllGasStations()
     +boolean deleteGasStation(int)
     +List<GasStationDto> getGasStationsByGasolineType(String)
     +List<GasStationDto> getGasStationsByProximity(double,double)
     +List<GasStationDto> getGasStationsWithCoordinates(double,double,String,String)
     +List<GasStationDto> getGasStationsWithoutCoordinates(String,String)
     +void setReport(int,double,double,double,double,double,int)
     +List<GasStationDto> getGasStationByCarSharing(String)
   }
  note right: This interface just contains the methods related to GasStation entity.\nThe implementation of these methods will be done in the "GasStationServiceImpl" class
}

package "it.polito.ezgas.serviceImpl" {
   class UserServiceImpl {
    +UserDto getUserById(int)
    +UserDto saveUser(UserDto)
    +List<UserDto> getAllUsers()
    +boolean deleteUser(int)
    +LoginDto login(IdPw)
    +int increaseUserReputation(int)
    +int decreaseUserReputation(int)
  }
  class GasStationServiceImpl {
    +GasStationDto getGasStationById(int)
    +GasStationDto saveGasStation(GasStationDto)
    +List<GasStationDto> getAllGasStations()
    +boolean deleteGasStation(int)
    +List<GasStationDto> getGasStationsByGasolineType(String)
    +List<GasStationDto> getGasStationsByProximity(double,double)
    +List<GasStationDto> getGasStationsWithCoordinates(double,double,String,String)
    +List<GasStationDto> getGasStationsWithoutCoordinates(String,String)
    +void setReport(int,double,double,double,double,double,int)
    +List<GasStationDto> getGasStationByCarSharing(String)
  } 
}

package "it.polito.ezgas.converter" {
   class UserConverter {
    +UserDto toUserDto(User)
  }
  class GasStationConverter {
    +GasStationDto toGasStationDto(GasStation)
  }
  class AdministratorConverter {
    +AdministratorDto toAdministratorDto(Administrator)
  }
  class PriceListConverter {
    +PriceListDto toPriceListDto(PriceList)
  }
  class GeoPointConverter {
    +GeoPointDto toGeoPointDto(GeoPoint)
  }
  class CarSharingCompanyConverter {
    +CarSharingCompanyDto toCarSharingCompanyDto(CarSharingCompany)
  }
  class EZGasConverter {
    +EZGasDto toEZGasDto(EZGas)
  }
  class AnonymousUserConverter {
    +AnonymousUserDto toAnonymousUserDto(AnonymousUser)
  }
}

User "1" -- "1" UserDto
User "1" -- "1" UserConverter
UserController "1" -- "1..*" UserDto
UserController "1" -- "0..*" GasStationDto
UserController "1" -- "1" UserServiceImpl
UserController "1" -- "0..*" GeoPointDto
UserController "1" -- "1" GasStationServiceImpl
UserService <|-- UserServiceImpl
GasStation "1" -- "1" GasStationDto
GasStation "1" -- "1" GasStationConverter
GasStationController "1" -- "1..*" GasStationDto
GasStationController "1" -- "1" GasStationServiceImpl
GasStationService <|-- GasStationServiceImpl
Administrator "1" -- "1" AdministratorDto
Administrator "1" -- "1" AdministratorConverter
AdministratorController "1" -- "1..*" AdministratorDto
AdministratorController "1" -- "1" UserServiceImpl
AdministratorController "1" -- "1" GasStationServiceImpl
AdministratorController "1" -- "0..*" GasStationDto
AdministratorController "1" -- "0..*" UserDto
AdministratorController "1" -- "0..*" PriceListDto
AdministratorController "1" -- "0..*" GeoPointDto
User <|-- Administrator
UserDto <|-- AdministratorDto
PriceList "1" -- "1" PriceListDto
PriceList "1" -- "1" PriceListConverter
PriceListDto "1..*" -- "1" PriceListController
PriceListController "1" -- "1..*" GasStationDto
PriceListController "1" -- "1..*" UserDto
GeoPoint "1" -- "1" GeoPointDto
GeoPoint "0..*" -- "1" GeoPointConverter
GeoPointController "1" -- "1..*" GeoPointDto
GeoPointController "1" -- "0..*" UserDto
GeoPointController "1" -- "1..*" AdministratorDto
GeoPointController "1" -- "0..*" AnonymousUserDto
GeoPointController "1" -- "0..*" GasStationDto
GeoPointController "1" -- "1" UserServiceImpl
GeoPointController "1" -- "1" GasStationServiceImpl
CarSharingCompany "1" -- "1" CarSharingCompanyDto
CarSharingCompany "1" -- "1" CarSharingCompanyConverter
CarSharingCompanyDto "1..*" -- "1" CarSharingCompanyController
CarSharingCompanyController "1" -- "0..*" GasStationDto
CarSharingCompanyController "1" -- "0..*" UserDto
CarSharingCompanyController "1" -- "1..*" AdministratorDto
CarSharingCompanyController "1" -- "0..*" AnonymousUserDto
EZGas "1" -- "1" EZGasDto
EZGas "1" -- "1" EZGasConverter
EZGasDto "1" -- "1" EZGasController
EZGasDto "1" -- "0..*" UserDto
EZGasDto "1" -- "0..*" GasStationDto
EZGasDto "1" -- "0..*" AdministratorDto
AnonymousUser "1" -- "1" AnonymousUserDto
AnonymousUser "1" -- "1" AnonymousUserConverter
AnonymousUserController "1" -- "0..*" AnonymousUserDto
AnonymousUserController "1" -- "1" UserServiceImpl
AnonymousUserController "1" -- "1" GasStationServiceImpl
User "0..*" -- "1" EZGas
User "0..*" -- "0..*" GeoPoint
User "1" -- "0..*" PriceList
AnonymousUser "0..*" -- "1" EZGas
AnonymousUser "0..*" -- "0..*" GeoPoint
GasStation "0..*" -- "1" EZGas
GasStation "1" -- "1" GeoPoint
GasStation "1" -- "0..1" PriceList
GasStation "0..*" -- "0..1" CarSharingCompany
UserDto "0..*" -- "1" EZGasDto
UserDto "0..*" -- "0..*" GeoPointDto
AnonymousUserDto "0..*" -- "1" EZGasDto
AnonymousUserDto "0..*" -- "0..*" GeoPointDto
UserDto "1" -- "0..*" PriceListDto
GasStationDto "0..*" -- "1" EZGasDto
GasStationDto "1" -- "1" GeoPointDto
GasStationDto "1" -- "0..1" PriceListDto
GasStationDto "0..*" -- "0..1" CarSharingCompanyDto

it.polito.ezgas.converter -right[hidden]-> it.polito.ezgas.serviceImpl
it.polito.ezgas.converter -right[hidden]-> it.polito.ezgas.service

@enduml
```



# Verification traceability matrix

\<for each functional requirement from the requirement document, list which classes concur to implement it>











# Verification sequence diagrams 
\<select key scenarios from the requirement document. For each of them define a sequence diagram showing that the scenario can be implemented by the classes and methods in the design>







