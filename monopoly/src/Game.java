/*
 * Contains Frame; Game Loop
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	//member declarations
	private boolean m_brunning;
	private JFrame m_frame;
	private JPanel m_wrapper;
	private JPanel m_board;
	private GridBagConstraints m_constraints;
	
	// tilelist created in world constructor
	World world = new World();
	
	
	//constructor and initialization
	Game(int w, int h, String name) {
		m_brunning = false;
		m_frame = new JFrame();
		m_frame.setSize(w, h);
		m_frame.setVisible(true);
		m_frame.setName(name);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_wrapper = new JPanel();
		m_board = new JPanel(new GridBagLayout());
		m_constraints = new GridBagConstraints();
		m_constraints.fill = GridBagConstraints.BOTH;
	}
	//condition for game loop set to true
	void init() {
		m_brunning = true;
	}
	//start game loop
	void run() {
		do {
			m_frame.add(m_wrapper);
			m_wrapper.setBackground(Color.black);
			m_board.setPreferredSize(new Dimension(900, 897));
			m_wrapper.add(m_board);
			//we add the tiles like this
			setuptiles();
		}
		while (m_brunning);
	}
	//stops game loop and clears resources
	void quit() {
		m_frame.dispose();
		m_brunning = false;
	}

	// add tiles from tilelist in world.java to current frame
	void setuptiles(){
		// temp tile object
		Tile temptile = null;
		// temp tilelist 
		ArrayList<Tile> tilelist = new ArrayList<Tile>();
		tilelist = world.getTilelist();
		for (int i = 0; i < tilelist.size(); i++){
			//get current tile from list
			temptile = tilelist.get(i);
			m_constraints.gridx = temptile.getX();
			m_constraints.gridy = temptile.getY();
			m_board.add(temptile.getPanel(), m_constraints);
		}
	}
}