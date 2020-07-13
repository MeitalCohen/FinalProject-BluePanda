package tot12; /**
 * Copyright (c) 2020, MindFusion LLC - Bulgaria.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;

import javax.swing.*;
import javax.swing.border.*;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.Appointment;
import com.mindfusion.scheduling.model.Item;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(504, 650);
		setTitle("MindFusion.Scheduling Sample: Drag & Drop");
		setInfo("This sample demonstrates how.<ul><li>ItemListView features work. Drag any of " +
			"the icons below over the control area in order to create new events of the " +
			"respective type.</li></ul>");
		
		createUI();
	}

	private void createUI()
	{
		JPanel pnlRight = new JPanel();
		pnlRight.setLayout(null);
		
		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.WEST, calendar, 0, SpringLayout.WEST, content);
		layout.putConstraint(SpringLayout.NORTH, calendar, 0, SpringLayout.NORTH, content);
		layout.putConstraint(SpringLayout.EAST, calendar, -120, SpringLayout.EAST, content);
		layout.putConstraint(SpringLayout.SOUTH, calendar, 0, SpringLayout.SOUTH, content);
		
		layout.putConstraint(SpringLayout.WEST, pnlRight, 0, SpringLayout.EAST, calendar);
		layout.putConstraint(SpringLayout.NORTH, pnlRight, 0, SpringLayout.NORTH, content);
		layout.putConstraint(SpringLayout.EAST, pnlRight, 0, SpringLayout.EAST, content);
		layout.putConstraint(SpringLayout.SOUTH, pnlRight, 0, SpringLayout.SOUTH, content);
		
		content.setLayout(layout);
		content.add(calendar);
		content.add(pnlRight);
		
		calendar.setCurrentView(CalendarView.Timetable);
		calendar.setTheme(ThemeType.Windows2003);
		calendar.setCustomDraw(EnumSet.of(CustomDrawElements.TimetableTimeline));

		// set calendar drag feedback type
		calendar.setDragFeedBackType(DragFeedBackType.GhostImage);

		// Setup the dates displayed in the Schedule view
		DateTime today = DateTime.today();

		calendar.getTimetableSettings().getDates().clear();
		for (int i = 0; i < 5; i++)
			calendar.getTimetableSettings().getDates().add(today.addDays(i));

		// Set the visible columns to 3
		calendar.getTimetableSettings().setVisibleColumns(3);
		
		JPanel pnlRTop = new JPanel();
		pnlRTop.setBounds(0, 0, 120, 80);
		pnlRTop.setBorder(new TitledBorder("Feedback type"));
		pnlRTop.setLayout(null);
		
		ButtonGroup group = new ButtonGroup();
		
		btnMethod1 = new JRadioButton("GhostImage1");
		btnMethod1.setBounds(10, 20, 100, 25);
		btnMethod1.setSelected(true);
		btnMethod2 = new JRadioButton("InsertionLine");
		btnMethod2.setBounds(10, 45, 100, 25);
		
		pnlRTop.add(btnMethod1);
		pnlRTop.add(btnMethod2);
		group.add(btnMethod1);
		group.add(btnMethod2);
		pnlRTop.setBounds(0, 0, 120, 80);


		btnMethod1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnMethod2.setSelected(false);
				calendar.setDragFeedBackType(DragFeedBackType.GhostImage);
			}
		});

		btnMethod2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnMethod1.setSelected(false);
				calendar.setDragFeedBackType(DragFeedBackType.InsertionLine);
			}
		});
		
		JPanel pnlBtm = new JPanel();
		pnlBtm.setLayout(null);
		pnlBtm.setBounds(5, 80, 110, 360);
		
		pnlRight.add(pnlRTop);
		pnlRight.add(pnlBtm);

		Appointment appointment = new Appointment();
		//appointment.getStyle().setImage(createImageIcon("Resources/appointment.gif", "Appointment").getImage());
		appointment.setHeaderText("Appointment");


		Appointment task = new Appointment();
		//task.getStyle().setImage(createImageIcon("Resources/task.gif", "Task").getImage());
		task.setHeaderText("Task");


		Appointment free = new Appointment();
		//free.getStyle().setImage(createImageIcon("Resources/free.gif", "Task").getImage());
		free.setHeaderText("Free");


		Appointment event = new Appointment();
		//event.getStyle().setImage(createImageIcon("Resources/event.gif", "Event").getImage());
		event.setHeaderText("Event");


		//create itemlistview
		ItemListView list = new ItemListView();
		list.setBounds(0, 0, 110, 360);
		list.setItemSize(new Dimension(50,50));
		list.setIconMargin(new Dimension(10,10));

		// add items to itemlistview
		list.addItems(new Item[]{appointment, task, free, event});


		pnlBtm.add(list);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path, String description)
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null)
	    {
	        return new ImageIcon(imgURL, description);
	    }
	    else
	    {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	
	boolean getMethod1()
	{
		return btnMethod1.isSelected();
	}
		
	private static final long serialVersionUID = 1L;

	private JRadioButton btnMethod1;
	private JRadioButton btnMethod2;
}

