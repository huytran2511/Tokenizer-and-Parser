# Tokenizer-and-Parser

The user may launch the application either with or without run-time arguments. When launched in the default mode, without any additional 
options or parameters, the application shall perform its analysis using the current folder as its starting point. In the other operational
mode, users may specify the path to the input file when launching the program. In this case, the application shall use the input parameter
as the starting point.

From the starting point, the application shall recursively retrieve the paths to every .java and .txt file in the hierarchy. It will look
into each folder, digging completely down, until it locates every one. With these paths, and using only these files, the program shall move
on to its analysis. 

The application shall sequentially open each file in the list and process it completely. The program shall not remove punctuation when 
processing the input file, but it will ignore or strip extra white-space from the input tokens. The program shall calculate the following
metrics:
 - Length of longest line in file
 - Average line length
 - Number of unique space-delineated tokens (case-sensitive)
 - Number of unique space-delineated tokens (case-insensitive)
 - Number of all space-delineated tokens in file
 - Most frequently occurring token(s)
 - Count of most frequently occurring token (case-insensitive)
 - 10 most frequent tokens with their counts (case-insensitive)
 - 10 least frequent tokens with their counts (case-insensitive)
 
With these values in hand, the program will then save the data in a corresponding file using a ’stats’ file type extension. For example,
if working with the input file App.java, the system shall open the file, inspect its contents, and then write the appropriate output to 
App.java.stats. The stats files shall exist in the same directory as their original input files. The contents of the file system shall 
dictate the number and placement of each output file. If the output file already exists, this program should overwrite the existing 
contents.
