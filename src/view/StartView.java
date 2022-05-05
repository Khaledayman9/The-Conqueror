package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import engine.Game;


public class StartView extends JFrame implements ActionListener {
	JFrame Mainview;
	JTextField tfPlayer;
	JButton BtStart;
	JButton BtMap;
	JLabel LbPlayer;
	JLabel LbCity;
	JLabel Img;
	JLabel Welcome;
	JPanel StartPanel;
	JPanel StartMap;
	static MainView game;
	static Game NewGame;
	ButtonGroup Group;
	JToggleButton Cairo;
	JToggleButton Sparta;
	JToggleButton Rome;

	public StartView() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/2.png"));
		playSound("sounds/GOTBGM.wav");	

		this.setSize(720, 580);
		this.setVisible(true);
		this.setTitle("Launcher");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setLayout(new BorderLayout());

		StartMap = new JPanel();
		StartMap.setBounds(0,0,720,580);
		StartMap.setVisible(false);
		StartMap.setLayout(null);
		add(StartMap,BorderLayout.CENTER);

		StartPanel = new JPanel();
		StartPanel.setBounds(0,0,720,580);
		StartPanel.setLayout(null);
		StartPanel.setVisible(true);
		add(StartPanel,BorderLayout.CENTER);

		JLabel map;
		map = new JLabel();
		map.setBounds(0, 0, 720,540);
		map.setIcon(new ImageIcon("images/map1.png"));
		map.setLayout(null);
		StartMap.add(map);

		JLabel start;
		start = new JLabel();
		start.setBounds(0, 0, 720,540);
		start.setIcon(new ImageIcon("images/wfef.jpg"));
		start.setLayout(null);
		StartPanel.add(start);


		LbCity = new JLabel("Choose your city:");
		LbCity.setBounds(10,10,175,30);
		LbCity.setForeground(new Color(30,30,30));
		LbCity.setFont(new Font("Tahoma", Font.BOLD, 15));
		map.add(LbCity);

		BtMap = new JButton("Choose City");
		BtMap.setFont(new Font("Tahoma", Font.BOLD, 11));
		BtMap.setBounds(300,130,120,34);
		start.add(BtMap);

		LbPlayer = new JLabel("Enter Your Name:");
		LbPlayer.setBounds(10,100,120,30);
		LbPlayer.setForeground(new Color(30,30,30));
		LbPlayer.setFont(new Font("Tahoma", Font.BOLD, 11));
		start.add(LbPlayer);

		tfPlayer = new JTextField();
		tfPlayer.setBounds(130,100,120,30);
		tfPlayer.setBackground(new Color(255,255,255,230));
		start.add(tfPlayer);
		tfPlayer.setColumns(10);

		BtStart = new JButton("Start Game");
		BtStart.setFont(new Font("Tahoma", Font.BOLD, 11));
		BtStart.setBounds(200,10,120,34);
		map.add(BtStart);

		Img = new JLabel(new ImageIcon("images/11.png"));
		Img.setBounds(10, 10, 75,75);
		start.add(Img);

		Welcome = new JLabel("Empire");
		Welcome.setForeground(new Color(0, 0, 0));
		Welcome.setFont(new Font("Lucida Calligraphy", Font.BOLD, 33));
		Welcome.setHorizontalAlignment(SwingConstants.CENTER);
		Welcome.setBounds(60, 10, 200, 75);

		start.add(Welcome);

		Group = new ButtonGroup();

		Cairo = new JToggleButton("Cairo");
		Cairo.setBounds(530,450,120,34);
		Cairo.setFocusable(false);
		Group.add(Cairo);
		map.add(Cairo);
		Rome = new JToggleButton("Rome");
		Rome.setBounds(210,170,120,34);
		Rome.setFocusable(false);
		Group.add(Rome);
		map.add(Rome);
		Sparta = new JToggleButton("Sparta");
		Sparta.setBounds(400,260,120,34);
		Sparta.setFocusable(false);
		Group.add(Sparta);
		map.add(Sparta);

		this.revalidate();
		this.repaint();
		Mainview = new JFrame();
		BtStart.addActionListener(this);
		BtMap.addActionListener(this);


	}

	public void playSound(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);	
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==BtStart) {
			CreateGame();
		}

		if(e.getSource()==BtMap) {
			if(tfPlayer.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Enter Your Name.");
			}
			else {
				StartPanel.setVisible(false);
				StartMap.setVisible(true);
			}
		}
	}
	public void CreateGame() {
		JToggleButton btn = new JToggleButton();
		if(Cairo.isSelected())
			btn = Cairo;
		else if(Rome.isSelected())
			btn = Rome;
		else if(Sparta.isSelected())
			btn = Sparta;
		try {
			if(Cairo.isSelected()||Rome.isSelected()||Sparta.isSelected()) {
				NewGame = new Game(tfPlayer.getText(),btn.getText());
				dispose();
				game = new MainView(NewGame,NewGame.getPlayer().getControlledCities().get(0));
				game.setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(null, "Please Choose A City.");
		} catch (IOException e1) {

		}
	}
	public static void main(String[] args) throws IOException {
		new StartView();
	}
}
