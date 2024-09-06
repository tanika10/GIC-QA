#To run test with Maven command line use below command   
#Enter required values for browser, username and password

mvn test -DsuiteXmlFile="testng.xml" -Dbrowser=chrome -Durl=https://germanyiscalling.com/ -Dusername=<username> -Dpassword=<password>

#extentReport generated in test-output folder