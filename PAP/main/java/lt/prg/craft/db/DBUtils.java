package lt.prg.craft.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lt.prg.craft.model.Preke;
import lt.prg.craft.model.Saskaita;
import lt.prg.craft.model.SaskaitosEilute;
import lt.prg.craft.model.Subjektas;
import lt.prg.craft.model.Uzsakymas;
import lt.prg.craft.model.UzsakymoEilute;
import lt.prg.craft.model.Vadybininkas;

public class DBUtils {

	
	private static Map<String, Preke> prekesCacheByNumeris = new HashMap<String, Preke>();
	private static Map<Long, Preke> prekesCacheById = new HashMap<Long, Preke>();
	private static Map<Long, Vadybininkas> vadybininkaiCacheById = new HashMap<Long, Vadybininkas>();
	private static Map<Long, Subjektas> subjektaiCacheById = new HashMap<Long, Subjektas>();
	
	static {
		try {
			for (Preke preke : loadPrekes()) {
				prekesCacheByNumeris.put(preke.getNumeris(), preke);
				prekesCacheById.put(preke.getId(), preke);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			for (Vadybininkas vadybininkas : loadVadybininkai()) {
				vadybininkaiCacheById.put(vadybininkas.getId(), vadybininkas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			for (Subjektas subjektas : loadSubjektai()) {
				subjektaiCacheById.put(subjektas.getId(), subjektas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	private static Connection getConnection() {
		Connection connection = null;;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/craft", "postgres", "test");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		return connection;
	}
	
	public static List<Saskaita> loadSaskaitos() throws Exception{
	
		List<Saskaita> list = new ArrayList<Saskaita>();
		
		Connection con = null;
		Statement statement = null;

		String sql = "select ID, PAVADINIMAS, DATA, SUMA, KIEKIS, TIEKEJO_ID, VADYBININKO_ID, STATUSAS from SASKAITA "+ 
						"where STATUSAS IN("+DBConstants.SASKAITA_NAUJA+","+
											 DBConstants.SASKAITA_PATVIRTINTA+") ORDER BY DATA";

		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				Long id = rs.getLong("ID");
				String pavadinimas = rs.getString("PAVADINIMAS");
				Timestamp data = rs.getTimestamp("DATA");
				Double suma = rs.getDouble("SUMA");
				Double kiekis = rs.getDouble("KIEKIS");
				Long tiekejoId = rs.getLong("TIEKEJO_ID");
				Long vadybininkoId = rs.getLong("VADYBININKO_ID");
				Integer statusas = rs.getInt("STATUSAS");
				
				Vadybininkas vadybininkas = getVadybininkas(vadybininkoId);
				Subjektas tiekejas = getSubjektas(tiekejoId);
				
				Saskaita saskaita = new Saskaita();
				saskaita.setId(id);
				saskaita.setPavadinimas(pavadinimas);
				saskaita.setData(data);
				saskaita.setSuma(suma);
				saskaita.setKiekis(kiekis);
				
				saskaita.setPardavimoVadybininkas(vadybininkas);
				saskaita.setTiekejas(tiekejas);
				
				saskaita.setStatusas(statusas);
				
				list.add(saskaita);
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return list;
	}
	
	public static List<Uzsakymas> loadUzsakymai() throws Exception{
		
		List<Uzsakymas> list = new ArrayList<Uzsakymas>();
		
		Connection con = null;
		Statement statement = null;

		String sql = "select ID, PAVADINIMAS, UZSAKYMO_DATA, ATVYKIMO_DATA, TIEKEJO_ID, VADYBININKO_ID, SUMA, NETTO, STATUSAS from UZSAKYMAS "
				+ "where STATUSAS IN("+DBConstants.UZSAKYMAS_NAUJAS+","+
									   DBConstants.UZSAKYMAS_PATVIRTINTAS+","+
									   DBConstants.UZSAKYMAS_IVYKDYTAS+") ORDER BY UZSAKYMO_DATA";

		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				Long id = rs.getLong("ID");
				String pavadinimas = rs.getString("PAVADINIMAS");
				Timestamp uzsakymoData = rs.getTimestamp("UZSAKYMO_DATA");
				Timestamp atvykimoData = rs.getTimestamp("ATVYKIMO_DATA");
				Long tiekejoId = rs.getLong("TIEKEJO_ID");
				Long vadybininkoId = rs.getLong("VADYBININKO_ID");
				Double suma = rs.getDouble("SUMA");
				Double netto = rs.getDouble("NETTO");
				Integer statusas = rs.getInt("STATUSAS");
				
				Vadybininkas vadybininkas = getVadybininkas(vadybininkoId);
				Subjektas tiekejas = getSubjektas(tiekejoId);
				
				Uzsakymas uzsakymas = new Uzsakymas();
				uzsakymas.setId(id);
				uzsakymas.setPavadinimas(pavadinimas);
				uzsakymas.setUzsakymoData(uzsakymoData);
				uzsakymas.setAtvykimoData(atvykimoData);
				uzsakymas.setSuma(suma);
				uzsakymas.setNetto(netto);
				
				uzsakymas.setPirkimoVadybininkas(vadybininkas);
				uzsakymas.setTiekejas(tiekejas);
				
				uzsakymas.setStatusas(statusas);
				
				list.add(uzsakymas);
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return list;
	}
	
	public static List<SaskaitosEilute> loadSaskaitosEilutes(Long saskaitosId) throws Exception{
		List<SaskaitosEilute> list = new ArrayList<SaskaitosEilute>();
		
		Connection con = null;
		Statement statement = null;

		String sql = "select SE.ID, SE.KAINA, SE.KIEKIS, U.PAVADINIMAS as \"UZSAKYMO_PAVADINIMAS\", SE.PREKES_ID, SE.UZSAKYMO_ID, SE.STATUSAS "
				+ "from SASKAITOS_EILUTE as SE "
					+ "left outer join UZSAKYMAS as U on SE.UZSAKYMO_ID=U.ID "
				+ "where SASKAITOS_ID="+saskaitosId+ " and SE.STATUSAS !="+DBConstants.SASKAITOS_EILUTE_ISTRINTA;

		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				Long id = rs.getLong("ID");
				
				Double kaina = rs.getDouble("KAINA");
				Double kiekis = rs.getDouble("KIEKIS");
							
				String uzsakymoNumeris = rs.getString("UZSAKYMO_PAVADINIMAS");
				Long uzsakymoId = rs.getLong("UZSAKYMO_ID");
				Long prekesId = rs.getLong("PREKES_ID");
				Integer statusas = rs.getInt("STATUSAS");
				
				//TODO move to dbcache?
				Preke preke = getPreke(prekesId);
				
				SaskaitosEilute saskaitosEilute = new SaskaitosEilute();
				saskaitosEilute.setId(id);
				saskaitosEilute.setSaskaitosId(saskaitosId);
				saskaitosEilute.setUzsakymoId(uzsakymoId);
				saskaitosEilute.setKiekis(kiekis);
				saskaitosEilute.setUzsakymoNumeris(uzsakymoNumeris);
				saskaitosEilute.setPrekesNumeris(preke.getNumeris());
				saskaitosEilute.setPreke(preke);
				
				saskaitosEilute.setKaina(kaina);
				saskaitosEilute.setStatusas(statusas);
				
				list.add(saskaitosEilute);
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return list;
	}
	
		public static List<UzsakymoEilute> loadUzsakymoEilutes(Long uzsakymoId) throws Exception{
		List<UzsakymoEilute> list = new ArrayList<UzsakymoEilute>();
		
		Connection con = null;
		Statement statement = null;

		String sql = "select UE.ID, U.PAVADINIMAS as \"UZSAKYMO_PAVADINIMAS\", UE.PREKES_ID, UE.KIEKIS, UE.KAINA, UE.SUMA, UE.NETTO, UE.VADYBININKO_ID, UE.KLIENTO_ID, UE.STATUSAS "
				+ "from UZSAKYMO_EILUTE as UE "
					+ "left outer join UZSAKYMAS as U on UE.UZSAKYMO_ID=U.ID "
					+ "WHERE UE.UZSAKYMO_ID="+uzsakymoId + " and UE.STATUSAS !="+DBConstants.UZSAKYMO_EILUTE_ISTRINTA;

		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				Long id = rs.getLong("ID");

				Double kaina = rs.getDouble("KAINA");
				Double kiekis = rs.getDouble("KIEKIS");
				Double suma = rs.getDouble("SUMA");
				Double netto = rs.getDouble("NETTO");
				Long vadybininkoId = rs.getLong("VADYBININKO_ID");
				Long klientoId = rs.getLong("KLIENTO_ID");				
				String uzsakymoNumeris = rs.getString("UZSAKYMO_PAVADINIMAS");
				
				Long prekesId = rs.getLong("PREKES_ID");
				
				Integer statusas = rs.getInt("STATUSAS");
				
				
				Preke preke = getPreke( prekesId);
				Vadybininkas vadybininkas = getVadybininkas(vadybininkoId);
				Subjektas klientas = getSubjektas(klientoId);
				
				UzsakymoEilute uzsakymoEilute = new UzsakymoEilute();
				uzsakymoEilute.setId(id);
				uzsakymoEilute.setUzsakymoId(uzsakymoId);
				uzsakymoEilute.setPreke(preke);
				uzsakymoEilute.setKaina(kaina);
				uzsakymoEilute.setKiekis(kiekis);
				uzsakymoEilute.setUzsakymoNumeris(uzsakymoNumeris);
				uzsakymoEilute.setPrekesNumeris(preke.getNumeris());
				uzsakymoEilute.setKlientas(klientas);
				uzsakymoEilute.setPardavimoVadybininkas(vadybininkas);
				uzsakymoEilute.setSuma(suma);
				uzsakymoEilute.setNetto(netto);
				uzsakymoEilute.setStatusas(statusas);
				list.add(uzsakymoEilute);
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return list;
	}
	
	public static List<String> getPrekesNumerisList(String uzsakymoNumeris) throws Exception{
		List<String> list = new ArrayList<String>();
		
		Connection con = null;
		Statement statement = null;

		String sql = "select distinct P.NUMERIS "
				+ "from UZSAKYMAS as U "
					+ "left outer join UZSAKYMO_EILUTE as UE on UE.UZSAKYMO_ID= U.ID "
					+ "left outer join PREKE as P on UE.PREKES_ID= P.ID "
				+ "where U.PAVADINIMAS='"+uzsakymoNumeris+"'"; //where STATUS="+OPEN;

		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				String prekesNumeris = rs.getString("NUMERIS");
				
				list.add(prekesNumeris);
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return list;
	}

	public static List<String> getUzsakymoNumerisList() throws Exception{
		List<String> list = new ArrayList<String>();
		
		Connection con = null;
		Statement statement = null;

		String sql = "select PAVADINIMAS from UZSAKYMAS where STATUSAS ="+DBConstants.UZSAKYMAS_PATVIRTINTAS; //where STATUS="+OPEN;

		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				String uzsakymoNumeris = rs.getString("PAVADINIMAS");
				
				list.add(uzsakymoNumeris);
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return list;
	}
	
	public static Uzsakymas loadUzsakymas(String uzsakymoNumeris) throws Exception{
		
		Uzsakymas uzsakymas = null;
		
		Connection con = null;
		Statement statement = null;

		String sql = "select ID, UZSAKYMO_DATA, ATVYKIMO_DATA, TIEKEJO_ID, VADYBININKO_ID, SUMA, NETTO, STATUSAS from UZSAKYMAS "
				+ "where STATUSAS IN("+DBConstants.UZSAKYMAS_NAUJAS+","+
									   DBConstants.UZSAKYMAS_PATVIRTINTAS+","+
									   DBConstants.UZSAKYMAS_IVYKDYTAS+") and PAVADINIMAS ='"+uzsakymoNumeris+"'";

		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				Long id = rs.getLong("ID");
				Timestamp uzsakymoData = rs.getTimestamp("UZSAKYMO_DATA");
				Timestamp atvykimoData = rs.getTimestamp("ATVYKIMO_DATA");
				Long tiekejoId = rs.getLong("TIEKEJO_ID");
				Long vadybininkoId = rs.getLong("VADYBININKO_ID");
				Double suma = rs.getDouble("SUMA");
				Double netto = rs.getDouble("NETTO");
				Integer statusas = rs.getInt("STATUSAS");
				
				Vadybininkas vadybininkas = getVadybininkas(vadybininkoId);
				Subjektas tiekejas = getSubjektas(tiekejoId);
				
				uzsakymas = new Uzsakymas();
				uzsakymas.setId(id);
				uzsakymas.setPavadinimas(uzsakymoNumeris);
				uzsakymas.setUzsakymoData(uzsakymoData);
				uzsakymas.setAtvykimoData(atvykimoData);
				uzsakymas.setSuma(suma);
				uzsakymas.setNetto(netto);
				
				uzsakymas.setPirkimoVadybininkas(vadybininkas);
				uzsakymas.setTiekejas(tiekejas);
				
				uzsakymas.setStatusas(statusas);
				break;
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return uzsakymas;
	}
	
	public static Double getPrekesKaina(Long uzsakymoId, Long prekesId) throws Exception{
		Double kaina = null;
		
		Connection con = null;
		Statement statement = null;

		String sql = "select KAINA from UZSAKYMO_EILUTE "
					+ "WHERE UZSAKYMO_ID="+uzsakymoId + " and "
						   + "PREKES_ID="+prekesId + " and "
						   + "STATUSAS !="+DBConstants.UZSAKYMO_EILUTE_ISTRINTA;

		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				kaina = rs.getDouble("KAINA");
				break;
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return kaina;
	}
	
	public static Preke getPreke(String prekesNumeris){
		return prekesCacheByNumeris.get(prekesNumeris);
	}
	
	public static Preke getPreke(Long prekesId){
		return prekesCacheById.get(prekesId);
	}
	
	public static Vadybininkas getVadybininkas(Long vadybininkoId){
		return vadybininkaiCacheById.get(vadybininkoId);
	}
	
	public static Subjektas getSubjektas(Long subjektoId){
		return subjektaiCacheById.get(subjektoId);
	}
	
	public static List<Vadybininkas> getVadybininkasListByTipas(Integer tipas){
		List<Vadybininkas> vadybininkai = new ArrayList<Vadybininkas>();
		for(Vadybininkas vadybininkas : vadybininkaiCacheById.values()){
			if(vadybininkas.getTipas().equals(tipas)){
				vadybininkai.add(vadybininkas);
			}
		}
		return vadybininkai;
	}
	
	public static List<Subjektas> getSubjektasList(){
		return new ArrayList<Subjektas>(subjektaiCacheById.values());
	}
	
	public static List<Vadybininkas> getVadybininkasList(){
		return new ArrayList<Vadybininkas>(vadybininkaiCacheById.values());
	}
	
	public static List<Subjektas> getSubjektasListByTipas(Integer tipas){
		List<Subjektas> subjektai = new ArrayList<Subjektas>();
		for(Subjektas subjektas : subjektaiCacheById.values()){
			if(subjektas.getTipas().equals(tipas)){
				subjektai.add(subjektas);
			}
		}
		return subjektai;
	}
	
	public static List<String> getPrekesNumerisList(){
		return new ArrayList<String>(prekesCacheByNumeris.keySet());
	}
	
	public static List<Preke> getPrekes(){
		return new ArrayList<Preke>(prekesCacheByNumeris.values());
	}
	
	private static Preke loadPreke(String prekesNumeris) throws Exception{
		
		Preke preke = null;
		
		Connection con = null;
		Statement statement = null;

		String sql = "select ID, PAVADINIMAS, SVORIS, KAINA, GRUPES_PAVADINIMAS from PREKE where NUMERIS='"+prekesNumeris+"'";

		/*String sql = "select P.ID, P.PAVADINIMAS, P.SVORIS, UE.KAINA, P.GRUPES_PAVADINIMAS "
				+ "from UZSAKYMAS as U "
					+ "left outer join UZSAKYMO_EILUTE as UE on UE.UZSAKYMO_ID= U.ID "
					+ "left outer join PREKE as P on UE.PREKES_ID= P.ID "
				+ "where U.PAVADINIMAS='"+uzsakymoNumeris+"' and P.NUMERIS='"+prekesNumeris+"'" ;*/
		
		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				
				Long id = rs.getLong("ID");
				String pavadinimas = rs.getString("PAVADINIMAS");
				Double svoris = rs.getDouble("SVORIS");
				Double kaina = rs.getDouble("KAINA");
				String grupesPavadinimas = rs.getString("GRUPES_PAVADINIMAS");
				
				preke = new Preke();
				preke.setId(id);
				preke.setNumeris(prekesNumeris);
				preke.setPavadinimas(pavadinimas);				
				preke.setSvoris(svoris);
				preke.setGrupesPavadinimas(grupesPavadinimas);
				break;
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return preke;
	}
	
	private static List<Preke> loadPrekes() throws Exception{
		
		List<Preke> prekes = new ArrayList<Preke>();
		
		Connection con = null;
		Statement statement = null;

		String sql = "select ID, NUMERIS, PAVADINIMAS, SVORIS, GRUPES_PAVADINIMAS from PREKE";

		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				
				Long id = rs.getLong("ID");
				String prekesNumeris = rs.getString("NUMERIS");
				String pavadinimas = rs.getString("PAVADINIMAS");
				Double svoris = rs.getDouble("SVORIS");
				String grupesPavadinimas = rs.getString("GRUPES_PAVADINIMAS");
				
				Preke preke = new Preke();
				preke.setId(id);
				preke.setNumeris(prekesNumeris);
				preke.setPavadinimas(pavadinimas);				
				preke.setSvoris(svoris);
				preke.setGrupesPavadinimas(grupesPavadinimas);
				
				prekes.add(preke);
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return prekes;
	}
	
	private static List<Vadybininkas> loadVadybininkai() throws Exception{
		
		List<Vadybininkas> vadybininkai = new ArrayList<Vadybininkas>();
		
		Connection con = null;
		Statement statement = null;

		String sql = "select ID, VARDAS, PAVARDE, TIPAS from VADYBININKAS";

		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				
				Long id = rs.getLong("ID");
				String vardas = rs.getString("VARDAS");
				String pavarde = rs.getString("PAVARDE");
				Integer tipas = rs.getInt("TIPAS");
				
				Vadybininkas vadybininkas = new Vadybininkas();
				vadybininkas.setId(id);
				vadybininkas.setVardas(vardas);
				vadybininkas.setPavarde(pavarde);	
				vadybininkas.setTipas(tipas);
				
				vadybininkai.add(vadybininkas);
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return vadybininkai;
	}
	
	private static List<Subjektas> loadSubjektai() throws Exception{
		
		List<Subjektas> subjektai = new ArrayList<Subjektas>();
		
		Connection con = null;
		Statement statement = null;

		String sql = "select ID, PAVADINIMAS, TIPAS from SUBJEKTAS";

		try
		{
			con = getConnection();

			statement = con.createStatement();

			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()){
				
				Long id = rs.getLong("ID");
				String pavadinimas = rs.getString("PAVADINIMAS");
				Integer tipas = rs.getInt("TIPAS");
				
				Subjektas subjektas = new Subjektas();
				subjektas.setId(id);
				subjektas.setPavadinimas(pavadinimas);
				subjektas.setTipas(tipas);
				
				subjektai.add(subjektas);
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		return subjektai;
	}	
//	public static List<Saskaita> loadSaskaitos(){
//		
//		List<Saskaita> saskaitos = new ArrayList<Saskaita>();
//		
//		Saskaita s1 = new Saskaita();
//		s1.setId(1L);;
//		s1.setPavadinimas("saskaita1");
////		Uzsakymas u1 = new Uzsakymas();
////		u1.setId(1L);
////		u1.setPavadinimas("vienas");
////		
////		UzsakymoEilute p1 = new UzsakymoEilute();
////		p1.setId(1L);
////		p1.setPrekesNumeris("1");
////		u1.getEilutes().add(p1);
////		
////		uzsakymai.add(u1);
////		
////		Uzsakymas u2 = new Uzsakymas();
////		u2.setId(2L);
////		u2.setPavadinimas("kitas");
////		
////		UzsakymoEilute p2 = new UzsakymoEilute();
////		p2.setId(2L);
////		p2.setPrekesNumeris("2");
////		u2.getEilutes().add(p2);
////		
////		uzsakymai.add(u2);
//		saskaitos.add(s1);
//		
//		return saskaitos;
//	}
	
	

	
	public static void insertUzsakymas(Uzsakymas uzsakymas) throws Exception {
		Connection con = null;

		PreparedStatement statement = null;

		String sql = "insert into UZSAKYMAS (PAVADINIMAS, UZSAKYMO_DATA, ATVYKIMO_DATA, TIEKEJO_ID, VADYBININKO_ID, STATUSAS) values (?, ?, ?, ?, ?," +DBConstants.UZSAKYMAS_NAUJAS+");";
		try {
			con = getConnection();

			statement = con.prepareStatement(sql);
			statement.setString(1, uzsakymas.getPavadinimas());
			statement.setDate(2, new java.sql.Date(uzsakymas.getUzsakymoData().getTime()));
			statement.setDate(3, new java.sql.Date(uzsakymas.getAtvykimoData().getTime()));
			statement.setLong(4, uzsakymas.getTiekejas().getId());
			statement.setLong(5, uzsakymas.getPirkimoVadybininkas().getId());
			statement.execute();


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public static void updateUzsakymas(Uzsakymas uzsakymas) throws Exception {
		Connection con = null;

		PreparedStatement statement = null;

		String sql = "update UZSAKYMAS set PAVADINIMAS = ?, " +
										  "UZSAKYMO_DATA = ?, " +
										  "ATVYKIMO_DATA = ?, " +
										  "TIEKEJO_ID = ?, " +
										  "VADYBININKO_ID = ? "+
								"WHERE ID="+uzsakymas.getId();
		try {
			con = getConnection();

			statement = con.prepareStatement(sql);
			statement.setString(1, uzsakymas.getPavadinimas());
			statement.setDate(2, new java.sql.Date(uzsakymas.getUzsakymoData().getTime()));
			statement.setDate(3, new java.sql.Date(uzsakymas.getAtvykimoData().getTime()));
			statement.setLong(4, uzsakymas.getTiekejas().getId());
			statement.setLong(5, uzsakymas.getPirkimoVadybininkas().getId());
			statement.execute();


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public static void patvirtintiUzsakymas(Long uzsakymoId) throws Exception {
		updateUzsakymasStatusas(uzsakymoId, DBConstants.UZSAKYMAS_PATVIRTINTAS);
	}
	
	public static void deleteUzsakymas(Long uzsakymoId) throws Exception {
		updateUzsakymasStatusas(uzsakymoId, DBConstants.UZSAKYMAS_ISTRINTAS);
	}
	
	private static void updateUzsakymasStatusas(Long uzsakymoId, Integer statusas) throws SQLException{
		Connection con = null;
		Statement statement = null;

		String sql = "update UZSAKYMAS set STATUSAS = " + statusas + " where ID=" + uzsakymoId;

		try {
			con = getConnection();

			statement = con.createStatement();
			statement.execute(sql);


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static void insertUzsakymoEilute(UzsakymoEilute uzsakymoEilute) throws Exception {
		Connection con = null;

		PreparedStatement statement = null;

		String sql = "insert into UZSAKYMO_EILUTE (UZSAKYMO_ID, PREKES_ID, KIEKIS, KAINA, SUMA, NETTO, VADYBININKO_ID, KLIENTO_ID, STATUSAS) values (?, ?, ?, ?, ?, ?, ?, ?," +DBConstants.UZSAKYMO_EILUTE_NAUJA+");";
		try {
			con = getConnection();

			statement = con.prepareStatement(sql);
			statement.setLong(1, uzsakymoEilute.getUzsakymoId());
			statement.setLong(2, uzsakymoEilute.getPreke().getId());
			statement.setDouble(3, uzsakymoEilute.getKiekis());
			statement.setDouble(4, uzsakymoEilute.getKaina());
			statement.setDouble(5, uzsakymoEilute.getSuma());
			statement.setDouble(6, uzsakymoEilute.getNetto());			
			statement.setLong(7, uzsakymoEilute.getPardavimoVadybininkas().getId());
			statement.setLong(8, uzsakymoEilute.getKlientas().getId());
			statement.execute();


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public static void updateUzsakymoEilute(UzsakymoEilute uzsakymoEilute) throws Exception {
		Connection con = null;

		PreparedStatement statement = null;

		String sql = "update UZSAKYMO_EILUTE set PREKES_ID = ?, " +
										        "KIEKIS = ?, " +
										        "KAINA = ?, " +
										        "VADYBININKO_ID = ?, " +
										        "KLIENTO_ID = ?, "+
										  		"SUMA = ?, " +
										  		"NETTO = ? " +
										  "WHERE ID="+uzsakymoEilute.getId();
		try {
			con = getConnection();

			statement = con.prepareStatement(sql);
			statement.setLong(1, uzsakymoEilute.getPreke().getId());
			statement.setDouble(2, uzsakymoEilute.getKiekis());
			statement.setDouble(3, uzsakymoEilute.getKaina());
			statement.setLong(4, uzsakymoEilute.getPardavimoVadybininkas().getId());
			statement.setLong(5, uzsakymoEilute.getKlientas().getId());
			statement.setDouble(6, uzsakymoEilute.getSuma());
			statement.setDouble(7, uzsakymoEilute.getNetto());			
			statement.execute();


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public static void deleteUsakymoEilute(Long uzsakymoEilutesId) throws Exception {
		Connection con = null;
		Statement statement = null;

		String sql = "update UZSAKYMO_EILUTE set STATUSAS = " + DBConstants.UZSAKYMO_EILUTE_ISTRINTA + " where ID=" + uzsakymoEilutesId;

		try {
			con = getConnection();

			statement = con.createStatement();
			statement.execute(sql);


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	/***************************/
	public static void insertSaskaita(Saskaita saskaita) throws Exception {
		Connection con = null;

		PreparedStatement statement = null;

		String sql = "insert into SASKAITA (PAVADINIMAS, DATA, TIEKEJO_ID, VADYBININKO_ID, STATUSAS) values (?, ?, ?, ?, " +DBConstants.SASKAITA_NAUJA+");";
		try {
			con = getConnection();

			statement = con.prepareStatement(sql);
			statement.setString(1, saskaita.getPavadinimas());
			statement.setDate(2, new java.sql.Date(saskaita.getData().getTime()));
			statement.setLong(3, saskaita.getTiekejas().getId());
			statement.setLong(4, saskaita.getPardavimoVadybininkas().getId());
			statement.execute();


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public static void updateSaskaita(Saskaita saskaita) throws Exception {
		Connection con = null;

		PreparedStatement statement = null;

		String sql = "update SASKAITA set PAVADINIMAS = ?, " +
										  "DATA = ?, " +
										  "TIEKEJO_ID = ?, " +
										  "VADYBININKO_ID = ? "+
								"WHERE ID="+saskaita.getId();
		try {
			con = getConnection();

			statement = con.prepareStatement(sql);
			statement.setString(1, saskaita.getPavadinimas());
			statement.setDate(2, new java.sql.Date(saskaita.getData().getTime()));
			statement.setLong(3, saskaita.getTiekejas().getId());
			statement.setLong(4, saskaita.getPardavimoVadybininkas().getId());
			statement.execute();


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public static void patvirtintiSaskaita(Long saskaitosId) throws Exception {
		updateSaskaitaStatusas(saskaitosId, DBConstants.SASKAITA_PATVIRTINTA);
	}
	
	public static void deleteSaskaita(Long saskaitosId) throws Exception {
		updateSaskaitaStatusas(saskaitosId, DBConstants.SASKAITA_ISTRINTA);
	}
	
	private static void updateSaskaitaStatusas(Long saskaitosId, Integer statusas) throws SQLException{
		Connection con = null;
		Statement statement = null;

		String sql = "update SASKAITA set STATUSAS = " + statusas + " where ID=" + saskaitosId;

		try {
			con = getConnection();

			statement = con.createStatement();
			statement.execute(sql);


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static void insertSaskaitosEilute(SaskaitosEilute saskaitosEilute) throws Exception {
		Connection con = null;

		PreparedStatement statement = null;

		String sql = "insert into SASKAITOS_EILUTE (SASKAITOS_ID, UZSAKYMO_ID, PREKES_ID, KIEKIS, KAINA, SUMA, NETTO, STATUSAS) values (?, ?, ?, ?, ?, ?, ?," +DBConstants.SASKAITOS_EILUTE_NAUJA+");";
		try {
			con = getConnection();

			statement = con.prepareStatement(sql);
			statement.setLong(1, saskaitosEilute.getSaskaitosId());
			statement.setLong(2, saskaitosEilute.getUzsakymoId());
			statement.setLong(3, saskaitosEilute.getPreke().getId());
			statement.setDouble(4, saskaitosEilute.getKiekis());
			statement.setDouble(5, saskaitosEilute.getKaina());
			statement.setDouble(6, saskaitosEilute.getSuma());
			statement.setDouble(7, saskaitosEilute.getNetto());			
			statement.execute();


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public static void updateSaskaitosEilute(SaskaitosEilute saskaitosEilute) throws Exception {
		Connection con = null;

		PreparedStatement statement = null;

		String sql = "update SASKAITOS_EILUTE set SASKAITOS_ID = ?, " +
												 "UZSAKYMO_ID = ?, " +
										  		 "PREKES_ID = ?, " +
										  		 "KIEKIS = ?, " +
										  		 "KAINA = ?, " +
										  		 "SUMA = ?, " +
										  		 "NETTO = ?, " +
								"WHERE ID="+saskaitosEilute.getId();
		try {
			con = getConnection();

			statement = con.prepareStatement(sql);
			statement.setLong(1, saskaitosEilute.getSaskaitosId());
			statement.setLong(2, saskaitosEilute.getUzsakymoId());
			statement.setLong(3, saskaitosEilute.getPreke().getId());
			statement.setDouble(4, saskaitosEilute.getKiekis());
			statement.setDouble(5, saskaitosEilute.getKaina());
			statement.setDouble(6, saskaitosEilute.getSuma());
			statement.setDouble(7, saskaitosEilute.getNetto());
			statement.execute();


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public static void deleteSaskaitosEilute(Long saskaitosEilutesId) throws Exception {
		Connection con = null;
		Statement statement = null;

		String sql = "update SASKAITOS_EILUTE set STATUSAS = " + DBConstants.SASKAITOS_EILUTE_ISTRINTA + " where ID=" + saskaitosEilutesId;

		try {
			con = getConnection();

			statement = con.createStatement();
			statement.execute(sql);


		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
}
