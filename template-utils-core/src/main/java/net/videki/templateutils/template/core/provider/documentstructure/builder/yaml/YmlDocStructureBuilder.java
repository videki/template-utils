package net.videki.templateutils.template.core.provider.documentstructure.builder.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.videki.templateutils.template.core.provider.documentstructure.builder.DocumentStructureBuilder;
import net.videki.templateutils.template.core.documentstructure.DocumentStructure;
import net.videki.templateutils.template.core.documentstructure.descriptors.TemplateElement;
import net.videki.templateutils.template.core.service.exception.TemplateProcessException;
import net.videki.templateutils.template.core.service.exception.TemplateServiceConfigurationException;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class YmlDocStructureBuilder implements DocumentStructureBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(YmlDocStructureBuilder.class);

    @Override
    public DocumentStructure build(final InputStream dsConfig) throws TemplateServiceConfigurationException {

        if (dsConfig == null) {
            throw new TemplateProcessException("cfb09b69-cb69-4cb9-b7b0-b060c0717cf3", "Null input caught.");
        }

        DocumentStructure result;
        SimpleModule module = new SimpleModule();
        module.addDeserializer(TemplateElement.class, new TemplateElementDeserializer());
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        mapper.registerModule(module);
        try {
            result = mapper.readValue(dsConfig, DocumentStructure.class);
            if (LOGGER.isDebugEnabled()) {
                final String msg = String.format("DocumentStructure loaded: configFile: %s", dsConfig);

                LOGGER.debug(msg);
            }
            if (LOGGER.isTraceEnabled()) {
                final String msg = String.format("DocumentStructure loaded: configFile: %s, content: %s", dsConfig,
                        ReflectionToStringBuilder.toString(result, ToStringStyle.MULTI_LINE_STYLE));

                LOGGER.debug(msg);
            }
        } catch (FileNotFoundException e) {
            final String msg = String.format("DocumentStructure configuration file not found: %s", dsConfig);
            LOGGER.error(msg, e);

            throw new TemplateServiceConfigurationException("9c4233f3-ce43-45b5-80f9-a06424916649", msg);
        } catch (Exception e) {
            final String msg = String.format("Error reading documentStructure configuration: %s", dsConfig);
            LOGGER.error(msg, e);

            throw new TemplateServiceConfigurationException("d53a8fb6-183f-466e-ad94-aa189bad455a", msg);
        }
        return result;
    }

}
