package br.seploc.reports;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtils {
	private static Configuration cfg = new Configuration();
	private static final String TEMPLATES_FOLDER = "src/relatorios";

	public static final String parseTemplate(Map map, String templateName)
			throws TemplateException, IOException {
		// diretório onde estão templates
		cfg.setDirectoryForTemplateLoading(new File(TEMPLATES_FOLDER));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setNumberFormat("0.######");
		// recupera o template

		Template t = cfg.getTemplate(templateName);
		StringWriter writer = new StringWriter();
		/** Freemarker **/
		t.process(map, writer);
		writer.flush();
		writer.close();
		return writer.toString();
	}

	public static final String parseTemplateWeb(Map map, String templateName,String dir)
			throws TemplateException, IOException {
		if(dir == null){
			
			// diretório onde estão templates
			cfg.setDirectoryForTemplateLoading(new File("/WEB-INF/reports/"));
		}else{
			// diretório onde estão templates
			cfg.setDirectoryForTemplateLoading(new File(dir));
		}
		
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		// recupera o template

		Template t = cfg.getTemplate(templateName);
		StringWriter writer = new StringWriter();
		/** Freemarker **/
		t.process(map, writer);
		writer.flush();
		writer.close();
		return writer.toString();
	}

}
