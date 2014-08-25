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

public class MessageDispatchGenerator extends CommandClassGenerator {

    public MessageDispatchGenerator() {
        super();
        this.setComparable(true);
    }

    protected void generateUsingDirectives( PrintWriter out ) {
        super.generateUsingDirectives(out);

        out.println("using System;");
    }

    protected void generateProperties( PrintWriter out ) {

        out.println("        private Exception rollbackCause = null;");
        out.println("        private long deliverySequenceId;");
        out.println("        private object consumer;");
        out.println("");

        super.generateProperties(out);
    }

    protected void generateAdditonalMembers( PrintWriter out ) {
        out.println("        public Exception RollbackCause");
        out.println("        {");
        out.println("            get { return this.rollbackCause; }");
        out.println("            set { this.rollbackCause = value; }");
        out.println("        }");
        out.println("");
        out.println("        public long DeliverySequenceId");
        out.println("        {");
        out.println("            get { return this.deliverySequenceId; }");
        out.println("            set { this.deliverySequenceId = value; }");
        out.println("        }");
        out.println("");
        out.println("        public object Consumer");
        out.println("        {");
        out.println("            get { return this.consumer; }");
        out.println("            set { this.consumer = value; }");
        out.println("        }");
        out.println("");

        super.generateAdditonalMembers( out );
    }

}
