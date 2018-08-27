

import java.util.*;



class Blackjack {
	public static void main(String[] args) {
		
		int puntenCount = 0;   //Voor het begin van het spel is het aantal punten van de speler nog 0
		int aasCount = 0;
		
		
		String[] Waardes = {"Aas", "2", "3", "4", "5","6", "7", "8", "9","10", "Boer", "Vrouw", "Heer"};
		String[] Kleuren = {"Harten ", "Schoppen ", "Klaver ", "Ruiten "};
		
		ArrayList<String> Kaarten = new ArrayList<String>();
		for (String i: Kleuren ) {
			for (String j: Waardes) {
				Kaarten.add(i + j);  //Maakt kaartendeck aan
			}			
		}
		
		ArrayList<String> Kaarten2 = new ArrayList<String>(Kaarten);  //kopie van de originele kaartenlijst
		
		ArrayList<Integer> Value = new ArrayList<Integer>();   //Maakt een lijst met alle waardes op zelfde volgorde als de kaarten		
		for (int z = 0; z<4 ; z++) {		
			Value.add(11);
			for (int x = 2; x < 11; x++)
				Value.add(x);
			for (int y = 0; y<3 ; y++)
				Value.add(10);
		}
		
		Collections.shuffle(Kaarten); // Shuffelt kaartendeck
		System.out.println("Hier een overzicht van de geschudde kaarten: "+ Kaarten);  // Laat geschudde kaartendeck zien
				
		Scanner sc = new Scanner(System.in);              

		ArrayList<String> SpelerKaarten = new ArrayList<String>();  //kan de kaarten van de speler opslaan
		
		for (int i = 0; i<2; i++) {    //Hier krijgt speler de eerste twee kaarten
			KaartenDeck kaartjes = new KaartenDeck();
			String eersteKaarten = kaartjes.kaart(Kaarten);
			SpelerKaarten.add(eersteKaarten);
			int indexKaartje = Kaarten2.indexOf(eersteKaarten); //Gaat na op welke plek de kaart staat in het niet geschudde lijstje
			puntenCount = puntenCount + Value.get(indexKaartje);
			Kaarten.remove(eersteKaarten);
			int value = Value.get(indexKaartje);
			if (value == 11)
				aasCount += 1;
		}
		
		if (puntenCount > 21) {
			puntenCount -= 10;
			aasCount -= 1;}
			
			
		System.out.println("Hier uw kaarten: "+SpelerKaarten);
		System.out.println("Uw totaal aantal punten is " + puntenCount);
		
		if (puntenCount == 21)
			System.out.println("BlackJack!");

		
		while (puntenCount<21) {
			System.out.println("k voor kaart, q voor stoppen en p voor passen");    //vraagt om input
			String input = sc.nextLine();					//wacht op input van speler
			if (input.equals("k")) {						//als speler vraagt om kaart 
				KaartenDeck kaart = new KaartenDeck();
				String nieuwKaart = kaart.kaart(Kaarten);  // Roept de kaart-functie aan om random kaart uit de lijst Kaarten te halen
				System.out.println(nieuwKaart);      //Laat kaart zien    
				SpelerKaarten.add(nieuwKaart); //Geeft kaart aan speler
				int indexKaart = Kaarten2.indexOf(nieuwKaart); //Gaat na op welke plek de kaart staat in het niet geschudde lijstje
				puntenCount = puntenCount + Value.get(indexKaart);  //Het aantal punten wordt verhoogd met de waarde van de kaart
				Kaarten.remove(0);//Haalt kaart weg uit de geschudde kaarten
				if (Value.get(indexKaart) == 11)
					aasCount += 1;
				if (puntenCount > 21) {   // Als totaal aantal punten hoger is dan 21 heeft speler verloren
					while (aasCount > 0 & puntenCount > 21) {
						puntenCount -= 10;
						aasCount -= 1;
					}
					} 
				if (aasCount == 0 & puntenCount > 21) {				
					System.out.println("Helaas, u heeft verloren.");}
				if (puntenCount == 21)    // Als totaal aantal punten gelijk is aan 21 heeft de speler gewonnen
					System.out.println("Blackjack!");
				System.out.println("Uw totaal aantal punten is " + puntenCount);
			}
			if(input.equals("p")) {
				System.out.println("U heeft gepast");
				System.out.println("Dit zijn uw kaarten: " + SpelerKaarten);
				System.out.println("Uw totaal aantal punten is " + puntenCount); //Geeft het aantal punten
				break;
			}
			if(input.equals("q")) {
				System.out.println("Het spel wordt gestopt"); //Geeft aan dat het spel wordt gestopt
				break;
			}

			
		}		
	}

}


class KaartenDeck{	
	Random random = new Random();
	String kaart(ArrayList<String> kaarten) {          // Deze functie haalt een random kaart uit het kaartendeck
		String kaart = kaarten.get(random.nextInt(kaarten.size()));
		return kaart;
		}
	}


class Kaart{
	String kleur;
	String Waarde;
	int punten;

}


