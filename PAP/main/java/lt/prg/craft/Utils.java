package lt.prg.craft;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lt.prg.craft.model.Subjektas;
import lt.prg.craft.model.Vadybininkas;

public class Utils {

	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private static NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);

	
	public static String getDateString(Date date){
		if(date == null)
			return "";
		return df.format(date);
	}
	
	public static String getVadybininkasString(Vadybininkas vadybininkas) {
		if (vadybininkas == null)
			return "";
		return vadybininkas.getPavarde() + " " + vadybininkas.getVardas();
	}
	
	public static String getSubjektasString(Subjektas subjektas) {
		if (subjektas == null)
			return "";
		return subjektas.getPavadinimas();
	}
	
	public static String formatDouble2(Double number){
		if(number == null)
			return "";
		return nf.format(number);
	}
	
	public static String formatDouble3(Double number){
		if(number == null)
			return "";
		return nf.format(number);
	}
}
