**********Task 3**********
**********PetStore Api_Automation_Framework**********
1.To run test cases:
1.a.Navigate respective services under resources folder of tests
1.b.Right Click on respective testng xml file and click on run
example: tests--->resources--->petService--->petService.xml

2.Tech Stack used while creating framework.
2.a.Language : Java
2.b.Core Framework : Maven
2.c.Build Tool: Gradle 
2.d.Framework :Spring
2.e.Integrated with Allure,TestNG and Gradle Report Framework.
2.f.Design Pattern: Injection and Builder Pattern.
2.g.intellij idea 2020.1.4

3.Framework Architecture:
3.a.Instance name used to be provided under settings.properties like stage,prod.
3.b.Compiler will read the file from Environments folder for respective environment.
3.c.All generic and framework level implementations are present under main.
3.d.Under tests folder we do have, Request and Response POJO,Constants,Enums,ServiceLauncher,Helper and DataProvider.
3.e.Service launcher search for respective service name and api uri under respective environment file.
3.f.Based on Methods ,it's navigate to respective processor to execute the genric method to launch and  object mapper to parse to the respective response.

TestCases Covered In Automation:
Sno	API	Testcase 	Actual	Expected	Manual	Automation
1	api/v3/pet	To Check whether pets are getting add to the store or not	Pets are getting add	Pet should be added to the store	Done	Done
2	api/v3/pet	To Check whether pets are getting add to the store based on different status 	"Pets are Getting add to the store with below status
1.Available
2.Pending
3.Sold"	Pet should be added to the store based on different status 	Done	Done
3	api/v3/pet	To check whether rsponse schema of api/v3/pet is correct or not	Response schema is correct 	Response schema should correct 	Done	Done
4	api/v3/pet	To check whether created category is correct or not 	Created Category is correct 	Created Category Should be correct 	Done	Done
5	api/v3/pet	To check whether created tag is correct or not 	Created Tag is correct	Created Tag Should be correct 	Done	Done
6	api/v3/pet	To check whether created Pet with status  is correct or not 	pets with created status are correct 	Status should  correct	Done	Done
7	api/v3/pet/findByStatus?status={}	To Check whetther available pets details are coming or not	Available pets details are coming 	Availabe pets details should come	Done	Done
8	api/v3/pet/findByStatus?status={}	To check whether response schema is correct or not 	Response schema is correct 	Response schema should correct 	Done	Done
9	api/v3/pet/findByStatus?status={}	To Check whetther Pending pets details are coming or not	pets with created status are correct 	Status should  correct	Done	Done
10	api/v3/pet/findByStatus?status={}	To Check whetther Sold pets details are coming or not	pets with created status are correct 	Status should  correct	Done	Done
11	/api/v3/store/order	To Check whether order is placed Successful or not	order is placed successfull	Order should place successfull	Done	Done
12	/api/v3/store/order	To check whether response schema is correct or not 	Response schema is correct 	Response schema should correct 	Done	Done
13	/api/v3/store/order	To check whether Correct Pet id is placed or not 	Correct Pet id placed 	Correct Pet Id should be placed 	Done	Done
14	/api/v3/store/order	To check whether order is approved  or not 	order is approved 	correct order should be apporved	Done	Done
15	/api/v3/store/order/{0}	To check whether order details are correct or not 	Order Details are Correct 	Order details should be correct 	Done	Done
16	/api/v3/store/order/{0}	To check whether response schema is correct or not 	Response schema is correct 	Response schema should correct 	Done	Done
17	/api/v3/store/order/{0}	To check whether Pet Id are correct or not 	Pet id is correct 	Pet Id should  correct 	Done	Done
18	/api/v3/store/order/{0}	To check whether created order id  are correct or not 	Created Order Id is correct 	Created Order id should  correct 	Done	Done


*********Task 1*********
*********Exploratory Test Charter*********

Charter: Monefy Android App will be explored in order to make ensure that all functional and User Acceptance expectation are matching.  
Areas: All Functional and UI/UX cases were covered, Device used while doing the exploratory testing :Redmi note 7s and Android Version 9PKQ1.1
Start: 7/02/2021 11:54 PM
Tester: "Binit Anand"
Task Breakdown:1.Functional 2.UI/UX 
Duration: 150 mins.
Charter1. Monefy Home Screen is covered for functional exploratory testing.
Priority: P0
Area:.a. Expenses and balance with different categories were covered. Money Transfer from cash to card vise versa have been tested and there effects on charts. Search and calculators were tested.
     .b. UI/UX of home screen have been tested while maintaing the different combination of expenses and balance.
Duration: 100 mins.
Expected Results: Tested area's are working as expected except the below findings.
Bugs:Respective Line which points to categories are not visible against for few of them in the chart.
Steps to reproduce:
1.Add 1000 as salary and 80000 as gifts 
2.Add House as 5000 , Bills as 2000, Health as 2000
3.Add Transport as 500 and Transfer money 100 cash to card.
4.Navigate to home screens and check for the chart.
Expected : Lines should be present against the transport and health.
Actual: Lines are not visible
Screenshot is attached in the final submission.

Charter2.Expenses and balance projections based on provided Durations.
Priority:P1
Areas: a.Based on different durations,how we are projecting and doing calculation of our expenses and balances.
       b.UI/UX of the above Point a was covered.
Duration: 30 mins.       
Expected Results: Tested areas are working as expected.No major and minor observations for the same.

        
Charter3.Explored addtion and edit of Categories,Accounts,Currencies and Settings.
Priority:P2
Areas: a.Categories, Accounts and currencies and Settings were covered .
       b.UI/UX of the above Point a was covered.
Duration: 20 mins.       
Expected Results: Tested areas are working as expected.No major and minor observations for the same.

  


