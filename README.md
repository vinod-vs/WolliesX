# WolliesX

Langauge Used  - Java
Framework Used  - Cucumber with Page factory model
Libary used for UI tests - Selenium Webdriver 3
Library used for API tests  - RestAssured API
Other tools used - Maven , TestNG 

How to Run:
===========

1) Download and install Java and Maven 
2) Clone the repo
3) Go into the project and run

> mvn test ( to run all tests )

> mvn -Dtest= <Test Name> ( to run a single test )
 
Assumptions:
===========

1) For Ebay Test , I created a user and have used in my test to checkout from the recently viewed items of the user
2) For API weather.org test , The API which gives 16 days forecast is not free to use , so registered for 5 days forecast 
which was free (gives an API key ) . The test works for all days but due to API key works only for next 5 days 



Improvements that can be done 
=============================

Add logic around updating geckodriver and firefox driver .
Logic around selection of browser from the run command 
Logic around environment for different config per environment ( dev, staging , prod )
URI path can be stored as properties in property files and can be used as needed 
Hook it up to Jenkins for running it with continous integration


