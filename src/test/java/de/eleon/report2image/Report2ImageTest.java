package de.eleon.report2image;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class Report2ImageTest {

    @Test
    public void testMavenPlugin() throws Exception {

        new File("cobertura.png").delete();

        Report2Image report2Image = new Report2Image();

        //noinspection unchecked
        report2Image.reports = new Map[] {
                ImmutableMap.<String, String>builder()
                        .put(Report2Image.HTML, "src/test/resources/cobertura/frame-summary.html")
                        .put(Report2Image.IMAGE, "cobertura.png")
                        .build()
        };

        report2Image.execute();

        assertTrue(new File("cobertura.png").exists());

    }

}