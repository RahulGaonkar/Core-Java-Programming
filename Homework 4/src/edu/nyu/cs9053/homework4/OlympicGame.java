package edu.nyu.cs9053.homework4;

public enum OlympicGame {
	WinterOlympics1924(HostCountry.France),
	WinterOlympics1928(HostCountry.Switzerland), 
	WinterOlympics1932(HostCountry.UnitedStates), 
	WinterOlympics1936("Germany"), 
	WinterOlympics1948(HostCountry.Switzerland), 
	WinterOlympics1952("Norway"), 
	WinterOlympics1956("Italy"),
	WinterOlympics1960(HostCountry.UnitedStates),
	WinterOlympics1964("Austria"),
	WinterOlympics1968(HostCountry.France),
	WinterOlympics1972("Japan"),
	WinterOlympics1976("Austria"),
	WinterOlympics1980(HostCountry.UnitedStates),
	WinterOlympics1984("Yugoslavia"),
	WinterOlympics1988("Canada"),
	WinterOlympics1992(HostCountry.France),
	WinterOlympics1994("Norway"),
	WinterOlympics1998("Japan"),
	WinterOlympics2002(HostCountry.UnitedStates),
	WinterOlympics2006("Italy"),
	WinterOlympics2010("Canada"),
	WinterOlympics2014("Russia"),
	WinterOlympics2018("South Korea");

    private final String hostCountry;

    private OlympicGame(String hostCountry) {
        this.hostCountry = hostCountry;
    }
    
    private OlympicGame(HostCountry country) {
        this.hostCountry = country.toString(); 
    }

    public String getHostCountry() {
        return hostCountry;
    }

    public static void printOlympicGameHostCountry(OlympicGame... olympicGames) {
        for (OlympicGame game : olympicGames) {
            System.out.printf("%s%n", game.getHostCountry());
        }
	}	
	}  

