package enginuity.logger.utec.impl;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import enginuity.NewGUI.interfaces.TreeNode;
import enginuity.NewGUI.interfaces.TuningEntity;
import enginuity.logger.utec.commInterface.UtecInterface;
import enginuity.logger.utec.gui.mapTabs.UtecDataManager;
import enginuity.logger.utec.mapData.UtecMapData;

public class UtecTuningEntityImpl implements TuningEntity{

	// Misc items
	private String currentPort = null;
	private int fileChosen;
	private JFileChooser fileChooser =  new JFileChooser();

	
	private Vector<JMenu> jMenuItems = new Vector<JMenu>();
	
	// Menu Items
	public JMenuItem saveItem = new JMenuItem("Save Log");
	public JMenuItem saveMapItem = new JMenuItem("Save Map To File");
	public JMenuItem exitItem = new JMenuItem("Exit");
	public JMenuItem resetUtec = new JMenuItem("Force Utec Reset");
	public JMenuItem startLogging = new JMenuItem("Start Logging");
	public JMenuItem closePort = new JMenuItem("Close Port");
	public JMenuItem loadMapOne = new JMenuItem("Load Map #1");
	public JMenuItem loadMapTwo = new JMenuItem("Load Map #2");
	public JMenuItem loadMapThree = new JMenuItem("Load Map #3");
	public JMenuItem loadMapFour = new JMenuItem("Load Map #4");
	public JMenuItem loadMapFive = new JMenuItem("Load Map #5");
	public JMenuItem loadMapFile = new JMenuItem("Load Map File");

	public JMenuItem saveMapOne = new JMenuItem("Save To Map #1");
	public JMenuItem saveMapTwo = new JMenuItem("Save To Map #2");
	public JMenuItem saveMapThree = new JMenuItem("Save To Map #3");
	public JMenuItem saveMapFour = new JMenuItem("Save To Map #4");
	public JMenuItem saveMapFive = new JMenuItem("Save To Map #5");
	
	
	public UtecTuningEntityImpl(){
		init();
		initJMenu();
	}
	
	private void initJMenu(){

		// *********************************************
		// Add a menu item for basic application actions
		// *********************************************
		// Define the menu system
		JMenu fileMenu = new JMenu("File");
		saveItem.addActionListener(this);
		saveMapItem.addActionListener(this);
		exitItem.addActionListener(this);
		fileMenu.add(saveItem);
		fileMenu.add(saveMapItem);
		fileMenu.add(exitItem);
		jMenuItems.add(fileMenu);
		
		// ******************************************
		// Add menu item to coordinate Utec operation
		// ******************************************
		JMenu actionMenu = new JMenu("Actions");
		this.resetUtec.addActionListener(this);
		this.startLogging.addActionListener(this);
		this.closePort.addActionListener(this);
		actionMenu.add(this.resetUtec);
		actionMenu.add(this.startLogging);
		actionMenu.add(this.closePort);
		jMenuItems.add(actionMenu);
		
		// ****************************************
		// Add menu item to pull maps from the utec
		// ****************************************
		JMenu getMapsMenu = new JMenu("Load Map");
		loadMapOne.addActionListener(this);
		loadMapTwo.addActionListener(this);
		loadMapThree.addActionListener(this);
		loadMapFour.addActionListener(this);
		loadMapFive.addActionListener(this);
		loadMapFile.addActionListener(this);
		getMapsMenu.add(loadMapOne);
		getMapsMenu.add(loadMapTwo);
		getMapsMenu.add(loadMapThree);
		getMapsMenu.add(loadMapFour);
		getMapsMenu.add(loadMapFive);
		getMapsMenu.add(loadMapFile);
		jMenuItems.add(getMapsMenu);
		

		// ****************************************
		// Add menu item to save maps to the utec
		// ****************************************
		JMenu setMapsMenu = new JMenu("Save Map");
		saveMapOne.addActionListener(this);
		saveMapTwo.addActionListener(this);
		saveMapThree.addActionListener(this);
		saveMapFour.addActionListener(this);
		saveMapFive.addActionListener(this);
		setMapsMenu.add(saveMapOne);
		setMapsMenu.add(saveMapTwo);
		setMapsMenu.add(saveMapThree);
		setMapsMenu.add(saveMapFour);
		setMapsMenu.add(saveMapFive);
		jMenuItems.add(setMapsMenu);

		// ***************************************
		// Add a menu item for comm port selection
		// ***************************************
		JMenu portsMenu = new JMenu("Select Port");

		// Gather list of ports from interface
		Vector portsVector = UtecInterface.getPortsVector();

		Iterator portsIterator = portsVector.iterator();
		int counter = 0;
		while (portsIterator.hasNext()) {
			counter++;
			Object o = portsIterator.next();
			String theName = (String) o;
			JMenuItem item = new JMenuItem(theName);
			item.setName(theName);
			item.addActionListener(this);
			portsMenu.add(item);
			/*
			if (counter == 1) {
				defaultPort = theName;
				UtecInterface.setPortChoice(defaultPort);
			}
			*/
		}
		jMenuItems.add(portsMenu);
	}
	
	public TreeNode getJTreeNodeStructure() {
		UtecTreeNode parentNode = new UtecTreeNode("Utec Maps", TreeNode.NODimension);
		UtecTreeNode childOne = new UtecTreeNode("Child One", TreeNode.DATA3D);
		UtecTreeNode childTwo = new UtecTreeNode("Child Two", TreeNode.DATA3D);
		UtecTreeNode childThree = new UtecTreeNode("Child Three", TreeNode.NODimension);
		UtecTreeNode childFour = new UtecTreeNode("Child Four", TreeNode.DATA2D);
		
		childThree.addChild(childFour);
		parentNode.addChild(childOne);
		parentNode.addChild(childTwo);
		parentNode.addChild(childThree);
		
		return parentNode;
	}

	public double[][] getTableData(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		return "UTEC Tuning Entity";
	}

	public Vector<JMenu> getMenuItems() {
		
		return jMenuItems;
	}


	/**
	 * Implements actionPerformed
	 * 
	 * Action listeners for buttons/menus that throw them
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			// Open Port
			if (cmd.equals("New")) {
				System.out.println("New action occuring");
			}

			// Close Port
			else if (cmd.equals("Open")) {
				System.out.println("Open action occuring");
			}

			// Start Capture
			else if (cmd.equals("Save Log")) {
				String saveFileName = null;
				System.out.println("Save action occuring");
				fileChosen = fileChooser.showSaveDialog(null);
				if (fileChosen == JFileChooser.APPROVE_OPTION) {
					saveFileName = fileChooser.getSelectedFile().getPath();
					// selectedFile = fileChooser.getSelectedFile();
					try {
						File file = new File(saveFileName);
						FileWriter out = new FileWriter(file);
						//out.write(bottomPanel.totalLog);
						out.close();
						//bottomPanel.totalLog = "";
					} catch (IOException e2) {
						System.out
								.println("Couldn't save file " + saveFileName);
						e2.printStackTrace();
					}
				}

			}

			else if (cmd.equals("Save Map To File")) {
				System.out.println("Saving map to file.");
				if (UtecDataManager.getCurrentMapData() != null) {

					String saveFileName = null;
					System.out.println("Save map now.");
					fileChosen = fileChooser.showSaveDialog(null);
					if (fileChosen == JFileChooser.APPROVE_OPTION) {
						saveFileName = fileChooser.getSelectedFile().getPath();
						UtecDataManager.getCurrentMapData().writeMapToFile(saveFileName);

					}
				} else {
					System.out.println("Map is null.");
				}
			}

			else if (cmd.equals("Load Map #1")) {
				System.out.println("Starting to get map 1");
				UtecInterface.pullMapData(1);
			}

			else if (cmd.equals("Load Map #2")) {
				System.out.println("Starting to get map 2");
				UtecInterface.pullMapData(2);
			}

			else if (cmd.equals("Load Map #3")) {
				System.out.println("Starting to get map 3");
				UtecInterface.pullMapData(3);
			}

			else if (cmd.equals("Load Map #4")) {
				System.out.println("Starting to get map 4");
				UtecInterface.pullMapData(4);
			}

			else if (cmd.equals("Load Map #5")) {
				System.out.println("Starting to get map 5");
				UtecInterface.pullMapData(5);
			}

			else if (cmd.equals("Load Map File")) {
				System.out.println("Load Map From File");

				String saveFileName = null;
				fileChosen = fileChooser.showSaveDialog(null);
				if (fileChosen == JFileChooser.APPROVE_OPTION) {
					saveFileName = fileChooser.getSelectedFile().getPath();
					UtecMapData mapData = new UtecMapData(saveFileName);
					UtecDataManager.setCurrentMap(mapData);
				}
			}
			
			else if (cmd.equals("Save To Map #1")) {
				System.out.println("Starting to save map #1");
				UtecInterface.sendMapData(1);
			}

			else if (cmd.equals("Save To Map #2")) {
				System.out.println("Starting to save map #2");
				UtecInterface.sendMapData(2);
			}

			else if (cmd.equals("Save To Map #3")) {
				System.out.println("Starting to save map #3");
				UtecInterface.sendMapData(3);
			}

			else if (cmd.equals("Save To Map #4")) {
				System.out.println("Starting to save map #4");
				UtecInterface.sendMapData(4);
			}

			else if (cmd.equals("Save To Map #5")) {
				System.out.println("Starting to save map #5");
				UtecInterface.sendMapData(5);
			}
			else if (cmd.equals("Exit")) {
				// Use interface to finally close the connection to the Utec
				UtecInterface.closeConnection();
				System.out.println("Exit action occuring");

				// Close out the application
				System.exit(0);
			}
			
			else if(cmd.equals("Force Utec Reset")){
				System.out.println("Resetting the Utec");
				UtecInterface.resetUtec();
			}
			
			else if(cmd.equals("Start Logging")){
				System.out.println("Kicking off the logging.");
				UtecInterface.startLoggerDataFlow();
			}
			
			else if(cmd.equals("Close Port")){
				System.out.println("Closing access to the currently opened port (if any).");
				UtecInterface.closeConnection();
			}
			

			// Only non explicitly defined actions are those generated by ports.
			// Since an arbitrary machine could have any number of serial ports
			// its impossible to hard code choices based on menu items generated
			// on the fly.
			// Must pull the calling object and interrogate
			else {
				JMenuItem theItem = (JMenuItem) e.getSource();
				String portChoice = theItem.getName();
				System.out.println("Port chosen: " + portChoice);
				currentPort = portChoice;
				UtecInterface.setPortChoice(currentPort);
				UtecInterface.openConnection();
			}
	}
}
