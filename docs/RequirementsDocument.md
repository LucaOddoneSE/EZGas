# Requirements Document 

Authors: Luca Oddone

Date: 04/18/2020

Version: 2.0

# Contents

- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
	
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	+ [Functional Requirements](#functional-requirements)
	+ [Non functional requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	+ [Use case diagram](#use-case-diagram)
	+ [Use cases](#use-cases)
    	+ [Relevant scenarios](#relevant-scenarios)
- [Glossary](#glossary)
- [System design](#system-design)
- [Deployment diagram](#deployment-diagram)


# Stakeholders


| Stakeholder name  | Description | 
| ----------------- |:-----------:|
|                   |             | 

# Context Diagram and interfaces

## Context Diagram
\<Define here Context diagram using UML use case diagram>

\<actors are a subset of stakeholders>

## Interfaces
\<describe here each interface in the context diagram>

\<GUIs will be described graphically in a separate document>

| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| -----:|
|       |  |  |

# Stories and personas
\<A Persona is a realistic impersonation of an actor. Define here a few personas and describe in plain text how a persona interacts with the system>

\<Persona is-an-instance-of actor>

\<stories will be formalized later as use cases>


# Functional and non functional requirements

## Functional Requirements

\<In the form DO SOMETHING, or VERB NOUN, describe high level capabilities of the system>

\<will match to high level use cases>

| ID        | Description  |
| ------------- |:-------------:| 
|  FR1     |  |
|  FR2     |   |

## Non Functional Requirements

\<Describe constraints on functional requirements>

| ID        | Type (efficiency, reliability, ..)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NFR1     |   |  | |
|  NFR2     | |  | |
|  NFR3     | | | |


# Use case diagram and use cases


## Use case diagram
\<define here UML Use case diagram UCD summarizing all use cases, and their relationships>


\<next describe here each use case in the UCD>
### Use case 1, UC1
| Actors Involved        |  |
| ------------- |:-------------:| 
|  Precondition     | \<Boolean expression, must evaluate to true before the UC can start> |  
|  Post condition     | \<Boolean expression, must evaluate to true after UC is finished> |
|  Nominal Scenario     | \<Textual description of actions executed by the UC> |
|  Variants     | \<other executions, ex in case of errors> |

##### Scenario 1.1 

\<describe here scenarios instances of UC1>

\<a scenario is a sequence of steps that corresponds to a particular execution of one use case>

\<a scenario is a more formal description of a story>

\<only relevant scenarios should be described>

| Scenario 1.1 | |
| ------------- |:-------------:| 
|  Precondition     | \<Boolean expression, must evaluate to true before the scenario can start> |
|  Post condition     | \<Boolean expression, must evaluate to true after scenario is finished> |
| Step#        | Description  |
|  1     |  |  
|  2     |  |
|  ...     |  |

##### Scenario 1.2

### Use case 2, UC2
..

### Use case
..



# Glossary

\<use UML class diagram to define important concepts in the domain of the system, and their relationships> 

\<concepts are used consistently all over the document, ex in use cases, requirements etc>

# System Design
\<describe here system design>

\<must be consistent with Context diagram>

Since EzGas Application is meant to be a software application, we focus our attention on the software part rather than the hardware part.
The goal here is to describe in a clear and complete manner the system behavior and all its functionalities performed, through UML Class Diagram notation.

```plantuml
@startuml

scale 200 width
scale 700 height
left to right direction

class Smartphone {

+turnGPSon()
+turnGPSoff()
+getPosition()
+readQRCode()
+getSMSauthentication()
}

class PC {

+turnWebClientOn()
+turnWebClientOff()
+getPosition()

}

class EZGasServer {

+createSystemAdministrator()
+createNewMemeber()
+updateMemberInformation()
+deleteMember()
+createGasStationAdmin()
+deletGasStatioAdmin()
+getNewFuelStationName()
+getNewFuelStationPrice()
+getNewFuelStationPosition()
+updateExistingFuelStationName()
+updateExistingFuelStationPrice()
+deleteExistingFuelStation()
+createTrustedMember()
+recoverPassword()
+manageQRCode()
+manageUserDiscount()

}

class EZGasApp {

+open()
+close()
+signIn()
+signUp()
+deleteAccount()
+getPositionViaGPS()
+getPositionViaGMaps()
+sendPosition()
+getPrice()
+sendPrice()
+searchGasStationForPrice()
+searchGasStationForFuel()
+searchGasStationForRadius()
+getUserDiscount()
+generateQRCode()
+navigateUsertoGasStation()

}

class GoogleServer {

+elaborateInformation()
+sendPosition()

}

EZGasApp o-- "1..*" Smartphone
EZGasApp o-- "1..*" PC
EZGasApp o-- "1" EZGasServer
EZGasApp -- "1..*" GoogleServer
Smartphone o-- "1..*" GoogleServer
PC o-- "1..*" GoogleServer

@enduml
```


# Deployment Diagram 

\<describe here deployment diagram >

Deployment Diagram, represented here, aims to show conceptual entities (applications and services) defined before from another point of view, based on UML Deployment Diagram notation.

```plantuml
@startuml

scale 200 width
scale 700 height

node Computer {
 rectangle "Web Client" <<Application>> as ex1
 rectangle "QR Code Reader" <<Application>> as ex2
 rectangle "Maps" <<Application>> as ex3
}


node node3 as "EZGas Management Server" {
 rectangle "Authentication" <<Service>> as ex4
 rectangle "Storage" <<Service>> as ex5
 rectangle "Configuration" <<Service>> as ex16
}

node Smartphone {
 rectangle "Web Client" <<Application>> as ex7
 rectangle "QR Code Reader" <<Application>> as ex8
 rectangle "GPS" <<Service>> as ex9
 rectangle "Maps" <<Application>> as ex10
}

Computer -- node3 : HTTP
Smartphone -- node3 : HTTP

@enduml
```

| Node        | Linked to node           | Description  |
| ------------- |:-------------:| -----:|
| Computer      |  EZGas Management Server| Connection is established via a HTTP logical link |
| Smartphone | EZGas Management Server  | Connection is established via a HTTP logical link |

| Stereotype (type of entity)    | Description   |
| ------------- |-------------:|
| Application   | A group of services visible to end-user |
| service       | An end-user or middleware service      |
