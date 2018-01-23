# jpa-editor

This very simple JPA editor with H2DB database and Groovy lang.  
  
  
This app use ![sample-database](https://github.com/ezhov-da/sample-database) for work with JPA.  

Please, load **sample.mv.db** and run on local H2DB server for use.

This editor work with classes hierarchy:  
![hierarchy](https://github.com/ezhov-da/jpa-editor/blob/master/diagram.png?raw=true)

For use editor, write yor code in "User code" panel and push button "Execute script".  
Result show in "Result" tab.  

For run app:
* Download zip archive
* Unzip archive
* Start command line
* cd to unzip folder
* execute: **mvn package**
* execute: **java -cp target\jpa-editor-1.0-SNAPSHOT.jar ru.ezhov.App** 

![screenshot](https://github.com/ezhov-da/jpa-editor/blob/master/app.png?raw=true)
