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

public class SessionInfoGenerator extends CommandClassGenerator {

    protected void generateVisitBody( PrintWriter out ) {
        out.println("            return visitor.ProcessAddSession( this );");
    }

    protected void generateConstructors( PrintWriter out ) {

        out.println("        public "+getClassName()+"()");
        out.println("        {");
        out.println("        }");
        out.println("");
        out.println("        public "+getClassName()+"(ConnectionInfo connectionInfo, long sessionId)");
        out.println("        {");
        out.println("            this.sessionId = new SessionId(connectionInfo.ConnectionId, sessionId);");
        out.println("        }");
        out.println("");

        super.generateConstructors(out);
    }

}
