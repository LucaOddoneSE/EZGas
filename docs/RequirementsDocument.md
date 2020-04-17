# Requirements Document 

Authors:

Date:

Version:

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
|        Driver           |      Uses the application for checking the prices of fuels in different gas stations | 
|        Gas station administrator           |      Uses the application for updating the prices of the fuels        |
|        Developer           |      Develops the application        |


# Context Diagram and interfaces

## Context Diagram

```plantuml
left to right direction
actor Driver as a
actor GasStation_Admin as b
rectangle System {
a -- (Ez Gas Application)
b -- (Ez Gas Application)
}
```



## Interfaces


| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| -----:|
|    Driver   | GUI | Touch screen |
|    Gas station administrator   | GUI | Touch screen |
|    Google Map   | Web Service | Internet connection |

# Stories and personas
Peter is a plumber and travels a lot on his LPG van for work in differend towns, up to hundreds of kilometers a day. He knows all the best gas station on his usual routes but work sometimes takes in other cities he doesnt know very well. Peter pays attention to the price of LPG so he uses EzGaz to find a cheap gas station with LPG in a 5 km range, or further but on his way home. One day he goes a bit out of his way to fill the tank on a very cheap gas atation he found with EzGaz, but when he gets there the actual price is higher than advertised so he reports it on the app and he will recive a discount for his contribution. After making a few reports like that Peter becomes a trusted user

Janice works in upper managment of a big company and drives a Porches. On tuesdays after work she has to pick up her son from piano practice, and is usually in a hurry. When shee sees the gas tank almost empty she would uses find the nearest gas station and doesn't care about the price because she is running late.

Frank just opened a gas station very close to a big interstate but not quite visible from the road, so he registers his business on EzGaz to advertise if very competitive prices. Many people come to him with discount codes on their phones for a small proce reduction on the fuel, which is worth because of the many new clients he gets.


# Functional and non functional requirements

## Functional Requirements

| ID        | Description  |
| ------------- |:-------------:|  
|  FR1     | Searching for a nearby gas station(using the GPS position) |
|  FR2     | Searching for the cheapest gas station(for a certain type of fuel) in a radius set by the user |
|  FR3     | Showing a map with gas stations and their prices |
|  FR4     | Navigate the user to a chosen gas station |
|  FR5     | Authenticatig the users (differenciating the gas station managers and trusted users) |
|  FR6     | Adding a gas station to the list |
|  FR7     | Reporting a wrong or missing information |
|  FR8     | Keeping track of the users' contributions and rights to discounts |
|  FR9     | Keeping track of earned discounts and allowing users to claim them at gas stations |

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

# Deployment Diagram 

\<describe here deployment diagram >
