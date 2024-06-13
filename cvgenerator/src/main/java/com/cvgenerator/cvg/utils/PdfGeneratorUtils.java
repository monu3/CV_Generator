package com.cvgenerator.cvg.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Component
public class PdfGeneratorUtils {

    public static void generatePdfFromHtml(String htmlContent, String outputFileName) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputFileName);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);

            log.info("PDF generated successfully at {}", outputFileName);
        } catch (Exception e) {
            log.error("Error generating PDF", e);
            throw new RuntimeException("Error generating PDF", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("Error closing output stream", e);
                }
            }
        }
    }

    public static String parseThymeleafTemplate(String templateName, Context context) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process(templateName, context);
    }
}
