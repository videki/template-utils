package net.videki.templateutils.template.core.processor;

import static org.junit.Assert.assertNotNull;
import java.io.InputStream;
import org.junit.Test;

public class AbstractInputTemplateProcessorTest {

  @Test
  public void getTemplate() {
    final String fileName = "/templates/fillDocxTableRows.docx";
    InputStream i = AbstractTemplateProcessor.getTemplate(fileName);
    
    assertNotNull(i);
  }  
}
