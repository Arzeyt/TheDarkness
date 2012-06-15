package us.twoguys.thedarkness.GUI;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainListSelectionListener extends TheDarknessGUI implements ListSelectionListener {

	TheDarknessGUI instance;
	
	public MainListSelectionListener(TheDarknessGUI instance){
		this.instance = instance;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e){
		
		if(instance.mainList.getSelectedIndex() == 0){
			subCategoryList = new JList(this.effects);
			
		}else if(instance.mainList.getSelectedIndex() == 1){
			subCategoryList = new JList(this.mobs);
			
		}else if(instance.mainList.getSelectedIndex()==2){
			subCategoryList = new JList(this.mirages);
		}
		
		//subCategoryList.setLocation(160, 30);
		//subCategoryList.setSize(150, 50);
		subCategoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		subCategoryList.setLayoutOrientation(JList.VERTICAL);
		subCategoryList.addListSelectionListener(new SubListSelectionListener());
		
		try{
			instance.selectPanel.remove(instance.subScrollPane);
		}catch(Exception e1){}
		
	    instance.subScrollPane = new JScrollPane(subCategoryList);
	    instance.subScrollPane.setLocation(160,30);
	    instance.subScrollPane.setSize(150, 100);
	    instance.selectPanel.add(instance.subScrollPane);
	    
		/*
		JScrollPane subScrollPane = new JScrollPane(subCategoryList);
		subScrollPane.setLocation(160,30);
		subScrollPane.setSize(150,100);
		*/
		
	    instance.selectPanel.updateUI();
	
	}

}
