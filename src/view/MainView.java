package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;


public class MainView extends JFrame  implements ActionListener{
	JLabel LbPlayerN;
	JLabel LbName;
	JLabel LbCity;
	JLabel LbCityN;
	JLabel LbTreasuryN;
	JLabel LbFoodN;
	JLabel LbCountN;
	JLabel LbTreasury;
	JLabel LbFood;
	JLabel LbCount;
	JLabel LbFarm;
	JLabel LbMarket;
	JLabel LbBarrack;
	JLabel LbStable;
	JLabel LbArchery;
	JLabel LFarm;
	JLabel LMarket;
	JLabel LBarrack;
	JLabel LStable;
	JLabel LArchery;
	JLabel UFarm;
	JLabel UMarket;
	JLabel UBarrack;
	JLabel UStable;
	JLabel UArchery;
	JLabel RBarrack;
	JLabel RStable;
	JLabel RArchery;
	JButton BtMap;
	JButton BtTarget;
	JButton BtStore;
	JButton BtIntiate;
	JButton BtArmy;
	JButton BtAddUnit;
	JButton BtEnd;
	JButton BtFarm;
	JButton BtMarket;
	JButton BtBarrack;
	JButton BtStable;
	JButton BtArchery;
	JButton BtUpFarm;
	JButton BtUpMarket;
	JButton BtUpBarrack;
	JButton BtUpStable;
	JButton BtUpArchery;
	JButton BtRecBarrack;
	JButton BtRecStable;
	JButton BtRecArchery;
	JPanel mapPanel;
	JPanel mainPanel;
	JPanel pFarm;
	JPanel pMarket;
	JPanel pBarrack;
	JPanel pStable;
	JPanel pArchery;
	JPanel InfoPanel;
	JPanel pInfoPanel;
	JLabel cityView;
	JLabel LbPlayerInfo;
	JLabel logo;
	JLabel LDefend;
	City city;
	Game game;
	JFrame store;
	JFrame CityArmy;
	JTextArea DefendingArmy;
	JScrollPane DefPane;

	JTable table = new JTable(new DefaultTableModel(new Object[]{"Type", "Level","Current_Count","Max_Count"}, 0));
	DefaultTableModel model;

	JTable CArmytable = new JTable(new DefaultTableModel(new Object[]{"Type", "Level","Current_Count","Max_Count"}, 0));
	DefaultTableModel Cmodel;

	JButton EnterCityC;
	JButton EnterCityR;
	JButton EnterCityS;
	String InputName;
	MainView Game;
	JButton Back;

	JPanel ArmyInfoPanel;
	JLabel ArmyView;

	JButton EnterCity;
	JButton AttackCity;

	CheckArmies CheckControlledArmies;

	int ArmyCount = -1;

	JButton TCairo;
	JButton TRome;
	JButton TSparta;

	ButtonGroup Group = new ButtonGroup();
	JToggleButton Cairo;
	JToggleButton Rome;
	JToggleButton Sparta;

	JButton BtAttack;
	JButton BtAutoResolve;
	JButton BtLaySiege;
	BattleView Battle;

	int LayCount;

	ArrayList<ArmyButton> btns = new ArrayList<ArmyButton>();
	ArrayList<UnitButton> Ubtns = new ArrayList<UnitButton>();



	public MainView(Game NewGame,City cityPhoto) throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\2.png"));
		this.setTitle("Empire v1.0");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setLayout(new BorderLayout());

		game = NewGame;
		city = cityPhoto;

		pInfoPanel = new JPanel();
		pInfoPanel.setLayout(new FlowLayout());
		pInfoPanel.setBackground(new Color(0xFFFFEE));
		getContentPane().add(pInfoPanel, BorderLayout.NORTH);

		ImageIcon Arrow = new ImageIcon("images/Back.png");
		mapPanel = new JPanel();
		Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
		mapPanel.setBounds(0,35,(int)size.getWidth(),(int)size.getHeight());
		mapPanel.setVisible(false);
		mapPanel.setLayout(new BorderLayout());
		this.getContentPane().add(mapPanel,BorderLayout.CENTER);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBounds(0,0,this.getWidth(),this.getHeight());
		mainPanel.setVisible(true);
		this.getContentPane().add(mainPanel,BorderLayout.CENTER);

		InfoPanel  = new JPanel();
		InfoPanel.setFont(new Font("Tahoma", Font.BOLD, 11));
		InfoPanel.setBackground(new Color(70, 130, 180));
		InfoPanel.setBounds(0,0,200,500);
		InfoPanel.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		InfoPanel.setLayout(null);
		mainPanel.add(InfoPanel,BorderLayout.CENTER);

		cityView = new JLabel();
		cityView.setBounds(220, 0, 854, 500);
		String s = "images/" + cityPhoto.getName() +".png";
		this.cityView.setIcon(new ImageIcon(s));
		mainPanel.add(cityView, BorderLayout.EAST);

		ArmyInfoPanel  = new JPanel();
		ArmyInfoPanel.setFont(new Font("Tahoma", Font.BOLD, 11));
		ArmyInfoPanel.setBackground(new Color(170, 210, 160));
		ArmyInfoPanel.setBounds(0,0,220,this.getHeight());
		ArmyInfoPanel.setLayout(null);
		mapPanel.add(ArmyInfoPanel,BorderLayout.CENTER);

		ArmyView = new JLabel();
		ArmyView.setBounds(220, 0, 860, this.getHeight());
		ArmyView.setIcon(new ImageIcon("images/map2.jpg"));
		ArmyView.setLayout(null);
		mapPanel.add(ArmyView, BorderLayout.EAST);

		BtAttack = new JButton("Attack");
		BtAttack.setBounds(70, 450, 130, 50);
		BtAttack.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		BtAttack.setFocusable(false);
		ArmyInfoPanel.add(BtAttack);

		BtAutoResolve = new JButton("Auto Resolve");
		BtAutoResolve.setBounds(70, 510, 130, 50);
		BtAutoResolve.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		BtAutoResolve.setFocusable(false);
		ArmyInfoPanel.add(BtAutoResolve);

		BtLaySiege = new JButton("Lay Siege");
		BtLaySiege.setBounds(70, 570, 130, 50);
		BtLaySiege.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		BtLaySiege.setFocusable(false);
		ArmyInfoPanel.add(BtLaySiege);

		DefendingArmy = new JTextArea("Type  Level  CurrentCount  MaxCount");
		DefendingArmy.setBounds(20, 20, 250, 150);
		//InfoPanel.add(DefendingArmy);

		DefPane = new JScrollPane(table);
		DefPane.setBounds(20, 40, 250, 150);
		InfoPanel.add(DefPane);
		table.setDragEnabled(false);

		model = (DefaultTableModel) table.getModel();

		BtStore = new JButton("Store");
		BtStore.setBounds(70, 450, 130, 50);
		BtStore.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		BtStore.setFocusable(false);
		InfoPanel.add(BtStore);

		BtMap = new JButton("Map View");
		BtMap.setBounds(70, 510, 130, 50);
		BtMap.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		BtMap.setFocusable(false);
		InfoPanel.add(BtMap);

		BtTarget = new JButton("Target City");
		BtTarget.setBounds(150, 570, 130, 50);
		BtTarget.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		BtTarget.setFocusable(false);
		BtTarget.setEnabled(false);
		InfoPanel.add(BtTarget);

		BtIntiate = new JButton("Intiate Army");
		BtIntiate.setBounds(10, 570, 130, 50);
		BtIntiate.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		BtIntiate.setFocusable(false);
		InfoPanel.add(BtIntiate);

		BtArmy = new JButton("Show Stationed Armies");
		BtArmy.setBounds(10, 630, 140, 50);
		BtArmy.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		BtArmy.setFocusable(false);
		InfoPanel.add(BtArmy);

		BtAddUnit = new JButton("Add Unit");
		BtAddUnit.setBounds(160, 630, 120, 50);
		BtAddUnit.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		BtAddUnit.setFocusable(false);
		InfoPanel.add(BtAddUnit);

		BtEnd = new JButton("End Turn");
		BtEnd.setPreferredSize(new Dimension(100,25));
		BtEnd.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		BtEnd.setFocusable(false);


		pFarm = new JPanel();
		pMarket = new JPanel();
		pBarrack = new JPanel();
		pStable = new JPanel();
		pArchery = new JPanel();

		pFarm.setBounds(80, 60, 250, 100);
		pMarket.setBounds(750, 60, 250, 100);
		pBarrack.setBounds(60, 500, 250, 100);
		pStable.setBounds(700, 500, 250, 100);
		pArchery.setBounds(400, 550, 250, 100);

		pFarm.setLayout(new FlowLayout(FlowLayout.CENTER,20, 5));
		pMarket.setLayout(new FlowLayout(FlowLayout.CENTER,20, 5));
		pBarrack.setLayout(new FlowLayout(FlowLayout.CENTER,20, 5));
		pStable.setLayout(new FlowLayout(FlowLayout.CENTER,20, 5));
		pArchery.setLayout(new FlowLayout(FlowLayout.CENTER,20, 5));

		cityView.add(pFarm);
		cityView.add(pMarket);
		cityView.add(pBarrack);
		cityView.add(pStable);
		cityView.add(pArchery);

		LbFarm = new JLabel("Farm");
		LbMarket = new JLabel("Market");
		LbBarrack = new JLabel("Barrack");
		LbStable = new JLabel("Stable");
		LbArchery = new JLabel("Archery Range");

		LbFarm.setPreferredSize(new Dimension(50,20));
		LbMarket.setPreferredSize(new Dimension(50,20));
		LbBarrack.setPreferredSize(new Dimension(50,20));
		LbStable.setPreferredSize(new Dimension(50,20));
		LbArchery.setPreferredSize(new Dimension(100,20));

		pFarm.add(LbFarm);
		pMarket.add(LbMarket);
		pBarrack.add(LbBarrack);
		pStable.add(LbStable);
		pArchery.add(LbArchery);

		Farm farm = new Farm();
		Market market = new Market();
		Barracks barracks = new Barracks();
		Stable stable = new Stable();
		ArcheryRange archeryRange = new ArcheryRange();

		LFarm = new JLabel("Level: "+farm.getLevel());
		LMarket = new JLabel("Level: "+market.getLevel());
		LBarrack = new JLabel("Level: "+barracks.getLevel());
		LStable = new JLabel("Level: "+stable.getLevel());
		LArchery = new JLabel("Level: "+archeryRange.getLevel());

		LFarm.setPreferredSize(new Dimension(50,20));
		LMarket.setPreferredSize(new Dimension(50,20));
		LBarrack.setPreferredSize(new Dimension(50,20));
		LStable.setPreferredSize(new Dimension(50,20));
		LArchery.setPreferredSize(new Dimension(50,20));

		pFarm.add(LFarm);
		pMarket.add(LMarket);
		pBarrack.add(LBarrack);
		pStable.add(LStable);
		pArchery.add(LArchery);

		UFarm = new JLabel("Upgrade Cost: "+farm.getUpgradeCost());
		UMarket = new JLabel("Upgrade Cost: "+market.getUpgradeCost());
		UBarrack = new JLabel("Upgrade Cost: "+barracks.getUpgradeCost());
		UStable = new JLabel("Upgrade Cost: "+stable.getUpgradeCost());
		UArchery = new JLabel("Upgrade Cost: "+archeryRange.getUpgradeCost());

		pFarm.add(UFarm);
		pMarket.add(UMarket);
		pBarrack.add(UBarrack);
		pStable.add(UStable);
		pArchery.add(UArchery);

		BtUpFarm = new JButton("Upgrade");
		BtUpMarket = new JButton("Upgrade");
		BtUpBarrack = new JButton("Upgrade");
		BtUpStable = new JButton("Upgrade");
		BtUpArchery = new JButton("Upgrade");

		pFarm.add(BtUpFarm);
		pMarket.add(BtUpMarket);
		pBarrack.add(BtUpBarrack);
		pStable.add(BtUpStable);
		pArchery.add(BtUpArchery);

		RBarrack = new JLabel("Recruit Cost: "+barracks.getRecruitmentCost());
		RStable = new JLabel("Recruit Cost: "+stable.getRecruitmentCost());
		RArchery = new JLabel("Recruit Cost: "+archeryRange.getRecruitmentCost());

		pBarrack.add(RBarrack);
		pStable.add(RStable);
		pArchery.add(RArchery);

		BtRecBarrack = new JButton("Recruit");
		BtRecStable = new JButton("Recruit");
		BtRecArchery = new JButton("Recruit");

		pBarrack.add(BtRecBarrack);
		pStable.add(BtRecStable);
		pArchery.add(BtRecArchery);

		pFarm.setVisible(false);
		pMarket.setVisible(false);
		pBarrack.setVisible(false);
		pStable.setVisible(false);
		pArchery.setVisible(false);

		LDefend = new JLabel();
		LDefend = new JLabel("Defending Army:");
		LDefend.setBounds(5, 5, 200, 25);
		LDefend.setFont(new Font(null,Font.BOLD,15));
		InfoPanel.add(LDefend);

		LbPlayerInfo = new JLabel("Player's Information:");
		LbPlayerInfo.setBounds(5, 5, 200, 25);
		LbPlayerInfo.setVerticalAlignment(JLabel.NORTH);
		LbPlayerInfo.setFont(new Font(null,Font.BOLD,15));

		LbPlayerN = new JLabel(NewGame.getPlayer().getName());
		LbPlayerN.setBounds(60, 43, 115, 25);
		LbPlayerN.setFont(new Font(null,Font.BOLD,14));

		LbName = new JLabel("Name: ");
		LbName.setBounds(15,43,50,25);;
		LbName.setFont(new Font(null,Font.BOLD,14));

		LbCity = new JLabel("City:");
		LbCity.setBounds(15, 67, 30, 25);
		LbCity.setFont(new Font(null,Font.BOLD,14));

		LbCityN = new JLabel(cityPhoto.getName());
		LbCityN.setBounds(45, 67, 65, 25);
		LbCityN.setFont(new Font(null,Font.BOLD,14));

		String G = NewGame.getPlayer().getTreasury() + "";
		String F = NewGame.getPlayer().getFood() + "";
		String T = NewGame.getCurrentTurnCount() + "";
		LbTreasuryN = new JLabel(G);
		LbFoodN = new JLabel(F);
		LbCountN = new JLabel(T);
		LbTreasuryN.setBounds(60, 92,55, 25);
		LbFoodN.setBounds(53, 117,57, 25);
		LbCountN.setBounds(95, 142,50, 25);
		LbTreasuryN.setFont(new Font(null,Font.BOLD,14));
		LbFoodN.setFont(new Font(null,Font.BOLD,14));
		LbCountN.setFont(new Font(null,Font.BOLD,14));

		LbTreasury = new JLabel("Coins:");
		LbFood = new JLabel("Food:");
		LbCount = new JLabel("Turn count:");
		LbTreasury.setBounds(15, 92,50, 25);
		LbFood.setBounds(15, 117,50, 25);
		LbCount.setBounds(15, 142,100, 25);
		LbTreasury.setFont(new Font(null,Font.BOLD,14));
		LbFood.setFont(new Font(null,Font.BOLD,14));
		LbCount.setFont(new Font(null,Font.BOLD,14));

		pInfoPanel.add(LbPlayerInfo);
		LbPlayerInfo.setBorder(new EmptyBorder(0, 0, 0, 50));
		pInfoPanel.add(LbName);
		pInfoPanel.add(LbPlayerN);
		LbPlayerN.setBorder(new EmptyBorder(0, 0, 0, 20));
		pInfoPanel.add(LbCity);
		pInfoPanel.add(LbCityN);
		LbCityN.setBorder(new EmptyBorder(0, 0, 0, 20));
		pInfoPanel.add(LbTreasury);
		pInfoPanel.add(LbTreasuryN);
		LbTreasuryN.setBorder(new EmptyBorder(0, 0, 0, 20));
		pInfoPanel.add(LbFood);
		pInfoPanel.add(LbFoodN);
		LbFoodN.setBorder(new EmptyBorder(0, 0, 0, 20));
		pInfoPanel.add(LbCount);
		pInfoPanel.add(LbCountN);
		LbCountN.setBorder(new EmptyBorder(0, 0, 0, 200));
		pInfoPanel.add(BtEnd);
		pInfoPanel.add(Box.createRigidArea(new Dimension(100, 0)));


		Store();
		store.setVisible(false);

		CityArmy = new JFrame();
		CityArmy.setBounds(200,300,300,500);
		CityArmy.setLayout(new FlowLayout());

		TCairo = new JButton("Cairo");
		TRome = new JButton("Rome");
		TSparta = new JButton("Sparta");
		TCairo.setBounds(10, 10, 80, 25);
		TSparta.setBounds(100, 10, 80, 25);
		TRome.setBounds(190, 10, 80, 25);
		CityArmy.add(TCairo);
		CityArmy.add(TSparta);
		CityArmy.add(TRome);
		TCairo.setVisible(false);
		TSparta.setVisible(false);
		TRome.setVisible(false);
		if(city.getName().toLowerCase().equals("cairo")) {
			TCairo.setEnabled(false);
		}
		if(city.getName().toLowerCase().equals("sparta")) {
			TSparta.setEnabled(false);
		}
		if(city.getName().toLowerCase().equals("rome")) {
			TRome.setEnabled(false);
		}



		Cairo = new JToggleButton("Cairo");
		Cairo.setBounds(700,600,120,34);
		Cairo.setFocusable(false);
		ArmyView.add(Cairo);
		Group.add(Cairo);
		Rome = new JToggleButton("Rome");
		Rome.setBounds(300,220,120,34);
		Rome.setFocusable(false);
		ArmyView.add(Rome);
		Group.add(Rome);
		Sparta = new JToggleButton("Sparta");
		Sparta.setBounds(540,330,120,34);
		Sparta.setFocusable(false);
		ArmyView.add(Sparta);
		Group.add(Sparta);

		CheckControlledArmies = new CheckArmies();
		CheckControlledArmies.setText("Check Controlled Armies & Their Status");
		CheckControlledArmies.setBounds(750,0,300,50);
		ArmyView.add(CheckControlledArmies);

		Back = new JButton("Back");
		Back.setBounds(20,650,110,40);
		Back.setIcon(Arrow);
		Back.setFont(new Font(null,Font.BOLD,13));
		Back.setHorizontalTextPosition(JButton.RIGHT);
		Back.setFocusable(false);
		ArmyInfoPanel.add(Back);


		EnterCityC = new JButton("Enter Cairo");
		EnterCityC.setBounds(800,600,120,34);
		EnterCityC.setFont(new Font(null,Font.BOLD,13));
		EnterCityC.setFocusable(false);
		EnterCityC.setEnabled(false);
		ArmyView.add(EnterCityC);
		String s1 = "Cairo";
		boolean flag = false;
		for(int i = 0;i < NewGame.getPlayer().getControlledCities().size();i++) {
			if(NewGame.getPlayer().getControlledCities().get(i).getName().toLowerCase().equals(s1.toLowerCase())) {
				flag = true;
				break;
			}
		}
		if(flag) {
			EnterCityC.setEnabled(true);
		}


		EnterCityR = new JButton("Enter Rome");
		EnterCityR.setBounds(400,220,120,34);
		EnterCityR.setFont(new Font(null,Font.BOLD,13));
		EnterCityR.setFocusable(false);
		EnterCityR.setEnabled(false);
		ArmyView.add(EnterCityR);

		String s2 = "Rome";
		boolean flag2 = false;
		for(int i = 0;i < NewGame.getPlayer().getControlledCities().size();i++) {
			if(NewGame.getPlayer().getControlledCities().get(i).getName().toLowerCase().equals(s2.toLowerCase())) {
				flag2 = true;
				break;
			}
		}
		if(flag2) {
			EnterCityR.setEnabled(true);
		}

		EnterCityS = new JButton("Enter Sparta");
		EnterCityS.setBounds(640,330,120,34);
		EnterCityS.setFont(new Font(null,Font.BOLD,13));
		EnterCityS.setFocusable(false);
		EnterCityS.setEnabled(false);
		ArmyView.add(EnterCityS);
		String s3 = "Sparta";
		boolean flag3 = false;
		for(int i = 0;i < NewGame.getPlayer().getControlledCities().size();i++) {
			if(NewGame.getPlayer().getControlledCities().get(i).getName().toLowerCase().equals(s3.toLowerCase())) {
				flag3 = true;
				break;
			}
		}
		if(flag3) {
			EnterCityS.setEnabled(true);
		}



		EnterCityC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapPanel.setVisible(false);
				mainPanel.setVisible(true);
				BtEnd.setEnabled(true);
			}});

		EnterCityR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapPanel.setVisible(false);
				mainPanel.setVisible(true);
				BtEnd.setEnabled(true);
			}});

		EnterCityS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapPanel.setVisible(false);
				mainPanel.setVisible(true);
				BtEnd.setEnabled(true);
			}});

		BtMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);
				mapPanel.setVisible(true);
			}});



		BtEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGame.endTurn();
				for(ArmyButton a :CheckControlledArmies.besiege) {
					LayCount++;
				}

				LbTreasuryN.setText(NewGame.getPlayer().getTreasury()+"");
				LbFoodN.setText(NewGame.getPlayer().getFood()+"");
				LbCountN.setText(NewGame.getCurrentTurnCount()+"");
				for(ArmyButton a : CheckControlledArmies.march) {
					a.setText("Army "+city.getName()+" to "+ a.army.getTarget()+"\n"+" Distatnce: "+a.army.getDistancetoTarget()+"\n"+"Status: "+a.army.getCurrentStatus());
				}

				for(ArmyButton a : CheckControlledArmies.march) {
					if(a.army.getCurrentStatus().equals(Status.IDLE)) {
						CheckControlledArmies.controlledInfo.Idle.add(a);
						CheckControlledArmies.controlledInfo.Marching.remove(a);
						CheckControlledArmies.march.remove(a);
						CheckControlledArmies.idle.add(a);
					}
					if(a.army.getDistancetoTarget()==0) {
						if(a.army.getCurrentLocation().toLowerCase().equals(Cairo.getText().toLowerCase())) {
							Cairo.setSelected(true);
							BtEnd.setEnabled(false);
							Back.setEnabled(false);
							EnterCityR.setEnabled(false);
							EnterCityS.setEnabled(false);
							Rome.setEnabled(false);
							Sparta.setEnabled(false);
							JOptionPane.showMessageDialog(null, "You have reached your target city!"+"\n"+"You Should now attack or lay siege.");
						}
						if(a.army.getCurrentLocation().toLowerCase().equals(Rome.getText().toLowerCase())) {
							Rome.setSelected(true);
							BtEnd.setEnabled(false);
							Back.setEnabled(false);
							EnterCityC.setEnabled(false);
							EnterCityS.setEnabled(false);
						}
						if(a.army.getCurrentLocation().toLowerCase().equals(Sparta.getText().toLowerCase())) {
							Sparta.setSelected(true);
							BtEnd.setEnabled(false);
							Back.setEnabled(false);
							EnterCityR.setEnabled(false);
							EnterCityC.setEnabled(false);
						}

					}
				}

				if(LayCount>3) {
					JOptionPane.showMessageDialog(null, "You now have to Attack or Auto Resolve!");
					BtEnd.setEnabled(false);
					Back.setEnabled(false);
					EnterCityC.setEnabled(false);
					EnterCityS.setEnabled(false);
					EnterCityR.setEnabled(false);
				}

				if(NewGame.isGameOver()==true && NewGame.getAvailableCities().size()==NewGame.getPlayer().getControlledCities().size()) {
					int result = JOptionPane.showConfirmDialog(BtEnd,
							"You Win!! "+LbPlayerN.getText()
							+"\n"+" Start New Game?",
							"Game Over", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						dispose();
						try {
							new StartView();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if (result == JOptionPane.NO_OPTION) System.exit(0);
				}
				else if(NewGame.isGameOver()) {
					int result = JOptionPane.showConfirmDialog(BtEnd,
							"Game Over. You Lost!"
									+"\n"+" Start New Game?",
									"Game Over", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						dispose();
						try {
							new StartView();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if (result == JOptionPane.NO_OPTION) System.exit(0);
				}
			}});


		BtStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.setVisible(true);
			}});


		BtUpFarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (EconomicBuilding e : cityPhoto.getEconomicalBuildings()) {
					if (e instanceof Farm) {
						try {
							game.getPlayer().upgradeBuilding(e);
							LbTreasuryN.setText(NewGame.getPlayer().getTreasury()+"");
							LFarm.setText("Level: "+e.getLevel());
							UFarm.setText("Upgrade Cost: "+e.getUpgradeCost());
							repaint();
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (BuildingInCoolDownException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (MaxLevelException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}

				}
			}
		});
		BtUpMarket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (EconomicBuilding e : cityPhoto.getEconomicalBuildings()) {
					if (e instanceof Market) {
						try {
							game.getPlayer().upgradeBuilding(e);
							LbTreasuryN.setText(NewGame.getPlayer().getTreasury()+"");
							LMarket.setText("Level: "+e.getLevel());
							UMarket.setText("Upgrade Cost: "+e.getUpgradeCost());
							repaint();
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (BuildingInCoolDownException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (MaxLevelException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}

				}
			}
		});
		BtUpBarrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (MilitaryBuilding e : cityPhoto.getMilitaryBuildings()) {
					if (e instanceof Barracks) {
						try {
							game.getPlayer().upgradeBuilding(e);
							LbTreasuryN.setText(NewGame.getPlayer().getTreasury()+"");
							LBarrack.setText("Level: "+e.getLevel());
							UBarrack.setText("Upgrade Cost: "+e.getUpgradeCost());
							repaint();
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (BuildingInCoolDownException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (MaxLevelException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}

				}
			}
		});
		BtUpStable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (MilitaryBuilding e : cityPhoto.getMilitaryBuildings()) {
					if (e instanceof Stable) {
						try {
							game.getPlayer().upgradeBuilding(e);
							LbTreasuryN.setText(NewGame.getPlayer().getTreasury()+"");
							LStable.setText("Level: "+e.getLevel());
							UStable.setText("Upgrade Cost:"+e.getUpgradeCost());
							repaint();
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (BuildingInCoolDownException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (MaxLevelException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}

				}
			}
		});
		BtUpArchery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (MilitaryBuilding e : cityPhoto.getMilitaryBuildings()) {
					if (e instanceof ArcheryRange) {
						try {
							game.getPlayer().upgradeBuilding(e);
							LbTreasuryN.setText(NewGame.getPlayer().getTreasury()+"");
							LArchery.setText("Level: "+e.getLevel());
							UArchery.setText("Upgrade Cost: "+e.getUpgradeCost());
							repaint();
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (BuildingInCoolDownException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (MaxLevelException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}

				}
			}
		}
				);

		BtRecBarrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					game.getPlayer().recruitUnit("Infantry", cityPhoto.getName());
					LbTreasuryN.setText(NewGame.getPlayer().getTreasury()+"");
					while(model.getRowCount()>0)
						model.removeRow(0);
					for(Unit x : city.getDefendingArmy().getUnits()) {
						if(x instanceof Infantry) {
							model.addRow(new Object[]{"Infantry", ""+x.getLevel(), ""+x.getCurrentSoldierCount(),""+x.getMaxSoldierCount()});
						}
					}
					DefUpdate();
				} catch (BuildingInCoolDownException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (MaxRecruitedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (NotEnoughGoldException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		}
				);

		BtRecStable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					game.getPlayer().recruitUnit("Cavalry", cityPhoto.getName());
					LbTreasuryN.setText(NewGame.getPlayer().getTreasury()+"");
					while(model.getRowCount()>0)
						model.removeRow(0);
					for(Unit x : city.getDefendingArmy().getUnits()) {
						if(x instanceof Cavalry) {

							model.addRow(new Object[]{"Cavalry", ""+x.getLevel(), ""+x.getCurrentSoldierCount(),""+x.getMaxSoldierCount()});
						}
					}
					DefUpdate();
				} catch (BuildingInCoolDownException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (MaxRecruitedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (NotEnoughGoldException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		}
				);

		BtRecArchery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					game.getPlayer().recruitUnit("Archer", cityPhoto.getName());
					LbTreasuryN.setText(NewGame.getPlayer().getTreasury()+"");
					String y="Type  Level  CurrentCount  MaxCount";
					while(model.getRowCount()>0)
						model.removeRow(0);
					for(Unit x : city.getDefendingArmy().getUnits()) {
						if(x instanceof Archer) {
							model.addRow(new Object[]{"Archer", ""+x.getLevel(), ""+x.getCurrentSoldierCount(),""+x.getMaxSoldierCount()});
						}
					}
					DefUpdate();
				} catch (BuildingInCoolDownException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (MaxRecruitedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (NotEnoughGoldException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		}
				);


		BtTarget.addActionListener(this);
		BtIntiate.addActionListener(this);
		BtArmy.addActionListener(this);
		Back.addActionListener(this);
		CheckControlledArmies.addActionListener(this);
		BtAddUnit.addActionListener(this);
		BtAttack.addActionListener(this);
		BtAutoResolve.addActionListener(this);
		BtLaySiege.addActionListener(this);
		Painter();
		this.revalidate();
		this.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Back) {
			mapPanel.setVisible(false);
			mainPanel.setVisible(true);
			BtEnd.setEnabled(true);
		}

		if(e.getSource()==BtAttack) {
			Battle = new BattleView();
			for(City c : game.getAvailableCities()) {
				if(c.getName().equals("Cairo")){
					for(Unit u : c.getDefendingArmy().getUnits()) {
						Battle.Defending.add(new UnitButton(u));
					}
				}
				if(c.getName().equals("Sparta")){
					for(Unit u : c.getDefendingArmy().getUnits()) {
						Battle.Defending.add(new UnitButton(u));
					}
				}
				if(c.getName().equals("Rome")){
					for(Unit u : c.getDefendingArmy().getUnits()) {
						Battle.Defending.add(new UnitButton(u));
					}
				}
			}
			for(ArmyButton a : CheckControlledArmies.idle) {
				if(a.army.getDistancetoTarget()==0) {
					for(UnitButton u : a.ArmyUnits)
						Battle.Attacking.add(u);
				}
			}
			Battle.setVisible(true);
		}

		if(e.getSource()==BtLaySiege) {
			LayCount = 0;
			for(ArmyButton a : CheckControlledArmies.idle) {
				for(City c : game.getAvailableCities()) {
					if(a.army.getCurrentLocation().toLowerCase().equals(c.getName().toLowerCase())) {

						try {
							game.getPlayer().laySiege(a.army, c);
							CheckControlledArmies.controlledInfo.Besieging.add(a);
							CheckControlledArmies.besiege.add(a);
							CheckControlledArmies.controlledInfo.Idle.remove(a);
						} catch (TargetNotReachedException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (FriendlyCityException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}

					}
				}
			}
		}

		if(e.getSource()==BtAutoResolve) {
			for(ArmyButton a : CheckControlledArmies.idle) {
				for(City c : game.getAvailableCities()) {
					if(a.army.getDistancetoTarget()==0 &&a.army.getCurrentLocation().toLowerCase().equals(c.getName().toLowerCase())) {
						try {
							game.autoResolve(a.army, c.getDefendingArmy());
							BtEnd.setEnabled(true);
							Back.setEnabled(true);
							for(City c1 : game.getPlayer().getControlledCities()) {
								if(c1.getName().equals("Rome"))
									EnterCityR.setEnabled(true);
								if(c1.getName().equals("Sparta"))
									EnterCityS.setEnabled(true);
								if(c1.getName().equals("Cairo"))
									EnterCityC.setEnabled(true);
							}
							Rome.setEnabled(true);
							Sparta.setEnabled(true);
							if(a.army.getUnits().size() !=0) {
								JOptionPane.showMessageDialog(null, "You Successfully Occupy this City!");
								CheckControlledArmies.idle.remove(a);
							}else {
								JOptionPane.showMessageDialog(null, "You lost this Battle");
								CheckControlledArmies.idle.remove(a);
							}
						} catch (FriendlyFireException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
			}

			for(ArmyButton a : CheckControlledArmies.besiege) {
				for(City c : game.getAvailableCities()) {
					if(a.army.getDistancetoTarget()==0 &&a.army.getCurrentLocation().toLowerCase().equals(c.getName().toLowerCase())) {
						try {
							game.autoResolve(a.army, c.getDefendingArmy());
							BtEnd.setEnabled(true);
							Back.setEnabled(true);
							for(City c1 : game.getPlayer().getControlledCities()) {
								if(c1.getName().equals("Rome"))
									EnterCityR.setEnabled(true);
								if(c1.getName().equals("Sparta"))
									EnterCityS.setEnabled(true);
								if(c1.getName().equals("Cairo"))
									EnterCityC.setEnabled(true);
							}
							Rome.setEnabled(true);
							Sparta.setEnabled(true);
							if(a.army.getUnits().size() !=0) {
								JOptionPane.showMessageDialog(null, "You Successfully Occupy this City!");
								CheckControlledArmies.besiege.remove(a);
							}else {
								CheckControlledArmies.besiege.remove(a);
								JOptionPane.showMessageDialog(null, "You lost this Battle");
							}
						} catch (FriendlyFireException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
			}
		}

		if(e.getSource()==BtTarget) {
			CityArmy.setVisible(true);
			for(ArmyButton a : btns) {
				a.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						TCairo.setVisible(true);
						TSparta.setVisible(true);
						TRome.setVisible(true);
						TCairo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								game.targetCity(a.army, "Cairo");
								CityArmy.setVisible(false);
								CheckControlledArmies.controlledInfo.Marching.add(a);
								CheckControlledArmies.march.add(a);
							}});
						TSparta.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								game.targetCity(a.army, "Sparta");
								CityArmy.setVisible(false);
								CheckControlledArmies.controlledInfo.Marching.add(a);
								CheckControlledArmies.march.add(a);
							}});
						TRome.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								game.targetCity(a.army, "Rome");
								CityArmy.setVisible(false);
								CheckControlledArmies.controlledInfo.Marching.add(a);
								CheckControlledArmies.march.add(a);
							}});
					}});
			}
		}


		if(e.getSource()==BtAddUnit) {
			if(model.getRowCount()==0) {
				JOptionPane.showMessageDialog(null, "No units left in the defeding army");
			}
			else if(table.isRowSelected(table.getSelectedRow())) {
				Vector x = model.getDataVector().elementAt(table.getSelectedRow());
				String name = (String) x.get(0);
				CityArmy.setVisible(true);

				for(ArmyButton a : btns) {

					a.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							a.UFrame.setVisible(true);
							try {
								a.army.relocateUnit(city.getDefendingArmy().getUnits().get(table.getSelectedRow()));
								a.UFrame.txt.setText(a.UFrame.txt.getText()+"\n"+x);
								a.ArmyUnits.add(Ubtns.get(table.getSelectedRow()));
								Ubtns.remove(table.getSelectedRow());
								model.removeRow(table.getSelectedRow());
								CityArmy.setVisible(false);
							} catch (MaxCapacityException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
						}});
				}

			}
			else {
				JOptionPane.showMessageDialog(null, "Please select a unit in the defending army.");
			}
		}

		if(e.getSource()==BtIntiate) {
			if(model.getRowCount()==0) {
				JOptionPane.showMessageDialog(null, "Recruit at least one unit.");
			}
			else if(table.isRowSelected(table.getSelectedRow())) {
				Vector x = model.getDataVector().elementAt(table.getSelectedRow());
				String name = (String) x.get(0);

				game.getPlayer().initiateArmy(city, city.getDefendingArmy().getUnits().get(table.getSelectedRow()));
				ArmyCount++;
				ArmyButton a = new ArmyButton(game.getPlayer().getControlledArmies().get(ArmyCount));
				a.UFrame.txt.setText(""+x);
				btns.add(a);
				a.setText("Army "+ ArmyCount+" "+city.getName());
				a.ArmyUnits.add(Ubtns.get(table.getSelectedRow()));
				Ubtns.remove(table.getSelectedRow());
				CityArmy.add(a);
				model.removeRow(table.getSelectedRow());
				if(!BtTarget.isEnabled())
					BtTarget.setEnabled(true);
				a.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						a.UFrame.setVisible(true);
					}});



			}
			else {
				JOptionPane.showMessageDialog(null, "Please select a unit in the defending army.");
			}
		}

		if(e.getSource()==BtArmy) {
			CityArmy.setVisible(true);
		}

		if(e.getSource() == CheckControlledArmies) {
			CheckControlledArmies.controlledInfo.setVisible(true);
		}

	}
	public void Painter() {
		if(city.getName().equals("Cairo")) {
			logo = new JLabel(new ImageIcon("images/egy1.png"));
			logo.setBounds(40, 220, 200, 200);
			InfoPanel.add(logo);
			InfoPanel.setBackground(new Color(170,130,60));
			pInfoPanel.setBackground(new Color(170,120,50));
			BtStore.setBackground(new Color(170,120,50));
			BtEnd.setBackground(new Color(170,120,50));
			BtMap.setBackground(new Color(170,120,50));
			BtTarget.setBackground(new Color(170,120,50));
			BtIntiate.setBackground(new Color(170,120,50));
			BtArmy.setBackground(new Color(170,120,50));
			BtAddUnit.setBackground(new Color(170,120,50));
			pFarm.setBackground(new Color(170,130,60,200));
			pMarket.setBackground(new Color(170,130,60,200));
			pBarrack.setBackground(new Color(170,130,60,200));
			pStable.setBackground(new Color(170,130,60,200));
			pArchery.setBackground(new Color(170,130,60,200));
			pFarm.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pMarket.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pBarrack.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pStable.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pArchery.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		}

		if(city.getName().equals("Rome")) {
			logo = new JLabel(new ImageIcon("images/rome1.png"));
			logo.setBounds(40, 220, 200, 200);
			InfoPanel.add(logo);
			InfoPanel.setBackground(new Color(255,240,200));
			pInfoPanel.setBackground(new Color(0xFFFFEE));
			BtStore.setBackground(new Color(230,230,190));
			BtEnd.setBackground(new Color(230,230,190));
			BtMap.setBackground(new Color(230,230,190));
			BtTarget.setBackground(new Color(230,230,190));
			BtIntiate.setBackground(new Color(230,230,190));
			BtArmy.setBackground(new Color(230,230,190));
			BtAddUnit.setBackground(new Color(230,230,190));
			pFarm.setBackground(new Color(255,255,200,200));
			pMarket.setBackground(new Color(255,255,200,200));
			pBarrack.setBackground(new Color(255,255,200,200));
			pStable.setBackground(new Color(255,255,200,200));
			pArchery.setBackground(new Color(255,255,200,200));
			pFarm.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pMarket.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pBarrack.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pStable.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pArchery.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		}

		if(city.getName().equals("Sparta")) {
			logo = new JLabel(new ImageIcon("images/sparta2.png"));
			logo.setBounds(40, 220, 200, 200);
			InfoPanel.add(logo);
			InfoPanel.setBackground(new Color(160,60,40));
			pInfoPanel.setBackground(new Color(180,60,40));
			BtStore.setBackground(new Color(180,60,40));
			BtEnd.setBackground(new Color(180,60,40));
			BtMap.setBackground(new Color(180,60,40));
			BtTarget.setBackground(new Color(180,60,40));
			BtIntiate.setBackground(new Color(180,60,40));
			BtArmy.setBackground(new Color(180,60,40));
			BtAddUnit.setBackground(new Color(180,60,40));
			pFarm.setBackground(new Color(160,60,40,200));
			pMarket.setBackground(new Color(160,60,40,200));
			pBarrack.setBackground(new Color(160,60,40,200));
			pStable.setBackground(new Color(160,60,40,200));
			pArchery.setBackground(new Color(160,60,40,200));
			pFarm.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pMarket.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pBarrack.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pStable.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			pArchery.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
		}
	}

	public void Store() {
		ArrayList<JButton>Sbuttons = new ArrayList<JButton>();
		store = new JFrame();
		JPanel storePanel = new JPanel();
		store.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\2.png"));
		Farm farm = new Farm();
		Market market = new Market();
		Barracks barracks = new Barracks();
		Stable stable = new Stable();
		ArcheryRange archeryRange = new ArcheryRange();
		BtFarm = new JButton("Farm        "+"BuildingCost: "+farm.getCost());
		BtMarket = new JButton("Market        "+"BuildingCost: "+market.getCost());
		BtBarrack = new JButton("Barrak        "+"BuildingCost: "+barracks.getCost());
		BtStable = new JButton("Stable        "+"BuildingCost: "+stable.getCost());
		BtArchery = new JButton("ArcheryRange        "+"BuildingCost: "+archeryRange.getCost());
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\2.png"));
		store.setBounds(200,100,300,500);
		store.setVisible(true);
		store.setTitle("Store");
		store.setLayout(new BorderLayout());
		storePanel.setLayout(new GridLayout(5,1,20,20));
		store.add(storePanel);
		store.add(new JPanel(),BorderLayout.EAST);
		store.add(new JPanel(),BorderLayout.WEST);
		store.add(new JPanel(),BorderLayout.NORTH);
		store.add(new JPanel(),BorderLayout.SOUTH);
		BtFarm.setFocusable(false);
		BtMarket.setFocusable(false);
		BtBarrack.setFocusable(false);
		BtStable.setFocusable(false);
		BtArchery.setFocusable(false);
		storePanel.add(BtFarm);
		storePanel.add(BtMarket);
		storePanel.add(BtBarrack);
		storePanel.add(BtStable);
		storePanel.add(BtArchery);
		Sbuttons.add(BtFarm);
		Sbuttons.add(BtMarket);
		Sbuttons.add(BtBarrack);
		Sbuttons.add(BtStable);
		Sbuttons.add(BtArchery);
		BtFarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					game.getPlayer().build("Farm", city.getName());
					LbTreasuryN.setText(game.getPlayer().getTreasury()+"");
					BtFarm.setEnabled(false);
					if(!pFarm.isVisible()) repaint();
					pFarm.setVisible(true);
				} catch (NotEnoughGoldException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		BtMarket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					game.getPlayer().build("Market", city.getName());
					LbTreasuryN.setText(game.getPlayer().getTreasury()+"");
					BtMarket.setEnabled(false);
					if(!pMarket.isVisible()) repaint();
					pMarket.setVisible(true);

				} catch (NotEnoughGoldException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		BtBarrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					game.getPlayer().build("Barracks", city.getName());
					LbTreasuryN.setText(game.getPlayer().getTreasury()+"");
					BtBarrack.setEnabled(false);
					if(!pBarrack.isVisible()) repaint();
					pBarrack.setVisible(true);

				} catch (NotEnoughGoldException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		BtStable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					game.getPlayer().build("Stable", city.getName());
					LbTreasuryN.setText(game.getPlayer().getTreasury()+"");
					BtStable.setEnabled(false);
					if(!pStable.isVisible()) repaint();
					pStable.setVisible(true);
				} catch (NotEnoughGoldException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		BtArchery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					game.getPlayer().build("archeryRange", city.getName());
					LbTreasuryN.setText(game.getPlayer().getTreasury()+"");
					BtArchery.setEnabled(false);
					if(!pArchery.isVisible()) repaint();
					pArchery.setVisible(true);
				} catch (NotEnoughGoldException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
				);




		if(city.getName().equals("Cairo")) {
			store.setBackground(new Color(170,130,60));
			for(JButton c : Sbuttons) {
				c.setBackground(new Color(170,120,50));
				c.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			}
		}
		if(city.getName().equals("Rome")) {
			store.setBackground(new Color(170,130,60));
			for(JButton c : Sbuttons) {
				c.setBackground(new Color(230,230,190));
				c.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			}
		}
		if(city.getName().equals("Sparta")) {
			store.setBackground(new Color(170,130,60));
			for(JButton c : Sbuttons) {
				c.setBackground(new Color(180,60,40));
				c.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(130, 80, 30)));
			}
		}


	}


	public void DefUpdate() {
		while(model.getRowCount()>0)
			model.removeRow(0);
		while(Ubtns.size() > 0) {
			Ubtns.removeAll(Ubtns);
		}
		for(Unit x : city.getDefendingArmy().getUnits()) {
			Ubtns.add(new UnitButton(x));
			if(x instanceof Infantry) {
				model.addRow(new Object[]{"Infantry", ""+x.getLevel(), ""+x.getCurrentSoldierCount(),""+x.getMaxSoldierCount()});
			}
			if(x instanceof Cavalry) {
				model.addRow(new Object[]{"Cavalry", ""+x.getLevel(), ""+x.getCurrentSoldierCount(),""+x.getMaxSoldierCount()});
			}
			if(x instanceof Archer) {
				model.addRow(new Object[]{"Archer", ""+x.getLevel(), ""+x.getCurrentSoldierCount(),""+x.getMaxSoldierCount()});
			}
		}
	}
}