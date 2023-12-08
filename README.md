# file-checker

File Checker is a Java application that checks the chosen directory for files that contain the specified character.

This application was made because certain cloud storage services change file names after download, replacing certain characters with, for example, an underscore.
This can be an annoyance, especially when downloading a large number of files.
Also, Windows Explorer cannot be used to filter such special characters, sadly.

This program helps you identify files containing the specified character, so you can easily find and change them.

## Limitations

Currently, you might experience that non-ASCII characters will not be processed correctly.
This issue is especially prevalent on Windows machines.
This is not an issue caused by the application itself; rather, this issue is likely caused by your terminal of choice.
