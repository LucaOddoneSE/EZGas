# Project Dashboard

The dashboard collects the key measures about the project.
To be used to control the project, and compare with others. These measures will not be used to graduate the project. <br>
We consider two phases: <br>
-New development: From project start (april 13) to delivery of version 1 fixed (june 9)  <br>
-Maintenance: implementation of CR4 and CR7 (june 9 to end)   <br>
Report effort figures from the timesheet or timesheetCR document, compute size from the source code.

## New development 
| Measure| Value |
|---|---|
|effort E (report here effort in person hours, for New development, from timesheet)  | 177 |
|size S (report here size in LOC of all code written, excluding test cases)  | 79670 |
|productivity = S/E | 450.11 |
|defects after release D (number of defects found running official acceptance tests and fixed in CR0) | 15 |
|defect density = D/S| 0.0002 |
| effort for non-quality ENQ (effort for CR0, or effort to fix defects found running official acceptance tests, from timesheetCR) | 55 |
| effort for non quality, relative = ENQ / E | 0.31 |

## Maintenance

| Measure | Value|
|---|---|
| size S_CR4 = only lines added for CR4 = total size with CR4 - S | 50 |
| actual effort (from timesheetCR) AE_CR4 | 11 |
| productivity P_CR4 = S_CR4/ EA_CR4 | 4.54 |
| estimated effort (from estimationCR) EE_CR4 | 32 |
|estimation accuracy CR4 = EE_CR4/AE_CR4  | 2.9 |
|||
| size S_CR7 =only lines added for CR7 = total size with CR7 - S | 15 |
| actual effort (from timesheetCR) AE_CR7 | 10 |
| productivity P_CR7 = S_CR7/ AE_CR7 | 1.5 |
| estimated effort (from estimationCR) EE_CR7 | 24 |
|estimation accuracy CR7 = EE_CR7/AE_CR7  | 2.4 |