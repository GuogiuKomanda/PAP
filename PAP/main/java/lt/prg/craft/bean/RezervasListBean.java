package lt.prg.craft.bean;

import java.util.ArrayList;

import lt.prg.craft.model.RezervoEilute;

public class RezervasListBean {
	
	private ArrayList<RezervoEilute> gautos = new ArrayList<RezervoEilute>();
	private ArrayList<RezervoEilute> uzsakytos = new ArrayList<RezervoEilute>();
	public ArrayList<RezervoEilute> getGautos()
	{
		return gautos;
	}
	public void setGautos(ArrayList<RezervoEilute> gautos)
	{
		this.gautos = gautos;
	}
	public ArrayList<RezervoEilute> getUzsakytos()
	{
		return uzsakytos;
	}
	public void setUzsakytos(ArrayList<RezervoEilute> uzsakytos)
	{
		this.uzsakytos = uzsakytos;
	}
	
	
	
}
