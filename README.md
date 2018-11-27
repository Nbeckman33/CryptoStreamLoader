# CryptoStreamLoader
Load Streaming Crypto Exchange Market Data to Elastic-Search-Engine

# Functional Flow:

 1. Extracts historic data for BTC-USD pair from GDAX/Coinbase API
 2. parse and load the JSON Data to Elastic-Search running on http://localhost:9300


# Run Info:

   1. check JAVA_HOME, set it to JDK-8
   2. check MAVEN_HOME or M2_HOME , set it to maven-3

   3. Make sure that elastic-search instance is up & running on port 9300
       - For further details on how to install and run, refer the git project:
           ```
            Git Project page: https://github.com/kanthgithub/CryptoAnalytics
           ```
    4. navigate to the project directory (if you are running from command console)
    5. run command
       ```
        mvn clean install
        ```
    6. After successful build , start application :
       ```
       mvn spring-boot:run
       ```
    7. application will startup on random port, in case if you want to set to specific port:
       ```
       - open application.yaml file
       - update property:
         - server:
              port: 0
       ```

 
