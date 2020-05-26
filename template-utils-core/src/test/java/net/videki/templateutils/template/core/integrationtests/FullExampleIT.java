package net.videki.templateutils.template.core.integrationtests;

import net.videki.templateutils.template.core.configuration.TemplateServiceConfiguration;
import net.videki.templateutils.template.core.documentstructure.GenerationResult;
import net.videki.templateutils.template.core.service.exception.TemplateNotFoundException;
import net.videki.templateutils.template.core.util.FileSystemHelper;
import net.videki.templateutils.template.core.context.TemplateContext;
import net.videki.templateutils.template.core.documentstructure.DocumentStructure;
import net.videki.templateutils.template.core.documentstructure.ValueSet;
import net.videki.templateutils.template.core.documentstructure.descriptors.TemplateElement;
import net.videki.templateutils.template.core.service.TemplateService;
import net.videki.templateutils.template.core.service.TemplateServiceRegistry;
import net.videki.templateutils.template.core.service.exception.TemplateServiceConfigurationException;
import net.videki.templateutils.template.core.service.exception.TemplateServiceException;
import net.videki.templateutils.template.test.dto.ContractDataFactory;
import net.videki.templateutils.template.test.dto.DocDataFactory;
import net.videki.templateutils.template.test.dto.OfficerDataFactory;
import net.videki.templateutils.template.test.dto.OrgUnitDataFactory;
import net.videki.templateutils.template.test.dto.contract.Contract;
import net.videki.templateutils.template.test.dto.doc.DocumentProperties;
import net.videki.templateutils.template.test.dto.officer.Officer;
import net.videki.templateutils.template.test.dto.organization.OrganizationUnit;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FullExampleIT {
    private static final Locale LC_HU = new Locale("hu", "HU");

    private static final String TL_COVER_KEY = "cover";
    private static final String TL_COVER_FILE = "01-cover_v03.docx";

    private static final String TL_CONTRACT_KEY = "contract";
    private static final String TL_CONTRACT_FILE_HU = "02-contract_v09_hu.docx";
    private static final String TL_CONTRACT_FILE_EN = "02-contract_v09_en.docx";

    private static final String TL_TERMS_KEY = "terms";
    private static final String TL_TERMS_FILE = "03-terms_v02.docx";

    private static final String TL_CONDITIONS_KEY = "terms";
    private static final String TL_CONDITIONS_FILE = "04-conditions_v11.xlsx";

    private static final String inputDir = "/full-example";

    private final TemplateService templateService = TemplateServiceRegistry.getInstance();
    private final TemplateServiceConfiguration templateServiceConfiguration = TemplateServiceConfiguration.getInstance();

    @Test
    public void fillAndSaveStructuredTemplateTest() {
        try {
            final DocumentStructure templateStructure = getContractDocStructure();
            final ValueSet testData = getTestData();

            GenerationResult result = this.templateService.fill(templateStructure, testData);
            this.templateServiceConfiguration.getResultStore().save(result);

        } catch (TemplateNotFoundException | TemplateServiceException e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(true);
    }

    private TemplateContext getCoverData(final String transactionId) {
        final OrganizationUnit orgUnit = OrgUnitDataFactory.createOrgUnit();
        final Officer officer = OfficerDataFactory.createOfficer();
        final DocumentProperties documentProperties = DocDataFactory.createDocData(transactionId);

        final TemplateContext context = new TemplateContext();
        context.getCtx().put("org", orgUnit);
        context.getCtx().put("officer", officer);
        context.getCtx().put("doc", documentProperties);

        return context;
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

    private DocumentStructure getContractDocStructure() throws TemplateServiceConfigurationException {
        final DocumentStructure result = new DocumentStructure();

        result.getElements().add(
                new TemplateElement(TL_COVER_KEY, FileSystemHelper.getFullPath(inputDir, TL_COVER_FILE))
                    .withDefaultLocale(LC_HU));

        result.getElements().add(
                new TemplateElement(TL_CONTRACT_KEY)
                    .withTemplateName(FileSystemHelper.getFullPath(inputDir, TL_CONTRACT_FILE_HU), LC_HU)
                    .withTemplateName(FileSystemHelper.getFullPath(inputDir, TL_CONTRACT_FILE_EN), Locale.ENGLISH)
                    .withDefaultLocale(LC_HU)
        );

        result.getElements().add(
                new TemplateElement(TL_TERMS_KEY, FileSystemHelper.getFullPath(inputDir, TL_TERMS_FILE))
                    .withDefaultLocale(LC_HU));

        result.getElements().add(
                new TemplateElement(TL_CONDITIONS_KEY, FileSystemHelper.getFullPath(inputDir, TL_CONDITIONS_FILE))
                    .withDefaultLocale(LC_HU));

        return result;
    }

    private ValueSet getTestData() {

        final ValueSet result = new ValueSet();
        final String transactionId = result.getTransactionId();
        result
                .addContext(TL_COVER_KEY, getCoverData(transactionId))
                .addContext(TL_CONTRACT_KEY, getContractTestData())
                .addDefaultContext(TL_TERMS_KEY, null)
                .addContext(TL_CONDITIONS_KEY, getContractTestData())
                .withLocale(LC_HU)
//                .withLocale(Locale.ENGLISH)
                ;

        return result;
    }

}