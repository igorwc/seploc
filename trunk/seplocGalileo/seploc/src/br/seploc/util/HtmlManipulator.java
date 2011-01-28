package br.seploc.util;

import java.util.HashMap;
import java.util.Map;

public class HtmlManipulator {

	private static final String HTML_ENTITIES_TABLE = "À    &Agrave;    Á    &Aacute;    Â    &Acirc;    Ã    &Atilde;    Ä    &Auml;    Å    &Aring;    "
			+ "Æ    &AElig;    Ç    &Ccedil;    È    &Egrave;    É    &Eacute;    Ê    &Ecirc;    Ë    &Euml;    "
			+ "Ì    &Igrave;    Í    &Iacute;    Î    &Icirc;    Ï    &Iuml;    Ð    &ETH;    Ñ    &Ntilde;    "
			+ "Ò    &Ograve;    Ó    &Oacute;    Ô    &Ocirc;    Õ    &Otilde;    Ö    &Ouml;    Ø    &Oslash;    "
			+ "Ù    &Ugrave;    Ú    &Uacute;    Û    &Ucirc;    Ü    &Uuml;    Ý    &Yacute;    Þ    &THORN;    "
			+ "ß    &szlig;    à    &agrave;    á    &aacute;    â    &acirc;    ã    &atilde;    ä    &auml;    "
			+ "å    &aring;    æ    &aelig;    ç    &ccedil;    è    &egrave;    é    &eacute;    ê    &ecirc;    "
			+ "ë    &euml;    ì    &igrave;    í    &iacute;    î    &icirc;    ï    &iuml;    ð    &eth;    "
			+ "ñ    &ntilde;    ò    &ograve;    ó    &oacute;    ô    &ocirc;    õ    &otilde;    ö    &ouml;    "
			+ "ø    &oslash;    ù    &ugrave;    ú    &uacute;    û    &ucirc;    ü    &uuml;    ý    &yacute;    "
			+ "þ    &thorn;    ÿ    &yuml;    ¡     &iexcl;"
			+ "    ¢     &cent;    £     &pound;    ¤     &curren;    ¥     &yen;    ¦     &brvbar;    §     &sect;    "
			+ "¨     &uml;    ©     &copy;    ª     &ordf;    «     &laquo;    ¬     &not;    ­     &shy;    ®     &reg;    "
			+ "¯     &macr;    °     &deg;    ±     &plusmn;    ²     &sup2;    ³     &sup3;    ´     &acute;    µ     &micro;    "
			+ "¶     &para;    ·     &middot;    ¸     &cedil;    ¹     &sup1;    º     &ordm;    »     &raquo;    ¼     &frac14;   "
			+ " ½     &frac12;    ¾     &frac34;    ¿     &iquest;    ×     &times;    ÷     &divide;";
	/** MAPEAMENTO: HTML ENTITY ---> CARATERE UNICODE */
	private static final Map<String, Character> HTML_ENTITY_TO_UNICODE_MAP = new HashMap<String, Character>();

	/** MAPEAMENTO: CARATERE UNICODE ---> HTML ENTITY */
	private static final Map<Character, String> UNICODE_TO_HTML_ENTITY_MAP = new HashMap<Character, String>();
	/**
	 * Valor dado por RAW_HTML_ENTITY_TABLE.hashCode(), usado para guardar e
	 * saber se houve modificação acidental
	 */
	private static final int HTML_ENTITY_TABLE_HASHCODE = -1193202793;
	/**
	 * Bloco estático Popula os mapas HTML_ENTITY_TO_UNICODE_MAP e
	 * UNICODE_TO_HTML_ENTITY_MAP.
	 */
	static {
		/* Checa o hash code da tabela HTML_ENTITIES_TABLE */
//		if (HTML_ENTITIES_TABLE.hashCode() != HTML_ENTITY_TABLE_HASHCODE) {
//			throw new RuntimeException(
//					"(INTERNO) HtmlManipulator.RAW_HTML_ENTITY_TABLE MAL FORMADO.");
//		}

		/* POPULA OS MAPAS: HTML ENTITY <---> CARATERE UNICODE */
		final String[] elementos = HTML_ENTITIES_TABLE.split("\\s+");

		for (int i = 0; i < elementos.length; i += 2) {
			final char unicode = (char) (elementos[i].charAt(0));
			HTML_ENTITY_TO_UNICODE_MAP.put(elementos[i + 1], unicode);
			UNICODE_TO_HTML_ENTITY_MAP.put(unicode, elementos[i + 1]);
		}
//		HTML_ENTITY_TO_UNICODE_MAP.put("&nbsp;", ' ');
//		UNICODE_TO_HTML_ENTITY_MAP.put(' ', "&nbsp;");
	}

	/**
	 * Substitui todos os caracteres não ASCII em HTML entities equivalentes.
	 * 
	 * @param s
	 *            input string
	 * @return string com os caracteres especiais substituidos
	 */
	public static String converteParaHtml(final String s) {
		final StringBuilder t = new StringBuilder();

		for (char c : s.toCharArray()) {
			final String entity = UNICODE_TO_HTML_ENTITY_MAP.get(c);

			if (entity == null || c == '>' || c == '<' ) {
				t.append(c);
			} else {
				t.append(entity);
			}
		}

		return t.toString();
	}

	public static void main(String args[]) {
		System.out.println(HTML_ENTITIES_TABLE.hashCode());
		System.out.println(HTML_ENTITY_TO_UNICODE_MAP.toString());
		System.out.println(UNICODE_TO_HTML_ENTITY_MAP.toString());
		System.out
				.println(converteParaHtml("<td colspan=\"3\" style=\"width: 85%\">FOOD & MART. COMÉRCIO ALIMENTÍCIO LTDA( BONAPARTE)</td>"));

	}
}
