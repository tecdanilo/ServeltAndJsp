package br.com.avaliacaojavajr.webtest.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;

public class SistemaUtil {
	
	private static Logger logger = Logger.getLogger(SistemaUtil.class);
	 
	public static final String SENDGRID_USERNAME = "orsegups"; 
	
	public static final String SENDGRID_PASSWORD = "Ors1234!"; 
	
	private static final String MAIL_ADMIN_SISTEMA = "danilo.silva@orsegups.com.br";
	
	private static final String USER_AGENT = "Mozilla/5.0";
	private static SimpleDateFormat formatter;
	
	
	private SistemaUtil(){
		
	}
	
	public static String getDayOfWeek(Date data) { 
		String[] diaSemana = new String[]{"Dom","Seg","Ter","Qua","Qui","Sex","Sab"};
		
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
		return diaSemana[dayOfWeek - 1]; 
	}
	
	public static String formatDate(Date date, String mask) {
		SimpleDateFormat format = new SimpleDateFormat(mask);
		String txt = format.format(date);
		return txt;
	}
	
	/**
	 * Formata o objeto em formato dd/MM/yyyy hh24:mm
	 * 
	 * @param date
	 * @return
	 */
	public static String formataData(GregorianCalendar date) {
		return formataData(date, "dd/MM/yyyy HH:mm");
	}

	/**
	 * Formata o objeto no formato recebido.
	 * 
	 * @param date
	 * @return
	 */
	public static String formataData(GregorianCalendar date, String format) {
		if (isNotNull(date, format)) {
			return formataData(date.getTime(), format);
		}
		return null;
	}

	/**
	 * Formata o objeto em formato dd/MM/yyyy HH:mm
	 * 
	 * @param date
	 * @return
	 */
	public static String formataData(Date date, String format) {
		if (isNotNull(date, format)) {
			formatter = new SimpleDateFormat(format);
			return formatter.format(date);
		}
		return null;
	}
	

	public static String trimSeguro(String value) {
		if (value == null || value.length() == 0 || "null".equals(value)){
			return null;
		} else {
			return value.trim();
		}
	}

	/**
	 * Verifica se o objeto é nulo
	 * <p>
	 * Se for uma String, também irá validar se a String é apenas um espaço ou uma String vazia ("").
	 * Caso for, é considerado como null;
	 * 
	 * @param values
	 * @return true se o objeto não for null
	 */
	public static boolean isNotNull(Object... values) {
		return !isNull(values);
	}

	/**
	 * 
	 * Verifica se a String é nula ou vazia
	 * 
	 * @param value
	 * @return true se o objeto for null
	 */
	public static boolean isNull(String value) {
		if (value == null || (value != null && value.trim().length() == 0)){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Recebe uma array de objetos e valida se os mesmos são nulos. Se um deles
	 * for, o método retorna true imediatamente. Se nenhum for null, retorna
	 * false.
	 * <p>
	 * Se for uma String, também irá validar se a String é apenas um espaço ou uma String vazia ("").
	 * Caso for, é considerado como null;
	 * 
	 * @param objects
	 * @return true se o objeto for null
	 */
	public static boolean isNull(Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			if (isNull(objects[i]))	{
				return true;
			}
		}
		return false;
	}

	/**
	 * Valida se o objeto recebido por parâmetro é null
	 * <p>
	 * Se for uma String, também irá validar se a String é apenas um espaço ou uma String vazia ("").
	 * Caso for, é considerado como null;
	 * 
	 * @param object
	 * @return true se o objeto for null
	 */
	public static boolean isNull(Object object) {
		return object instanceof String ? isNull((String) object) : object == null;
	}
	
	public static String formatarString(String texto, String mascara) throws ParseException	{
		MaskFormatter mf = new MaskFormatter(mascara);
		mf.setValueContainsLiteralCharacters(false);
		return mf.valueToString(texto);
	}
	
	public static String toString(Object value) {
		if (value == null) {
			return "";
		} else {
			return toString(value.toString());
		}
	}

	public static String toString(String value) {
		if (value == null || value.length() == 0 || "null".equals(value)){
			return "";
		} else {
			return value;
		}
	}

	
	public static Long toLong(final String value) {
		if (value == null){
			return null;
		}
		try	{
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Long toLong(final String value, final Long defaultValue) {
		Long v = toLong(value);

		if (v == null){
			return defaultValue;
		}
		return v;
	}
	
	/**
	 * Converte um Timemillis em GregorianCalendar
	 * @param millis
	 * @return
	 */
	public static GregorianCalendar toGregorianCalendar(Long millis){
		if (millis == 0 || millis == null){
			return (GregorianCalendar)null;
		}else{
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(millis);
			return gc;
		}
	}
	
	/**
	 * Converte um java.sql.Date para GregorianCalendar
	 * @param sqlDate
	 * @return
	 */
	public static GregorianCalendar toGregorianCalendar(java.sql.Date sqlDate){
		if (sqlDate != null) {
			return toGregorianCalendar(sqlDate.getTime());
		} else {
			return null;
		}
	}
	
	/**
	 * Converte um java.sql.sqlTimeStamp para GregorianCalendar
	 * @param sqlDate
	 * @return
	 */
	public static GregorianCalendar toGregorianCalendar(java.sql.Timestamp sqlTimeStamp){
		if (sqlTimeStamp != null){
			return toGregorianCalendar(sqlTimeStamp.getTime());
		} else {
			return null;
		}
	}
	
	
	/**
	 * Converte um java.util.Date para GregorianCalendar
	 * @param date
	 * @return
	 */
	public static GregorianCalendar toGregorianCalendar(java.util.Date date){
		if (date != null) {
			return toGregorianCalendar(date.getTime());
		} else {
			return null;
		}
	}
	
	/**
	 * Converte timemillis para Timestamp
	 * @param timemillis - tempo em milisegundos
	 * @return
	 */
	public static Timestamp toTimestamp(Long timemillis){
		if (timemillis != null && timemillis > 0L) {
			return new Timestamp(timemillis);
		} else {
			return null;
		}
	}
	
	/**
	 * Converte timemillis para java.sql.Date
	 * @param timemillis - tempo em milisegundos
	 * @return
	 */
	public static java.sql.Date toSqlDate(Long timemillis) {
		if (timemillis != null && timemillis > 0L) {
			return new java.sql.Date(timemillis);
		} else {
			return null;
		}
	}
	
	public static String retiraCaracteresAcentuados(String stringFonte) {
		if (stringFonte == null) return null;
		
		String passa = stringFonte;
		passa = passa.replaceAll("[ÂÀÁÄÃ]", "A");
		passa = passa.replaceAll("[âãàáä]", "a");
		passa = passa.replaceAll("[ÊÈÉË]", "E");
		passa = passa.replaceAll("[êèéë]", "e");
		passa = passa.replaceAll("[ÎÍÌÏ]", "I");
		passa = passa.replaceAll("[îíìï]", "i");
		passa = passa.replaceAll("[ÔÕÒÓÖ]", "O");
		passa = passa.replaceAll("[ôõòóö]", "o");
		passa = passa.replaceAll("[ÛÙÚÜ]", "U");
		passa = passa.replaceAll("[ûúùü]", "u");
		passa = passa.replaceAll("Ç", "C");
		passa = passa.replaceAll("ç", "c");
		passa = passa.replaceAll("[ýÿ]", "y");
		passa = passa.replaceAll("[&]", "e");
		passa = passa.replaceAll("Ý", "Y");
		passa = passa.replaceAll("ñ", "n");
		passa = passa.replaceAll("Ñ", "N");
		passa = passa.replaceAll("[-+=*%$#@!_]", "");
		passa = passa.replaceAll("['\"]", "");
		passa = passa.replaceAll("[<>()\\{\\}]", "");
		passa = passa.replaceAll("['\\\\.()|/]", "");
		passa = passa.replaceAll("[^!-ÿ]{1}[^ -ÿ]{0,}[^!-ÿ]{1}|[^!-ÿ]{1}", " ");
		passa = passa.replaceAll("[',]", " ");
		return passa;
	}
	
	public static String truncaCampo(String campo, int maxSize) {
		String retorno = "";
		if (campo != null && !campo.isEmpty()){
			retorno = campo.substring(0, (campo.length()>maxSize? maxSize:campo.length() ));
		}
		
		return retorno;
	}
	
	/**
	 * Retorna o ultimo dia de um mes.
	 * @param gc
	 * @return
	 */
	public static GregorianCalendar retornaUltimoDiaMes(GregorianCalendar gc){
		GregorianCalendar c = (GregorianCalendar) gc.clone();
		int dia = gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		c.set(GregorianCalendar.DAY_OF_MONTH, dia);
		return c;
	}
	
	/**
	 * Retorna o ultimo dia do mes atual.
	 * @param gc
	 * @return
	 */
	public static GregorianCalendar retornaUltimoDiaMes(){
		return retornaUltimoDiaMes((GregorianCalendar) GregorianCalendar.getInstance());
	}
	
	/**
	 * Gera uma lista de competencias até uma data base.
	 * @param calendar - databa base para geração
	 * @param qtde - Quantidade de Competencias a serem geradas.
	 * @return
	 */
	public static List<String> geraListaCompetencias(GregorianCalendar calendar, int qtde)
	{
		List<String> cptList = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
		try{
			
			if (calendar != null)
			{
				cptList = new ArrayList<String>();
				Calendar dia = GregorianCalendar.getInstance();
				for (int i = 0; i < qtde; i++)
				{
					String diaStr = dateFormat.format(dia.getTime());
					cptList.add(diaStr);
					dia.add(Calendar.MONTH, -1);
				}
			}
			return cptList;
		}catch(Exception e){
			cptList.clear();
			cptList.add(dateFormat.format(calendar.getTime()));
			return cptList;
		}
	}

}