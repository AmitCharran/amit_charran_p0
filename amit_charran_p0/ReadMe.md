##### Connecting to DB
Create a "secret" package under com.revature
Then create a class called secretValues.java

Class should look like below. Import proper username and password below
Database Schema must be titled p0 if you are using your own database
```package com.revature.secret;

public final class secretValues {
public static final String endpoint = "jdbc:postgresql://database-1.cjwopjwkj9us.us-east-2.rds.amazonaws.com/postgres"; //format --- jdbc:postgresql://{host}[:{port}]/[{database}]
public static final String username = "";
public static final String password = "";
}