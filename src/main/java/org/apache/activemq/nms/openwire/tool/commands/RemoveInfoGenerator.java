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

public class RemoveInfoGenerator extends CommandClassGenerator {

    protected void generateVisitBody( PrintWriter out ) {

        out.println("            switch(objectId.GetDataStructureType())");
        out.println("            {");
        out.println("                case ConnectionId.ID_CONNECTIONID:");
        out.println("                    return visitor.ProcessRemoveConnection((ConnectionId) objectId);");
        out.println("                case SessionId.ID_SESSIONID:");
        out.println("                    return visitor.ProcessRemoveSession((SessionId) objectId);");
        out.println("                case ConsumerId.ID_CONSUMERID:");
        out.println("                    return visitor.ProcessRemoveConsumer((ConsumerId) objectId);");
        out.println("                case ProducerId.ID_PRODUCERID:");
        out.println("                    return visitor.ProcessRemoveProducer((ProducerId) objectId);");
        out.println("                default:");
        out.println("                    throw new IOException(\"Unknown remove command type: \" + objectId.GetDataStructureType());");
        out.println("            }");
    }
}
