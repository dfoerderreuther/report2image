/*
* Copyright 2014 Dominik Foerderreuther <dominik@eleon.de>
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package de.eleon.report2image;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.util.Map;

@Mojo(name = "report2image")
public class Report2Image extends AbstractMojo {

    public static final String HTML = "html";
    public static final String IMAGE = "image";
    public static final String WIDTH = "width";
    public static final String DEFAULT_WIDTH = "800";

    @Parameter(required = true)
    protected Map<String, String>[] reports;

    @Parameter(required = false)
    protected boolean hideTag = false;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        renderReports();
    }

    private void renderReports() {
        for (Map<String, String> report : reports) {
            String html = report.get(HTML);
            String image = report.get(IMAGE);
            int width = Integer.parseInt(report.getOrDefault(WIDTH, DEFAULT_WIDTH));
            renderReport(html, image, width);
        }
    }

    private void renderReport(String html, String image, int width) {
        getLog().info(String.format("render html: %s, image: %s, width%d", html, image, width));
        try {
            Renderer renderer = new Renderer(html);
            renderer.setWidth(width);
            if (hideTag) renderer.setTag(false);
            renderer.render(image);
        } catch (IOException e) {
            getLog().error("can't create image", e);
        }
    }
}
