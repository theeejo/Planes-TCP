package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		int port = Integer.parseInt(ResourceBundle.getBundle("settings").getString("port"));
		String hostname = ResourceBundle.getBundle("settings").getString("host");
		
		Scanner scannerName = new Scanner(System.in);
		System.out.println("Type your unique name: ");

		String name = scannerName.nextLine();

		try (Socket socket = new Socket(hostname, port)){
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());

			if(name.equals(ResourceBundle.getBundle("settings").getString("name1")) || name.equals(ResourceBundle.getBundle("settings").getString("name2"))||
					name.equals(ResourceBundle.getBundle("settings").getString("name3"))) {
				System.out.println("Connected to server successfully.");

				System.out.println("Start game? Type Y to Start.");
				Scanner scannerChoice = new Scanner(System.in);
				String choice = scannerChoice.nextLine();
				while(true) {
				if(choice.toUpperCase().equals("Y")) {

					// randomize a file
					Random rn = new Random();
					int fileNumber = rn.nextInt(3) + 1;
					Scanner sc = new Scanner(new BufferedReader(new FileReader("planes"+fileNumber+".txt")));
					String [][] array = new String[10][10];
					while(sc.hasNextLine()) {
						for(int i=0; i<array.length; i++) {
							String[] linie = sc.nextLine().trim().split("");
							for(int j=0; j<linie.length; j++) {
								array[i][j] = linie[j]; 
							}
						}
					}


					int destroyed = 0;
					while(destroyed < 3) {
						//afisare matrice

						System.out.println("Choose where to shoot. Line and Column.");
						Scanner scanner = new Scanner(System.in);
						int line = Integer.parseInt(scanner.nextLine());
						int column = Integer.parseInt(scanner.nextLine()); // check if they are valid
						
						if(line<1 || line>10 || column<1 || column>10) {
							System.out.println("Invalid line or column number.");
							return;
						}
						
						
						String character = new String();				
						
						character = array[line-1][column-1];
					
						if(character.equals("A") ) {
							System.out.println("X");
							destroyed++;
							for(int i=0;i<10;i++) {
								for(int j=0;j<10; j++) {
									if(array[i][j].equals("A") || array[i][j].equals("1")) {
										array[i][j]="0";
									}
								}
							}

						}else if(character.equals("B")) {
							System.out.println('X');
							destroyed++;
							for(int i=0;i<10;i++) {
								for(int j=0;j<10; j++) {
									if(array[i][j].equals("B") || array[i][j].equals("2")) {
										array[i][j]="0";
									}
								}
							}
							

						}else if(character.equals("C")) {
							System.out.println('X');
							destroyed++;
							for(int i=0;i<10;i++) {
								for(int j=0;j<10; j++) {
									if(array[i][j].equals("C")|| array[i][j].equals("3")) {
										array[i][j]="0";
									}
								}
							}
							

						}else if(character.equals("1") || character.equals("2") || character.equals("3")) {
							System.out.println('1');
							array[line-1][column-1] = "0";
							

						}
						else if(character .equals("0")) {
							System.out.println('0');
						}
				}
					System.out.println("Game over. All 3 planes were shot down. You win!");


					System.out.println("Play again? Press Y.");
					choice = scannerChoice.nextLine();
				
				}else {
					System.out.println("Invalid input.");
					break;
				}}
			} 
			else {
				System.out.println("Wrong name.");
				
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			System.exit(0);
		}
		
	}
}
