package net.videki.templateutils.template.core.processor.converter.pdf.docx4j;

import net.videki.templateutils.template.core.configuration.FontConfig;
import net.videki.templateutils.template.core.configuration.TemplateServiceConfiguration;
import net.videki.templateutils.template.core.service.exception.TemplateServiceConfigurationException;
import net.videki.templateutils.template.core.util.FileSystemHelper;
import net.videki.templateutils.template.core.processor.converter.ConversionException;
import net.videki.templateutils.template.core.processor.converter.Converter;
import net.videki.templateutils.template.core.service.InputFormat;
import net.videki.templateutils.template.core.service.OutputFormat;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;

public class DocxToPdfConverterTest {

    public static class TemplateServiceConfigurationTest extends TemplateServiceConfiguration {
        @Override
        protected void setFontDir(String fontDir) {
            super.setFontDir(fontDir);
        }
    }

    @Test
    public void inputFormatTest() {

        final Converter x = new DocxToPdfConverter();

        Assert.assertEquals(InputFormat.DOCX, x.getInputFormat());
    }

    @Test
    public void outputFormatTest() {

        final Converter x = new DocxToPdfConverter();

        Assert.assertEquals(OutputFormat.PDF, x.getOutputFormat());
    }

    @Test
    public void convertValidTemplateOk() {
        OutputStream result;

        final String inputDir = "templates/unittests/docx";
        final String fileName = "SimpleContract_v1_21.docx";

        final Converter x = new DocxToPdfConverter();

        final InputStream is = getTemplate(FileSystemHelper.getFileNameWithPath(inputDir, fileName));

        result = x.convert(is);

        Assert.assertNotNull(result);
    }

    @Test
    public void convertInValidTemplate() {
        final String inputDir = "templates/unittests/docx";
        final String fileName = "there_is_no_such_file.docx";

        final Converter x = new DocxToPdfConverter();

        final InputStream is = getTemplate(FileSystemHelper.getFileNameWithPath(inputDir, fileName));

        try {
            x.convert(is);

            Assert.assertFalse(false);
        } catch (ConversionException e) {
            Assert.assertEquals("7d90a4a1-14df-4d1a-87d8-fd9b146357e8", e.getCode());
        } catch (Exception e) {
            Assert.assertFalse(false);
        }
    }

    @Test
    public void convertInValidTemplateFormat() {
        final String inputDir = "templates/unittests/docx";
        final String fileName = "invalidFile.docx";

        try {
            final InputStream is = getTemplate(FileSystemHelper.getFileNameWithPath(inputDir, fileName));
            final Converter x = new DocxToPdfConverter();
            x.convert(is);

            Assert.assertFalse(false);
        } catch (final ConversionException e) {
            Assert.assertEquals("c0a3ab2e-297d-4634-85cc-d171fd0772f1", e.getCode());
        } catch (final Exception e) {
            Assert.assertFalse(false);
        }
    }

    @Test
    public void fontConfigInvalid() {
        try {
            TemplateServiceConfiguration.load(null);
        } catch (final TemplateServiceConfigurationException e) {
            Assert.assertEquals("876075ed-6e23-4d84-95ba-05f45ba9193a", e.getCode());
        } catch (final Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void fontConfigAddExtraNoFontDirEmpty() {
        try {
            TemplateServiceConfigurationTest configuration = new TemplateServiceConfigurationTest();
            configuration.setFontDir("");
            TemplateServiceConfiguration.load(configuration);
        } catch (final TemplateServiceConfigurationException e) {
            Assert.assertEquals("656dca28-bc61-4059-991f-7bb65ec916e6", e.getCode());
        } catch (final Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void fontConfigAddExtraFontDir() {
        try {
            TemplateServiceConfiguration configuration = new TemplateServiceConfigurationTest();
            final FontConfig myFont = new FontConfig();
            myFont.setBasedir("/fonts");
            configuration.getFontConfig().add(myFont);

            TemplateServiceConfiguration.load(configuration);

            Assert.assertTrue(true);
        } catch (final Exception e) {
            Assert.fail();
        }
    }

    private static InputStream getTemplate(final String templateFile) {
        InputStream result;

        result = DocxToPdfConverter.class.getClassLoader().getResourceAsStream(templateFile);

        return result;

    }

}
