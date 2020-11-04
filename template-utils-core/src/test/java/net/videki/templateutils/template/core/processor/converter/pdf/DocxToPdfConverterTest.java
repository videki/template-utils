package net.videki.templateutils.template.core.processor.converter.pdf;

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

    private static InputStream getTemplate(final String templateFile) {
        InputStream result;

        result = DocxToPdfConverter.class.getClassLoader().getResourceAsStream(templateFile);

        return result;

    }


}