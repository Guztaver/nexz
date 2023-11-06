# NEXZ [api]

Here contains the api of the `NEXZ` project.
The api is based on [Java 20](https://www.oracle.com/java/technologies/javase/20u-relnotes.html), and uses
the [Spring Framework](https://spring.io).

## Running the api

By default, if you want to just run in dev mode, you can run with `gradlew` with the `bootRun` with that command:

```bash
./gradlew bootRun
```

or in Windows:

```powershell
.\gradlew.bat bootRun
```

If you want to run with a database, you need to set these three environment
variables: `DB_URL`, `DB_USER`, `DB_PASSWORD`
and the `DB_URL` needs to start with `jdbc:yoursql://`.

## Building

In order to build the project, you need to set a `.env` file, or pass the `DB_URL`, `DB_USER` and `DB_PASSWORD`
in the variable env, aside with `./gradlew build` in Linux, or `.\gradlew.bat build` in Windows.

There is an example:

```bash
export DB_URL="jdbc:postgresql://db.supersecureserver.com:5432/api"; export DB_USER="spring"; export DB_PASSWORD="MyPassword"; 
./gradlew build
```

or in Windows:

```powershell
.\gradlew.bat build
```

with the `.env` file.