//this file is part of notepad++
//Copyright (C)2003 Don HO <donho@altern.org>
//
//This program is free software; you can redistribute it and/or
//modify it under the terms of the GNU General Public License
//as published by the Free Software Foundation; either
//version 2 of the License, or (at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program; if not, write to the Free Software
//Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.



#include "PluginDefinition.h"
#include <Shobjidl.h>
#include "menuCmdID.h"
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <windows.h> 
#include <iomanip>
#include <sstream>
#include <fstream>
#include <string>
#include <tchar.h>

OPENFILENAMEA ofn;
std::string filename;
LPSTR cString = strdup(filename.c_str());
bool autorun = true;

//char filename[MAX_PATH];
//
// The plugin data that Notepad++ needs
//
FuncItem funcItem[nbFunc];

//
// The data of Notepad++ that you can use in your plugin commands
//
NppData nppData;

//
// Initialize your plugin data here
// It will be called while plugin loading   
void pluginInit(HANDLE /*hModule*/)
{

	// Where the application checks to see if a filepath.txt exists and has content.
	// If it does, it reads it. Otherwise it creates a blank one. 
	
	std::ifstream inFile;
	std::ofstream outFile;
	std::string line;
	std::string autoStartNum;

	// Attempt to open existing filepath.txt
	inFile.open("C:\\Program Files (x86)\\notepad++\\filepath.txt"); 

	// If filepath exists exist - above statement returns false

	/*####### USEFUL MESSAGE BOX COMMANDS ######### */
	//::MessageBox(NULL, TEXT("No filepath set for Memoranda"), TEXT("Filepath is blank or no longer valid."), MB_OK);	
	//::MessageBoxA(NULL, filename.c_str(), "Selected Filepath", 0);

	if (inFile.is_open()) {
		getline(inFile, line);
		filename = line.c_str();

		getline(inFile, autoStartNum);

		if(!autoStartNum.empty()){
			if (autoStartNum == "0") {
				autorun = false;
			}
			else if (autoStartNum == "1") {
				autorun = true;
			}
			else {
				::MessageBox(NULL, TEXT("Invalid Auto-run value"), TEXT("Error reading auto-run value"), MB_OK);
			}
		}

		if (autorun == true) {
			openMemoranda();
		}
		inFile.close();
	}
	// Path does not exist 
	else {

		outFile.open("C:\\Program Files (x86)\\notepad++\\filepath.txt");  // This path ~~relative~~ ABSOLUTE to /Notepad++ main directory
		outFile.close();
		inFile.close(); // If this conditional passes, then inFile is never closed properly.
	}
}

//
// Here you can do the clean up, save the parameters (if any) for the next session
//
void pluginCleanUp()
{
	/*Here dependent on the filepath's origin, I remove the original filepath.txt file 
	and make a new filepath.txt in the root directory of npp++.
	This done mainly to ensure no read/write errors with lingering empty spaces and terminators, etc.
	*/

	// Save whatever file-path was made to memoranda. 
	if (!(cString == NULL) && !(cString[0] == 0)) {
		filename = cString;
		writeToFile();

	} else {
		writeToFile();
	}
}

//
// Initialization of your plugin commands
// You should fill your plugins commands here
void commandMenuInit()
{

    //--------------------------------------------//
    //-- STEP 3. CUSTOMIZE YOUR PLUGIN COMMANDS --//
    //--------------------------------------------//
    // with function :
    // setCommand(int index,                      // zero based number to indicate the order of command
    //            TCHAR *commandName,             // the command name that you want to see in plugin menu
    //            PFUNCPLUGINCMD functionPointer, // the symbol of function (function pointer) associated with this command. The body should be defined below. See Step 4.
    //            ShortcutKey *shortcut,          // optional. Define a shortcut to trigger this command
    //            bool check0nInit                // optional. Make this menu item be checked visually
    //            );
    setCommand(0, TEXT("Set Filepath"), setFilepath, NULL, false);
    setCommand(1, TEXT("Launch Memoranda"), openMemoranda, NULL, false);
	setCommand(2, TEXT("Auto-Start Memoranda"), flipAutoRun, NULL, false);
}

//
// Here you can do the clean up (especially for the shortcut)
//
void commandMenuCleanUp()
{
	// Don't forget to deallocate your shortcut here
}


//
// This function help you to initialize your plugin commands
//
bool setCommand(size_t index, TCHAR *cmdName, PFUNCPLUGINCMD pFunc, ShortcutKey *sk, bool check0nInit) 
{
    if (index >= nbFunc)
        return false;

    if (!pFunc)
        return false;

    lstrcpy(funcItem[index]._itemName, cmdName);
    funcItem[index]._pFunc = pFunc;
    funcItem[index]._init2Check = check0nInit;
    funcItem[index]._pShKey = sk;

    return true;
}

//----------------------------------------------//
//-- STEP 4. DEFINE YOUR ASSOCIATED FUNCTIONS --//
//----------------------------------------------//
void setFilepath()
{
	//::MessageBox(NULL, TEXT("Please Enter the Filepath to Memoranda"), TEXT("Memoranda Filepath"), MB_OK);

	ZeroMemory(&filename, sizeof(filename));
	ZeroMemory(&ofn, sizeof(ofn));
	ofn.lStructSize = sizeof(ofn);
	ofn.hwndOwner = NULL;  // If you have a window to center over, put its HANDLE here
	ofn.lpstrFilter = "filename Files\0*.txt\0Any File\0*.*\0";
	ofn.lpstrFile = cString;
	ofn.nMaxFile = MAX_PATH;
	ofn.lpstrTitle = "Choose Memoranda executable/jar file";
	ofn.Flags = OFN_DONTADDTORECENT | OFN_FILEMUSTEXIST;

	filename = cString;

	if (GetOpenFileNameA(&ofn))
	{

		//std::cout << "You chose the file \"" << filename << "\"\n";
		::MessageBoxA(NULL, cString, "Selected Filepath", 0);
		//int fileLength = strlen(filename);
	}
	else
	{
		// All this stuff below is to tell you exactly how you messed up above. 
		// Once you've got that fixed, you can often (not always!) reduce it to a 'user cancelled' assumption.
		switch (CommDlgExtendedError())
		{
		case CDERR_DIALOGFAILURE: std::cout << "CDERR_DIALOGFAILURE\n";   break;
		case CDERR_FINDRESFAILURE: std::cout << "CDERR_FINDRESFAILURE\n";  break;
		case CDERR_INITIALIZATION: std::cout << "CDERR_INITIALIZATION\n";  break;
		case CDERR_LOADRESFAILURE: std::cout << "CDERR_LOADRESFAILURE\n";  break;
		case CDERR_LOADSTRFAILURE: std::cout << "CDERR_LOADSTRFAILURE\n";  break;
		case CDERR_LOCKRESFAILURE: std::cout << "CDERR_LOCKRESFAILURE\n";  break;
		case CDERR_MEMALLOCFAILURE: std::cout << "CDERR_MEMALLOCFAILURE\n"; break;
		case CDERR_MEMLOCKFAILURE: std::cout << "CDERR_MEMLOCKFAILURE\n";  break;
		case CDERR_NOHINSTANCE: std::cout << "CDERR_NOHINSTANCE\n";     break;
		case CDERR_NOHOOK: std::cout << "CDERR_NOHOOK\n";          break;
		case CDERR_NOTEMPLATE: std::cout << "CDERR_NOTEMPLATE\n";      break;
		case CDERR_STRUCTSIZE: std::cout << "CDERR_STRUCTSIZE\n";      break;
		case FNERR_BUFFERTOOSMALL: std::cout << "FNERR_BUFFERTOOSMALL\n";  break;
		case FNERR_INVALIDFILENAME: std::cout << "FNERR_INVALIDFILENAME\n"; break;
		case FNERR_SUBCLASSFAILURE: std::cout << "FNERR_SUBCLASSFAILURE\n"; break;
		default: std::cout << "You cancelled.\n";
		}
	}
}

/* Is what actually executes the file to be opened via Windows*/
void openMemoranda()
{
	// ##### TEST OUTPUT ####
	//::MessageBoxA(NULL, filename.c_str() , "filename VALUE", 0);
	//::MessageBoxA(NULL, cString, "cSTRING VALUE", 0);

	if (!filename.empty()) { 
		ShellExecuteA(NULL, "open", filename.c_str(), NULL, NULL, SW_SHOWDEFAULT); // This needs <windows.h>
	}
	else if (!(cString == NULL) && !(cString[0] == 0)) {
		ShellExecuteA(NULL, "open", cString, NULL, NULL, SW_SHOWDEFAULT); // This needs <windows.h> in order to run
	}
	else {
		::MessageBox(NULL, TEXT("Invalid Filepath"), TEXT("Filepath is blank or no longer valid."), MB_OK);
	}
}

void flipAutoRun() {

	autorun = !autorun;

	::MessageBoxA(NULL, BoolToString(autorun), "Auto-run set to:", 0);
}

bool writeToFile() {

	std::ofstream outFile;
	std::string line;

	// Save whatever file-path was made to memoranda. 
	if (!(cString == NULL) && !(cString[0] == 0)) {
		remove("C:\\Program Files (x86)\\notepad++\\filepath.txt");
		outFile.open("C:\\Program Files (x86)\\notepad++\\filepath.txt");
		//::MessageBoxA(NULL, cString, "Selected Filepath", 0);
		//filename = cString;
		outFile.write(filename.c_str(), strlen(filename.c_str()));
		outFile.write("\n", sizeof(char));
		if (autorun == true) {
			outFile.write("1", sizeof(char));
		}
		else {
			outFile.write("0", sizeof(char));
		}
		outFile << std::endl;
		outFile.close();
	}

	return true;
}

inline const char * const BoolToString(bool b){
	return b ? "Autorun ON" : "Autorun OFF";
}

