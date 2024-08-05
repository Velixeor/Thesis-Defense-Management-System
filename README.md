# Thesis Defense Management System (TDMS)

# Server module
Contains backend server of service
> After building executable JAR file will be in `tdms/server/target` folder
> 
> To start:`java -jar <jar-file>`
> 
> URL is: `http://localhost:8080`

# Web module
Contains frontend part of service
> go to `tdms/web/` folder
> 
> To start: `npm run dev`
>
> URL you will see in console

# How to build
1. Install `Maven`
2. Install `Java 17`

> While building, **web** project compiles frontend, and **server** project automatically copies its to `/server/target/classes/static` and into generated `<jar-file>`

> In IntelliJ Idea you can run `Execute maven goal`, to write further commands

| Description               | Command                              |
|---------------------------|--------------------------------------|
| Clear & Run tests & Build | `mvn clear install`                  |
| Run tests & build         | `mvn install`                        |
| Skip tests & build        | `mvn install -Dmaven.test.skip=true` |
>To use multithreading during build process use `-T <threads>` keyword

### There is final result (add `clear` if you need)
```shell
mvn install -Dmaven.test.skip=true -T 4
```
