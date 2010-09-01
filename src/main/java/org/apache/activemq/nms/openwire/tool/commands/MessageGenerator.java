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

public class MessageGenerator extends CommandClassGenerator {

    public MessageGenerator() {
        super.setMarshalAware(true);
        super.setCloneable(true);
        super.addAdditionalBase("MessageReference");
    }

    protected void generateUsingDirectives( PrintWriter out ) {
        super.generateUsingDirectives(out);

        out.println("using System;");
        out.println("");
        out.println("using Apache.NMS.Util;");
    }

    protected void generateProperties( PrintWriter out ) {

        super.generateProperties(out);

        out.println("        private bool readOnlyMsgProperties;");
        out.println("        private bool readOnlyMsgBody;");
        out.println("");
        out.println("        public const int DEFAULT_MINIMUM_MESSAGE_SIZE = 1024;");
        out.println("");

    }

    protected void generatePropertyAccessors( PrintWriter out ) {

        super.generatePropertyAccessors(out);

        out.println("        public virtual bool ReadOnlyProperties" );
        out.println("        {");
        out.println("            get { return this.readOnlyMsgProperties; }" );
        out.println("            set { this.readOnlyMsgProperties = value; }");
        out.println("        }");
        out.println("");
        out.println("        public virtual bool ReadOnlyBody" );
        out.println("        {");
        out.println("            get { return this.readOnlyMsgBody; }" );
        out.println("            set { this.readOnlyMsgBody = value; }");
        out.println("        }");
        out.println("");
    }

    protected void generateAdditonalMembers( PrintWriter out ) {

        super.generateAdditonalMembers( out );

        out.println("        public virtual int Size()" );
        out.println("        {");
        out.println("            int size = DEFAULT_MINIMUM_MESSAGE_SIZE;");
        out.println("");
        out.println("            if(marshalledProperties != null)");
        out.println("            {");
        out.println("                size += marshalledProperties.Length;");
        out.println("            }");
        out.println("            if(content != null)");
        out.println("            {");
        out.println("                size += content.Length;");
        out.println("            }");
        out.println("");
        out.println("            return size;");
        out.println("        }");
        out.println("");
        out.println("        public virtual void OnSend()" );
        out.println("        {");
        out.println("            this.ReadOnlyProperties = true;" );
        out.println("            this.ReadOnlyBody = true;");
        out.println("        }");
        out.println("");
        out.println("        public virtual void OnMessageRollback()" );
        out.println("        {");
        out.println("            this.redeliveryCounter++;" );
        out.println("        }");
        out.println("");
        out.println("        public bool IsExpired()");
        out.println("        {");
        out.println("            return this.expiration == 0 ? false : DateTime.UtcNow > DateUtils.ToDateTimeUtc(this.expiration);");
        out.println("        }");
        out.println("");
    }

    protected void generateCloneBody( PrintWriter out ) {

        out.println("            // Since we are a derived class use the base's Clone()");
        out.println("            // to perform the shallow copy. Since it is shallow it");
        out.println("            // will include our derived class. Since we are derived,");
        out.println("            // this method is an override.");
        out.println("            " + getClassName() + " o = (" + getClassName() + ") base.Clone();");
        out.println("");
        out.println("            if( this.messageId != null )");
        out.println("            {");
        out.println("                o.MessageId = (MessageId) this.messageId.Clone();");
        out.println("            }");
        out.println("");
        out.println("            return o;");
    }
}
