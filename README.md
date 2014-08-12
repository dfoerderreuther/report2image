# Report2Image

Report2Image is a maven plugin to convert a html report into a single image. It can be used to display the reported test coverage at drone.io, for example.

## Example usage in pom.xml with jacoco

The following pom settings create a jacoco coverage report and converts the index-page of the report into a png.

### Jacoco configuration
            
    <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.7.1.201405082137</version>
        <executions>
            <execution>
                <id>jacoco-initialize</id>
                <goals>
                    <goal>prepare-agent</goal>
                </goals>
            </execution>
            <execution>
                <id>jacoco-integration</id>
                <phase>prepare-package</phase>
                <goals>
                    <goal>report</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    
    
### Report2Image configuration

    <plugin>
        <groupId>de.eleon</groupId>
        <artifactId>report2image</artifactId>
        <executions>
            <execution>
                <phase>package</phase>
                <goals>
                    <goal>report2image</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <reports>
                <report>
                    <html>target/site/jacoco/index.html</html>
                    <image>target/jacoco.png</image>
                </report>
                <!-- ... -->
            </reports>
        </configuration>
    </plugin>


