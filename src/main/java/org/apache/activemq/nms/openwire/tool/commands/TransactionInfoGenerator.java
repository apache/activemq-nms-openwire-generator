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

public class TransactionInfoGenerator extends CommandClassGenerator {

    protected void generateVisitBody( PrintWriter out ) {

        out.println("            switch(type)");
        out.println("            {");
        out.println("                case TransactionInfo.BEGIN:");
        out.println("                    return visitor.ProcessBeginTransaction(this);");
        out.println("                case TransactionInfo.END:");
        out.println("                    return visitor.ProcessEndTransaction(this);");
        out.println("                case TransactionInfo.PREPARE:");
        out.println("                    return visitor.ProcessPrepareTransaction(this);");
        out.println("                case TransactionInfo.COMMIT_ONE_PHASE:");
        out.println("                    return visitor.ProcessCommitTransactionOnePhase(this);");
        out.println("                case TransactionInfo.COMMIT_TWO_PHASE:");
        out.println("                    return visitor.ProcessCommitTransactionTwoPhase(this);");
        out.println("                case TransactionInfo.ROLLBACK:");
        out.println("                    return visitor.ProcessRollbackTransaction(this);");
        out.println("                case TransactionInfo.RECOVER:");
        out.println("                    return visitor.ProcessRecoverTransactions(this);");
        out.println("                case TransactionInfo.FORGET:");
        out.println("                    return visitor.ProcessForgetTransaction(this);");
        out.println("                default:");
        out.println("                    throw new IOException(\"Transaction info type unknown: \" + type);");
        out.println("            }");
    }

    protected void generateProperties( PrintWriter out ) {

        out.println("        public const byte BEGIN = 0;");
        out.println("        public const byte PREPARE = 1;");
        out.println("        public const byte COMMIT_ONE_PHASE = 2;");
        out.println("        public const byte COMMIT_TWO_PHASE = 3;");
        out.println("        public const byte ROLLBACK = 4;");
        out.println("        public const byte RECOVER = 5;");
        out.println("        public const byte FORGET = 6;");
        out.println("        public const byte END = 7;");
        out.println("");

        super.generateProperties(out);
    }

}
