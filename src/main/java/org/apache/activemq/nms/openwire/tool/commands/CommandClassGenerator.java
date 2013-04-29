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

import org.codehaus.jam.JProperty;

public class CommandClassGenerator extends CommandCodeGenerator {

    public void generate( PrintWriter out ) {

        // Start with the license.
        generateLicence(out);

        generateUsingDirectives(out);

        out.println("");
        out.println("namespace Apache.NMS.ActiveMQ.Commands");
        out.println("{");
        out.println("    /*");
        out.println("     *");
        out.println("     *  Command code for OpenWire format for "+getClassName() );
        out.println("     *");
        out.println("     *  NOTE!: This file is auto generated - do not modify!");
        out.println("     *         if you need to make a change, please see the Java Classes");
        out.println("     *         in the nms-activemq-openwire-generator module");
        out.println("     *");
        out.println("     */");

        generateClassDefinition( out );

        out.println("        public const byte ID_"+ getClassName().toUpperCase() + " = " + getOpenWireOpCode() + ";");
        out.println("");

        generateProperties( out );

        generateConstructors(out);

        out.println("        ///");
        out.println("        /// <summery>");
        out.println("        ///  Get the unique identifier that this object and its own");
        out.println("        ///  Marshaler share.");
        out.println("        /// </summery>");
        out.println("        ///");
        out.println("        public override byte GetDataStructureType()");
        out.println("        {");
        out.println("            return ID_" + getClassName().toUpperCase() + ";");
        out.println("        }");
        out.println("");

        if( isCloneable() ) {
            out.println("        ///");
            out.println("        /// <summery>");
            out.println("        ///  Clone this object and return a new instance that the caller now owns.");
            out.println("        /// </summery>");
            out.println("        ///");
            out.println("        public override Object Clone()");
            out.println("        {");
            generateCloneBody( out );
            out.println("        }");
            out.println("");
        }

        out.println("        ///");
        out.println("        /// <summery>");
        out.println("        ///  Returns a string containing the information for this DataStructure");
        out.println("        ///  such as its type and value of its elements.");
        out.println("        /// </summery>");
        out.println("        ///");
        out.println("        public override string ToString()");
        out.println("        {");
        generateToStringBody( out );
        out.println("        }");
        out.println("");

        generateAdditonalMembers( out );
        generatePropertyAccessors( out );

        if( isComparable() ) {

            out.println("        public override int GetHashCode()");
            out.println("        {");
            generateHashCodeBody(out);
            out.println("        }");
            out.println("");
            out.println("        public override bool Equals(object that)");
            out.println("        {");
            out.println("            if(that is "+getClassName()+")");
            out.println("            {");
            out.println("                return Equals(("+getClassName()+") that);");
            out.println("            }");
            out.println("");
            out.println("            return false;");
            out.println("        }");
            out.println("");
            out.println("        public virtual bool Equals("+getClassName()+" that)");
            out.println("        {");
            generateEqualsBody(out);
            out.println("        }");

        }

        if( getBaseClassName().equals( "BaseCommand" ) ) {
            out.println("        ///");
            out.println("        /// <summery>");
            out.println("        ///  Return an answer of true to the is"+getClassName()+"() query.");
            out.println("        /// </summery>");
            out.println("        ///");
            out.println("        public override bool Is"+getClassName());
            out.println("        {");
            out.println("            get { return true; }");
            out.println("        }");
            out.println("");
        }

        if( getBaseClassName().equals( "BaseCommand" ) ) {
            out.println("        ///");
            out.println("        /// <summery>");
            out.println("        ///  Allows a Visitor to visit this command and return a response to the" );
            out.println("        ///  command based on the command type being visited.  The command will call" );
            out.println("        ///  the proper processXXX method in the visitor." );
            out.println("        /// </summery>");
            out.println("        ///");
            out.println("        public override Response Visit(ICommandVisitor visitor)" );
            out.println("        {");
            generateVisitBody(out);
            out.println("        }");
            out.println("");
        }

        out.println("    };");
        out.println("}");
        out.println("");
    }

    protected void generateUsingDirectives( PrintWriter out ) {

        if( getBaseClassName().equals( "BaseCommand" ) ) {
            out.println("");
            out.println("using Apache.NMS.ActiveMQ.State;");
        }
    }

    protected void generateClassDefinition( PrintWriter out ) {
        out.print("    public class "+getClassName()+" : " );

        out.print( getBaseClassName() );

        for( String name : getAdditionalBases() ) {
            out.print( ", "+name );
        }

        if( isMarshalAware() ) {
            out.print( ", MarshallAware" );
        }

        if( isCloneable() ) {
            out.print( ", ICloneable" );
        }

        out.println();
        out.println("    {");
    }

    protected void generateConstructors( PrintWriter out ) {
        // The default constructor is fine for basic Commands.
    }

    protected void generateProperties( PrintWriter out ) {

        if( getProperties().isEmpty() ) {
            return;
        }

        for( JProperty property : getProperties() ) {
            String type = toCSharpType(property.getType());
            String name = decapitalize(property.getSimpleName());

            out.println("        "+type+" "+name+";");
        }

        out.println("");
    }

    protected void generateVisitBody( PrintWriter out ) {
        out.println("            return visitor.Process"+getClassName()+"(this);");
    }

    protected void generateToStringBody( PrintWriter out ) {

        if( getProperties().isEmpty() ) {
            out.println("            return GetType().Name + \"[ ]\";");
            return;
        }

        out.println("            return GetType().Name + \"[ \" + ");

        if( getBaseClassName().equals( "BaseCommand" ) ) {
            out.println("                \"commandId = \" + this.CommandId + \", \" + " );
            out.println("                \"responseRequired = \" + this.ResponseRequired + \", \" + " );
        }

        int size = getProperties().size();
        int count = 0;

        for( JProperty property : getProperties() ) {
            String name = property.getSimpleName();

            if( property.getType().isArrayType() && toCSharpType(property.getType()).startsWith("byte")) {
                out.print("                \"" + name + " = \" + " + name + " ?? System.Text.ASCIIEncoding.ASCII.GetString(" + name + ") + ");
            } else {
                out.print("                \"" + name + " = \" + " + name + " + ");
            }

            if( ++count != size ) {
                out.println("\", \" + ");
            }
        }

        out.println("\" ]\";");
    }

    protected void generateCloneBody( PrintWriter out ) {

        out.println("            // Since we are a derived class use the base's Clone()");
        out.println("            // to perform the shallow copy. Since it is shallow it");
        out.println("            // will include our derived class. Since we are derived,");
        out.println("            // this method is an override.");
        out.println("            " + getClassName() + " o = (" + getClassName() + ") base.Clone();");
        out.println("");
        out.println("            return o;");
    }

    protected void generateAdditonalMembers( PrintWriter out ) {
    }

    protected void generatePropertyAccessors( PrintWriter out ) {

        for( JProperty property : getProperties() ) {
            String type = toCSharpType(property.getType());
            String accessorName = property.getSimpleName();
            String propertyName = decapitalize(accessorName);

            out.println("        public " + type + " " + accessorName );
            out.println("        {");
            out.println("            get { return " + propertyName + "; }" );
            out.println("            set { this." + propertyName + " = value; }");
            out.println("        }");
            out.println("");

        }
    }

    protected void generateHashCodeBody( PrintWriter out ) {

        if( getProperties().isEmpty() ) {
            out.println("            return HashCode(this);");
        } else {
            out.println("            int answer = 0;");
            out.println("");

            for( JProperty property : getProperties() ) {
                String accessorName = property.getSimpleName();

                out.println("            answer = (answer * 37) + HashCode("+accessorName+");");
            }

            out.println("");
            out.println("            return answer;");
        }
    }

    protected void generateEqualsBody( PrintWriter out ) {

        if( !getProperties().isEmpty() ) {
            for( JProperty property : getProperties() ) {
                String accessorName = property.getSimpleName();

                if( !property.getType().isArrayType() ) {
                    out.println("            if(!Equals(this."+accessorName+", that."+accessorName+"))");
                    out.println("            {");
                    out.println("                return false;");
                    out.println("            }");
                } else {
                    out.println("            if(!ArrayEquals(this."+accessorName+", that."+accessorName+"))");
                    out.println("            {");
                    out.println("                return false;");
                    out.println("            }");
                }
            }

            out.println("");
        }

        out.println("            return true;");
    }

}
