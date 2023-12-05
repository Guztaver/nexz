# NEXZ [api]

Here contains the api of the `NEXZ` project.
The api is based on [Java 17](https://www.oracle.com/java/technologies/javase/17u-relnotes.html), and uses
the [Spring Framework](https://spring.io).

## Building

To run the application with the `jar` file, you just need to run:

```shell
./gradlew bootJar
```

or in Windows:

```powershell
.\gradlew.bat bootJar
```

## Running

In order to run the project, you need to set a `.env` file with that content:

```dotenv
# Database details, such as username, password and the url, without it, the applicaiton will NOT run
DB_USER=appUser
DB_PASSWORD=password
DB_URL:postgresql:database.mysite.com:5432/databasename

# The port that the application will run 
PORT=8080

# This is nescessary for the authentication in the application
JWT_TOKEN=h+y4n2vcIWw7Em5W+5MH0DXt8y7sXOf6uoHJk2zze3O0kEJpRnekDLEqMys+xVh16ponytVqsCTD4hiB+rssrSiBr3UgPrbqO0RB855/uGogD5odqlEFpkl7lQidocPXhWg7u0WkAYdLO/TPenmIdBGdL02/bYzQa8WmwgSFJqdK+A0ZJydomUIj04OMRrNPmXHtZM6arunpkGI6IC3zFdhrmM4K16F4LHFXJVJenXlhlxARgtrqtMZJfxfFfgbTSDZ9oEOHJ6gAzFL4vnPegFIk8WpEoEbGrrulE0m65kssvblnohCLnOHdncZYLlNurl76eT3LVzw7hqQhos3tmw==
```

There is an example:

```bash
./gradlew bootRun
```

or in Windows:

```powershell
.\gradlew.bat bootRun
```

... or if you build, just run the `.jar` that are generated from the application.