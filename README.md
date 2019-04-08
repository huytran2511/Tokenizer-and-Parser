# Tokenizer-and-Parser

This program will recursively retrieve the paths to all .java and .txt files in a folder. It will open each file sequentially, process it and perform an analysis to calculate the following metrics below:

 - Length of longest line in file
 - Average line length
 - Number of unique space-delineated tokens (case-sensitive)
 - Number of unique space-delineated tokens (case-insensitive)
 - Number of all space-delineated tokens in file
 - Most frequently occurring token(s)
 - Count of most frequently occurring token (case-insensitive)
 - 10 most frequent tokens with their counts (case-insensitive)
 - 10 least frequent tokens with their counts (case-insensitive)
 
The program will then save the data in a file with a .stats file type extension. For example, if the input file is App.java, the program will open the file, process its contents, and output to App.java.stats. The output .stats files will exist in the same directory as their original input files. If the output file already exists, the program will overwrite the existing contents.
 
The user may launch the program with or without a parameter. When launched in the default mode without a parameter, the program will take the current folder as its starting point. The user may specify a file path as a parameter when launching the program. In this case, the program will use the input parameter as the starting point.
