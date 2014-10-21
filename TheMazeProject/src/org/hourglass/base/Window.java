package org.hourglass.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

public class Window
{

	private JFrame frmMazeGenerator;
	private JTextField txtFWidth;
	private JTextField txtFHeight;
	private JTextField txtFBlockS;
	private JTextField txtFSeed;

	/**
	 * Launch the application.
	 */
	public static void createWindow()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Window window = new Window();
					window.frmMazeGenerator.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmMazeGenerator = new JFrame();
		frmMazeGenerator.setResizable(false);
		frmMazeGenerator.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Calle\\DropboxH\\Dropbox\\Snake - Spel\\Resurser\\icon2.ico"));
		frmMazeGenerator.setTitle("Maze Generator");
		frmMazeGenerator.setBounds(100, 100, 231, 285);
		frmMazeGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMazeGenerator.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Maze Generator Parameters");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 205, 20);
		frmMazeGenerator.getContentPane().add(lblTitle);
		
		txtFWidth = new JTextField();
		txtFWidth.setText("60");
		txtFWidth.setBounds(90, 42, 125, 20);
		frmMazeGenerator.getContentPane().add(txtFWidth);
		txtFWidth.setColumns(10);
		
		txtFHeight = new JTextField();
		txtFHeight.setText("50");
		txtFHeight.setColumns(10);
		txtFHeight.setBounds(90, 73, 125, 20);
		frmMazeGenerator.getContentPane().add(txtFHeight);
		
		txtFBlockS = new JTextField();
		txtFBlockS.setText("8");
		txtFBlockS.setToolTipText("");
		txtFBlockS.setColumns(10);
		txtFBlockS.setBounds(90, 104, 125, 20);
		frmMazeGenerator.getContentPane().add(txtFBlockS);
		
		txtFSeed = new JTextField();
		txtFSeed.setToolTipText("Leave empty for random seed");
		txtFSeed.setColumns(10);
		txtFSeed.setBounds(90, 135, 125, 20);
		frmMazeGenerator.getContentPane().add(txtFSeed);
		
		JLabel lblWidth = new JLabel("Grid width:");
		lblWidth.setBounds(10, 45, 70, 14);
		frmMazeGenerator.getContentPane().add(lblWidth);
		
		JLabel lblHeight = new JLabel("Grid height:");
		lblHeight.setBounds(10, 76, 70, 14);
		frmMazeGenerator.getContentPane().add(lblHeight);
		
		JLabel lblBlockS = new JLabel("Block size:");
		lblBlockS.setBounds(10, 107, 70, 14);
		frmMazeGenerator.getContentPane().add(lblBlockS);
		
		JLabel lblSeed = new JLabel("Seed:");
		lblSeed.setBounds(10, 138, 70, 14);
		frmMazeGenerator.getContentPane().add(lblSeed);
		
		final JCheckBox chckbxDebugMode = new JCheckBox("Debug Mode");
		chckbxDebugMode.setBounds(10, 167, 205, 23);
		frmMazeGenerator.getContentPane().add(chckbxDebugMode);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new Core(Integer.valueOf(txtFWidth.getText()), Integer.valueOf(txtFHeight.getText()), Integer.valueOf(txtFBlockS.getText()), Integer.valueOf(txtFSeed.getText()), chckbxDebugMode.isSelected());
			}
		});
		btnGenerate.setBounds(10, 197, 205, 49);
		frmMazeGenerator.getContentPane().add(btnGenerate);
		
		
	}
	
	public void close()
	{
		frmMazeGenerator.dispose();
	}
}
