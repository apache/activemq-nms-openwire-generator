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

public class DestinationInfoGenerator extends CommandClassGenerator {

    protected void generateVisitBody( PrintWriter out ) {

        out.println("        if(IsAddOperation)");
        out.println("        {");
        out.println("            return visitor.ProcessAddDestination(this);");
        out.println("        }");
        out.println("        else if(IsRemoveOperation)");
        out.println("        {");
        out.println("            return visitor.ProcessRemoveDestination(this);");
        out.println("        }");
        out.println("        throw new IOException(\"Unknown operation type: \" + OperationType);");

    }

    protected void generateProperties( PrintWriter out ) {

        out.println("        public const byte ADD_OPERATION_TYPE = 0;");
        out.println("        public const byte REMOVE_OPERATION_TYPE = 1;");

        super.generateProperties(out);
    }

    protected void generateAdditonalMembers( PrintWriter out ) {

        out.println("        public bool IsAddOperation");
        out.println("        {");
        out.println("            get");
        out.println("            {");
        out.println("                return OperationType == ADD_OPERATION_TYPE;");
        out.println("            }");
        out.println("        }");
        out.println("");
        out.println("        public bool IsRemoveOperation");
        out.println("        {");
        out.println("            get");
        out.println("            {");
        out.println("                return OperationType == REMOVE_OPERATION_TYPE;");
        out.println("            }");
        out.println("        }");

        super.generateAdditonalMembers(out);
    }

}
