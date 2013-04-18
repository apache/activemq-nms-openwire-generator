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

public class MessageAckGenerator extends CommandClassGenerator {

    protected void generateConstructors( PrintWriter out ) {

        out.println("        public "+getClassName()+"() : base()");
        out.println("        {");
        out.println("        }");
        out.println("");
        out.println("        public "+getClassName()+"(MessageDispatch dispatch, byte ackType, int messageCount) : base()");
        out.println("        {");
        out.println("            this.ackType = ackType;");
        out.println("            this.consumerId = dispatch.ConsumerId;");
        out.println("            this.destination = dispatch.Destination;");
        out.println("            this.lastMessageId = dispatch.Message.MessageId;");
        out.println("            this.messageCount = messageCount;");
        out.println("        }");
        out.println("");
        out.println("        public "+getClassName()+"(Message message, byte ackType, int messageCount) : base()");
        out.println("        {");
        out.println("            this.ackType = ackType;");
        out.println("            this.destination = message.Destination;");
        out.println("            this.lastMessageId = message.MessageId;");
        out.println("            this.messageCount = messageCount;");
        out.println("        }");
        out.println("");

        super.generateConstructors(out);
    }

}
