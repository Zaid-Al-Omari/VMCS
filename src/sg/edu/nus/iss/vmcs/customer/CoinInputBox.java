/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;

import sg.edu.nus.iss.vmcs.store.MoneyStore;
import sg.edu.nus.iss.vmcs.store.CoinStoreItem;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.store.StoreObject;

/**
 * This interface object is part of the Customer Panel&#46; It is used to enter
 * Coins into the vending machine.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
@SuppressWarnings("serial")
public class CoinInputBox extends Panel{
	private CoinButton[] btnCoinButton;
	
	/**
	 * This constructor creates an instance of the object.
	 * @param cctrl the TransactionController.
	 */
	public CoinInputBox(MoneyStore store, CoinReceiver coinReceiver){
		int cashStoreSize= store.getStoreSize();
		StoreItem[] cashStoreItems= store.getItems();
		
		btnCoinButton=new CoinButton[cashStoreSize+1];
		CoinInputListener coinInputListener=new CoinInputListener(coinReceiver);
		
		setLayout(new GridBagLayout());
		for(int i=0;i<cashStoreItems.length;i++){
			StoreItem storeItem=cashStoreItems[i];
			CoinStoreItem cashStoreItem=(CoinStoreItem)storeItem;
			StoreObject storeObject=cashStoreItem.getContent();
			Coin coin=(Coin)storeObject;
			String coinName=coin.getName();
			int coinValue=coin.getValue();
			btnCoinButton[i]=new CoinButton(coinName,coinValue,coin.getAttributes());
			btnCoinButton[i].addActionListener(coinInputListener);
			add(btnCoinButton[i],new GridBagConstraints(i,1,1,1,1.0,0.0,
				    GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,
				    new Insets(0,0,0,0),10,8));
		}
		btnCoinButton[cashStoreSize]=new CoinButton("Invalid",-1,MoneyStore.INVALID_COIN_WEIGHT);
		btnCoinButton[cashStoreSize].addActionListener(coinInputListener);
		add(btnCoinButton[cashStoreSize],new GridBagConstraints(cashStoreSize,1,1,1,1.0,0.0,
			    GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,
			    new Insets(0,0,0,0),10,8));
	}
	
	/**
	 * This method activates the Coin Input Box if the parameter is TRUE&#46;
	 * Otherwise, the Coin Input Box is deactivated.
	 * @param active TRUE to activate the CoinInputBox, otherwise, deactivate the CoinInputBox.
	 */
	public void setActive(boolean active){
		if(btnCoinButton!=null){
			for(int i=0;i<btnCoinButton.length;i++){
				btnCoinButton[i].setEnabled(active);
			}
		}
	}
}//CoinInputBox