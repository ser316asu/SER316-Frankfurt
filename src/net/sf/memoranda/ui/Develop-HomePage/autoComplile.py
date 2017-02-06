#!/usr/bin/python
import os
from subprocess import call

root = "/home/joshuabecker/School/SER316/Project/SER316-Frankfurt/src/net/sf/memoranda/ui/Develop-HomePage/"

os.chdir(root)

dontRun = False

for file in os.listdir(root):
    if (file.endswith(".java")):
        result = call(["javac","-d","bin",file])
        print result
        if result != 0:
            dontRun = True
    elif(file.endswith(".class")):
        call(["rm",file])
   

if not dontRun :
    call(["java","-cp","bin","MAIN"])
