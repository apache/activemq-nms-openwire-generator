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

public class MessageIdGenerator extends CommandClassGenerator {

    protected void generateProperties( PrintWriter out ) {

        super.generateProperties(out);

        out.println("        private string key = null;");
        out.println("");
    }

    protected void generateUsingDirectives( PrintWriter out ) {
        super.generateUsingDirectives(out);

        out.println("using System;");
    }

    protected void generateConstructors( PrintWriter out ) {

        out.println("        public "+getClassName()+"() : base()");
        out.println("        {");
        out.println("        }");
        out.println("");
        out.println("        public "+getClassName()+"(ProducerId producerId, long producerSequenceId) : base()");
        out.println("        {");
        out.println("            this.producerId = producerId;");
        out.println("            this.producerSequenceId = producerSequenceId;");
        out.println("        }");
        out.println("");
        out.println("        public "+getClassName()+"(string value) : base()");
        out.println("        {");
        out.println("            this.SetValue(value);");
        out.println("        }");
        out.println("");

        super.generateConstructors(out);
    }

    protected void generateToStringBody( PrintWriter out ) {

        out.println("            if(key == null) ");
        out.println("            {");
        out.println("                if (!String.IsNullOrEmpty(textView))");
        out.println("                {");
        out.println("                    if (textView.StartsWith(\"ID:\"))");
        out.println("                    {");
        out.println("                        key = textView;");
        out.println("                    }");
        out.println("                    else");
        out.println("                    {");
        out.println("                        key = \"ID:\" + textView;");
        out.println("                    }");
        out.println("                }");
        out.println("                else");
        out.println("                {");
        out.println("                    key = producerId.ToString() + \":\" + producerSequenceId + \":\" + brokerSequenceId;");
        out.println("                }");
        out.println("            }");
        out.println("            ");
        out.println("            return key;");
    }

    protected void generateAdditonalMembers( PrintWriter out ) {

        out.println("        /// <summary>");
        out.println("        /// Sets the value as a String");
        out.println("        /// </summary>");
        out.println("        public void SetValue(string messageKey)");
        out.println("        {");
        out.println("            this.key = messageKey;");
        out.println("");
        out.println("            // Parse off the sequenceId");
        out.println("            int p = messageKey.LastIndexOf(\":\");");
        out.println("            if(p >= 0)");
        out.println("            {");
        out.println("                producerSequenceId = Int64.Parse(messageKey.Substring(p + 1));");
        out.println("                messageKey = messageKey.Substring(0, p);");
        out.println("            }");
        out.println("            producerId = new ProducerId(messageKey);");
        out.println("        }");
        out.println("");

        super.generateAdditonalMembers( out );
    }
}
