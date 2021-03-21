# Smart-Energy-Management-and-Monitoring-System
Introduction of the file diectory
---
1. Gui.java contains the main method
2. Put all java file in the "src" file
3. All txt file that used to store the data should in the same path of "src" file

How it is developed?
---
It is a Java group project that developed by 6 students in 12 weeks. Iterations are planned and Agile methods are used in all activities, from requirements, through to
analysis/design, implementation and testing. I am the team lead and implement Company.java with another team member.

How to run it?
---
1. Open the command  line and use "cd" command access the path of directory "src". e.g. D:\xxx\src>
	
2. Compile java file by typing the command "javac filename.java" then press Enter, it will generate java classes automatically. The sequence of  compiling is  (1) MultiValueMap.java  (2) LinkedMultiValueMap.java  (3) UserAccount.java  (4) Company.java  (5) Gui.java
	
3. Run the main method, typing "java Gui" and pressing enter, then the software will work
4. There will be a GUI displayed, please follow the prompts and choices to use the software.
5. The default Administrator ID is admin, User ID is 0001.

What functions it has?
---
**Overview**:
The smart energy monitoring system should make it easy for the consumer to view their electricity and gas usage and help them to keep the cost down. The energy provider should be able to use the smart energy management system to set up tariff, get the meter readings from each household and generate bills. Each household can have only one smart energy monitoring software, one smart electricity meter and one smart gas meter. The smart energy monitoring software can communicate with the smart electricity meter and smart gas meter. The energy provider uses the smart energy management system to communicate with the smart energy monitoring software at each household.

**Functions for Smart Energy Monitoring system**:
1. View electricity consumption and cost
>The system display the live electricity usage of the day, in kWh, and the current electricity of the day.
2. View gas consumption and cost
>The system display the live gas usage of the day, in KWh, and current gas cost of the day.
3. Budget and alert
>Consumer is able to set a budget to keep track of the spending. If the electricity or gas usage is lower than the budget, a green signal is displayed; or if it is over the budget, a red signal is displayed. The consumer can change the budget at any time. The budget can be set in either usage or cost.
4. View historic consumption and costs
>Consumer can view the historic monthly consumption and costs.
5. Check tariff
>Consumer can view the current tariff
6. Send meter readings to energy provider
>The systen cab sebd gas and electricity meter readings to the energy provider every month, on a fixed day of the month. The meter reading is a 4-digit number, with each unit is 1KWh.

**Functions for Smart Energy Management system**:
1. Add/view/remove consumer and meter information of each household
>The energy provider is able to add new consumer to the system and remove existing consumer. When adding the consumer to the system, the household¡¯s smart energy monitoring software and smart meter are registered to the system. The system is able to remove an existing consumer. The energy provider can view the readings and bills history of the consumer.
2. Set tariff
>The tariff is set by the energy provider and can only be changed by the energy provider. The standard tariff is as below:
-Gas Unit rate 3.88p per kWh
-Electricity Unit rate 14.60p per kWh
3. Receive meter readings and generate bills
>The system can receive monthly gas and electricity meter readings from households and generate a monthly bill and send it to the consumer. Since no real consumers, we only pop up an interface saying "The bill has been sent" to simulate this function.

**User Manual is attached**:
[User Manual.pdf](https://github.com/helen731/Smart-Energy-Management-and-Monitoring-System/files/6085878/User.Manual.pdf)


