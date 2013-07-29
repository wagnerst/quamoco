package de.quamoco.utils;


public class Logger {
	
	private static Types.MessageType currentLevel = Types.MessageType.INFO;
	
	public static void setLevel(Types.MessageType level) {
		currentLevel = level;
	}
	
	public static Types.MessageType getLevel(){
		return currentLevel;
	}

	public static void getLogger(String message, Types.MessageType type){
		
		if(type.ordinal()<= getLevel().ordinal()){
			System.out.println(message);
		}
		
	}

}

