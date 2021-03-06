package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Score {


	private JTextField userName;
	private JFrame frame;
	private boolean asking = false;
	private int bestScore;
	private int actualScore;
	public static final int MAX_SCORE_AMOUNT = 5;
	private String[] scoreNames = new String[MAX_SCORE_AMOUNT];
	private int[] scorePoints = new int[MAX_SCORE_AMOUNT];
	private static Score score = new Score();
	
	private Score () {
		readFromFile();
		bestScore = scorePoints[0];
		actualScore = 0;
	}
	
	public static Score getScore() {
		return score;
	}
	
	public void readFromFile(){
        String fileName = "src/principal/scores.txt";

        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
           
            int cont = 0;
            while((line = bufferedReader.readLine()) != null && cont!=MAX_SCORE_AMOUNT) {
    			String[] result = line.split(",");
    			scoreNames[cont] = result[0];
    			scorePoints[cont] = Integer.parseInt(result[1]);
    			cont++;
    			
            }     	
            
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" +  fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"  + fileName + "'");                  
        }
    }


	
	public void useFileWriter( ) {
			
        String fileName = "src/principal/scores.txt";
		
        
        String content = "";

        for(int i=0;i< scorePoints.length;i++){
        	content = content + scoreNames[i]+","+scorePoints[i]+"\n";
		}
      
		 Writer writer = null;
			
		try {
			writer = new FileWriter(fileName);
			writer.write(content);
			
		} catch (IOException e) {
			System.err.println("Error writing the file : ");
			e.printStackTrace();
		} finally {
			
		if (writer != null) {
			try {
				writer.close();
			}catch (IOException e) {
				System.err.println("Error closing the file : ");
				e.printStackTrace();
				}
			}
		}
	}
	
	
		
	public void printScores(){
		
		for(int i = 0; i < scorePoints.length;i++){	
			System.out.println(scoreNames[i]+", "+scorePoints[i]);
		}
	}

	public void add(Integer score, String name) {
		for (int i = 0 ; i < MAX_SCORE_AMOUNT ; i++) {
			if(scorePoints[i] < score) {
				int aux = scorePoints[i];
				String auxName = scoreNames[i];
				scorePoints[i] = score;
				scoreNames[i] = name;
				score = aux;
				name = auxName;
			}
		}
	}
	
	
	
	public void saveScore(){
		asking = true;
		frame = new JFrame();
		JPanel newPanel = new JPanel();
		JLabel label0 = new JLabel("Your score is "+actualScore);
		JLabel label = new JLabel("Enter your name:");
		label0.setForeground(Color.WHITE);
		label.setForeground(Color.WHITE);
		userName = new JTextField(20);

		JButton btn = new JButton("Accept");

		newPanel.add(label0, BorderLayout.NORTH);
		newPanel.add(label, BorderLayout.NORTH);

		newPanel.add(userName, BorderLayout.CENTER);
		newPanel.setVisible(true);
		
		newPanel.add(btn,BorderLayout.SOUTH);
		newPanel.setBackground(Color.BLACK);

		
		frame.add(newPanel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setSize(380, 110);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(actualScore, userName.getText());
				frame.setVisible(false);
				asking = false;
				useFileWriter();
				reset();
			}
		});
	}
	
	
	public int getActualScore(){
		return actualScore;
	}
	
	
	public int getHighScore(){
		return scorePoints[0];
	}
	
	
	public int getCertainScore(int i){
		return scorePoints[i];
	}
	
	
	public String getCertainName(int i){
		return scoreNames[i];
	}
	
	
	public boolean askName(){
		return asking;
	}
	
	
	public void fixWindow(){
		actualScore += 100;
	}
	
	
	public void nextSector(){
		actualScore += 500;
	}
	
	
	public void loseHP(){
		if (actualScore>800)
			actualScore -= 500;
		else
			actualScore = 0;
	}
	
	
	public void reset() {
		actualScore = 0;
	}
	

}
