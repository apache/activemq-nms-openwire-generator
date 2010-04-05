/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.nms.openwire.tool.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.apache.activemq.openwire.tool.MultiSourceGenerator;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.FixCRLF;
import org.codehaus.jam.JClass;

/**
 *
 * @version $Revision: 409828 $
 */
public class CommandGenerator extends MultiSourceGenerator {

    private CommandCodeGeneratorsFactory generatorsFactory =
        new CommandCodeGeneratorsFactory();

    protected String targetDir="./src/main/csharp";

    public String getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    protected void processClass(JClass jclass) {

        // Prepare the State variables for the current class
        simpleName = jclass.getSimpleName();
        superclass = jclass.getSuperclass();
        className = getClassName(jclass);
        baseClass = getBaseClassName(jclass);

        System.out.println(getClass().getName() + " processing class: " + simpleName);

        try {
            // Using the current JClass state in the MultiSourceGenerator we can
            // now generate the Source for the CSharp commands.
            generateClassFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object run() {
        if (destDir == null) {
            destDir = new File(
                targetDir+"/Commands");
        }

        return super.run();
    }

    protected void generateClassFile() throws Exception {

        File headerFile = new File(destDir, className + ".cs");

        CommandCodeGenerator generator = generatorsFactory.getCodeGenerator( className );

        generator.setJClass( getJclass() );
        generator.setProperties( getProperties() );
        generator.setMarshalAware( isMarshallerAware() );
        generator.setClassName( getClassName() );
        generator.setBaseClassName( getBaseClass() );
        generator.setOpenWireOpCode( getOpenWireOpCode(getJclass()) );
        generator.setComparable( className.endsWith("Id") || generator.isComparable() );

        for (int i = 0; i < getJclass().getInterfaces().length; i++) {
            JClass intf = getJclass().getInterfaces()[i];

            String name = generator.toCSharpType( intf );
            if( name != null && !name.endsWith("DataStructure") && !name.equals("Command") ) {
                generator.addAdditionalBase( name );
            }
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(headerFile));
            generator.generate(out);
        } catch( Exception e ) {
            throw new RuntimeException(e);
        } finally {
            if( out != null ) {
                out.close();
            }
        }

        // Use the FixCRLF Ant Task to make sure the file has consistent
        // newlines so that SVN does not complain on checkin.
        Project project = new Project();
        project.init();
        FixCRLF fixCRLF = new FixCRLF();
        fixCRLF.setProject(project);
        fixCRLF.setSrcdir(headerFile.getParentFile());
        fixCRLF.setIncludes(headerFile.getName());
        fixCRLF.execute();
    }

    @Override
    protected void generateFile(PrintWriter arg0) throws Exception {
        // Not used here since we override the process method.
    }

}
