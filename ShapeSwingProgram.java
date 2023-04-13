import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.event.MenuKeyEvent;

public class ShapeSwingProgram extends JFrame implements ActionListener {
    
    
    protected PaintPanel paintPanel = new PaintPanel();

    public ShapeSwingProgram(String title) {
        super(title);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JLabel widthPrompt = new JLabel("Width:");
        buttonPanel.add(widthPrompt);
        JTextField width = new JTextField();
        width.setColumns(5);
        width.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintPanel.currentWidth = Integer.parseInt(width.getText());
			}
        });
        buttonPanel.add(width);
        JLabel heightPrompt = new JLabel("Height:");
        buttonPanel.add(heightPrompt);
        JTextField height = new JTextField();
        height.setColumns(5);
        height.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintPanel.currentHeight = Integer.parseInt(height.getText());
			}
        });
        buttonPanel.add(height);
        this.add(buttonPanel, BorderLayout.PAGE_START);
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("File");
        menu.setMnemonic(MenuKeyEvent.VK_F);
        menuBar.add(menu);

        //a group of file-related menu items 
        menuItem = new JMenuItem("Open", MenuKeyEvent.VK_O);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Save", MenuKeyEvent.VK_S);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Save As...", MenuKeyEvent.VK_A);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Exit", MenuKeyEvent.VK_X);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Build our shape menu
        menu = new JMenu("Shape");
        ButtonGroup shapeGroup = new ButtonGroup();
        menuItem = new JRadioButtonMenuItem("Rectangle");
        menuItem.addActionListener(this);
        shapeGroup.add(menuItem);
        menu.add(menuItem);
        menuItem = new JRadioButtonMenuItem("Square");
        menuItem.addActionListener(this);
        shapeGroup.add(menuItem);
        menu.add(menuItem);
        menuItem = new JRadioButtonMenuItem("Oval");
        menuItem.addActionListener(this);
        shapeGroup.add(menuItem);
        menu.add(menuItem);
        menuItem = new JRadioButtonMenuItem("Circle");
        menuItem.addActionListener(this);
        shapeGroup.add(menuItem);
        menu.add(menuItem);
        menuItem = new JRadioButtonMenuItem("Triangle");
        menuItem.addActionListener(this);
        shapeGroup.add(menuItem);
        menu.add(menuItem);

        // Create the color submenu
        menu.addSeparator();        
        submenu = new JMenu("Color");

        // create a group so you can only select one color at a time
        ButtonGroup colorGroup = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("Red");
        rbMenuItem.addActionListener(this);
        rbMenuItem.setSelected(true);
        colorGroup.add(rbMenuItem);
        submenu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Yellow");
        rbMenuItem.addActionListener(this);
        colorGroup.add(rbMenuItem);
        submenu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Green");
        rbMenuItem.addActionListener(this);
        colorGroup.add(rbMenuItem);
        submenu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Black");
        rbMenuItem.addActionListener(this);
        colorGroup.add(rbMenuItem);
        submenu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("White");
        rbMenuItem.addActionListener(this);
        colorGroup.add(rbMenuItem);
        submenu.add(rbMenuItem);

        // Turn submenu into an actual submenu by adding it to another menu (specifically, our second menu)
        menu.add(submenu);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        paintPanel.setPreferredSize(new Dimension(500,500));
        paintPanel.setBackground(Color.WHITE);
        this.getContentPane().add(paintPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        ShapeSwingProgram paintprogram = new ShapeSwingProgram("Shape Dropper Paint Program");
        paintprogram.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand()=="Rectangle")
            paintPanel.currentShape = PaintPanel.ShapeType.RECTANGLE;
        else if (e.getActionCommand()=="Square")
            paintPanel.currentShape = PaintPanel.ShapeType.SQUARE;
        else if (e.getActionCommand() == "Oval")
        	paintPanel.currentShape = PaintPanel.ShapeType.OVAL;
        else if (e.getActionCommand() == "Circle")
        	paintPanel.currentShape = PaintPanel.ShapeType.CIRCLE;
        else if (e.getActionCommand() == "Triangle")
        	paintPanel.currentShape = PaintPanel.ShapeType.TRIANGLE;
        else if (e.getActionCommand() == "Red")
        	paintPanel.currentColor = "#ff0000";
        else if (e.getActionCommand() == "Yellow")
        	paintPanel.currentColor = "#ffff00";
        else if (e.getActionCommand() == "Green")
        	paintPanel.currentColor = "#00ff00";
        else if (e.getActionCommand() == "Black")
        	paintPanel.currentColor = "#000000";
        else if (e.getActionCommand() == "White")
        	paintPanel.currentColor = "#ffffff";
        else if (e.getActionCommand() == "Open")
        {
        	JFileChooser fileChooser = new JFileChooser();
        	fileChooser.setCurrentDirectory(new File("."));
        	int response = fileChooser.showOpenDialog(null);
        	if(response == JFileChooser.APPROVE_OPTION)
        	{
        		paintPanel.open(fileChooser.getSelectedFile().getAbsolutePath());
        	}
        }
        else if (e.getActionCommand() == "Save As...")
        {
        	JFileChooser fileChooser = new JFileChooser();
        	fileChooser.setCurrentDirectory(new File("."));
        	int response = fileChooser.showSaveDialog(null);
        	if(response == JFileChooser.APPROVE_OPTION)
        	{
        		paintPanel.saveAs(fileChooser.getSelectedFile().getAbsolutePath());
        	}
        }
        else if (e.getActionCommand() == "Save")
        	paintPanel.save();
        else if (e.getActionCommand() == "Exit")
        	this.dispose();
    }
}