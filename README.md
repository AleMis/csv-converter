# CSV Converter #

This csv converter provide opportunity to load a specified .csv file (UTF-8 encoding), next process data from 
this file, and save a .csv file (ISO-88591-1 encoding with processed data) to the selected folder.

### Build With ###
* Java - main programming language
* JavaFx - GUI
* Maven - dependency management
* Intellij IDEA - IDE

### Prerequesites ###
* Maven

### Running the app ###
1. Download project from github
2. Build project using command: 'mvn clean install'
3. You can run the app:
    * by running the Main method in Intellij, or
    * by using command in Intellij terminal: e.g. java -jar out\artifacts\converter_jar\converter.jar

You can also use only the converter.jar file as a normal desktop app to run this program. (converter.jar file is in out\* folder). 


### Input .csv file ###

   Feature          |    Type
------------------- | ------------------
File-encoding       | UTF-8
Separator-character | semicolon(;)
Quote-character     | double-quotes(")
Escape-character    | backslash(\\)

#### Example of input data structure ####

   Product Name     |    Link           | SKU      |Selling-Price | description                 |
------------------- | ------------------|----------|--------------|-----------------------------|
Product #1          | http://link.com   |AT-23     |USD 1,232.99  |This field contains no date! |
Product #2          | http://link.com   |234234    |431.333,0 EUR |Here a date: 20.09.2014      |
Product #3          | http://link.com   |UZ733-2   |USD 8654.56   |American date: 12-23-2003    |


### Output .csv file ###

   Feature          |    Type
------------------- | ------------------
File-encoding       | ISO-8859-1
Separator-character | pipe-symbol
Quote-character     | single-quotes(\')
Escape-character    | backslash(\\)

#### Example of output data structure ####

   'name'     |    'offerurl'              | 'price'    |'published'   | 'description'                 |
------------- | ---------------------------|------------|--------------|-------------------------------|
'Product #1'  | 'http://link.com?=AT-23'   | '1232.99'  |''            |'This field contains no date!' |
'Product #2'  | 'http://link.com?=234234'  | '431333.00'|'20.09.2014'  |'Here a date: 20.09.2014'      |
'Product #3'  | 'http://link.com?=UZ733-2' | '8654.56'  |'23.12.2003'  |'American date: 12-23-2003'    |