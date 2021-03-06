package Characters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import Cards.Block;
import Cards.Kick;
import Cards.Punch;
import Inventory.Equipment;
import Inventory.PlayerMainInventory;
import MainFiles.MainClass;

public class Player extends Character implements ActionListener, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int keyPress;
	private int cardsPerTurn;
	private int currentXP;
	private int currentGold;
	
	private static PlayerMainInventory pInventory;
	private static Equipment pEquipment;
	
	public Player(MainClass mc){
		this.setTotalHP(25);
		this.setCurrentHP(this.getTotalHP());
		this.setAttack(2);
		this.setDefence(1);
		this.setCardsPerTurn(2);
		this.setAlive(true);
		
		this.setLevel(1);
		this.setCurrentGold(100);
		
		this.setEImage("/Resources/Player.png");
		this.charClass = new CharClass(mc.getMapTiles().getSubimage(0, 25, 25, 25));
		charClass.setToolTipText("Me!");
		this.charEncClass = new CharEncounterClass(this.getEImage());
		
		this.setCExit(true);
		
		pInventory = new PlayerMainInventory(mc);
		//mc.getATW().add(pInventory.getMainFrame());
		
		createNewDeck();
	}
	
	public void createNewDeck()
	{
		this.createDeck(60);		
		//create the cards themselves
		Punch punch = new Punch();
		Kick kick = new Kick();
		Block block = new Block();
		
		for (int a=0; a<=19; a++)
		{
		this.setCard(a, punch);
		this.setCard(a + 20, kick);
		this.setCard(a + 40, block);
		}
		//System.out.println(d.length);

	}


	public void runMap(MainClass mc) {	
		if (keyPress != 0)
		{
			//System.out.println(keyPress);
			this.setCMove(true);
			
			if (keyPress == 68 || keyPress == 39)
			{
				this.moveRight();
				keyPress = 0;
			}
			else if (keyPress == 65 || keyPress == 37)
			{
				this.moveLeft();
				keyPress = 0;
			}
			else if (keyPress == 87 || keyPress == 38)
			{
				this.moveUp();
				keyPress = 0;
			}
			else if (keyPress == 83 || keyPress == 40)
			{
				this.moveDown();
				keyPress = 0;
			}
			else {keyPress = 0;}
		}
	}
	
	
	public int getKeyPress() {
		return keyPress;
	}
	
	public void setKeyPress(int keyPress) {
		this.keyPress = keyPress;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCardsPerTurn(int i){
		this.cardsPerTurn = i;
	}
	
	public int getCardsPerTurn(){
		return this.cardsPerTurn;
	}
	
	public int getCurrentXP(){
		return this.currentXP;
	}

	public void setCurrentXP(int currentXP) {
		this.currentXP = currentXP;
	}
	
	public void addXP(int a){
		this.currentXP= this.currentXP + a;
	}
	
	public void levelUp(){
		this.setCurrentXP(this.getCurrentXP() - this.xpToLevel());
		this.nextLevel();
		this.setAttack(this.getAttack() + 1);
		this.setDefence(this.getDefence() + 1);
		this.setTotalHP(this.getTotalHP() + 5);
		this.resetHP();
	}
	
	public int xpToLevel(){
		int xptl;
		xptl = (int)(Math.exp(this.getLevel())/Math.log(this.getLevel()+1));
		return xptl;
	}
	
	public int currentXPToLevel(){
		return this.xpToLevel() - this.getCurrentXP();
	}

	public int getCurrentGold() {
		return currentGold;
	}

	public void setCurrentGold(int gold) {
		this.currentGold = gold;
		//System.out.println(this.currentGold);
	}

	public void addGold(int gold){
		this.currentGold = this.getCurrentGold() + gold;
		//System.out.println(this.currentGold);
	}
	
	public static PlayerMainInventory getInv(){
		return pInventory;
	}

	
	
}
