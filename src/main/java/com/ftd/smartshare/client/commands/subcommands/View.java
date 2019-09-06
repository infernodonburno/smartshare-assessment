package com.ftd.smartshare.client.commands.subcommands;

import picocli.CommandLine;

@CommandLine.Command(
        description = "Displays information of file if password is correct",
        name = "view",
        aliases = "v",
        mixinStandardHelpOptions = true
)

public class View implements Runnable {
	
	@CommandLine.Parameters(arity="1", index = "0", description = "Name of the file to view")
    private String fileName;

    @CommandLine.Parameters(arity="1", index = "1", description = "The password for the file")
    private String password;
    
    @Override 
    public void run() {   	
    	
    	
    }

}
