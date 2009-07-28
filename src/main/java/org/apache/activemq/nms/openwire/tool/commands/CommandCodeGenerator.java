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

import java.io.PrintWriter;
import java.util.List;

import org.codehaus.jam.JClass;
import org.codehaus.jam.JProperty;

public abstract class CommandCodeGenerator {

    private JClass jClass;
    private List<JProperty> properties;
    private boolean marshalAware = false;
    private String className;
    private String baseClassName;
    private String openWireOpCode;
    private boolean genIsClass = false;

    public abstract void generate( PrintWriter out );

    protected void generateLicence(PrintWriter out) {
        out.println("/*");
        out.println(" * Licensed to the Apache Software Foundation (ASF) under one or more");
        out.println(" * contributor license agreements.  See the NOTICE file distributed with");
        out.println(" * this work for additional information regarding copyright ownership.");
        out.println(" * The ASF licenses this file to You under the Apache License, Version 2.0");
        out.println(" * (the \"License\"); you may not use this file except in compliance with");
        out.println(" * the License.  You may obtain a copy of the License at");
        out.println(" *");
        out.println(" * http://www.apache.org/licenses/LICENSE-2.0");
        out.println(" *");
        out.println(" * Unless required by applicable law or agreed to in writing, software");
        out.println(" * distributed under the License is distributed on an \"AS IS\" BASIS,");
        out.println(" * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.");
        out.println(" * See the License for the specific language governing permissions and");
        out.println(" * limitations under the License.");
        out.println(" */");
        out.println("");
    }

    public boolean isGenIsClass() {
        return genIsClass;
    }

    public void setGenIsClass(boolean genIsClass) {
        this.genIsClass = genIsClass;
    }

    public JClass getJClass() {
        return jClass;
    }

    public void setJClass(JClass jClass) {
        this.jClass = jClass;
    }

    public List<JProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<JProperty> properties) {
        this.properties = properties;
    }

    public boolean isMarshalAware() {
        return marshalAware;
    }

    public void setMarshalAware(boolean marshalAware) {
        this.marshalAware = marshalAware;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBaseClassName() {
        return baseClassName;
    }

    public void setBaseClassName(String baseClassName) {
        this.baseClassName = baseClassName;
    }

    public String getOpenWireOpCode() {
        return openWireOpCode;
    }

    public void setOpenWireOpCode(String openWireOpCode) {
        this.openWireOpCode = openWireOpCode;
    }

//    protected String toFileName( JClass type ) {
//        String name = type.getSimpleName();
//
//        if( name.equals( "String" ) ) {
//            return null;
//        } else if( type.isArrayType() ) {
//            JClass arrayClass = type.getArrayComponentType();
//            return toHeaderFileName( arrayClass );
//        } else if( name.equals( "Throwable" ) || name.equals( "Exception" ) ) {
//            return "BrokerError";
//        } else if( name.equals("BaseDataStructure" ) ){
//            return "DataStructure";
//        } else if( name.equals("ByteSequence") ) {
//            return "std::vector<unsigned char>";
//        } else if( !type.isPrimitiveType() ) {
//            return name;
//        } else {
//            return null;
//        }
//    }

    protected String toCSharpType(JClass type) {
        String name = type.getSimpleName();
        if (name.equals("String")) {
            return "string";
        }
        else if( name.equals( "Throwable" ) || name.equals( "Exception" ) ) {
            return "BrokerError";
        }
        else if( name.equals("ByteSequence") ) {
            return "byte[]";
        }
        else if( name.equals("boolean") ) {
            return "bool";
        }
        else if( !type.isPrimitiveType() ) {
            return name;
        }
        else {
            return name;
        }
    }

    /**
     * Converts the Java type to a CSharp default value
     */
    protected String toCSharpDefaultValue(JClass type) {
        String name = type.getSimpleName();

        if (name.equals("boolean")) {
            return "false";
        } else if( name.equals("String") ) {
            return "\"\"";
        } else if( !type.isPrimitiveType() ) {
            return "null";
        } else {
            return "0";
        }
    }

    protected String decapitalize(String text) {
        if (text == null) {
            return null;
        }
        return text.substring(0, 1).toLowerCase() + text.substring(1);
    }

    protected String capitalize(String text) {
        if (text == null) {
            return null;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

}
