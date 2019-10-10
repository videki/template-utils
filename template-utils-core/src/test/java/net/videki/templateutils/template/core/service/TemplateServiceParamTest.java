package net.videki.templateutils.template.core.service;

import net.videki.templateutils.template.core.TestHelper;
import net.videki.templateutils.template.core.context.TemplateContext;
import net.videki.templateutils.template.core.documentstructure.DocumentStructure;
import net.videki.templateutils.template.core.documentstructure.ValueSet;
import net.videki.templateutils.template.core.documentstructure.descriptors.TemplateElement;
import net.videki.templateutils.template.core.service.exception.TemplateNotFoundException;
import net.videki.templateutils.template.core.service.exception.TemplateServiceConfigurationException;
import net.videki.templateutils.template.core.service.exception.TemplateServiceException;
import net.videki.templateutils.template.test.dto.ContractDataFactory;
import net.videki.templateutils.template.test.dto.OfficerDataFactory;
import net.videki.templateutils.template.test.dto.OrgUnitDataFactory;
import net.videki.templateutils.template.test.dto.contract.Contract;
import net.videki.templateutils.template.test.dto.officer.Officer;
import net.videki.templateutils.template.test.dto.organization.OrganizationUnit;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TemplateServiceParamTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateService.class);
    private static final TemplateService ts = TemplateServiceRegistry.getInstance();

    @Test
    public void fillNoParamsSingleDocTemplateAndDto() {

        String resultCode;

        try(final OutputStream ignored = ts.fill((String)null, null)) {
            resultCode = "No exception";
        } catch (IOException e) {
            resultCode = "IOException";
        } catch (TemplateServiceConfigurationException e) {
            resultCode = e.getCode();
        } catch (TemplateServiceException e) {
            e.printStackTrace();
            resultCode = e.getCode();
        }

        assertEquals("070f463e-743f-4cb2-a651-bd11e844728d", resultCode);
    }

    @Test
    public void fillNoParamsSingleDocTemplateAndDtoAndFormat() {
        String resultCode;

        try(final OutputStream ignored = ts.fill(null, null, null)) {
            resultCode = "No exception";
        } catch (IOException e) {
            resultCode = "IOException";
        } catch (TemplateServiceConfigurationException e) {
            resultCode = e.getCode();
        } catch (TemplateServiceException e) {
            e.printStackTrace();
            resultCode = e.getCode();
        }

        assertEquals("c936e550-8b0e-4577-bffa-7f36b211d981", resultCode);
    }

    @Test
    public void fillNoParamsSingleDocStructAndValues() {
        String resultCode = "";

        try {
            ts.fill((DocumentStructure) null, null);

        } catch (TemplateServiceConfigurationException e) {
            resultCode = e.getCode();
        } catch (TemplateServiceException e) {
            e.printStackTrace();
            resultCode = e.getCode();

        }

        assertEquals("bdaa9376-28b4-4718-9859-2ef5d88ab3b0", resultCode);
    }

    @Test
    public void fillSimpleTemplate() {
        boolean testResult;

        final String inputDir = "/templates/docx";
        final String projectOutDir = System.getProperty("user.dir") + "/build/test-results/test";

        final String fileName = "SimpleContract_v1_21.docx";
        final String resultFileName = "result-" + fileName;

        try {
            OutputStream result = ts.fill(inputDir + "/" + fileName, getContractTestData(), OutputFormat.DOCX);

            LOGGER.info("Result file: {}/{}.", projectOutDir, resultFileName);

            FileOutputStream o = new FileOutputStream(projectOutDir + "/" + resultFileName);

            o.write(((ByteArrayOutputStream)result).toByteArray());
            o.flush();
            o.close();
            result.close();

            testResult = true;

            LOGGER.info("Done.");
        } catch (IOException e) {
            System.out.println("error:");
            e.printStackTrace();

            testResult = false;

        } catch (TemplateServiceException e) {
            e.printStackTrace();

            testResult = false;
        }

        assertTrue(testResult);
    }

    @Test
    public void fillSimpleTemplateNonexistingTemplateFile() {
        boolean testResult = false;

        final String inputDir = "/templates/docx";

        final String fileName = "there_is_no_such_template_file.docx";

        try {
            ts.fill(inputDir + "/" + fileName, getContractTestData(), OutputFormat.DOCX);

            LOGGER.info("Done.");
        } catch (TemplateNotFoundException e) {
            testResult = true;

        } catch (TemplateServiceException e) {
            e.printStackTrace();
        }

        assertTrue(testResult);

    }

    @Test
    public void fillSimpleTemplateViaDocumentStructre() {
        boolean testResult;

        final String inputDir = "/templates/docx";

        final String fileName = "SimpleContract_v1_21.docx";

        final DocumentStructure structure = new DocumentStructure();
        final TemplateElement docElement = new TemplateElement("contract", inputDir + "/" + fileName);
        docElement
                .withCount(1)
                .withDefaultLocale(new Locale("HU"));

        structure.getElements().add(docElement);

        final ValueSet values = new ValueSet();
        values.getValues().put(docElement.getTemplateElementId(), getContractTestData());

        List<OutputStream> resultDocs = null;
        try {
            resultDocs = ts.fill(structure, values);

            testResult = true;

            LOGGER.info("Done.");
        } catch (TemplateServiceException e) {
            e.printStackTrace();

            testResult = false;
        } finally {
            TestHelper.closeResults(resultDocs);
        }

        assertTrue(testResult);
    }


    private TemplateContext getContractTestData() {
        final Contract dto = ContractDataFactory.createContract();
        final OrganizationUnit orgUnit = OrgUnitDataFactory.createOrgUnit();

        final Officer officer = OfficerDataFactory.createOfficer();

        final TemplateContext context = new TemplateContext();
        context.getCtx().put("org", orgUnit);
        context.getCtx().put("officer", officer);
        context.getCtx().put("contract", dto);

        return context;
    }
/*
            (@NotNull final String templateName, @NotNull final T dto);

    <T> OutputStream fill(@NotNull final String templateName, @NotNull final T dto, @NotNull final OutputFormat format);

    List<OutputStream> fill(@NotNull final DocumentStructure documentStructure, @NotNull final ValueSet values);
*/

}
