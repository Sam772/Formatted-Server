Instructions to run the program:

1. Open your Java IDE (Eclipse/Intellij).

2. Run the FormattedServer.java class.

3. Run the FormattedClient.java class.

4. In the Java console on the FormattedClient, you should be given a prompt to input a message, hence input a message.

5. Again on the FormattedClient you should be given another prompt to input a format, uppercase or lowercase.

6. Either type uppercase or lowercase and see the returned message back on the FormattedClient.

7. To test multiple threads, it is best to use command prompt, ensure you are within the correct directory, this should be the directory where the FormattedServer, FormattedClientHnadler, and FormattedClient java files are.

8. Run the FormattedServer.java class on your IDE.

9. Open a couple of command prompt windows and cd into the directory as mentioned previously, type "java FormattedClient.java" to run the FormattedClient on both command prompt windows.

10. Interact with the program as normal, on the FormattedServer.java class java console window, you should see the messages clients are inputting.

NOTE: If you wish to run multiple instances via cmd, ensure the Keys folder is in the "src/FormattedServer" directory, which should be there by default, otherwise running instances directly from the ide will reference the Keys folder from the root directory instead, this is why the Keys folder has been duplicated.