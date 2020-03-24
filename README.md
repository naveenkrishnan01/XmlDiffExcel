# xml-Comparison for two files

This code will compare two xml files and the difference will be loaded to excel file
  
  Library Used
  - XmlUnit
  - Apache-Poi
  
  ### Files
  
  | Input  | Path |
  | ------ | ------ |
  | origin.xml | ../src/test/java/com/util/origin.xml|
  | dest.xml | ../src/test/java/com/util/dest.xml |
  |xmlDiff.xlsx| ../src/test/java/com/util/xmlDiff.xlsx |

The diff file is checked before running the compare, to check if it exist. If it is exist delete it , so that we
get new output files all the time

Instruction
```sh
git clone the repo from github
Run the code from local machine
```